package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import java.util.List;
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
public class GetRestaurantOnZipcodeCuisineTypeCommand implements Command {

    @Inject RestaurantController restaurantController;

    @Inject
    public GetRestaurantOnZipcodeCuisineTypeCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "get-on-zipcode-cuisintype";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(
                        getName(), "Tell the bot what zipcode and cuisine type you are looking for")
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "zipcode",
                                        "The bot will search restaurant in the zipcode")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "cuisinetype",
                                        "The bot will search restaurant matched specific cuisinetype")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /getRestaurantOnZipcodeCuisineType");
        int zipcode = (int) event.getOption("zipcode").getAsLong();
        String cuisinType = event.getOption("cuisinetype").getAsString();

        List<Restaurant> result =
                restaurantController.getRestaurantsBasedOnZipcodeCuisineType(zipcode, cuisinType);
        if (result.isEmpty()) {
            event.reply("No restaurant  found in zipcode " + zipcode + " matched " + cuisinType)
                    .queue();
        } else {
            StringBuilder sb = new StringBuilder();
            String bulletSymbol = ":small_blue_diamond:";
            sb.append(
                    bulletSymbol
                            + String.format(
                                    "%$1-30s  %$2-20s  %$3-15s  %$4-30s  %5-20s\n",
                                    "Restaurant Name",
                                    "Cuisine Type",
                                    "Zip Code",
                                    "Operating Hours",
                                    "Aver.Cost Per Guest"));
            for (Restaurant restaurant : result) {
                sb.append(
                        bulletSymbol
                                + String.format(
                                        "%$1-30s  %$2-20s  %$3-15s  %$4-30s  %5-20s\n",
                                        restaurant.getName(),
                                        restaurant.getCuisineType(),
                                        restaurant.getZipcode(),
                                        restaurant.getOperatingHours(),
                                        restaurant.getAverageCostPerGuest()));
            }
            event.reply(sb.toString()).queue();
        }
    }
}
