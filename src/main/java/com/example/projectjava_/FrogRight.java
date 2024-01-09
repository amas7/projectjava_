package com.example.projectjava_;

public class FrogRight extends Frog{
    public FrogRight() {
        super(0, 0, 0, 0, "","frogRight.png", "frogRightWithTongue.png");
        fillRandom(Constantes.minMassFrog, Constantes.maxInitialMassFrog, Constantes.minSpeedFrog, Constantes.maxInitialSpeedFrog);
        randomName();
    }

    @Override
    public boolean catchFly(Fly fly) {
        return catchFlyCommon(fly,Constantes.shiftXMouthFrogRight,Constantes.shiftYMouthFrogRight,0);
    }
}

