package com.example.projectjava_;

import java.util.Random;


public abstract class Fly extends Animal implements Movement{
    private double direction;


    public Fly(int x, int y, double mass, double speed,String pathToImage1) {
        super(x, y, mass,speed,pathToImage1);
        this.direction = Math.random() * 360;


    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    @Override
    public void grow(double addedMass) {
        if (!isDead()) {
            setMass(getMass() + addedMass);
            if (getMass() < Constantes.const1GrowFly) {
                setSpeed(getSpeed() + 0.1*addedMass);
                setSpeed(Math.max(getSpeed(),1d));
            } else {
                setSpeed(getSpeed() -(Constantes.const2GrowFly * (getSpeed() - Constantes.const1GrowFly)));
                setSpeed(Math.max(getSpeed(),1d));
            }
        }
    }

    public void grow() {
        grow(1d);
    }
    @Override
    public void move() {
            Random random = new Random();

            do {
                //decide randomly if the fly changes direction
                if (random.nextDouble()>Constantes.probaFlyNotChangeDirection) {
                    //new direction
                    setDirection(Math.random() * 360);
                }

                // Calculate new position
                int newX =(int) (getX() + Math.cos(Math.toRadians(getDirection())) * getSpeed());
                int newY = (int) (getY() + Math.sin(Math.toRadians(getDirection())) * getSpeed());

                // Check if the new position is within bounds
                if (checkIfInWindow(newX,newY)) {
                    // Update position
                    setX(newX);
                    setY(newY);
                    break; // Break out of the loop if the position is valid
                }


            } while (true); // Repeat until a valid position is found

    }

    @Override
    public String toString() {
        return "I am a "+ getClass().getName()+". I weigh " +
                String.format("%.2f", getMass()) + " and my speed is " +
                String.format("%.2f", getSpeed()) + " My X,Y position is " +
                Integer.toString(getX()) + " " + Integer.toString(getY())+ "." ;
    }






}
