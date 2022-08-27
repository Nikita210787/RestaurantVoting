package ru.restourant_voting.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(name = "votes_unique_user_date_idx", columnNames = {"user_id", "date"})
})
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends AbstractPersistable<Integer> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(nullable = false, updatable = false, columnDefinition = "date default CURRENT_DATE()")
    private Date date = Date.valueOf(LocalDate.now());

}
