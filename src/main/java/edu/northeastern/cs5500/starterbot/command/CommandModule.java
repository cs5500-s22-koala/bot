package edu.northeastern.cs5500.starterbot.command;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import edu.northeastern.cs5500.starterbot.button.ButtonClickHandler;
import edu.northeastern.cs5500.starterbot.button.PlaceOrderButtonCommand;

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
    public Command provideGetRestaurantOnZipcodeCommand(
            GetRestaurantOnZipcodeCommand getRestaurantOnZipcodeCommand) {
        return getRestaurantOnZipcodeCommand;
    }

    @Provides
    @IntoSet
    public Command provideGetRestaurantOnZipcodeCuisineTypeCommand(
            GetRestaurantOnZipcodeCuisineTypeCommand getRestaurantOnZipcodeCuisineTypeCommand) {
        return getRestaurantOnZipcodeCuisineTypeCommand;
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
    public Command provideClearCartCommand(ClearCartCommand clearCartCommand) {
        return clearCartCommand;
    }

    @Provides
    @IntoSet
    public Command providePlaceOrderCommand(PlaceOrderCommand placeOrderCommand) {
        return placeOrderCommand;
    }

    @Provides
    @IntoSet
    public Command provideCheckOrderStatusCommand(CheckOrderStatusCommand checkOrderStatusCommand) {
        return checkOrderStatusCommand;
    }

    @Provides
    @IntoSet
    public Command providePlaceOrderButtonCommand(PlaceOrderButtonCommand placeOrderButtonCommand) {
        return placeOrderButtonCommand;
    }

    @Provides
    @IntoSet
    public ButtonClickHandler providePlaceOrderButtonCommandClickHandler(
            PlaceOrderButtonCommand placeOrderButtonCommand) {
        return placeOrderButtonCommand;
    }
}
