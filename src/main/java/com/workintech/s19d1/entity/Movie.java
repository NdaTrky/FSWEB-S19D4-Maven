package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "film adı null olamaz!")
    private String name;

    @Column(name = "director_name")
    @NotNull(message = "Yönetmen ismi null olamaz!")
    private String directorName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_actor", schema = "fsweb",
            inverseJoinColumns = @JoinColumn(name = "actor_id"),
            joinColumns = @JoinColumn(name = "movie_id"))
    private List<Actor> actors;

    public void addActor(Actor actor) { // Yöntem adı düzeltildi
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }
}
