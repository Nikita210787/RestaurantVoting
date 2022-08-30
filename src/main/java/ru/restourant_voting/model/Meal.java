package ru.restourant_voting.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Getter
@AllArgsConstructor
public class Meal {
    //@Pattern(regexp = "^[^\\t]*$", message = "The name cannot contain a tab character")
    @Length(min = 2, max = 128)
    private String title;

    @PositiveOrZero
    private Long price;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (!name.equals(meal.name)) return false;
        return price.equals(meal.price);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }*/
}