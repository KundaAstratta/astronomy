package com.outer.astronomy.domain.service.heisenberg;

import org.junit.jupiter.api.Test;

import com.outer.astronomy.domain.service.quantum.heisenberg.NeutronStarQuantumEffectService;

import static org.junit.jupiter.api.Assertions.*;

public class NeutronStarQuantumEffectServiceTest {

   @Test
   void testGenerateQuantumEffectsData() {
        // Test data
        double minDensity = 1e30;
        double maxDensity = 1e35;
        int numValues = 3;

        // Expected output (simplified for example, calculate actual values for your test)
        String expectedOutput = "Density (neutrons/m^3),Δx (m),Δp (kg·m/s)\n" +
                "1,00e+30,1,00e-10,5,27e-25\n" +
                "3,16e+32,1,47e-11,3,59e-24\n" +
                "1,00e+35,2,15e-12,2,45e-23\n";

        // Create an instance of your service class (replace with your actual implementation)
        NeutronStarQuantumEffectService service = new NeutronStarQuantumEffectService();
        //service.setLogger(logger); // Inject the mock logger

        // Call the method under test
        String result = service.generateQuantumEffectsData(minDensity, maxDensity, numValues);

        // Assertions
        assertEquals(expectedOutput, result); // Check if the output matches

    }

}
