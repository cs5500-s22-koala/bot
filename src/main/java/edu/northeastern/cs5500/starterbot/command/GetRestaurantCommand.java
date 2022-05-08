package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.RestaurantController;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import java.awt.Color;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
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
                                        "The bot will show you the restaurant's information")
                                .setRequired(true));
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /getRestaurant");
        String restaurantName = event.getOption("name").getAsString();

        Restaurant result = restaurantController.getSpecificRestaurantBasedOnName(restaurantName);
        if (result == null) {
            event.reply("No restaurant named " + restaurantName + " on the server").queue();
        } else {
            MessageEmbed eb =
                    new EmbedBuilder()
                            .setImage(result.getImageUrl())
                            .setTitle(result.getName())
                            .setDescription(result.getIntroduction())
                            .setColor(Color.GREEN)
                            .setFooter(result.getAddress())
                            .setColor(Color.GREEN)
                            .addField("Cuisine Type:", result.getCuisineType(), true)
                            .addField(
                                    "Avg Cost/Person ($)",
                                    String.valueOf(result.getAverageCostPerGuest()),
                                    true)
                            .addField("Operating Hours: ", result.getOperatingHours(), true)
                            .addField("Phone: ", "" + result.getPhone(), true)
                            .addField("Zip code:", "" + result.getZipcode(), true)
                            .build();
            event.replyEmbeds(eb).queue();
        }
    }
}
