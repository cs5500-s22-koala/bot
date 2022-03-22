package edu.northeastern.cs5500.starterbot.command;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class CommandModule {

    @Provides
    @IntoSet
    public Command provideSayCommand(SayCommand sayCommand) {
        return sayCommand;
    }

    @Provides
    @IntoSet
    public Command provideCatch(CatchCommand catchCommand) {
        return catchCommand;
    }
    @Provides
    @IntoSet
    public Command provideFavoriteCommand(FavoriteCommand favoriteCommand) {
        return favoriteCommand;
    }
}
