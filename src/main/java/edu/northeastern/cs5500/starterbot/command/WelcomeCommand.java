package edu.northeastern.cs5500.starterbot.command;

import java.awt.Color;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

/** This command will provide user with welcome information */
@Singleton
@Slf4j
public class WelcomeCommand implements Command {

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
        return new CommandData(getName(), "Welcome");
    }

    @Override
    public void onEvent(@NotNull CommandInteraction event) {
        log.info("event: /welcome");
        MessageEmbed embedBuilder =
                new EmbedBuilder()
                        .setTitle("Welcome to order your food")
                        .setDescription("Have a treat")
                        .setImage(
                                "https://news.cgtn.com/news/3d3d514d3551544d31457a6333566d54/img/d28dd1786f3e4bb598da6e577b369898/d28dd1786f3e4bb598da6e577b369898.jpg")
                        .setColor(Color.GREEN)
                        .addField(
                                "User Commands",
                                "**/get-restaurant** Get details of a restaurant based on name\n"
                                        + "**/get-restaurant-zipcode** Get a list of restaurants based on zipcode\n"
                                        + "**/get-restaurant-zipcode-cuisintype** Get a list of restaurants based on zipcode and cuisine type\n"
                                        + "**/get-menu-of-restaurant** Show menu of a restaurant\n"
                                        + "**/place-order** Place an order\n"
                                        + "**/check-order-status** Check status of an order\n"
                                        + "**/add-dish-to-cart** Add a dish to shopping cart\n"
                                        + "**/clear-cart** Clear shopping cart\n",
                                true)
                        .addField(
                                "Admin Commands",
                                "**/add-restaurant** Add a restaurant into database\n"
                                        + "**/add-customer** Add a customer into database\n"
                                        + "**/add-dish** Add a dish into database\n",
                                true)
                        .build();
        event.replyEmbeds(embedBuilder).queue();
    }
}
