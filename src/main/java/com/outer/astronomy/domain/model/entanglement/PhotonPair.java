package com.outer.astronomy.domain.model.entanglement;

public class PhotonPair {
    
        private final double angleA;
        private final double angleB;
        private final int resultA;
        private final int resultB;
    
        public PhotonPair(double angleA, double angleB, int resultA, int resultB) {
            this.angleA = angleA;
            this.angleB = angleB;
            this.resultA = resultA;
            this.resultB = resultB;
        }

        public double getAngleA() {
            return angleA;
        }

        public double getAngleB() {
            return angleB;
        }

        public int getResultA() {
            return resultA;
        }

        public int getResultB() {
            return resultB;
        }
    
}