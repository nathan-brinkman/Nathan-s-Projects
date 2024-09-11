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
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public class AssaultRifle extends Gun implements Renderable, Updatable{
    
    private Image gunImage;
    private ImageView gunImageView;
    
    GameElement bullet;
    
    int bulletCount=0;
    
    public AssaultRifle(double xPos, double yPos, Color col) throws FileNotFoundException {
        super(xPos, yPos, col);
        
        FileInputStream input = new FileInputStream("D:\\NetBeansProjects\\Final Project\\images\\gun2.png");
        gunImage = new Image(input);
        gunImageView = new ImageView();
        gunImageView.setImage(gunImage);
        
    }
    
    
    @Override
    public void update() {
        
        //System.out.println(getClickedMouse());
        bulletCount++;
        
        if(getClickedMouse() && bulletCount%3==0){
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
                GameState.addAllMovables(bullet);
                
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
