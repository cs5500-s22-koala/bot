package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

/**
 * The catch command will return a random resturant from the list and let the
 * user know what the
 * resturant is.
 */
@Singleton
@Slf4j
public class FavoriteCommand implements Command {
    @Inject
    public FavoriteCommand() {
    }

    @Override
    public String getName() {
        return "favorite";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "List your favorite resturants");
    }

    MessageEmbed favoriteResturant() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("title");
        embedBuilder.setDescription("This is a shiny resturant in downtow of Seattle");
        embedBuilder.setImage(
                "https://travel.home.sndimg.com/content/dam/images/travel/fullset/2014/08/21/92/seattle-restaurants-with-view-matts-market.rend.hgtvcom.1280.720.suffix/1491585038690.jpeg");
        return embedBuilder.build();
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /favorite");
        event.reply(event.getOption("content").getAsString()).queue();
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setEmbed(favoriteResturant());
        event.reply(messageBuilder.build()).queue();
    }
}
