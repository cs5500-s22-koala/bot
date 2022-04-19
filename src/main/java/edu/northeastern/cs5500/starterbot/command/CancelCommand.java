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
public class CancelCommand implements Command {

    ShoppingCart shoppingCart = ShoppingCart.getInstance();

    @Inject
    public CancelCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "cancel-order";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot whether you want to cancel order")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "answer",
                                        "The bot will cancel the order if you type 'YES'")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /cancelOrderCommand");
        String ans = event.getOption("answer").getAsString();
        if (ans.equals("YES")) {
            shoppingCart.clearShoppingCart();
            event.reply("Clear shopping cart").queue();
        }
    }
}
