package app.snakegame2screen;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Vector;


public class gameScreenController {

    @FXML
    ImageView foodImage;
    @FXML
    private AnchorPane gameWindowPane;

    @FXML
    private ImageView snakeHeadicon;

    @FXML
    private Label gameLabel;

    private Label scoreLabel;

    private Scene scene;

    private List<Rectangle> list=new Vector<Rectangle>();

    private int[] xx={1,0,-1,0};//RULD
    private int[] yy={0,-1,0,1};
    private int[] rr={270,180,90,0};
    private int dir=0;
    private int SIZE=3;
    public int score=0;
    private Food food;

    private boolean foodavail=false;

    private boolean gameOver=false;

    private boolean validFood()
    {
        for(int i=0;i<SIZE;i++){
            if(list.get(i).getX()==food.getX() && list.get(i).getY()==food.getY()) return false;
        }
        return true;
    }


    public void move()
    {

        if(checkGameOver()){
            gameOver=true;
            return;
        }

        if(!foodavail){
            food.init();

            while(validFood()!=true){
                food.init();
            }
            foodavail=true;
        }

        foodImage.setX(food.getX());
        foodImage.setY(food.getY());
        System.out.println(foodImage.getX()+"  "+foodImage.getY());

        if(snakeHeadicon.getX()+50*xx[dir]==food.getX() && snakeHeadicon.getY()+50*yy[dir]==food.getY()){
            foodavail=false;
            score+=10;
            list.get(SIZE).setVisible(true);
            SIZE++;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gameLabel.setText("Score : "+String.valueOf(score));
                }
            });
        }
        if(!foodavail) foodImage.setVisible(false);
        else{
            foodImage.setVisible(true);
        }



        for(int i= SIZE-1;i>0;i--){

            list.get(i).setX(list.get(i-1).getX());
            list.get(i).setY(list.get(i-1).getY());
        }

        list.get(0).setX(snakeHeadicon.getX());
        list.get(0).setY(snakeHeadicon.getY());

        snakeHeadicon.setX(snakeHeadicon.getX()+50*xx[dir]);
        snakeHeadicon.setY(snakeHeadicon.getY()+50*yy[dir]);

        snakeHeadicon.setRotate(rr[dir]);




    }

    

    private boolean checkGameOver() {
        if(snakeHeadicon.getX()+50*xx[dir]>=800 || snakeHeadicon.getX()+50*xx[dir]<0 ) return true;
        if(snakeHeadicon.getY()+50*yy[dir]>=500 || snakeHeadicon.getY()+50*yy[dir]<0) return true;

        for(int i=0;i<SIZE;i++){
            if(snakeHeadicon.getX()+50*xx[dir]==list.get(i).getX() && snakeHeadicon.getY()+50*yy[dir]==list.get(i).getY()) return true;
        }


        return false;
    }


    public void init() {


        food=new Food();


        buildbody();
        //DIR=RIGHT;

        Thread thread=new Thread(()->
        {

            

            while(!gameOver)
            {
                final long startTime = System.currentTimeMillis();

                move();


                final long endTime = System.currentTimeMillis();
                try {

                    Thread.sleep(500-(endTime-startTime));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }




            }
            //gameLabel.setText("GAME OVER");
        });
        thread.start();


        scene=gameWindowPane.getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case UP:
                        if(dir!=3) dir=1;

                        break;
                    case LEFT:
                        if(dir!=0) dir=2;

                        break;
                    case DOWN:
                        if(dir!=1) dir=3;

                        break;
                    case RIGHT:
                        if(dir!=2) dir=0;

                    default:
                        break;
                }
            }
        });



    }

    private void buildbody() {

        double x=snakeHeadicon.getX();
        double y= snakeHeadicon.getY();
        for(int i=0;i<100;i++){
            Rectangle r=new Rectangle(x-=50,y,50,50);
            r.setFill(Color.web("#A1DD70"));
            r.setStroke(Color.GREEN);
            r.setArcWidth(50);
            r.setArcHeight(50);
            list.add(r);
            gameWindowPane.getChildren().add(r);
            if(i>=3) r.setVisible(false);

        }


    }


}
