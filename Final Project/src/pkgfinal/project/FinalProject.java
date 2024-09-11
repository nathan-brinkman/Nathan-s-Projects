/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javafx.scene.layout.HBox;

/**
 *
 * @author brinkman.nathaniel23
 */
public class FinalProject extends Application{
    
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static Canvas canvas;
    
    GameElement gunTarget;
    GameElement chasingTarget;
    GameElement gunTarget2;
    GameElement chasingTarget2;
    GameElement chasingTarget3;
    GameElement gunChasingTarget;
    GameElement gunChasingTarget2;
    GameElement chasingTarget4;
    GameElement chasingTarget5;
    GameElement target;
    GameElement player;
    
    //DECLARE a static GameState object here (used in the timer)
    RedrawTimer timer = new RedrawTimer();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        StackPane root = new StackPane();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        
        int playerXSpeed = 5;
        int playerYSpeed = 7;
        //int gravity = 5;
       
        
        player = new Player(500, 100, 40, 70, Color.RED, playerXSpeed, playerYSpeed);
        GameElement leftWall = new Wall(-50, -5000, 50, HEIGHT+5000, Color.DIMGREY);
        GameElement rightWall = new Wall(WIDTH, -5000, 50, HEIGHT+5000, Color.DIMGREY);
        GameElement bottomWall = new Wall(0, HEIGHT, WIDTH, 50, Color.DIMGREY);
        GameElement topWall = new Wall(-10000, -5000, 20000, 50, Color.DIMGREY);
        
        
        target = new Target(0, 0, 0, 0, Color.LIME);
        GameState.addAllNonMovables(target);
        
        int r = (int)(Math.random()*4);
        
        if(r==0){
            chasingTarget = new ChasingTarget(Math.random()*WIDTH, 0, 35, 35, Color.LIME, 5);
        }
        if(r==1){
            chasingTarget = new ChasingTarget(Math.random()*WIDTH, HEIGHT, 35, 35, Color.LIME, 5);
        }
        if(r==2){
            chasingTarget = new ChasingTarget(0, Math.random()*HEIGHT, 35, 35, Color.LIME, 5);
        }
        if(r==3){
            chasingTarget = new ChasingTarget(WIDTH, Math.random()*HEIGHT, 35, 35, Color.LIME, 5);
        }
        
        GameState.addAllMovables(chasingTarget);
        
        
        //level one
        ArrayList<Platform> levelOneList = new ArrayList<Platform>();
        levelOneList.add(new Platform(150, 900, 400, 30, Color.BLUE));
        levelOneList.add(new Platform(1370, 900, 400, 30, Color.BLUE));
        levelOneList.add(new Platform(930, 850, 30, 230, Color.BLUE));
        levelOneList.add(new Platform(660, 620, 600, 30, Color.BLUE));
        levelOneList.add(new Platform(250, 200, 200, 30, Color.BLUE));
        levelOneList.add(new Platform(1470, 200, 200, 30, Color.BLUE));
        
        //level two
        ArrayList<Platform> levelTwoList = new ArrayList<Platform>();
//        levelTwoList.add(new Platform(965, 520, 40, 100, Color.TAN));
//        levelTwoList.add(new Platform(940, 580, 90, 40, Color.TAN));
//        levelTwoList.add(new Platform(300, 300, 25, 170, Color.BLACK));
//        levelTwoList.add(new Platform(300, 300, 25, 170, Color.BLACK));
//        levelTwoList.add(new Platform(227.5, 372.5, 170, 25, Color.BLACK));
//        levelTwoList.add(new Platform(372.5, 372.5, 25, 97.5, Color.BLACK));
//        levelTwoList.add(new Platform(227.5, 445, 97.5, 25, Color.BLACK));
//        levelTwoList.add(new Platform(227.5, 300, 25, 97.5, Color.BLACK));
//        levelTwoList.add(new Platform(300, 300, 97.5, 25, Color.BLACK));
        
