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
 * @author brinkman.nathaniel23
 */
public class Wall extends GameElement implements Renderable{
    public Wall(){
        
    }
    
    public Wall(int xPos, int yPos, int w, int h, Color col){
        super.setX(xPos);
        super.setY(yPos);
        super.setWidth(w);
        super.setHeight(h);
    }
    
    @Override
    public void onCollision(GameElement element){
        //System.out.println("wall collision");
    }
    
    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(super.getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 
    }
}
