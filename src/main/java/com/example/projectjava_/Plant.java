package com.example.projectjava_;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant extends Entity{


    public Plant(int x, int y) {

        super(x, y, "plant.png");
    }
    public Plant(){
        super(0,0,"plant.png");
    }

}
