/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author brinkman.nathaniel23
 */
public class Player extends GameElement implements Renderable,Updatable{
    
    //changes speed of player
    private int x = 5;
    
    private int xVelocity;
    private int yVelocity;
    
    private int leftVelocity;
    private int rightVelocity;
    private int upVelocity;
    private int downVelocity;
    
    private String upDirection;
    private String downDirection;
    private String leftDirection;
    private String rightDirection;
    
    private String jump;
    private static int jumpArray[] = {20,20,20,20,20,20,20,20,15,15,15,15,15,15,15,10,10,10,10,10,10,5,5,5,5,5,5,5,5,5,5,5,5,1,1,1,1,1,1,1,1,0};
    private int jumpArrayCount;
    private int jumpCount;
    private boolean stopHoldingJump;
    private int pressingJump;
    private boolean firstJump;
    
    private static int gravityArray[] = {0,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,
    5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,9,9,9,9,10,10,10,12};
    private int gravityArrayCount;
    private int gravity;
    
    public static int xArray[] = {1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,4,4,4,4,5};
    private int xArrayCount;
    
    GameElement gun;
    
    double mouseX;
    double mouseY;
    
    public Player(){
        
    }
    
    public Player(double xPos, double yPos, double w, double h, Color c, int xSpeed, int ySpeed) throws FileNotFoundException{
        super(xPos, yPos, w, h, c);
        
        gun = new Pistol(xPos, yPos, Color.BLACK);
        GameState.addAllMovables(gun);
        
        xVelocity = xSpeed;
        yVelocity = ySpeed;
        
        upVelocity=0;
        downVelocity=0;
        leftVelocity=0;
        rightVelocity=0;
        
        upDirection = "none";
        downDirection = "none";
        leftDirection = "none";
        rightDirection = "none";
        
        jump = "none";
        jumpArrayCount = 0;
        jumpCount = 2;
        stopHoldingJump = true;
        pressingJump=0;
        firstJump = false;
        
        gravityArrayCount=0;
        gravity=0;
        
        xArrayCount=0;
        
        for(int i=0; i<jumpArray.length; i++){
            jumpArray[i] = jumpArray[i]+x;
        }
        for(int i=0; i<gravityArray.length; i++){
            gravityArray[i] = gravityArray[i]+x;
        }
        for(int i=0; i<xArray.length; i++){
            xArray[i] = xArray[i]+x;
        }
        
        mouseX=0;
        mouseY=0;
        
        
    }
    
    public void setXVelocity(int xSpeed){
        xVelocity = xSpeed;
    }
    
    public void setYVelocity(int ySpeed){
        yVelocity = ySpeed;
    }
    
    public void setUpDirection(String up){
        upDirection = up;
    }
    
    public void setDownDirection(String down){
        downDirection = down;
    }
    
    public void setLeftDirection(String left){
        leftDirection = left;
    }
    
    public void setRightDirection(String right){
        rightDirection = right;
    }
    
    public void setXArrayCountToZero(){
        xArrayCount=0;
    }
    
    //tells if the player has hit the jump button
    public void setJump(String j){
        jump = j;
    }
    
    //sets the index for the jump array
    public void setJumpArrayCount(){
        jumpArrayCount = 0;
    }
    
    public void setLeftVelocity(int left){
        leftVelocity = left;
    }
    
    public void setRightVelocity(int right){
        rightVelocity = right;
    }
    
    public void setUpVelocity(int up){
        upVelocity = up;
    }
    
    public void setDownVelocity(int down){
        downVelocity = down;
    }
    
    public void setGravityArrayCount(int count){
        if(stopHoldingJump && jumpCount>-1){
            gravityArrayCount = count;
        }
    }
    
    //reduces the jump count of the player only if the player has stopped holding down the spacebar
    public void reduceJumpCount(){
        if(stopHoldingJump){
            if(firstJump){
                jumpCount = jumpCount-2;
                firstJump = false;
            }
            else{
                jumpCount--;
            }
        }
    }
    
    public int getGravity(){
        return gravityArray[gravityArrayCount];
    }
    
    //sets count for how many jumps the player can do
    public void setJumpCount(int count){
        jumpCount = count;
    }
    
    //checks if the player has stopped holding the jump button (spacebar)
    public void setStopHoldingJump(boolean holdingJump){
        stopHoldingJump = holdingJump;
    }
    
    public void setPressingJump(){
        if(stopHoldingJump){
            pressingJump=1;
        }
    }
    
    public void setPressingJumpToZero(){
        pressingJump=0;
    }
    
    //gets the amount of jumps the player has
    public int getJumpCount(){
        return jumpCount;
    }
    
    public void setMouseX(double x){
        mouseX = x;
        //System.out.println("x " +x);
    }
    
    public void setMouseY(double y){
        mouseY = y;
        //System.out.println("y " +y);
    }
    
    public double rotate(){
        
        double theta = 0;
     
        double xDiff = (gun.getX()+gun.getWidth()/2)-mouseX;
        double yDiff = ((gun.getY()+gun.getHeight()/2)-15)-mouseY;
        
        theta = Math.toDegrees(Math.atan2(xDiff, yDiff)) + 90;
        //System.out.println(theta);
        
        return theta;
    }
    
    public ImageView getImageView(){
        return ((Gun)gun).getImageView();
    }
    
    public void setClickedMouse(boolean clickedMouse){
        //System.out.println(clickedMouse);
        ((Gun)gun).setClickedMouse(clickedMouse);
    }
    
