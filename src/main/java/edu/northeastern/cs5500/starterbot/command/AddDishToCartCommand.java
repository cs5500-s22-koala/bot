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
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /addDishToCart");
        int quantity = (int) event.getOption("quantity").getAsLong();
        String dishName = event.getOption("dishname").getAsString();
        String restaurant = event.getOption("restaurantname").getAsString();

        Dish dishToAdd = dishController.findADish(dishName, restaurant);
        if (dishToAdd == null) {
            event.reply("Please enter valid dish or restaurant name ").queue();
        } else {
            shoppingCart.addDishToCart(dishToAdd, quantity);
            // TODO: use string builder and display cart info from event.reply
            shoppingCart
                    .getCart()
                    .forEach((key, value) -> System.out.println(key.getDishName() + ": " + value));

            event.reply(
                            quantity
                                    + " "
                                    + dishName
                                    + " from "
                                    + restaurant
                                    + " has been added "
                                    + "to cart ")
                    .queue();
        }
    }
}
