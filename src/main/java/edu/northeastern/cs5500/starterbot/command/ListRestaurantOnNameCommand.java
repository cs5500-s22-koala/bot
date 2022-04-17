package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class ListRestaurantOnNameCommand implements Command {

    @Inject RestaurantController restaurantController;

    @Inject
    public ListRestaurantOnNameCommand() {}

    @Override
    public String getName() {
        return "listrestaurantonname";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what name to address you with")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /listrestaurantonname");
        String restaurantName = event.getOption("name").getAsString();

        Restaurant selectedRestaurant =
                restaurantController.getSpecificRestaurantBasedOnName(restaurantName);

        if (selectedRestaurant == null) {
            event.reply("Sorry, we cannot find " + restaurantName).queue();
        } else {
            event.reply(
                            "Here is the restaurant "
                                    + selectedRestaurant.getName()
                                    + " "
                                    + selectedRestaurant.getCuisineType()
                                    + " "
                                    + selectedRestaurant.getZipcode())
                    .queue();
        }
    }
}
