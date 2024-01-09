package com.example.projectjava_;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ecosystem {
    private int nbFrog;
    private int nbFly;

    private static int nbPlant;
    private static int nbFlyInitial;
    private static int nbFrogInitial;
    private ArrayList<Plant> tabPlant = new ArrayList<Plant>();
    private ArrayList<Frog> tabFrog = new ArrayList<Frog>();
    private ArrayList<Fly> tabFly = new ArrayList<Fly>();

    private Timer timer; //timer used for animation and update
    public Ecosystem() {

        //set the number of fly and frog in the ecosystem
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the initial number of frogs: ");
                this.nbFrog = Integer.parseInt(scanner.nextLine());
                if (this.nbFrog <=Constantes.nbMaxFrog)
                {break;} // Break out of the loop if input is a valid integer
                else {System.out.println("number is to big");}
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        while (true) {
            try {
                System.out.print("Enter the initial number of flies: ");
                this.nbFly = Integer.parseInt(scanner.nextLine());
                break; // Break out of the loop if input is a valid integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        nbPlant = nbFrog;
        nbFlyInitial = nbFly;
        nbFrogInitial = nbFrog;

        placeEntity();

        timer = new Timer();

        TimerTask moveTask = new TimerTask() {
            @Override
            public void run() {
                moveFlies();
            }
        };
        timer.scheduleAtFixedRate(moveTask, Constantes.initialDelayTimer, Constantes.movePeriod);

        TimerTask growTask = new TimerTask() {
            @Override
            public void run() {
                growAnimal();
            }
        };
        timer.scheduleAtFixedRate(growTask, Constantes.initialDelayTimer, Constantes.growPeriod);

        TimerTask logTask = new TimerTask() {
            @Override
            public void run() {
                displayAndSaveInfo();
            }
        };
        timer.scheduleAtFixedRate(logTask, Constantes.initialDelayTimer, Constantes.logPeriod);

        TimerTask feedingTask = new TimerTask() {
            @Override
            public void run() {
                frogFeeding();
            }
        };
        timer.scheduleAtFixedRate(feedingTask, Constantes.initialDelayTimer, Constantes.movePeriod);
    }
    public void placePlant() {
        int k;
        int i;

        Random random = new Random();

        int pos_x;
        int pos_y;
        for (k = 0; k < getNbPlant(); k++) {
            Plant plant = new Plant();
            while (true) {
                pos_x = random.nextInt(Constantes.lengthOfPond - Constantes.lengthOfPlant);
                pos_y = random.nextInt(Constantes.heightOfPond - Constantes.heightOfPlant- Constantes.heightOfFrog) + Constantes.heightOfFrog;

                // check if the plant are far enough from each other
                boolean ValidPosition = true;

                Plant alreadyPlacedPlant;
                for (i=0;i<k;i++) {

                    alreadyPlacedPlant = tabPlant.get(i);
                    double pow2distance = (pos_x - alreadyPlacedPlant.getX())*(pos_x - alreadyPlacedPlant.getX()) + (pos_y - alreadyPlacedPlant.getY())*(pos_y - alreadyPlacedPlant.getY());

                    if (pow2distance < Constantes.pow2MinDistanceBtwPlant) {
                        ValidPosition = false;
                        break; //exit the for loop, at least one plant is closer than minDistanceBtwPlant
                    }
                }

                if (ValidPosition) {
                    break; // exit the do while loop if all the alreadyPlacedPlant are farther than minDistanceBtwPlant
                }
            }

            plant.setX(pos_x);
            plant.setY(pos_y);
            tabPlant.add(plant);
        }
    }
    public void placeFrog() {//Frogs are placed above each plant
        int count = 0;
        Random random = new Random();

        for (Plant plant : tabPlant) {
            count = count +1;
            System.out.println(count);
            Frog frog;

            // Randomly choose between FrogLeft and FrogRight
            if (random.nextBoolean()) {
                frog = new FrogLeft();
            } else {
                frog = new FrogRight();
            }
            //position of the frog obtained with the position of the plant
            int frogX = plant.getX() + Constantes.shiftAlongXPlantFrog;
            int frogY = plant.getY() + Constantes.shiftAlongYPlantFrog;

            // Check if the frog is within the window boundaries
            if (frog.checkIfInWindow(frogX, frogY)) {
                frog.setX(frogX);
                frog.setY(frogY);
                tabFrog.add(frog);

            } else {
                System.out.println("Error frog out of window");
            }
        }
    }
    public void placeFly() {
        Random random = new Random();

        int nbFlyGreen = random.nextInt(nbFly);
        int nbFlyBlue = random.nextInt(nbFly-nbFlyGreen+1);
        int nbFlyRed = nbFly - nbFlyGreen - nbFlyBlue;
        if (nbFlyRed<0) {System.out.println("pb nb fly");}


        for (int k = 0; k < nbFlyGreen; k++) {
            FlyGreen fly = new FlyGreen();
            int pos_x = random.nextInt(Constantes.lengthOfPond - Constantes.lengthOfFly);
            int pos_y = random.nextInt(Constantes.heightOfPond - Constantes.heightOfFly);
            fly.setX(pos_x);
            fly.setY(pos_y);
            tabFly.add(fly);
        }

        for (int k = 0; k < nbFlyBlue; k++) {
            FlyBlue fly = new FlyBlue();
            int pos_x = random.nextInt(Constantes.lengthOfPond - Constantes.lengthOfFly);
            int pos_y = random.nextInt(Constantes.heightOfPond - Constantes.heightOfFly);
            fly.setX(pos_x);
            fly.setY(pos_y);
            tabFly.add(fly);
        }

        for (int k = 0; k < nbFlyRed; k++) {
            FlyRed fly = new FlyRed();
            int pos_x = random.nextInt(Constantes.lengthOfPond - Constantes.lengthOfFly);
            int pos_y = random.nextInt(Constantes.heightOfPond - Constantes.heightOfFly);
            fly.setX(pos_x);
            fly.setY(pos_y);
            tabFly.add(fly);
        }
    }
    public void placeEntity(){
        placePlant();
        placeFrog();
        placeFly();

    }

    public void moveFlies(){
        int countFly = 0;
        for (Fly fly : tabFly){
            if (!fly.isDead()){

                countFly += 1;

                fly.move();}
        }
        setNbFly(countFly);

        if (getNbFly() ==0){
            System.out.println("all Fly are dead");
            timer.cancel();
        }
    }

    public void growAnimal(){
        for (Fly fly : tabFly){
            fly.grow();
        }
        int countFrog = 0;
        for (Frog frog : tabFrog){
            frog.grow(0.2d);
        }


    }

    public void displayAndSaveInfo() {
        StringBuilder logs = new StringBuilder();
        logs.append("Le score est de ").append(String.valueOf(getScore())).append("\n");
        System.out.println("Le score est de " + String.valueOf(getScore()));
        for (Plant plant : tabPlant) {
            logs.append(plant.toString()).append("\n");
            System.out.println(plant.toString());
        }
        for (Frog frog : tabFrog) {
            logs.append(frog.toString()).append("\n");
            System.out.println(frog.toString());
        }
        for (Fly fly : tabFly) {
            logs.append(fly.toString()).append("\n");
            System.out.println(fly.toString());
        }

        saveToFile(logs.toString(), "logs.txt");
    }



    private void saveToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return getNbFlyInitial()-getNbFly();
    }
    public void frogFeeding(){
        for (Frog frog : tabFrog){

            if (frog.isTong()){
                for (Fly fly : tabFly){
                    if (!fly.isDead())
                    {String a = frog.eat(fly);
                        if (a != null)
                        {System.out.println(a);};
                    }
                }

            }
        }
    }

    public static int getNbPlant() {
        return nbPlant;
    }



    public int getNbFly() {
        return nbFly;
    }

    public void setNbFly(int nbFly) {
        this.nbFly = nbFly;
    }

    public static int getNbFlyInitial() {
        return nbFlyInitial;
    }

    public static int getNbFrogInitial() {
        return nbFrogInitial;
    }

    public ArrayList<Plant> getTabPlant() {
        return tabPlant;
    }

    public ArrayList<Frog> getTabFrog() {
        return tabFrog;
    }

    public ArrayList<Fly> getTabFly() {
        return tabFly;
    }

    public Timer getTimer() {
        return timer;
    }

}
