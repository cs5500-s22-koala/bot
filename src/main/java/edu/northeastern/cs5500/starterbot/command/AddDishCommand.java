package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.DishController;
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
public class AddDishCommand implements Command {

    @Inject DishController dishController;

    @Inject
    public AddDishCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "add-dish";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what dish you want to add")
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "dishid",
                                        "The bot will use this number as dishId")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "dishname",
                                        "The bot will use this as dish Name")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "restaurantname",
                                        "The bot will save the dish to the restaurant's menu")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.NUMBER,
                                        "dishprice",
                                        "The bot will set the dish price to the given number")
                                .setRequired(true));
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /addDishCommand");
        int dishId = (int) event.getOption("dishid").getAsLong();
        String dishName = event.getOption("dishname").getAsString();
        String restaurant = event.getOption("restaurantname").getAsString();
        double price = event.getOption("dishprice").getAsDouble();

        if (dishId == 0 || dishName == null || restaurant == null || price == 0) {
            event.reply("Please enter valid dish info ").queue();
        } else {
            dishController.addDish(dishId, dishName, price, restaurant);
            event.reply(
                            String.format(
                                    "Dish %s has been inserted into %s's menu",
                                    dishName, restaurant))
                    .queue();
        }
    }
}
