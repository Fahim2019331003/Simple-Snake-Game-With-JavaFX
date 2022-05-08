package app.snakegame2screen;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Food {
    private double x=0;
    private double y=0;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }

    public void init()
    {
        x= ((int) (Math.random()*1000000)) % 16 ;
        y= ((int) (Math.random()*1000000)) % 10 ;
        x*=50;
        y*=50;

    }

}
