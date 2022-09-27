package ru.restaurant_voting.model;

//import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

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
public class Restaurant extends BaseEntity {

    @Length(min = 2, max = 128)
    @NotBlank
    @Column(name = "title", nullable = false, unique = true, length = 128)
    private String title;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    //  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Menu> menus = new ArrayList<>();


    @OnDelete(action = OnDeleteAction.CASCADE)
    //@Schema(hidden = true)
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @NotNull
    private List<Vote> votes = new ArrayList<>();

}