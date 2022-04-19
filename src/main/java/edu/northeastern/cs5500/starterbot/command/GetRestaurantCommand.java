package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
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
public class GetRestaurantCommand implements Command {

    @Inject RestaurantController restaurantController;

    @Inject
    public GetRestaurantCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "get-restaurant";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what restaurant name is")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /getRestaurant");
        String restaurantName = event.getOption("name").getAsString();

        Restaurant result = restaurantController.getSpecificRestaurantBasedOnName(restaurantName);
        if (result == null) {
            event.reply("No restaurant named " + restaurantName + " on the server").queue();
        } else {
            // TODO: Come up with a better format to display restaurant info
            event.reply(result.toString()).queue();
        }
    }
}
