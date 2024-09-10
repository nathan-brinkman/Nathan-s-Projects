#include "MovingObject.h"
#include "Enemy.h"
#include "Gun.h"
#include "Bullet.h"

#include <SFML/Graphics.hpp>
using namespace sf;

#include <iostream>
using namespace std;

#include <vector>
#include <cstdlib>
#include <ctime>
#include <string>
#include <fstream>

int main() {

    //size of sfml window
    const unsigned int WIN_X = 1600;
    const unsigned int WIN_Y = 900;

    srand(time(0));
    rand();
    rand();

    //output file
    ofstream outputFile("score.txt");
    if(outputFile.fail()){
        cerr << "error creating output file" << endl;
        return -1;
    }
    bool writeToFile = 0;

    //ground
    int ground_height = 50;
    RectangleShape ground;
    ground.setSize(Vector2f(WIN_X, ground_height));
    ground.setPosition(0, WIN_Y-ground_height);
    ground.setFillColor(Color(5,120,60));

    //turret
    int turret_radius = 100;
    CircleShape turret;
    turret.setPosition(WIN_X/2 - turret_radius, WIN_Y - turret_radius - ground_height);
    turret.setRadius(turret_radius);
    turret.setFillColor(Color(115,115,120));

    //vector of enemies
    vector<MovingObject*> enemies;
    int enemy_width = 50;
    int enemy_height = 25;
    double min_speed = .07;
    double max_speed = .11;
    for(int i=0; i<5; i++){
        double speed = min_speed + (max_speed - min_speed) * (rand()%RAND_MAX) / RAND_MAX;
        enemies.push_back(new Enemy(rand()%(WIN_X-enemy_width+1), -enemy_height - rand()%400, enemy_width, enemy_height, Color::Red, speed));
        //cout << speed << " ";
    }

    //gun
    int gun_width = 24;
    int gun_height = 186;
    MovingObject* gun = new Gun(WIN_X/2 - gun_width/2, WIN_Y-turret_radius-ground_height-gun_height, gun_width, gun_height, Color(60,60,60), 0);

    //vector of bullets
    vector<MovingObject*> bullets;

    //end game
    Font myFont;
    if( !myFont.loadFromFile( "arial.ttf" ) ){
        return -1;
    }
    Text endGame;
    endGame.setFont( myFont );
    endGame.setString( "You Lost!" );
    int characterSize = 250;
    endGame.setCharacterSize(characterSize);
    endGame.setPosition( WIN_X/2 - 530, WIN_Y/2 - characterSize );
    endGame.setFillColor( Color::Red );
    bool lost = 0;

    //score
    int score = 0;
    bool addEnemy = 0;
    Text scoreText;
    scoreText.setFont(myFont);
    scoreText.setString("Score: " + to_string(score));
    scoreText.setCharacterSize(30);
    scoreText.setPosition(WIN_X - 200, 50);
    scoreText.setFillColor(Color::White);

    RenderWindow window(VideoMode(WIN_X, WIN_Y), "Final Project");

    Event event;

    Vector2i mousePosition;
    
    while(window.isOpen()){
        
        window.clear();

        //draw here

        //bullet
        for(unsigned int i=0; i<bullets.size(); i++){
            static_cast<Bullet*>(bullets[i])->draw(window);
            static_cast<Bullet*>(bullets[i])->updatePosition();

            //if bullet goes out of window, erase bullet
            if(bullets[i]->getX() < 0 || bullets[i]->getX() > WIN_X){
                bullets.erase(bullets.begin() + i);
            }
            if(bullets[i]->getY() < 0 || bullets[i]->getY() > WIN_Y){
                bullets.erase(bullets.begin() + i);
            }

            //if bullet collides with enemy, erase enemy and add a new one to vector of enemies
            for(unsigned int j=0; j<enemies.size(); j++){
                if(static_cast<Bullet*>(bullets[i])->collide(enemies[j])){
                    enemies.erase(enemies.begin()+j);
                    double speed = min_speed + (max_speed - min_speed) * (rand()%RAND_MAX) / RAND_MAX;
                    enemies.push_back(new Enemy(rand()%(WIN_X-enemy_width+1), -enemy_height - rand()%400, enemy_width, enemy_height, Color::Red, speed));
                    score = score + 10;
                    addEnemy = 1;
                }
            }
        }

        //gun
        static_cast<Gun*>(gun)->setAngle(mousePosition.x, mousePosition.y, window.getSize().x, window.getSize().y);
        static_cast<Gun*>(gun)->draw(window);

        //turret
        window.draw(turret);

        //ground
        window.draw(ground);

        //enemies
        for(unsigned int i=0; i<enemies.size(); i++){
            static_cast<Enemy*>(enemies[i])->draw(window);
            static_cast<Enemy*>(enemies[i])->updatePosition();
            
            //if an enemy reaches the gound, game is lost
            if(static_cast<Enemy*>(enemies[i])->hitGround()){
                lost = 1;
                writeToFile = 1;
                enemies.clear();
            }
        }

        //every 5 enemies killed add an aditional enemy
        if(score % 50 == 0 && addEnemy){
            double speed = min_speed + (max_speed - min_speed) * (rand()%RAND_MAX) / RAND_MAX;
            enemies.push_back(new Enemy(rand()%(WIN_X-enemy_width+1), -enemy_height - rand()%400, enemy_width, enemy_height, Color::Red, speed));
            addEnemy = 0;
        }

        //endgame
        if(lost){
            window.draw(endGame);

            //writes final score to output file
            if(writeToFile){
                outputFile << "Final Score: " + to_string(score);
                outputFile.close();
                writeToFile = 0;
            }
        }

        //score text
        scoreText.setString("Score: " + to_string(score));
        window.draw(scoreText);

        window.display();

        mousePosition = Mouse::getPosition(window);

        //event handling
        while(window.pollEvent(event)){

            if(event.type == Event::Closed) {
                window.close();
            }

            if(event.type == Event::KeyPressed){
                switch(event.key.code){
                    case Keyboard::Q:
                        window.close();
                        break;
                    case Keyboard::Escape:
                        window.close();
                        break;
                    default:
                        break;
                }
            }

            if(event.type == Event::MouseButtonPressed){
                int bullet_width = 16;
                int bullet_height = 24;
                double bullet_speed = 1;
                MovingObject* bullet = new Bullet(WIN_X / 2, WIN_Y - ground_height, bullet_width, bullet_height, Color::Yellow, static_cast<Gun*>(gun)->getAngle(), bullet_speed);
                bullets.push_back(bullet);
            }

        }

    }

    return 0;
}