package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal")
public class Meal extends BaseEntity {

    @Column(nullable = false)
    @NotNull
    @Length(min = 2, max = 128)
    private String title;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero
    private Long price;

    @JsonBackReference
    @Schema(hidden = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @Nullable
    @MaybeNull
    @JoinTable(
            name = "menu_meal",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menus = new java.util.ArrayList<>();


    public Meal(String title, Long price) {
        this.title = title;
        this.price = price;
    }
}