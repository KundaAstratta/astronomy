package com.outer.astronomy.domain.entity.alcubierre;

public record Gridsize(int gridSize) {

    public Gridsize(int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("Grid size must be positive");
        }
        if (gridSize > 100) {
            throw new IllegalArgumentException("Grid size must be less than 100");
        }
        this.gridSize = gridSize;
    }

}
