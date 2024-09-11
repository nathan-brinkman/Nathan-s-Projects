/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class Gun extends GameElement implements Renderable, Updatable{
    
    private Image gunImage;
    private ImageView gunImageView;
    private double theta;
    private double gunX;
    private double gunY;
    
    GameElement bullet;
    
    private boolean clickedMouse;
    private boolean clickRelease;
    
    private boolean shootGun;
    
    private boolean laser;
    
    public void onCollision(GameElement element){
        
    }
    
    @Override
    public void update() {
        
        //System.out.println(clickedMouse);
        
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        
        //System.out.println(50*Math.cos(Math.toRadians(theta)));
        
        //draws gun on the left
        if(theta>90 || theta<-90){
            
            gunImageView.setRotate(-theta);
            gunX = super.getX() + 50*Math.cos(Math.toRadians(theta));
            gunY = super.getY() + 50*Math.sin(Math.toRadians(theta));
            gunImageView.setX(gunX);
            gunImageView.setY(gunY);
        
        
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
        
            gunImage = gunImageView.snapshot(params, null);
            
            canvas.getGraphicsContext2D().drawImage(gunImage, getX() + 50*Math.cos(Math.toRadians(theta)), getY() + 50*Math.sin(Math.toRadians(theta)) + gunImage.getHeight(), gunImage.getWidth(), -gunImage.getHeight());
            
        }
        
        //draws gun on the right
        else{
            
            gunImageView.setRotate(theta);
            gunX = super.getX() + 50*Math.cos(Math.toRadians(theta));
            gunY = super.getY() + 50*Math.sin(Math.toRadians(theta));
            gunImageView.setX(gunX);
            gunImageView.setY(gunY);
        
        
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
        
            gunImage = gunImageView.snapshot(params, null);
            
            canvas.getGraphicsContext2D().drawImage(gunImage, getX() + 50*Math.cos(Math.toRadians(theta)), getY() + 50*Math.sin(Math.toRadians(theta)));
        }
        
    }
    
    public ImageView getImageView(){
        
        return gunImageView;
    }
    
    public void setAngle(double angle){
        theta = -angle;
    }
    
    public void setClickedMouse(boolean m){
        //System.out.println(m);
        clickedMouse = m;
    }
    
    public void setClickRelease(boolean cr){
        clickRelease = cr;
    }
    
    public void setShootGun(boolean s){
        shootGun = s;
    }
    
    public double getGunX(){
        return gunX;
    }
    
    public double getGunY(){
        return gunY;
    }
    
    public double getTheta(){
        return theta;
    }
    
    public void setGunX(double x){
        gunX = x;
    }
    
    public void setGunY(double y){
        gunY = y;
    }
    
    public boolean getClickedMouse(){
        return clickedMouse;
    }
    
    public boolean getClickRelease(){
        return clickRelease;
    }
    
    public boolean getShootGun(){
        return shootGun;
    }
    
    public void setLaser(boolean l){
        laser = l;
    }
    
    public boolean getLaser(){
        return laser;
    }
    
    
//    public static void main(String[] args) throws IOException {
//        // TODO code application logic here
//        Gun gunImage = new Gun();
//        launch(args);
//    }
    
    public Gun(double xPos, double yPos, Color col) throws FileNotFoundException{
        
        super(xPos, yPos, 32, 26, col);
        
        FileInputStream input = new FileInputStream("D:\\NetBeansProjects\\Final Project\\images\\gun2.png");
        gunImage = new Image(input);
        gunImageView = new ImageView();
        gunImageView.setImage(gunImage);
        
        gunX = xPos;
        gunY = yPos;
        
        clickedMouse = false;
        clickRelease = true;
        
        shootGun = false;
        
        laser = false;
        
    }
}
