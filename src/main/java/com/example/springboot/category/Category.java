package com.example.springboot.category;

import com.example.springboot.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id; // Kan skicka in null med objektversion = skapa ny rad o autogenerera ID

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "symbol")
    private String symbol;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category") // refererar till fältnamnet i Location entiteten; inte kolumnnamn i DB
    private List<Location> locations = new ArrayList<>();
}
