/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public abstract class GameElement{
    private double x;
    private double y;
    private double width;
    private double height;
    private Color col;
    private int gravity=5;

    public GameElement(){
        x=0;
        y=0;
        width=0;
        height=0;
        col=Color.BLACK;
    }
    
    public GameElement(double x, double y, double width, double height, Color col) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.col = col;
    }
    
    
    
    public void setX(double xPos){
        x = xPos;
    }
    
    public void setY(double yPos){
        y = yPos;
    }
    
    public void setWidth(double w){
        width = w;
    }
    
    public void setHeight(double h){
        height = h;
    }
    
    public void setColor(Color c){
        col = c;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public Color getColor(){
        return col;
    }
    
    public int getGravity(){
        return gravity;
    }
    
    public boolean checkCollision(GameElement element){
        if(x<element.getX()+element.getWidth() && x+width>element.getX() && y<element.getY()+element.getHeight() && y+height>element.getY()){
            //System.out.println(x+" "+y+" "+width+" "+height);
            //System.out.println("check collision");
            return true;
        }
        return false;
    }
    
    public boolean isColliding(ArrayList elementArray){
        //System.out.println("isCollision");
        for(int i=0; i < elementArray.size(); i++){
            if(x<((GameElement)elementArray.get(i)).getX()+((GameElement)elementArray.get(i)).getWidth() && x+width>=((GameElement)elementArray.get(i)).getX()
            && y<((GameElement)elementArray.get(i)).getY()+((GameElement)elementArray.get(i)).getHeight() && y+height>=((GameElement)elementArray.get(i)).getY()){
                //System.out.println("is Colliding");
                return true;
                
            }
        }
        //System.out.println("is not Colliding");
        return false;
    }
    
    public abstract void onCollision(GameElement element);
}
