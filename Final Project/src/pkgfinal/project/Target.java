/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.awt.Font;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author brink
 */
public class Target extends GameElement implements Renderable, Updatable{
    
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    
    private static int targetCount;
    
    private double playerX;
    private double playerY;
    private double playerWidth;
    private double playerHeight;
    
    private boolean levelOne;
    private boolean levelTwo;
    private boolean levelThree;
    private boolean levelFour;
    private boolean levelFive;
    private boolean levelSix;
    private boolean levelSeven;
    private boolean levelEight;
    
    public Target(double xPos, double yPos, int w, int h, Color c) throws FileNotFoundException{
        super(xPos, yPos, w, h, c);
        
        targetCount = 0;
       
        playerX = 0;
        playerY = 0;
        
        playerWidth=0;
        playerHeight=0;
        
        levelOne=true;
        levelTwo=true;
        levelThree=true;
        levelFour=true;
        levelFive=true;
        levelSix = true;
        levelSeven=true;
        levelEight=true;
    }
    
    public void setPlayerPosition(double x, double y){
        playerX = x;
        playerY = y;
    }
    
    public double getPlayerX(){
        return playerX;
    }
    
    public double getPlayerY(){
        return playerY;
    }
    
    public void setPlayerDimensions(double w, double h){
        playerWidth = w;
        playerHeight = h;
    }
    
    public double getPlayerWidth(){
        return playerWidth;
    }
    
    public double getPlayerHeight(){
        return playerHeight;
    }
    
    public int getTargetCount(){
        return targetCount;
    }
    
    public void setLevelOne(boolean b){
        levelOne = b;
    }
    
    public boolean getLevelOne(){
        return levelOne;
    }
    
    public void setLevelTwo(boolean b){
        levelTwo = b;
    }
    
    public boolean getLevelTwo(){
        return levelTwo;
    }
    
    public void setLevelThree(boolean b){
        levelThree = b;
    }
    
    public boolean getLevelThree(){
        return levelThree;
    }
    
    public void setLevelFour(boolean b){
        levelFour = b;
    }
    
    public boolean getLevelFour(){
        return levelFour;
    }
    
    public void setLevelFive(boolean b){
        levelFive = b;
    }
    
    public boolean getLevelFive(){
        return levelFive;
    }
    
    public void setLevelSix(boolean b){
        levelSix = b;
    }
    
    public boolean getLevelSix(){
        return levelSix;
    }
    
    public void setLevelSeven(boolean b){
        levelSeven = b;
    }
    
    public boolean getLevelSeven(){
        return levelSeven;
    }
    
    public void setLevelEight(boolean b){
        levelEight = b;
    }
    
    public boolean getLevelEight(){
        return levelEight;
    }
    
    public void increaseTargetCount(){
        targetCount++;
    }
    
    public void setTargetCount(int c){
        targetCount = c;
    }
    
    

    @Override
    public void onCollision(GameElement element) {
        
    }
    

    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.strokeText(targetCount+"", WIDTH/2, 50);
    }

    @Override
    public void update() {
        
         
    }
}
