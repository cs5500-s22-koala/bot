package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.DishController;
import edu.northeastern.cs5500.starterbot.model.Dish;
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
public class ShowMenuCommand implements Command {

    @Inject DishController dishController;

    @Inject
    public ShowMenuCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "show-menu";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(
                        getName(), "Tell the bot which restaurant's menu you want to look at")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "restaurantname",
                                        "The bot will display menu of the restaurant")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /showMenuCommand");
        String restaurant = event.getOption("restaurantname").getAsString();
        List<Dish> menu = dishController.getMenuOfARestaurant(restaurant);

        if (menu.size() == 0) {
            event.reply("Please enter valid restaurant name ").queue();
        } else {
            event.reply(
                            "restaurant "
                                    + restaurant
                                    + " has "
                                    + menu.size()
                                    + " dishes on the"
                                    + "menu: \n"
                                    + menu)
                    .queue();
            // TODO: display menu in an embed, can add button handler
        }
    }
}
