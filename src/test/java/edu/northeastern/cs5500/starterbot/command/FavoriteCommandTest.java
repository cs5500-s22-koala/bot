package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

class FavoriteCommandTest {
    @Test
    void testNameMatchesData() {
        FavoriteCommand favoriteCommand = new FavoriteCommand();
        String name = favoriteCommand.getName();
        CommandData commandData = favoriteCommand.getCommandData();

        assertThat(name).isEqualTo(commandData.getName());
    }

    // @Test
    // <MessageEmbed> void testThatFavoriteHaveImages() {
    // FavoriteCommand favoriteCommand = new FavoriteCommand();
    // MessageEmbed embed = (MessageEmbed) favoriteCommand.favoriteResturant();
    // // assertThat(embed.getImage().getUrl().isNotEmpty());

    // }
}
