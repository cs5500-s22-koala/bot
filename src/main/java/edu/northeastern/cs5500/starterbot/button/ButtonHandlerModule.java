package edu.northeastern.cs5500.starterbot.button;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import edu.northeastern.cs5500.starterbot.dropdown.GetMenuOfRestaurantCommand;

@Module
public class ButtonHandlerModule {

    @Provides
    @IntoSet
    public ButtonClickHandler provideGetMenuOfRestaurantCommandButtonHandler(
            GetMenuOfRestaurantCommand getMenuOfRestaurantCommand) {
        return getMenuOfRestaurantCommand;
    }
}
