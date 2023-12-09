package com.example.springboot.mocks;

import com.example.springboot.category.CategoryView;
import com.example.springboot.location.LocationView;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

public class LocationViewImpl implements LocationView {

    private Integer id;
    private String name;
    private Boolean isPrivate;
    private String description;
    private String userId;
    private Point<G2D> coordinate;
    private CategoryViewImpl category;  // Use the concrete implementation for CategoryView

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Point<G2D> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public CategoryView getCategory() {
        return category;
    }

    public void setCategory(CategoryViewImpl category) {
        this.category = category;
    }

    public static class CategoryViewImpl implements CategoryView {

        private String categoryName;
        private String symbol;
        private String categoryDescription;

        @Override
        public String getName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        @Override
        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String getDescription() {
            return categoryDescription;
        }

        public void setCategoryDescription(String categoryDescription) {
            this.categoryDescription = categoryDescription;
        }
    }
}
