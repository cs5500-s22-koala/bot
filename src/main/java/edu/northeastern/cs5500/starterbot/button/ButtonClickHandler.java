package edu.northeastern.cs5500.starterbot.button;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public interface ButtonClickHandler {
    @Nonnull
    public String getName();

    public void onButtonClick(ButtonClickEvent event);
}
