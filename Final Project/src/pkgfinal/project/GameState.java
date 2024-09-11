/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author brink
 */
public class GameState {
    private static ArrayList<Renderable> nonMovingRenderables = new ArrayList();
    private static ArrayList<Renderable> movingRenderables = new ArrayList();
    private static ArrayList<Renderable> playerRenderables = new ArrayList();
    
    private static ArrayList<Updatable> nonMovingUpdatables = new ArrayList();
    private static ArrayList<Updatable> movingUpdatables = new ArrayList();
    private static ArrayList<Updatable> playerUpdatables = new ArrayList();
    
    private static ArrayList<GameElement> nonMovingGameElements = new ArrayList();
    private static ArrayList<GameElement> movingGameElements = new ArrayList();
    private static ArrayList<GameElement> playerGameElements = new ArrayList();
    
    public static void addAllNonMovables(GameElement element){
        
        if(element instanceof Updatable){
            nonMovingUpdatables.add((Updatable)element);
        }
        
        if(element instanceof Renderable){
            nonMovingRenderables.add((Renderable)element);
        }
        
        nonMovingGameElements.add(element);
        
    }
    
    public static void addAllMovables(GameElement element){
        
        if(element instanceof Updatable){
            movingUpdatables.add((Updatable)element);
        }
        
        if(element instanceof Renderable){
            movingRenderables.add((Renderable)element);
        }
        
        //System.out.println(movingGameElements);
        
        movingGameElements.add(element); 
    }
    
    public static void addAllPlayers(GameElement element){
        
        if(element instanceof Updatable){
            playerUpdatables.add((Updatable)element);
        }
        
        if(element instanceof Renderable){
            playerRenderables.add((Renderable)element);
        }
        
        playerGameElements.add(element); 
    }
    
    
    public static void removeMovable(GameElement element){
        
        //System.out.println("remove");
        
        if(element instanceof Updatable){
            movingUpdatables.remove((Updatable)element);
        }
        
        if(element instanceof Renderable){
            movingRenderables.remove((Renderable)element);
        }
        
        movingGameElements.remove(element);
        
    }
    
    public static void removeNonMovable(GameElement element){
        
        if(element instanceof Updatable){
            nonMovingUpdatables.remove((Updatable)element);
        }
        
        if(element instanceof Renderable){
            nonMovingRenderables.remove((Renderable)element);
        }
        
        nonMovingGameElements.remove(element);
    }
    
//    public static int getGameElements(int i){
//        return gameElements.get(i).getX();
//    }
    
    public static void addNonMovingRenderables(Renderable r){
        nonMovingRenderables.add(r);
    }
    
    public static void addMovingRenderables(Renderable r){
        movingRenderables.add(r);
    }
    
    public static void addPlayerRenderables(Renderable r){
        playerRenderables.add(r);
    }
    
    public static void addNonMovingUpdatables(Updatable u){
        nonMovingUpdatables.add(u);
    }
    
    public static void addMovingUpdatables(Updatable u){
        movingUpdatables.add(u);
    }
    
    public static void playerUpdatables(Updatable u){
        playerUpdatables.add(u);
    }
    
    public static void addNonMovingGameElements(GameElement element){
        nonMovingGameElements.add(element);
    }
    
    public static void addMovingGameElements(GameElement element){
        movingGameElements.add(element);
    }
    
    public static void addPlayerGameElements(GameElement element){
        playerGameElements.add(element);
    }
    
    public static void drawAll(Canvas canvas){
        for(int i=0; i<nonMovingGameElements.size(); i++){
            nonMovingRenderables.get(i).draw(canvas, nonMovingGameElements.get(i).getColor());
        }
        for(int i=0; i<playerGameElements.size(); i++){
            playerRenderables.get(i).draw(canvas, playerGameElements.get(i).getColor());
        }
        for(int i=0; i<movingGameElements.size(); i++){
            movingRenderables.get(i).draw(canvas, movingGameElements.get(i).getColor());
        }
    }
    
