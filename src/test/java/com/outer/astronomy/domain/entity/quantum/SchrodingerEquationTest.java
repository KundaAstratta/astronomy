package com.outer.astronomy.domain.entity.quantum;

import org.junit.jupiter.api.Test;

import com.outer.astronomy.domain.model.quantum.schrodinger.SchrodingerEquation;

import static org.junit.jupiter.api.Assertions.*;

class SchrodingerEquationTest {

    @Test
    void shouldSchrodingerEquationWithImaginaryAndRealPartsNotNull_WithSomeValues() {
        SchrodingerEquation schrodingerSimulation = new SchrodingerEquation(1000,10,10,0.1,0,1,2,"6000");
        assertEquals(10, schrodingerSimulation.simulate().realPart().length);
        assertEquals(10,schrodingerSimulation.simulate().imagPart().length);
    }

}