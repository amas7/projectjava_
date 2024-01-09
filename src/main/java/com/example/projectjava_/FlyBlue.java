package com.example.projectjava_;

public class FlyBlue extends Fly{

    public FlyBlue(){
        super(0,0,0,0,"flyBlue.png");
        fillRandom(Constantes.minMassFlyBlue, Constantes.maxInitialMassFlyBlue, Constantes.minSpeedFlyBlue, Constantes.maxInitialSpeedFlyBlue);
    }
}