    public static void updateAll(){
        
        for(int i=0; i<nonMovingUpdatables.size(); i++){
            nonMovingUpdatables.get(i).update();
        }
        
        for(int i=0; i<movingUpdatables.size(); i++){
            movingUpdatables.get(i).update();
        }
        
        for(int i=0; i<playerUpdatables.size(); i++){
            playerUpdatables.get(i).update();
        }
    }
    
    public static void collideAll(){
        
        //System.out.println("collide all");
        
        //check collision for players
        for(int i=0; i<playerGameElements.size(); i++){
            
            //checks collision with non movable objects
            for(int j=0; j<nonMovingGameElements.size(); j++){
                //System.out.println(((Player)playerGameElements.get(i)).getJumpCount());
                if(playerGameElements.get(i).checkCollision(nonMovingGameElements.get(j))){
                    playerGameElements.get(i).onCollision(nonMovingGameElements.get(j));
                }
                
            }
            if(playerGameElements.get(i).isColliding(nonMovingGameElements)==false && ((Player)playerGameElements.get(i)).getJumpCount()>1){
                //System.out.println("jump");
                ((Player)playerGameElements.get(i)).setJumpCount(0);
            }
            
            //checks collision with movable objects
            for(int j=0; j<movingGameElements.size(); j++){
                if(playerGameElements.get(i).checkCollision(movingGameElements.get(j))){
                    if(!(movingGameElements.get(j) instanceof Gun)){
                        playerGameElements.get(i).onCollision(movingGameElements.get(j));
                        //System.out.println("collision with movable object");
                    }
                }
                
            }
            
            //checks collision with other players
            for(int j=0; j<playerGameElements.size(); j++){
                if(playerGameElements.get(i).checkCollision(playerGameElements.get(j))){
                    if(i!=j){
                        playerGameElements.get(i).onCollision(playerGameElements.get(j));
                    }
                }
                
            }
        }
        
        //ceck collision for non moving objects
        for(int i=0; i<nonMovingGameElements.size(); i++){
            
            //checks collision with movable objects
            for(int j=0; j<movingGameElements.size(); j++){
                if(nonMovingGameElements.get(i).checkCollision(movingGameElements.get(j))){
                    nonMovingGameElements.get(i).onCollision(movingGameElements.get(j));
                }
                
            }
            
            //checks collision with other players
            for(int j=0; j<playerGameElements.size(); j++){
                try{
                    if(nonMovingGameElements.get(i).checkCollision(playerGameElements.get(j))){
                        nonMovingGameElements.get(i).onCollision(playerGameElements.get(j));

                    }
                }
                catch(Exception e){
                    //System.out.println("bruh");
                }
                
            }
        }
        
        
        //check collision for moving objects
        for(int i=0; i<movingGameElements.size(); i++){
            
            //checks collision with non movable objects
            for(int j=0; j<nonMovingGameElements.size(); j++){
                try{
                    if(movingGameElements.get(i).checkCollision(nonMovingGameElements.get(j))){
                        movingGameElements.get(i).onCollision(nonMovingGameElements.get(j));
                    }
                }
                catch(Exception e){
                    //System.out.println("bruh");
                }
                
            }
            
            //checks collision with movable objects
            for(int j=0; j<movingGameElements.size(); j++){
                try{
                    if(i!=j){
                        if(movingGameElements.get(i).checkCollision(movingGameElements.get(j))){
                            movingGameElements.get(i).onCollision(movingGameElements.get(j));
                        }
                    }
                }catch(Exception e){
                    //System.out.println("bruh");
                }
            }
            
            //checks collision with other players
            for(int j=0; j<playerGameElements.size(); j++){
                try{
                    if(movingGameElements.get(i).checkCollision(playerGameElements.get(j))){
                        movingGameElements.get(i).onCollision(playerGameElements.get(j));

                    }
                }catch(Exception e){
                    //System.out.println("bruh");
                }
                
            }
        }
        
        
    }
}
