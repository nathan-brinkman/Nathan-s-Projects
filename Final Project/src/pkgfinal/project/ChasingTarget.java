/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public class ChasingTarget extends Target implements Renderable, Updatable{
    
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    
    private double theta = 0;
    
    double speed;
    
    public ChasingTarget(double xPos, double yPos, int w, int h, Color c, double s) throws FileNotFoundException {
        super(xPos, yPos, w, h, c);
        speed = s;
        
    }
    
    public void rotate(){
        
     
        double xDiff = (getX()+getWidth()/2) - getPlayerX();
        double yDiff = (getY()+getHeight()/2) - getPlayerY();
        
        
        theta = Math.toDegrees(Math.atan2(xDiff, yDiff)) + 90;
        
        //System.out.println(xDiff +" " + yDiff +" "+ theta);
        //System.out.println(theta);
        
    }
    
    public void setSpeed(double s){
        speed = s;
    }

    @Override
    public void onCollision(GameElement element) {
        if(getX()<=element.getX()+element.getWidth() && getX()+getWidth()>=element.getX() && getY()<=element.getY()+element.getHeight() && getY()+getHeight()>=element.getY() && element instanceof Bullet){
            
            double x = Math.random()*WIDTH;
            double y = Math.random()*HEIGHT;
            
           

//            int r = (int)(Math.random()*4);
//            if(r==0){
//                x = Math.random()*WIDTH;
//                y = 0;
//                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
//                    x = Math.random()*WIDTH;
//                }
//            }
//            if(r==1){
//                x = Math.random()*WIDTH;
//                y = HEIGHT;
//                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
//                    x = Math.random()*WIDTH;
//                }
//            }
//            if(r==2){
//                x = 0;
//                y = Math.random()*HEIGHT;
//                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
//                    y = Math.random()*HEIGHT;
//                }
//            }
//            if(r==3){
//                x = WIDTH;
//                y = Math.random()*HEIGHT;
//                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
//                    y = Math.random()*HEIGHT;
//                }
//            }

         while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
            y = Math.random()*HEIGHT;
            x = Math.random()*WIDTH;
        }
            
            
            setX(x);
            setY(y);
            setWidth(getWidth());
            setHeight(getHeight());
            setColor(getColor());
            increaseTargetCount();
            
        }
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(super.getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
        
    }

    @Override
    public void update() {
        
        
        rotate();
        
        //change back to 5
        super.setX(getX() +  speed*Math.cos(Math.toRadians(theta)));
        super.setY(getY() -  speed*Math.sin(Math.toRadians(theta)));
        
    }
    
}