    public void setClickRelease(boolean cr){
        ((Gun)gun).setClickRelease(cr);
    }
    
    public void upgradeToAR() throws FileNotFoundException{
        GameState.removeMovable(gun);
        gun = new AssaultRifle(getX(), getY(), Color.BLACK);
        GameState.addAllMovables(gun);
    }
    
    public void upgradeToShotgun() throws FileNotFoundException{
        GameState.removeMovable(gun);
        gun = new Shotgun(getX(), getY(), Color.BLACK);
        GameState.addAllMovables(gun);
    }
    
    public void upgradeToAutoShotgun() throws FileNotFoundException{
        GameState.removeMovable(gun);
        gun = new AutoShotgun(getX(), getY(), Color.BLACK);
        GameState.addAllMovables(gun);
    }
    
    public void upgradeToLaser(){
        ((Gun)gun).setLaser(true);
    }
    
    
    public void onCollision(GameElement element){
        
        //System.out.println("player collision");
        int xOffset = 10;
        int yOffset = 21;
        
        if(super.getX() + xOffset > element.getX() + element.getWidth() && leftDirection.equals("left")){
            if((Math.abs(super.getY() - element.getY() - element.getHeight())<=2 && Math.abs(super.getY() - element.getY() - element.getHeight())>=0)
            || (Math.abs(super.getY() + super.getHeight() - element.getY())<=2 && Math.abs(super.getY() + super.getHeight() - element.getY())>=0)){
 
            }
            else{
                super.setX(element.getX()+element.getWidth());
            }
            xArrayCount=0;
            //System.out.println("left");
        }
        else if(super.getX() + super.getWidth() - xOffset < element.getX() && rightDirection.equals("right")){
            if((Math.abs(super.getY() - element.getY() - element.getHeight())<=2 && Math.abs(super.getY() - element.getY() - element.getHeight())>=0)
            || (Math.abs(super.getY() + super.getHeight() - element.getY())<=2 && Math.abs(super.getY() + super.getHeight() - element.getY())>=0)){
 
            }
            else{
                super.setX(element.getX()-super.getWidth());
            }
            xArrayCount=0;
            //System.out.println("right");
        }
          
        if(super.getY() + yOffset > element.getY() + element.getHeight()){
            super.setY(element.getY()+element.getHeight());
        }
        else if(super.getY() + super.getHeight() - yOffset < element.getY()){
            super.setY(element.getY()-super.getHeight());
            gravityArrayCount=0;
            jumpCount = 2;
            downVelocity=0;
            firstJump=true;
            //System.out.println("bottom");
        }
        
        if(element instanceof Bullet || element instanceof Target){
            System.exit(0);
            setX(30);
            setY(-30);
        }
        

        
    }
    
    @Override
    public void update() {
        
        gravity = gravityArray[gravityArrayCount];
        gravityArrayCount++;
        if(gravityArrayCount>gravityArray.length-1){
            gravityArrayCount=gravityArray.length-1;
        }
        
        if(xArrayCount>xArray.length-1){
            xArrayCount=xArray.length-1;
        }
        if(leftDirection.equals("left") && rightDirection.equals("right")){
            xArrayCount=0;
        }
        
        //System.out.println(xArrayCount);
        
        //System.out.println(gravityArray[gravityArrayCount]);
        
        if(leftDirection.equals("left") && rightDirection.equals("none")){
            super.setX(super.getX()-xArray[xArrayCount]);
            xArrayCount++;
        }

        
        if(rightDirection.equals("right") && leftDirection.equals("none")){
            super.setX(super.getX()+xArray[xArrayCount]);
            xArrayCount++;
        }

        
        if(upDirection.equals("up") && downDirection.equals("none")){
            super.setY(super.getY()+upVelocity);
        }

        
        if(downDirection.equals("down") && upDirection.equals("none")){
            downVelocity = yVelocity;
        }
        
//        System.out.println("gravityArrayCount: " +gravityArrayCount);
//        System.out.println("jumpCount: " +jumpCount);
//        System.out.println("jump: " +jump);
//        System.out.println("jumpArrayCount: "+jumpArrayCount);
//        System.out.println("pressingJump: "+pressingJump);
//        System.out.println(stopHoldingJump+"\n");
        if(jumpCount>-2 && jump.equals("jump") && (stopHoldingJump || (pressingJump==1 && jumpArrayCount<jumpArray.length-1))){
            jumpArrayCount=0;
            //System.out.println("hi");
        }
        
        //System.out.println(stopHoldingJump);
        //System.out.println("jumpArrayCount: "+jumpArrayCount);
        //System.out.println("jump: " +jump);
        if((jump.equals("jump") || jumpArrayCount>0) && jumpArrayCount < jumpArray.length){
            super.setY(super.getY()-jumpArray[jumpArrayCount]);
            jumpArrayCount++;
        }
        else if(jumpArrayCount>=jumpArray.length && stopHoldingJump && jumpCount>-1){
            jumpArrayCount=0;
        }
        
        pressingJump=0;
        super.setY(super.getY()+downVelocity+gravity);
        
        //player y + 1/2 player height - 1/2 gun height
        gun.setY(super.getY()+35-15);
        gun.setX(super.getX()+20-19);
        ((Gun)gun).setAngle(rotate());
        
        
    }
    
    @Override
    public void draw(Canvas canvas, Color col) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(super.getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight()); 
    }
    
    
}
