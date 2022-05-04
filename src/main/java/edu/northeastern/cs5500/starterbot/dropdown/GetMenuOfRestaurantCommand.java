package edu.northeastern.cs5500.starterbot.dropdown;

import edu.northeastern.cs5500.starterbot.button.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.command.Command;
import edu.northeastern.cs5500.starterbot.controller.DishController;
import edu.northeastern.cs5500.starterbot.controller.OrderController;
import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
import edu.northeastern.cs5500.starterbot.model.Dish;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

@Singleton
@Slf4j
public class GetMenuOfRestaurantCommand
        implements Command, SelectionMenuHandler, ButtonClickHandler {

    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    @Inject DishController dishController;
    @Inject OrderController orderController;

    @Inject
    public GetMenuOfRestaurantCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "get-menu-of-restaurant";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(
                        getName(), "Tell the bot which restaurant's menu you want to look at")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "restaurantname",
                                        "The bot will display menu of the restaurant")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /get menu of restaurant Command");
        String restaurant = event.getOption("restaurantname").getAsString();
        List<Dish> menuOfARestaurant = dishController.getMenuOfARestaurant(restaurant);

        if (menuOfARestaurant.size() == 0) {
            event.reply("Please enter valid restaurant name ").queue();
        } else {
            SelectionMenu menu = createSelectionMenu(menuOfARestaurant);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Please pick your dish from " + restaurant + "'s menu");
            eb.setDescription(
                    "You can add multiple dishes, but one at a time \nNote: you only have"
                            + " 1 minute to edit shopping cart");
            eb.setColor(0xffcc00);
            eb.setThumbnail(
                    "https://i.pinimg.com/originals/66/22/ab/6622ab37c6db6ac166dfec760a2f2939.gif");
            MessageBuilder mb = new MessageBuilder().setEmbeds(eb.build());
            event.reply(mb.build())
                    .setEphemeral(false)
                    .addActionRow(menu)
                    .queue(m -> m.deleteOriginal().queueAfter(60, TimeUnit.SECONDS));
        }
    }

    public SelectionMenu createSelectionMenu(List<Dish> menuOfRestaurant) {
        SelectionMenu.Builder menu =
                SelectionMenu.create(this.getName() + ":menu") // can be accessed through
                        // event.getComponent().getId()
                        .setPlaceholder("Choose a dish to shopping cart")
                        .setRequiredRange(1, 1);
        for (Dish d : menuOfRestaurant) {
            menu.addOptions(
                    SelectOption.of(
                                    d.getDishName()
                                            + ": $"
                                            + d.getPrice(), // label is what is shown to users
                                    d.getRestaurantName() + ":" + d.getDishName())
                            .withDescription(d.getDishDescription())
                            .withEmoji(Emoji.fromUnicode("U+1F449")));
        }
        return menu.build();
    }

    @Override
    public void onSelectionMenu(@Nonnull SelectionMenuEvent event) {
        SelectOption option = event.getInteraction().getSelectedOptions().get(0);
        int quantity = 1;
        String dishName = option.getValue().split(":")[1];
        String restaurant = option.getValue().split(":")[0];
        String discordUserId = event.getUser().getId();

        Dish dishToAdd = dishController.findADish(dishName, restaurant);
        String restaurantInCart = shoppingCart.getCurrentRestaurantOfUser(discordUserId);

        log.info("discordUserID: " + event.getUser().getId());
        log.info("current restaurant: " + restaurant);
        log.info("restaurant in shopping cart: " + restaurantInCart);

        if (restaurantInCart != null && !restaurant.equals(restaurantInCart)) {
            log.info("restaurant in cart and current selected restaurant are not the same");
            event.reply(
                            "Please enter dish from restaurant "
                                    + restaurantInCart
                                    + " or /clear shopping cart")
                    .queue();
            return;
        } else {
            log.info("onSelectionMenu: add dish to cart");
            shoppingCart.addDishToCart(discordUserId, dishToAdd, quantity);
            String description =
                    shoppingCart.displayCartInfoOfUser(discordUserId)
                            + "\nYou can place "
                            + "an order(CONFIRM), select more dish(CONTINUE) or clear shopping cart(CANCEL)";
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(event.getUser().getName() + ", here is your cart info(item: amount):");
            eb.setDescription(description);
            eb.setColor(0x00eaff);
            MessageBuilder messageBuilder = new MessageBuilder();
            messageBuilder = messageBuilder.setEmbeds(eb.build());

            messageBuilder =
                    messageBuilder.setActionRows(
                            ActionRow.of(
                                    Button.primary(this.getName() + ":confirm", "CONFIRM"),
                                    Button.secondary(this.getName() + ":cancel", "CANCEL")));
            event.reply(messageBuilder.build()).setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String buttonId = event.getButton().getId().split(":")[1];
        String discordUserId = event.getUser().getId();
        String discordUserName = event.getUser().getName();
        log.info("event: /onButtonClick {}", buttonId);

        if (buttonId.equals("confirm")) {
            String placeOrderMsg = placeOrder(discordUserId);
            if (placeOrderMsg.equals("")) {
                event.reply(
                                discordUserName
                                        + ", you don't have anything in your cart to place an order")
                        .queue();
            }
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(event.getUser().getName() + ", you've placed an order, thank you!:");
            eb.setDescription(placeOrderMsg);
            eb.setColor(0xea00ff);
            eb.setThumbnail("https://nhramuseum.org/wp-content/uploads/2018/12/cloud.gif");
            MessageBuilder mb = new MessageBuilder().setEmbeds(eb.build());
            event.reply(mb.build()).queue();
        } else if (buttonId.equals("cancel")) {
            shoppingCart.clearShoppingCartOfUser(discordUserId);
            event.reply(discordUserName + ", your shopping cart is empty now").queue();
        } else {
            event.reply("invalid operation").queue();
        }
    }

    public String placeOrder(String discordUserId) {
        int orderId = orderController.generateOrderId();
        double expense = shoppingCart.getPriceOfCartForUser(discordUserId);
        if (expense == 0.0) {
            return "";
        }
        HashMap<String, Integer> itemsOrdered =
                shoppingCart.getDishNameAndQuantityOfUserCart(discordUserId);
        orderController.addOrder(
                orderId,
                discordUserId,
                shoppingCart.getCurrentRestaurantOfUser(discordUserId),
                itemsOrdered,
                expense);

        shoppingCart.clearShoppingCartOfUser(discordUserId);
        String message = "Order number: " + orderId + "\nTotal expense: $" + expense;
        return message;
    }
}
