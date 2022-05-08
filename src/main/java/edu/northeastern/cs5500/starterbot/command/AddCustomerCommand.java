package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.CustomerController;
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
public class AddCustomerCommand implements Command {

    @Inject CustomerController customerController;

    @Inject
    public AddCustomerCommand() {}

    @Nonnull
    @Override
    public String getName() {
        return "add-customer";
    }

    @Nonnull
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what customer you want to add")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "customername",
                                        "The bot will use this name as customer name")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "phone",
                                        "The bot will use this as customer's phone")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "address",
                                        "The bot will use this as customer's address")
                                .setRequired(true))
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "bankaccount",
                                        "The bot will use this as bank account")
                                .setRequired(true));
    }

    @ExcludeFromJacocoGeneratedReport
    @Override
    public void onEvent(@Nonnull CommandInteraction event) {
        log.info("event: /addCustomerCommand");
        String customerName = event.getOption("customername").getAsString();
        String phone = event.getOption("phone").getAsString();
        String address = event.getOption("address").getAsString();
        String bankaccount = event.getOption("bankaccount").getAsString();

        if (customerName == null || phone == null || address == null || bankaccount == null) {
            event.reply("Please enter completed restaurant info ").queue();
        } else {
            customerController.addCustomer(customerName, phone, address, bankaccount);
            event.reply(String.format("Data %s inserted successfully!", customerName)).queue();
        }
    }
}
