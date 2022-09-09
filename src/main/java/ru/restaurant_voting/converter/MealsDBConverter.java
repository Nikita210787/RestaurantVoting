package ru.restaurant_voting.converter;
import ru.restaurant_voting.model.Meal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Converter
public class MealsDBConverter implements AttributeConverter<List<Meal>, String> {

    private static final String PROPS_SEPARATOR = "\t";
    private static final String meals_SEPARATOR = "\t\t";

    @Override
    public String convertToDatabaseColumn(@Valid List<Meal> MealList) {
        if (MealList == null) return null;

        StringBuilder sb = new StringBuilder();
        MealList.forEach(Meal -> {
            if (!Meal.getTitle().isEmpty() && Meal.getPrice() >= 0) {
                sb.append(Meal.getTitle());
                sb.append(PROPS_SEPARATOR);
                sb.append(Meal.getPrice());
                sb.append(meals_SEPARATOR);
            }
        });
        return sb.toString();
    }

    @Override
    public List<Meal> convertToEntityAttribute(String dbMealList) {
        if (dbMealList == null || dbMealList.isEmpty()) return null;

        String[] mealsSerialized = dbMealList.split(meals_SEPARATOR);

        if (mealsSerialized.length == 0) return null;

        List<Meal> result = new ArrayList<>();

        for (String MealSerialized : mealsSerialized) {
            String[] MealProps = MealSerialized.split(PROPS_SEPARATOR);
            if (MealProps.length == 0 || !MealSerialized.contains(PROPS_SEPARATOR)) continue;

            String MealName = MealProps[0].isEmpty() ? null : MealProps[0];
            Long MealPrice = MealProps[1].isEmpty() ? null : Long.parseLong(MealProps[1]);

            result.add(new Meal(MealName, MealPrice));
        }
        return result;
    }
}
