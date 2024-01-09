package com.example.projectjava_;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    private int x;
    private int y;

    private String pathToImage1;
    private String pathToImage2;
    private Image im1;

    private Image im2 = null;
    private ImageView imView;
    private int pondHeight = Constantes.heightOfPond;
    private int pondLenght  = Constantes.lengthOfPond;

    public Entity(int x, int y,String pathToImage1,String pathToImage2) {
        this.x = x;
        this.y = y;
        this.im1 = loadImage(pathToImage1);
        if (pathToImage2 != null){
        this.im2 = loadImage(pathToImage2);}
        this.imView = new ImageView(im1);
        this.imView.setX(this.x);
        this.imView.setY(this.y);
    }
    public Entity(int x, int y,String pathToImage1){
        this(x,y,pathToImage1,null);
    }


    public Image getIm1() {
        return im1;
    }

    public Image getIm2() {
        return im2;
    }

    public ImageView getImView() {
        return imView;
    }

    public void setImView(ImageView imView) {
        this.imView = imView;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.imView.setX(this.x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.imView.setY(this.y);
    }

    public int getPondHeight() {
        return pondHeight;
    }

    public int getPondLenght() {
        return pondLenght;
    }
    public Image loadImage(String imagePath) {
        try {
            return new Image(imagePath);
        } catch (RuntimeException e) {
            //error management
            System.err.println("Error oppenin image : " + e.getMessage());
            return null;
        }
    }

    public boolean checkIfInWindow(int newX, int newY) {
        if (getIm2() == null) {
            return (newX >= 0 && newX + getIm1().getWidth() <= getPondLenght())
                    && (newY >= 0 && newY + getIm1().getHeight() <= getPondHeight());
        } else {
            return (newX >= 0 && newX + Math.max(getIm1().getWidth(), getIm2().getWidth()) <= getPondLenght())
                    && (newY >= 0 && newY + Math.max(getIm1().getHeight(), getIm2().getHeight()) <= getPondHeight());
        }
    }


}
