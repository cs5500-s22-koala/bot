package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.CustomerController;
import edu.northeastern.cs5500.starterbot.model.Customer;
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
public class GetCustomerOnNameCommand implements Command {

    @Inject CustomerController customerController;

    @Inject
    public GetCustomerOnNameCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "get-customer-name";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what customer you are looking for")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "customername",
                                        "The bot will search customer based on the name")
                                .setRequired(true));
    }

    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /getCustomerOnName");
        String customerName = event.getOption("customername").getAsString();

        Customer result = customerController.getSpecificCustomerBasedOnName(customerName);
        if (result == null) {
            event.reply(customerName + " cannot be  found in database.").queue();
        } else {
            StringBuilder sb = new StringBuilder();
            String bulletSymbol = ":small_blue_diamond:";
            sb.append(
                    bulletSymbol
                            + String.format("%s,", "Customer Name")
                            + String.format("%s,", "Phone")
                            + String.format("%s,", "Address")
                            + String.format("%s", "Bank Account")
                            + "\n");
            sb.append(
                    bulletSymbol
                            + String.format("%s,", result.getCustomerName())
                            + String.format("%s,", result.getPhone())
                            + String.format("%s,", result.getAddress())
                            + String.format("%s", result.getBankAccount())
                            + "\n");
            event.reply(sb.toString()).queue();
        }
    }
}
