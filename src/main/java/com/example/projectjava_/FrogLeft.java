package com.example.projectjava_;
import java.util.Random;

public class FrogLeft extends Frog{
    public FrogLeft() {
        super(0, 0, 0, 0,"" ,"frogLeft.png", "frogLeftWithTongue.png");
        fillRandom(Constantes.minMassFrog, Constantes.maxInitialMassFrog, Constantes.minSpeedFrog, Constantes.maxInitialSpeedFrog);
        randomName();
    }


    @Override
    public boolean catchFly(Fly fly) {
        return catchFlyCommon(fly,Constantes.shiftXMouthFrogLeft,Constantes.shiftYMouthFrogLeft,1 );

    }
}
