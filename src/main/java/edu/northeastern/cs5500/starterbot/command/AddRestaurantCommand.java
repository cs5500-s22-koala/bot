package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
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
public class AddRestaurantCommand implements Command {

    @Inject RestaurantController restaurantController;

    @Inject
    public AddRestaurantCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "add-restaurant";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what restaurant you want to add")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name as restaurant name")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "cuisinetype",
                                        "The bot will use this as cuisine type")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "zipcode",
                                        "The bot will use this number as zip code")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "imageurl",
                                        "The bot will use this as image url")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /addRestaurantCommand");
        String restaurantName = event.getOption("name").getAsString();
        String cuisinType = event.getOption("cuisinetype").getAsString();
        int zipcode = (int) event.getOption("zipcode").getAsLong();
        String imageUrl = event.getOption("imageurl").getAsString();

        if (restaurantName == null || cuisinType == null || zipcode == 0 || imageUrl == null) {
            event.reply("Please enter completed restaurant info ").queue();
        } else {
            restaurantController.addRestaurant(restaurantName, cuisinType, zipcode, imageUrl);
            event.reply("Data " + restaurantName + " inserted successfully ").queue();
        }
    }
}
