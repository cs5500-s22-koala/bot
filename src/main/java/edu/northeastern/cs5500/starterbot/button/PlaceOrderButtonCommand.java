package edu.northeastern.cs5500.starterbot.button;

import edu.northeastern.cs5500.starterbot.command.Command;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;

@Singleton
@Slf4j
public class PlaceOrderButtonCommand implements Command, ButtonClickHandler {

    @Inject
    PlaceOrderButtonCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "place-order-button"; // change to "place-order" later
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Click button to place order or start over");
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /place order button");

        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder =
                messageBuilder.setActionRows(
                        ActionRow.of(
                                Button.primary(this.getName() + ":confirm", "CONFIRM"),
                                Button.success(this.getName() + ":continue", "CONTINUE SHOPPING"),
                                Button.secondary(this.getName() + ":cancel", "CANCEL")));
        messageBuilder =
                messageBuilder.setContent(
                        "Please select an option below to place an order"
                                + "/continue shopping/clear shopping cart");
        event.reply(messageBuilder.build()).queue();
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        // event.reply(event.getButton().getLabel()).queue();
        String buttonId = event.getButton().getId().split(":")[1];
        log.info("event: /onButtonClick {}", buttonId);

        if (buttonId.equals("confirm")) {
            // TODO: add place order operation
            event.reply("confirm order").queue();
        } else if (buttonId.equals("continue")) {
            // TODO: add associated messages
            event.reply("continue shopping").queue();
        } else if (buttonId.equals("cancel")) {
            // TODO: add clear shopping cart operation
            event.reply("clear shopping cart").queue();
        } else {
            event.reply("invalid operation").queue();
        }
    }
}
