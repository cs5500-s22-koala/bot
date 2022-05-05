package edu.northeastern.cs5500.starterbot.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.starterbot.model.Customer;
import edu.northeastern.cs5500.starterbot.model.Dish;
import edu.northeastern.cs5500.starterbot.model.Order;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.model.UserPreference;

@Module
public class RepositoryModule {
    // NOTE: You can use the following lines if you'd like to store objects in memory.
    // NOTE: The presence of commented-out code in your project *will* result in a lowered grade.

    @Provides
    public GenericRepository<UserPreference> provideUserPreferencesRepository(
            MongoDBRepository<UserPreference> repository) {
        return repository;
    }

    @Provides
    public Class<UserPreference> provideUserPreference() {
        return UserPreference.class;
    }

    @Provides
    public GenericRepository<Restaurant> provideRestaurantRepository(
            MongoDBRepository<Restaurant> repository) {
        return repository;
    }

    @Provides
    public Class<Restaurant> provideRestaurant() {
        return Restaurant.class;
    }

    @Provides
    public GenericRepository<Dish> provideDishRepository(MongoDBRepository<Dish> repository) {
        return repository;
    }

    @Provides
    public Class<Dish> provideDish() {
        return Dish.class;
    }

    @Provides
    public GenericRepository<Order> provideOrderRepository(MongoDBRepository<Order> repository) {
        return repository;
    }

    @Provides
    public Class<Order> provideOrder() {
        return Order.class;
    }

    @Provides
    public GenericRepository<Customer> provideCustomerRepository(
            MongoDBRepository<Customer> repository) {
        return repository;
    }

    @Provides
    public Class<Customer> provideCustomer() {
        return Customer.class;
    }
}
