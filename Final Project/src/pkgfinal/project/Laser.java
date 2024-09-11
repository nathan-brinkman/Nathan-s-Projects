/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author brink
 */
public class Laser extends Bullet{
    
    private Image laserImage;
    private ImageView laserImageView;
    
    private double theta;
    
    public Laser(double xPos, double yPos, Color c, double angle) throws FileNotFoundException {
        super(xPos, yPos, c, angle);
        
        theta = angle;
        
        FileInputStream input = new FileInputStream("D:\\NetBeansProjects\\Final Project\\images\\laser.png");
        laserImage = new Image(input);
        laserImageView = new ImageView();
        laserImageView.setImage(laserImage);
    }
    
    public void onCollision(GameElement element){
        
        //System.out.println("bullet collide");
        
        if(getX()<=element.getX()+element.getWidth() && getX()+getWidth()>=element.getX() && getY()<=element.getY()+element.getHeight() && getY()+getHeight()>=element.getY() && !(element instanceof Bullet) && !(element instanceof Gun) && !(element instanceof Platform)){
            GameState.removeMovable(this);
        }
        
    }
    
    @Override
    public void update() {
        
        super.update();
        
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        
        laserImageView.setRotate(theta);
        laserImageView.setX(getX());
        laserImageView.setY(getY());
        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        
        laserImage = laserImageView.snapshot(params, null);
        
        canvas.getGraphicsContext2D().drawImage(laserImage, getX(), getY());
        
        
    }
    
}
