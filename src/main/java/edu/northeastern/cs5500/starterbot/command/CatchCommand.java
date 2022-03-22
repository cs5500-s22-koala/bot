package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class CatchCommand implements Command {

    @Inject
    public CatchCommand() {
    }

    @Override
    public CommandData getCommandData() {
        // TODO Auto-generated method stub

        return new CommandData(getName(), "Catch a random pokeman");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "catch";
    }

    @Override
    public void onEvent(CommandInteraction event) {
        // TODO Auto-generated method stub
        log.info("event:/catch");
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("You caught the first doll");
        embedBuilder.setDescription("This is where we can say first doll");
        embedBuilder.setImage("https://assets.pokemon.com/assets/cms2/img/pokedex/full/132.png");
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setEmbed(embedBuilder.build());
        event.reply(messageBuilder.build()).queue();
    }
}
