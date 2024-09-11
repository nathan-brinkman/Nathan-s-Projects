/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 *
 * @author brink
 */
public class Levels extends GameElement implements Renderable{
    
    private ArrayList<Platform> level;
    
    public Levels(){
        
    }
    
    public Levels(ArrayList<Platform> p){
        level = p;
        
        for(int i=0; i<level.size(); i++){
            GameState.addAllNonMovables(level.get(i));
        }
        
    }
    
 
    @Override
    public void onCollision(GameElement element) {
        
    }

    @Override
    public void draw(Canvas canvas, Color col) {
        
        for(int i=0; i<level.size(); i++){
            level.get(i).draw(canvas, col);
        }
        
    }
    
}
