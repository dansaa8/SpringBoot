package com.example.springboot.location;

import com.example.springboot.Point2DSerializer;
import com.example.springboot.category.Category;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

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

    @Size(max = 255)
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate = false;

    @NotNull
    @JsonSerialize(using = Point2DSerializer.class)
    private Point<G2D> coordinate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_modified")
    private Instant lastModified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_category")
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

    public Point<G2D> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
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


}