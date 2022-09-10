package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import ru.restaurant_voting.converter.MealsDBConverter;

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
    @JsonBackReference ////  (@JsonIgnore и @JsonManagedReference, @JsonBackReference) for not avaliable "infinity recursion" https://overcoder.net/q/309597/%D1%80%D0%B0%D0%B7%D0%BD%D0%B8%D1%86%D0%B0-%D0%BC%D0%B5%D0%B6%D0%B4%D1%83-jsonignore-%D0%B8-jsonbackreference-jsonmanagedreference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false, updatable = false)
    @NotNull
    //@Schema(hidden = true)
    private Restaurant restaurant;

    @Column(nullable = false)
    @NotNull
    private LocalDate date;


    @Size(min = 1, max = 20)
    @NotNull
    @Convert(converter = MealsDBConverter.class)
    @Valid
    private List<Meal> meals = new ArrayList<>();

}