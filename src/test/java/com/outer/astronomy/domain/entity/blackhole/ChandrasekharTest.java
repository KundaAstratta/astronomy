package com.outer.astronomy.domain.entity.blackhole;

import org.junit.jupiter.api.Test;

import com.outer.astronomy.domain.model.blackhole.Chandrasekhar;

import static org.junit.jupiter.api.Assertions.*;

class ChandrasekharTest {

    @Test
    void shouldObtainChandrasekharLimit() {
        Chandrasekhar chandrasekhar = new Chandrasekhar(new double[]{0.7, 0.2, 0.1}, new int[]{1, 6, 8}, new double[]{1.007, 12.011, 15.999});
        assertEquals(7.812058667610603E29,chandrasekhar.calculateChandrasekharLimit(),0.1);
    }

}