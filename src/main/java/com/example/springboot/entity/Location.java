package com.example.springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "location", schema = "geo_POI")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "status")
    private String status;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_modified")
    private Instant lastModified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_category") // Owner side of the relation
    // Default lazy. Ej säkert att vi kommer hämta ut Category. Väntar med att hämta fk. Blir dålig prestanda.
    // Måste säga till Location att vi ska hämta Category direkt.
    // primitiva som motsv. en col hämtas direkt. Men referenser till andra tabeller, entitet från annan tabell
    // väntar spring boot med att hämta.
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(Instant lastModified) {
        this.lastModified = lastModified;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

/*
    TODO [JPA Buddy] create field to map the 'coordinate' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "coordinate", columnDefinition = "geometry(0, 0) not null")
    private Object coordinate;
*/
}