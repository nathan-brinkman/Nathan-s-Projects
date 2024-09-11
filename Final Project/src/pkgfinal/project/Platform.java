/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author brink
 */
public class Platform extends GameElement implements Renderable{
    public Platform(){
        
    }
    
    public Platform(double xPos, double yPos, double w, double h, Color c){
        super(xPos, yPos, w, h, c);
    }
    
    @Override
    public void onCollision(GameElement element){
        //System.out.println("platform collision");
    }
    
    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(super.getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 
    }
}
