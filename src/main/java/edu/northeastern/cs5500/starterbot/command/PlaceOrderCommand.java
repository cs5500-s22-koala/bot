package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.OrderController;
import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
import edu.northeastern.cs5500.starterbot.model.Dish;
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
                    getDishNameAndQuantity(shoppingCart.getCartOfUser(discordUserId));
            orderController.addOrder(
                    orderId,
                    discordUserId,
                    shoppingCart.getCurrentRestaurantOfUser(discordUserId),
                    itemsOrdered,
                    expense);

            shoppingCart.clearShoppingCartOfUser(discordUserId);

            event.reply(
                            event.getUser().getName()
                                    + " , you've placed an order, thank you!\n"
                                    + " Your order number is "
                                    + orderId
                                    + "\ntotal expense is "
                                    + expense)
                    .queue();
        }
    }

    public HashMap<String, Integer> getDishNameAndQuantity(HashMap<Dish, Integer> usercart) {
        HashMap<String, Integer> res = new HashMap<>();
        for (Dish dish : usercart.keySet()) {
            res.put(dish.getDishName(), usercart.get(dish));
        }
        return res;
    }
}
