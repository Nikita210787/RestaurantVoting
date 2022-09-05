package ru.restaurant_voting.model;

//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.restaurant_voting.converter.MealsConverter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(name = "menus_unique_restaurant_date_idx",
                columnNames = {"restaurant_id", "date"})
})
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false, updatable = false)
    @NotNull
    // @Schema(hidden = true)
    private Restaurant restaurant;

    @Column(nullable = false)
    @NotNull
    private LocalDate date;

    @Size(min = 1, max = 20)
    @NotNull
    @Convert(converter = MealsConverter.class)
    @Valid
    private List<Meal> meals = new ArrayList<>();

}