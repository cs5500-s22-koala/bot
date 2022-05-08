package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.OrderController;
import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class PlaceOrderCommand implements Command {

    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    @Inject OrderController orderController;

    @Inject
    public PlaceOrderCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "place-order";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot whether you want to place order");
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /placeOrderCommand");
        String discordUserId = event.getUser().getId();
        if (shoppingCart.getCartOfUser(discordUserId) == null) {
            event.reply("Your cart is empty. Please add dishes to cart first ").queue();
        } else {
            int orderId = orderController.generateOrderId();
            double expense = shoppingCart.getPriceOfCartForUser(discordUserId);
            HashMap<String, Integer> itemsOrdered =
                    shoppingCart.getDishNameAndQuantityOfUserCart(discordUserId);
            orderController.addOrder(
                    orderId,
                    discordUserId,
                    shoppingCart.getCurrentRestaurantOfUser(discordUserId),
                    itemsOrdered,
                    expense);

            shoppingCart.clearShoppingCartOfUser(discordUserId);
            event.reply(
                            String.format(
                                    "%s, you've placed an order, thank you!\nYour order number is %s\ntotal expense is %s",
                                    event.getUser().getName(), orderId, expense))
                    .queue();
        }
    }
}
