package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.OrderController;
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
public class CheckOrderStatusCommand implements Command {

    @Inject OrderController orderController;

    @Inject
    public CheckOrderStatusCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "check-order-status";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot if you want to check order status")
                .addOptions(
                        new OptionData(
                                        OptionType.INTEGER,
                                        "orderid",
                                        "The bot will check order with the orderId")
                                .setRequired(true));
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        int orderId = (int) event.getOption("orderid").getAsLong();
        log.info("event: /checkOrderStatus for orderId:" + orderId);
        if (orderId <= 0 || orderId >= orderController.generateOrderId()) {
            event.reply("Please enter valid orderId ").queue();
        } else if (orderController.checkOrderStatus(orderId)) {
            event.reply(String.format("Your order (orderNumber: %s) has been delivered", orderId))
                    .queue();
        } else {
            event.reply(
                            String.format(
                                    "Your order (orderNumber: %s) has not been delivered yet",
                                    orderId))
                    .queue();
        }
    }
}