        //level three
        ArrayList<Platform> levelThreeList = new ArrayList<Platform>();
        levelThreeList.add(new Platform(400, 800, 1120, 30, Color.MAGENTA));
        levelThreeList.add(new Platform(400, 500, 30, 300, Color.MAGENTA));
        levelThreeList.add(new Platform(1490, 500, 30, 300, Color.MAGENTA));
        levelThreeList.add(new Platform(800, 200, 30, 200, Color.MAGENTA));
        levelThreeList.add(new Platform(1090, 200, 30, 200, Color.MAGENTA));
        
        //level four
        ArrayList<Platform> levelFourList = new ArrayList<Platform>();
        levelFourList.add(new Platform(660, 620, 600, 30, Color.ORANGE));
        
        //change number when add level
        int whichLevel = (int)(Math.random()*4) + 1;
        
        if(whichLevel==1){
            Levels levelOne = new Levels(levelOneList);
        }
        if(whichLevel==2){
            Levels levelTwo = new Levels(levelTwoList);
        }
        if(whichLevel==3){
            Levels levelThree = new Levels(levelThreeList);
        }
        if(whichLevel==4){
            Levels levelFour = new Levels(levelFourList);
        }
        
        GameState.addAllPlayers(player);
        GameState.addAllNonMovables(leftWall);
        GameState.addAllNonMovables(rightWall);
        GameState.addAllNonMovables(bottomWall);
        GameState.addAllNonMovables(topWall);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Final Project");
        primaryStage.show();
        timer.start();
        
        
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                ((Player)player).setMouseX(event.getX());
                ((Player)player).setMouseY(event.getY());
                
            }
        });
        
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                ((Player)player).setMouseX(event.getX());
                ((Player)player).setMouseY(event.getY());
                
            }
        });
        
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                //System.out.println(event.getX());
                
                if(event.isPrimaryButtonDown()){
                    ((Player)player).setClickedMouse(true);
                }
                else{
                    ((Player)player).setClickedMouse(false);
                }
            }
        });
        
        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Player)player).setClickedMouse(false); 
                ((Player)player).setClickRelease(true);
            }
        });
        
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.S) {
                    ((Player)player).setDownDirection("down");
                    ((Player)player).setDownVelocity(playerYSpeed);
                    
                }
                if (event.getCode() == KeyCode.A) {
                    ((Player)player).setLeftDirection("left");
                    ((Player)player).setLeftVelocity(-playerXSpeed);
                    
                } 
                if (event.getCode() == KeyCode.D) {
                    ((Player)player).setRightDirection("right");
                    ((Player)player).setRightVelocity(playerXSpeed);
                    
                }
                if (event.getCode() == KeyCode.SPACE) {
                    ((Player)player).setJump("jump");
                    ((Player)player).setGravityArrayCount(0);
                    ((Player)player).reduceJumpCount();
                    ((Player)player).setPressingJump();
                    ((Player)player).setStopHoldingJump(false);
                }
            }

        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {

                if (event.getCode() == KeyCode.S) {
                    ((Player)player).setDownDirection("none");
                    ((Player)player).setDownVelocity(0);
                    //((Player)player).setDownPress("none");
                }
                if (event.getCode() == KeyCode.A) {
                    ((Player)player).setLeftDirection("none");
                    ((Player)player).setLeftVelocity(0);
                    ((Player)player).setXArrayCountToZero();
                    //((Player)player).setLeftPress("none");
                } 
                if (event.getCode() == KeyCode.D) {
                    ((Player)player).setRightDirection("none");
                    ((Player)player).setRightVelocity(0);
                    ((Player)player).setXArrayCountToZero();
                    //((Player)player).setRightPress("none");
                }
                if (event.getCode() == KeyCode.SPACE) {
                    ((Player)player).setJump("none");
                    ((Player)player).setPressingJumpToZero();
                    ((Player)player).setStopHoldingJump(true);
                }
            }
        });
        
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        launch(args);
    }
    
    
    public class RedrawTimer extends AnimationTimer {
        
       int targetCount = 0;
       
       
        
        public void handle(long now) {
            
            
            
            targetCount = ((Target)target).getTargetCount();
            
            //laser
            if(targetCount==80 && ((Target)target).getLevelEight()){
                ((Player)player).upgradeToLaser();
            }
            
            //auto shotgun
            if(targetCount==60 && ((Target)target).getLevelSix()){
                try {
                    ((Player)player).upgradeToAutoShotgun();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //shotgun
            if(targetCount==40 && ((Target)target).getLevelFour()){
                try {
                    ((Player)player).upgradeToShotgun();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //assault rifle
            if(targetCount==20 && ((Target)target).getLevelTwo()){
                try {
                    ((Player)player).upgradeToAR();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
            
            //updates each target with player information
            ((ChasingTarget)chasingTarget).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
            
            if(((Target)target).getLevelOne()==false){
                ((ChasingTarget)chasingTarget2).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
            }
            
            if(((Target)target).getLevelTwo()==false){
                ((GunTarget)gunTarget).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
                ((GunTarget)gunTarget).setShootGun(false);
            }
            
            if(((Target)target).getLevelThree()==false){
                ((ChasingTarget)chasingTarget3).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
            }
            
            if(((Target)target).getLevelFour()==false){
                ((GunTarget)gunTarget2).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
                ((GunTarget)gunTarget2).setShootGun(false);
            }
            
            if(((Target)target).getLevelFive()==false){
                ((GunChasingTarget)gunChasingTarget).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
                ((GunChasingTarget)gunChasingTarget).setShootGun(false);
            }
            
            if(((Target)target).getLevelSix()==false){
                ((ChasingTarget)chasingTarget4).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
            }
            
            if(((Target)target).getLevelSeven()==false){
                ((GunChasingTarget)gunChasingTarget2).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
                ((GunChasingTarget)gunChasingTarget2).setShootGun(false);
            }
            
            if(((Target)target).getLevelEight()==false){
                ((ChasingTarget)chasingTarget5).setPlayerPosition(((Player)player).getX(), ((Player)player).getY());
            }
            
            
            
            //adds new target each milestone of 10
            if(((Target)target).getTargetCount()==10 && ((Target)target).getLevelOne()){
                try {
                    chasingTarget2 = new ChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.LIME, 5);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllMovables(chasingTarget2);
                ((Target)target).setLevelOne(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==20 && ((Target)target).getLevelTwo()){
                try {
                    gunTarget = new GunTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.YELLOW);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllNonMovables(gunTarget);
                ((Target)target).setLevelTwo(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==30 && ((Target)target).getLevelThree()){
                try {
                    chasingTarget3 = new ChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.LIME, 5);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllMovables(chasingTarget3);
                ((Target)target).setLevelThree(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==40 && ((Target)target).getLevelFour()){
                try {
                    gunTarget2 = new GunTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.YELLOW);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllNonMovables(gunTarget2);
                ((Target)target).setLevelFour(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==50 && ((Target)target).getLevelFive()){
                try {
                    gunChasingTarget = new GunChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.SALMON);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                //it doesn't work in movables for some reason
                GameState.addAllNonMovables(gunChasingTarget);
                ((Target)target).setLevelFive(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==60 && ((Target)target).getLevelSix()){
                try {
                    chasingTarget4 = new ChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.PURPLE, 8);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllMovables(chasingTarget4);
                ((Target)target).setLevelSix(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==70 && ((Target)target).getLevelSeven()){
                try {
                    gunChasingTarget2 = new GunChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.SALMON);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                //it doesn't work in movables for some reason
                GameState.addAllNonMovables(gunChasingTarget2);
                ((Target)target).setLevelSeven(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            if(((Target)target).getTargetCount()==80 && ((Target)target).getLevelEight()){
                try {
                    chasingTarget5 = new ChasingTarget(Math.random()*WIDTH, Math.random()*HEIGHT, 35, 35, Color.LIME, 5);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
                GameState.addAllMovables(chasingTarget5);
                ((Target)target).setLevelEight(false);
                ((Target)target).setTargetCount(targetCount);
            }
            
            GraphicsContext gc = canvas.getGraphicsContext2D();
            
            GameState.updateAll();
            
            GameState.collideAll();
            
            gc.setFill(Color.DIMGREY);
            gc.fillRect(0, 0, WIDTH, HEIGHT);
            
            GameState.drawAll(canvas);
            
         
            
        }
           
    }
    
}
