package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(name = "one_menu_for_day", columnNames = {"restaurant_id", "date"})
})
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @Schema(hidden = true)
    private Restaurant restaurant;

    @Column(nullable = false)
    @NotNull
    private LocalDate date;
    @OnDelete(action = OnDeleteAction.CASCADE) // that need for deleting from joined tablet MENU_MEAL
    @ManyToMany(mappedBy = "menus", fetch = FetchType.EAGER)
    @Nullable
    private List<Meal> meals = new ArrayList<>();


}