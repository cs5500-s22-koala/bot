package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
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
public class ClearCartCommand implements Command {

    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    @Inject
    public ClearCartCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "clear-cart";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot whether you want to clear shopping cart")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "answer",
                                        "The bot will clear shopping cart if you type 'YES/yes/Y/y'")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /clearShoppingCart");
        String ans = event.getOption("answer").getAsString();
        String discordUserId = event.getUser().getId();

        if (ans.equalsIgnoreCase("YES") || ans.equalsIgnoreCase("Y")) {
            if (shoppingCart.getCartOfUser(discordUserId) == null) {
                event.reply(String.format("%s, Your shopping cart has nothing to clear", event.getUser().getName()))
                        .queue();
            } else {
                shoppingCart.clearShoppingCartOfUser(discordUserId);
                event.reply(String.format("%s, Your shopping cart is empty now", event.getUser().getName()))
                        .queue();
            }
        } else {
            event.reply(String.format("%s, please enter YES/yes/Y/s to clear shopping cart", event.getUser().getName()))
                    .queue();
        }
    }
}
