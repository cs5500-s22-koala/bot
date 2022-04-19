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
    public Command providePreferredNameCommand(PreferredNameCommand preferredNameCommand) {
        return preferredNameCommand;
    }

    @Provides
    @IntoSet
    public Command provideAddRestaurantCommand(AddRestaurantCommand addRestaurantCommand) {
        return addRestaurantCommand;
    }

    @Provides
    @IntoSet
    public Command provideShowCustomerCommand(ShowCustomerCommand showCustomerCommand) {
        return showCustomerCommand;
    }

    @Provides
    @IntoSet
    public Command provideGetRestaurantCommand(GetRestaurantCommand getRestaurantCommand) {
        return getRestaurantCommand;
    }

    @Provides
    @IntoSet
    public Command provideAddDishCommand(AddDishCommand addDishCommand) {
        return addDishCommand;
    }

    @Provides
    @IntoSet
    public Command provideShowMenuCommand(ShowMenuCommand showMenuCommand) {
        return showMenuCommand;
    }

    @Provides
    @IntoSet
    public Command provideAddDishToCartCommand(AddDishToCartCommand addDishToCartCommand) {
        return addDishToCartCommand;
    }

    @Provides
    @IntoSet
    public Command provideCancelCommand(CancelCommand cancelCommand) {
        return cancelCommand;
    }
}
