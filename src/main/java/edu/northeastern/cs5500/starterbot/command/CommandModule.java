package edu.northeastern.cs5500.starterbot.command;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import edu.northeastern.cs5500.starterbot.dropdown.GetMenuOfRestaurantCommand;

@Module
public class CommandModule {

    @Provides
    @IntoSet
    public Command provideAddRestaurantCommand(AddRestaurantCommand addRestaurantCommand) {
        return addRestaurantCommand;
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
    public Command provideGetMenuOfRestaurantCommand(
            GetMenuOfRestaurantCommand getMenuOfRestaurantCommand) {
        return getMenuOfRestaurantCommand;
    }

    @Provides
    @IntoSet
    public Command provideAddCustomerCommand(AddCustomerCommand addCustomerCommand) {
        return addCustomerCommand;
    }

    @Provides
    @IntoSet
    public Command provideGetCustomerOnNameCommand(
            GetCustomerOnNameCommand getCustomerOnNameCommand) {
        return getCustomerOnNameCommand;
    }

    @Provides
    @IntoSet
    public Command provideWelcomeCommand(WelcomeCommand welcomeCommand) {
        return welcomeCommand;
    }
}
