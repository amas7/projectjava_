package com.example.projectjava_;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.util.Duration;


import java.util.Random;

public abstract class Frog extends Animal{

    private boolean isTong;
    private String name;
    private boolean isFroglet;
    private static String species = "Rare Pepe";





    public Frog(int x, int y, double mass, double speed, String name, String pathToImage1, String pathToImage2)  {
        super( x,  y,  mass,  speed,  pathToImage1,  pathToImage2);
        this.isTong = false;
        this.name = name;
        updateFrogletStatus();

        //Media sound = new Media(getClass().getResource("frog.mp3").toString());
        //mediaPlayer = new MediaPlayer(sound);

        // Set up event handling for mouse press and release
        getImView().setOnMousePressed(event -> {
            setIsTong(true);
            updateImageView();

            //frog sound when clik on the frog
            //mediaPlayer.seek(Duration.ZERO);
            //mediaPlayer.play();
        });

        getImView().setOnMouseReleased(event -> {
            setIsTong(false);
            updateImageView();
        });

    }
    @Override
    public void grow(double  addedMass){
        setMass(getMass()+addedMass);
        if (getMass() < 12) {
            setSpeed(getSpeed()-0.1*getMass());
            setSpeed(Math.max(getSpeed(),7));
        }
        if (getMass() >= 30) {
            setSpeed(getSpeed()-(getMass()-30));
            setSpeed(Math.max(getSpeed(),7));
        }
        updateFrogletStatus();
    }

    public void grow(){
        grow(1d);
    }
    private void updateFrogletStatus() {
        setFroglet((getMass() > 1 && getMass() < 7));
    }
    public abstract boolean catchFly(Fly fly);

    public boolean catchFlyCommon(Fly fly, int shiftXMouth, int shiftYMouth, int leftNotRight) {
        int flyCenterX =  fly.getX()+Constantes.shiftXCenterFly;
        int flyCenterY = fly.getY()+Constantes.shiftYCenterFly;

        int frogMouthX = getX() + shiftXMouth;
        int frogMouthY = getY() + shiftYMouth;
        int pow2Distance = (flyCenterX - frogMouthX) * (flyCenterX - frogMouthX) + (frogMouthY - flyCenterY) * (frogMouthY - flyCenterY);

        if (leftNotRight == 1){return flyCenterX  <= frogMouthX && pow2Distance <= Constantes.pow2DistanceMaxCatchFly;}
        else {return flyCenterX  >= frogMouthX  && pow2Distance <= Constantes.pow2DistanceMaxCatchFly;}
    }

    public String eat(Fly fly){

        if (catchFly(fly)){
            boolean success = false;
            if (getSpeed()> fly.getSpeed()) {
                success = true;
                grow();
                fly.setMass(0);
                fly.setX(0); //the dead fly goes to cemetery at (0,0)
                fly.setY(0);
            } else {
                fly.grow(1);
            }
        if (success == true) {return getName() + " speed of " +getSpeed() +" eats " + fly.toString()+ fly.isDead();}
        else {return getName() + " speed of " +getSpeed()+" fails to eat " + fly.toString();}
        }
        else {
            return null;
        }

    }

    private void updateImageView() {
        if (isTong()) {
            getImView().setImage(getIm2());
        } else {
            getImView().setImage(getIm1());
        }
    }
    public void randomName(){
        Random random = new Random();
        //random Frog name
        String characters = Constantes.characterForName;
        int length = Constantes.nameLength;
        StringBuilder generatedString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            generatedString.append(characters.charAt(index));
        }
        setName(generatedString.toString());
    }

    public boolean isTong() {
        return isTong;
    }

    public void setIsTong(boolean tong) {
        isTong = tong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFroglet() {
        return isFroglet;
    }

    public void setFroglet(boolean froglet) {
        isFroglet = froglet;
    }

    public static String getSpecies() {
        return species;
    }

    public static void setSpecies(String species) {
        Frog.species = species;
    }

    @Override
    public String toString() {
        String type = "frog";
        if (isFroglet()) {
            type = "froglet";
        }
        String tongueIndicator = " not ";
        if (isTong()) {
            tongueIndicator = " ";
        }
        return "My name is " + getName() + " and I'm a " + getSpecies() + " " + type +" "+ getClass().getName()+ ". I weigh " +
                String.format("%.2f", getMass()) + " and my tongue has a speed of " +
                String.format("%.2f", getSpeed()) + " My X,Y position is " +
                Integer.toString(getX()) + " " + Integer.toString(getY())+ " My tongue is" + tongueIndicator + "out." ;
    }
}
