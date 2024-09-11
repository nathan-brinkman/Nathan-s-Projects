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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public class Bullet extends GameElement implements Renderable, Updatable{
    
    private Image bulletImage;
    private ImageView bulletImageView;
    
    private double theta;
    
    
    public Bullet(double xPos, double yPos, Color c, double angle) throws FileNotFoundException{
        super(xPos, yPos, 15, 8, c);
        
        theta = angle;
        
        FileInputStream input = new FileInputStream("D:\\NetBeansProjects\\Final Project\\images\\bullet.png");
        bulletImage = new Image(input);
        bulletImageView = new ImageView();
        bulletImageView.setImage(bulletImage);
    
    }
    

    
    public void onCollision(GameElement element){
        
        //System.out.println("bullet collide");
        
        if(getX()<=element.getX()+element.getWidth() && getX()+getWidth()>=element.getX() && getY()<=element.getY()+element.getHeight() && getY()+getHeight()>=element.getY() && !(element instanceof Bullet) && !(element instanceof Gun)){
            GameState.removeMovable(this);
        }
        
    }
    
    @Override
    public void update() {
        
        super.setX(getX() + 20*Math.cos(Math.toRadians(theta)));
        super.setY(getY() + 20*Math.sin(Math.toRadians(theta)));
        
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        
        bulletImageView.setRotate(theta);
        bulletImageView.setX(getX());
        bulletImageView.setY(getY());
        
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        
        bulletImage = bulletImageView.snapshot(params, null);
        
        canvas.getGraphicsContext2D().drawImage(bulletImage, getX(), getY());
        
        
    }
}
