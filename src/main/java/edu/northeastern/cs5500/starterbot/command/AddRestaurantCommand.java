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
                                        OptionType.NUMBER,
                                        "averagecost",
                                        "The bot will use this as averageCost")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "address",
                                        "The bot will use this as address")
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
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "phone",
                                        "The bot will use this as phone")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "operatinghours",
                                        "The bot will use this as operating hours")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "introduction",
                                        "The bot will use this as introduction")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /addRestaurantCommand");
        String restaurantName = event.getOption("name").getAsString();
        String cuisineType = event.getOption("cuisinetype").getAsString();
        double averageCost = (double) event.getOption("averagecost").getAsDouble();
        String address = event.getOption("address").getAsString();
        int zipcode = (int) event.getOption("zipcode").getAsLong();
        String imageUrl = event.getOption("imageurl").getAsString();
        int phone = (int) event.getOption("phone").getAsLong();
        String operatingHours = event.getOption("operatinghours").getAsString();
        String introduction = event.getOption("introduction").getAsString();

        if (restaurantName == null
                || cuisineType == null
                || zipcode == 0
                || imageUrl == null
                || address == null
                || phone == 0
                || averageCost == 0.0
                || operatingHours == null
                || introduction == null) {
            event.reply("Please enter completed restaurant info ").queue();
        } else {
            restaurantController.addRestaurant(
                    restaurantName,
                    cuisineType,
                    averageCost,
                    address,
                    zipcode,
                    imageUrl,
                    phone,
                    operatingHours,
                    introduction);
            event.reply("Data " + restaurantName + " inserted successfully ").queue();
        }
    }
}
