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
 * @author brink
 */
public class GunChasingTarget extends Target implements Renderable, Updatable{
    
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    
    GameElement pistol;
    
    int count = 1;
    
    
    public GunChasingTarget(double xPos, double yPos, int w, int h, Color c) throws FileNotFoundException {
        super(xPos, yPos, w, h, c);
        pistol = new Pistol(xPos, yPos, Color.BLACK);
        GameState.addAllMovables(pistol);
    }
    
     public double rotate(){
        
        double theta = 0;
     
        double xDiff = (pistol.getX()+pistol.getWidth()/2)-getPlayerX();
        double yDiff = ((pistol.getY()+pistol.getHeight()/2)-15)-getPlayerY();
        
        theta = Math.toDegrees(Math.atan2(xDiff, yDiff)) + 90;
        //System.out.println(theta);
        
        return theta;
    }
     
    @Override
    public void onCollision(GameElement element) {
        if(getX()<=element.getX()+element.getWidth() && getX()+getWidth()>=element.getX() && getY()<=element.getY()+element.getHeight() && getY()+getHeight()>=element.getY() && element instanceof Bullet){
            
            double x = Math.random()*WIDTH;
            double y = Math.random()*HEIGHT;
            
           

            int r = (int)(Math.random()*4);
            if(r==0){
                x = Math.random()*WIDTH;
                y = 0;
                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
                    x = Math.random()*WIDTH;
                }
            }
            if(r==1){
                x = Math.random()*WIDTH;
                y = HEIGHT;
                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
                    x = Math.random()*WIDTH;
                }
            }
            if(r==2){
                x = 0;
                y = Math.random()*HEIGHT;
                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
                    y = Math.random()*HEIGHT;
                }
            }
            if(r==3){
                x = WIDTH;
                y = Math.random()*HEIGHT;
                while(Math.abs(getPlayerX()-x)<800 && Math.abs(getPlayerY()-y)<800){
                    y = Math.random()*HEIGHT;
                }
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
    
    public void setShootGun(boolean s){
        ((Gun)pistol).setShootGun(s);
    }
    
    @Override
    public void update() {
        
        super.setX(getX() +  2*Math.cos(Math.toRadians(rotate())));
        super.setY(getY() -  2*Math.sin(Math.toRadians(rotate())));
        
         
        if(count%200==0){
            System.out.println("shoot");
            ((Gun)pistol).setShootGun(true);
        }
         
        pistol.setY(super.getY());
        pistol.setX(super.getX());
        ((Gun)pistol).setAngle(rotate());
        
        //((Gun)gun).setShootGun(false);
        count++;
    }

    
}
