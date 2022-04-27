package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

/** This command will provide user with welcome information */
@Singleton
@Slf4j
public class WelcomeCommand implements Command{
  @Inject
  public WelcomeCommand() {}

  /** @return String */
  @NotNull
  @Override
  public String getName() {
    return "welcome";
  }

  @NotNull
  @Override
  public CommandData getCommandData() {
    return new CommandData(getName(), "Welcome and start an order")
        .addOptions(
            new OptionData(
                OptionType.STRING,
                "content",
                "start discovering food")
                .setRequired(true));
  }

  @Override

  public void onEvent(@NotNull CommandInteraction event) {
    log.info("event: /welcome");
    event.reply(event.getOption("content").getAsString()).queue();
    EmbedBuilder embedBuilder = new EmbedBuilder();
    embedBuilder.setTitle("Welcome to order your food");
    embedBuilder.setDescription("have a treat");
    embedBuilder.setImage("https://news.cgtn.com/news/3d3d514d3551544d31457a6333566d54/img/d28dd1786f3e4bb598da6e577b369898/d28dd1786f3e4bb598da6e577b369898.jpg");
    MessageBuilder messageBuilder = new MessageBuilder();
    messageBuilder.setEmbeds(embedBuilder.build());
    event.reply(messageBuilder.build()).queue();

  }
}