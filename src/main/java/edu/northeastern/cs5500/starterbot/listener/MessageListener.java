package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.button.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.command.Command;
import edu.northeastern.cs5500.starterbot.dropdown.SelectionMenuHandler;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Slf4j
public class MessageListener extends ListenerAdapter {

    @Inject Set<Command> commands;
    @Inject Set<ButtonClickHandler> buttons;
    @Inject Set<SelectionMenuHandler> selectionMenus;

    static final String WELCOME_MESSAGE = "Welcome. This is DashBot!";

    /**
     * Bot send a welcome message to user if it join to a guild.
     *
     * @param event GuildJoinEvent, the bot join to a guild.
     */
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        TextChannel textChannel = event.getGuild().getDefaultChannel();
        textChannel.sendMessage(WELCOME_MESSAGE).queue();
    }

    @Inject
    public MessageListener() {
        super();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        for (Command command : commands) {
            if (command.getName().equals(event.getName())) {
                command.onEvent(event);
                return;
            }
        }
        log.error("Unknown slash command:{}", event.getName());
    }

    public Collection<CommandData> allCommandData() {
        return commands.stream().map(Command::getCommandData).collect(Collectors.toList());
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String handlerName = event.getButton().getId().split(":")[0];
        log.info("buttonHandlerName: {}", handlerName);

        for (ButtonClickHandler buttonClickHandler : buttons) {
            if (buttonClickHandler.getName().equals(handlerName)) {
                buttonClickHandler.onButtonClick(event);
                return;
            }
        }
        log.error("Unknown button handler:{}", handlerName);
    }

    @Override
    public void onSelectionMenu(@Nonnull SelectionMenuEvent event) {
        String handlerName = event.getComponent().getId().split(":")[0];
        log.info("onSelectionMenu: {}", handlerName);

        for (SelectionMenuHandler selectionMenuHandler : selectionMenus) {
            if (selectionMenuHandler.getName().equals(handlerName)) {
                selectionMenuHandler.onSelectionMenu(event);
                return;
            }
        }
        log.error("Unknown selectionMenu handler:{}", handlerName);
    }
}
