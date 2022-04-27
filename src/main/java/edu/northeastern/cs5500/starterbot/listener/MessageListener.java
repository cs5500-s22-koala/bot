package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.command.Command;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class MessageListener extends ListenerAdapter {
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

    @Inject Set<Command> commands;

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
    }

    public Collection<CommandData> allCommandData() {
        return commands.stream().map(Command::getCommandData).collect(Collectors.toList());
    }
}
