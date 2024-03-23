package com.outer.astronomy.domain.entity.alcubierre;

import com.outer.astronomy.domain.utils.Errors;

public record Gridsize(int gridSize) {

    public Gridsize(int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException(Errors.GridSizeMustBePositive);
        }
        if (gridSize > 100) {
            throw new IllegalArgumentException(Errors.GridSizeMustBeLessThan100);
        }
        this.gridSize = gridSize;
    }

}
