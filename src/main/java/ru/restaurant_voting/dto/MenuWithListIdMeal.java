package ru.restaurant_voting.dto;

import java.time.LocalDate;
import java.util.List;

// "record" starting from java version 16
public record MenuWithListIdMeal( List<Integer> listIdMeals, LocalDate date) {

}
