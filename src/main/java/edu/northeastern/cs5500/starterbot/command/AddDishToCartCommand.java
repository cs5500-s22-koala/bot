package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.DishController;
import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
import edu.northeastern.cs5500.starterbot.model.Dish;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class AddDishToCartCommand implements Command {

    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    @Inject DishController dishController;

    @Inject
    public AddDishToCartCommand() {}

    @Override
    public String getName() {
        return "add-dish-to-cart";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what dish you want to add to cart")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "restaurantname",
                                        "The bot will select dish from the restaurant")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "dishname",
                                        "The bot will add the dish to cart")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "quantity",
                                        "The bot will add the dish x times")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /addDishToCart");
        int quantity = (int) event.getOption("quantity").getAsLong();
        String dishName = event.getOption("dishname").getAsString();
        String restaurant = event.getOption("restaurantname").getAsString();
        String discordUserId = event.getUser().getId();

        Dish dishToAdd = dishController.findADish(dishName, restaurant);
        String restaurantInCart = shoppingCart.getCurrentRestaurantOfUser(discordUserId);

        log.info("discordUserID: " + event.getUser().getId());
        log.info("current restaurant " + restaurant);
        log.info("shopping cart restaurant " + restaurantInCart);

        if (dishToAdd == null) {
            event.reply("Please enter valid dish or restaurant name ").queue();
        } else if (restaurantInCart != null && !restaurant.equals(restaurantInCart)) {
            log.info("two restaurants are not the same");
            event.reply(
                            String.format(
                                    "Please enter dish from restaurant %s or clear shopping cart",
                                    restaurantInCart))
                    .queue();
        } else {
            shoppingCart.addDishToCart(discordUserId, dishToAdd, quantity);
            event.reply(
                            String.format(
                                    "%s %s from %s has been added to cart \n%s, %s",
                                    quantity,
                                    dishName,
                                    restaurant,
                                    event.getUser().getName(),
                                    shoppingCart.displayCartInfoOfUser(discordUserId)))
                    .queue();
        }
    }
}
