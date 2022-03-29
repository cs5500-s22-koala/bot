package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.command.Command;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class MessageListener extends ListenerAdapter {

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

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();

        if ((event.getGuild().getDefaultChannel()) != null) {

            EmbedBuilder builder = new EmbedBuilder();

            builder.setColor(0xf22613);
            builder.setThumbnail("http://image.png");
            builder.setTitle("Willkommen auf Da Hood!");
            builder.setFooter("Powered by Backxtar.");
            builder.setTimestamp(OffsetDateTime.now());
            builder.setDescription(
                    "Herzlich willkommen"
                            + member.getAsMention()
                            + "auf **Da Hood**!\n"
                            + "[**Da Hood - The Best Gaming-Discord!**](https://xyz.gg)");

            member.getUser()
                    .openPrivateChannel()
                    .queue(
                            (ch) -> {
                                ch.sendMessage(builder.build()).queue();
                            });
        }
    }
}
