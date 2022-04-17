package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantLoad;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import java.util.ArrayList;
import java.util.List;
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

    @Inject
    public GetRestaurantCommand() {}

    @Override
    public String getName() {
        return "getrestaurant";
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
        log.info("event: /getrestaurant");
        String restaurantName = event.getOption("name").getAsString();
        List<Restaurant> listRestaurant = RestaurantLoad.loadRestaurant(restaurantName);
        List<StringBuilder> builders = new ArrayList<StringBuilder>();
        builders.add(new StringBuilder());

        for (Restaurant restaurant : listRestaurant) {
            StringBuilder lastStringBuilder = builders.get(builders.size() - 1);
            lastStringBuilder.append(
                    restaurant.getName()
                            + " "
                            + restaurant.getCuisineType()
                            + " "
                            + restaurant.getZipcode()
                            + "\n");
        }

        // Send message
        if (listRestaurant.isEmpty()) {
            event.reply("There are no restaurants on this server.").queue();
        } else {
            for (StringBuilder sb : builders) event.reply(sb.toString()).queue();
        }
    }
}
