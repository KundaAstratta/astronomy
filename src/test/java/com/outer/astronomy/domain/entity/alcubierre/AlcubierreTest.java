package com.outer.astronomy.domain.entity.alcubierre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlcubierreTest {

    @Test
    public void testSimulate_smallGrid() {
        // Arrange
        Gridsize gridsize = new Gridsize(4);
        // Small grid size for faster testing
        Alcubierre alcubierre = new Alcubierre(gridsize);

        // Act
        double[][][] spacetime = alcubierre.simulate();

        // Assert - Check dimensions and a single value
        assertEquals(0, spacetime[0][1][3], 0.0001);
        assertEquals(-0.5, spacetime[0][2][3], 0.5);
    }
    @Test
    public void testSimulate_invalidGridSize_with_value_greater_than_100() {

        int invalidGridSize = 102;
        try {
            new Gridsize(invalidGridSize);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Grid size must be less than 100.");
        }

    }

    @Test
    public void testSimulate_invalidGridSize_with_value_less_than_0() {

        int invalidGridSize = -1;
        try {
            new Gridsize((invalidGridSize));
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Grid size must be positive.");
        }
    }
}