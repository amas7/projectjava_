package com.example.projectjava_;

public class FlyRed extends Fly{

    public FlyRed(){
        super(0,0,0,0,"flyRed.png");
        fillRandom(Constantes.minMassFlyRed, Constantes.maxInitialMassFlyRed, Constantes.minSpeedFlyRed, Constantes.maxInitialSpeedFlyRed);
    }
}