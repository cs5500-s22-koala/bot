package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
import edu.northeastern.cs5500.starterbot.service.MongoDBService;
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

    @Override
    public String getName() {
        return "addrestaurantcommand";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what name to address you with")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "cuisinetype",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "zipcode",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "imageurl",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /addrestaurantcommand");
        String restaurantName = event.getOption("name").getAsString();
        String cuisinType = event.getOption("cuisinetype").getAsString();
        long zipcode = event.getOption("zipcode").getAsLong();
        String imageUrl = event.getOption("imageurl").getAsString();

        MongoDBService mongoDBService = new MongoDBService();

        restaurantController.addRestaurant(restaurantName, cuisinType, zipcode, imageUrl);

        if (restaurantName == null || cuisinType == null || zipcode == 0 || imageUrl == null) {
            event.reply("Please enter completed restaurant info ").queue();
        } else {
            event.reply("Data " + restaurantName + " inserted successfully ").queue();
        }
    }
}
