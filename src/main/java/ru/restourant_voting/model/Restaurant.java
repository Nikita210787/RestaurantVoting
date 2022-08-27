package ru.restourant_voting.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends AbstractPersistable<Integer> {

    @Length(min = 2, max = 128)
    @NotBlank
    @Column(name = "title", nullable = false, unique = true, length = 128)
    private String title;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @NotNull
    private List<Vote> votes = new ArrayList<>();
}
