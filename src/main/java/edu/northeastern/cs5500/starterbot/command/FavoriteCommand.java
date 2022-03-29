package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class FavoriteCommand implements Command {

    @Inject
    public FavoriteCommand() {}

    @Override
    public String getName() {
        return "favorite";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "List your favorite resturant");
    }

    MessageEmbed favoriteResturant() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("title");
        embedBuilder.setDescription("This is a resturant");
        embedBuilder.setImage(
                "https://palisaderestaurant.com/images/slider/palisade-restaurant-seattle.jpg");
        return embedBuilder.build();
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /favorite");
        event.getChannel().sendMessage(favoriteResturant()).queue();
    }
}
