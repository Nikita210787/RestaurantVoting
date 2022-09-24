package ru.restaurant_voting.dto;

import ru.restaurant_voting.model.Menu;

// "record" starting from java version 16
public record RestaurantIncludeMenu(int id, String name, Menu todayMenu) {

}
