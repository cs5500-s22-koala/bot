package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.UserPreferenceController;
import edu.northeastern.cs5500.starterbot.function.ShowCustomer;
import edu.northeastern.cs5500.starterbot.model.Customer;
import java.awt.Color;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class ShowCustomerCommand implements Command {
    static final String FAIL_MESSAGE = "Sorry, your watched list is empty.";
    static final String WAIT_MESSAGE =
            "I am looking for your watched list...Please wait a moment...";
    static final String WATCHED_LIST_TITLE = "This is your watched list:";

    @Inject UserPreferenceController userPreferenceController;

    @Inject
    public ShowCustomerCommand() {}

    @Override
    public String getName() {
        return "showcustomer";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what name to address you with")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /showcustomer");
        String customerName = event.getOption("name").getAsString();

        ShowCustomer showcustomer = new ShowCustomer();

        List<Customer> customerList = showcustomer.showCustomerList(customerName);
        event.reply(WAIT_MESSAGE).queue();

        if (customerList.isEmpty()) {
            event.getChannel().sendMessage(FAIL_MESSAGE).queue();
        } else {
            EmbedBuilder watchedBuilder = new EmbedBuilder();
            watchedBuilder.setTitle(WATCHED_LIST_TITLE);
            watchedBuilder.setColor(Color.YELLOW);
            for (Customer customer : customerList) {
                watchedBuilder.addField(
                        customer.getCustomerName(), customer.getCustomerId(), false);
            }
            event.getChannel().sendMessage(watchedBuilder.build()).queue();
        }
    }
}
