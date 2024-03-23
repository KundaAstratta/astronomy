package com.outer.astronomy.domain.entity.blackhole;

public record Features(double mass, double radius, boolean isTraversable, double eventHorizonArea, double entropy, double temperature) {
    public Features (double mass, double radius, boolean isTraversable, double eventHorizonArea , double entropy, double temperature){

        this.mass = mass;
        this.radius = radius;
        this.isTraversable = isTraversable;
        this.eventHorizonArea = eventHorizonArea;
        this.entropy = entropy;
        this.temperature = temperature;
    }

}

