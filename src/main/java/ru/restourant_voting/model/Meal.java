package ru.restourant_voting.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.PositiveOrZero;

@Getter
@AllArgsConstructor
public class Meal {
    @Length(min = 2, max = 128)
    private String title;

    @PositiveOrZero
    private Long price;

}
