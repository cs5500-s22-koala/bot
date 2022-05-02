package edu.northeastern.cs5500.starterbot.dropdown;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class SelectionHandlerModule {
    @Provides
    @IntoSet
    public SelectionMenuHandler provideGetMenuOfRestaurantCommandMenuHandler(
            GetMenuOfRestaurantCommand getMenuOfRestaurantCommand) {
        return getMenuOfRestaurantCommand;
    }
}
