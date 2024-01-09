package com.example.projectjava_;

public class FlyGreen extends Fly{

    public FlyGreen(){
        super(0,0,0,0,"flyGreen.png");
        fillRandom(Constantes.minMassFlyGreen, Constantes.maxInitialMassFlyGreen, Constantes.minSpeedFlyGreen, Constantes.maxInitialSpeedFlyGreen);
    }
}