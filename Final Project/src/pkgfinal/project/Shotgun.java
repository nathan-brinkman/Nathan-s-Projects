/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public class Shotgun extends Gun implements Renderable, Updatable{
    
    private Image gunImage;
    private ImageView gunImageView;
    
    GameElement bullet;
    GameElement bullet2;
    GameElement bullet3;
    
    public Shotgun(double xPos, double yPos, Color col) throws FileNotFoundException {
        super(xPos, yPos, col);
        
        FileInputStream input = new FileInputStream("D:\\NetBeansProjects\\Final Project\\images\\gun2.png");
        gunImage = new Image(input);
        gunImageView = new ImageView();
        gunImageView.setImage(gunImage);
    }
    
    @Override
    public void update() {
        
        //System.out.println(getClickedMouse());
        
        if(getClickedMouse() && getClickRelease() || getShootGun()){
            try {
                
                //System.out.println(Math.cos(Math.toRadians(theta)) +" "+Math.sin(Math.toRadians(theta)));
                
                double bulletX = 0;
                double bulletY = 0;
                
                //top left
                if(getTheta()<=-90 && getTheta()>=-180){
                    bulletX = getGunX() - 18*Math.sin(Math.toRadians(getTheta()));
                    bulletY = getGunY();
                }
                
                //bottom left
                if(getTheta()<=-180 && getTheta()>=-270){
                    bulletX = getGunX();
                    bulletY = getGunY() + 9*Math.sin(Math.toRadians(getTheta()));
                }
                
                //top right
                if(getTheta()>=-90 && getTheta()<=0){
                    bulletX = getGunX() + 15*Math.cos(Math.toRadians(getTheta()));
                    bulletY = getGunY();
                }
                
                //bottom right
                if(getTheta()>=0 && getTheta() <=90){
                    bulletX = getGunX() + 15*Math.cos(Math.toRadians(getTheta())) +9*Math.sin(Math.toRadians(getTheta()));
                    bulletY = getGunY() + 9*Math.sin(Math.toRadians(getTheta()));
                }
                
                bullet = new Bullet(bulletX, bulletY, Color.BLACK, getTheta());
                bullet2 = new Bullet(bulletX, bulletY, Color.BLACK, getTheta()+5);
                bullet3 = new Bullet(bulletX, bulletY, Color.BLACK, getTheta()-5);
                GameState.addAllMovables(bullet);
                GameState.addAllMovables(bullet2);
                GameState.addAllMovables(bullet3);
                setClickRelease(false);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Gun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public void draw(Canvas canvas, Color col) {
        
        super.draw(canvas, col);
        
        
    }
    
}
