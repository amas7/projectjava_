package com.example.projectjava_;

import java.util.Random;

public abstract class Animal extends Entity{
    private double mass;
    private double speed;

    public Animal(int x, int y, double mass, double speed, String pathToImage1, String pathToImage2) {
        super(x, y,pathToImage1,pathToImage2);
        this.mass = mass;
        this.speed = speed;
    }

    public Animal(int x, int y, double mass, double speed, String pathToImage1) {
        this(x, y,mass,speed,pathToImage1,null);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMass() {
        return mass;
    }
    public void fillRandom(double minMass, double mxMass, double minSpeed, double maxSpeed){
        Random random = new Random();

        // random mass between the bound
        double randomMass = minMass + (mxMass - minMass) * random.nextDouble();
        setMass(randomMass);

        // random speed between the bound
        double randomSpeed = minSpeed + (maxSpeed - minSpeed) * random.nextDouble();
        setSpeed(randomSpeed);


    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public boolean isDead() {
        return getMass() ==0;
    }

    public abstract void grow(double extraMass);
}
