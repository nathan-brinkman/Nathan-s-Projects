#include "Enemy.h"

const int WIN_X = 1600;
const int WIN_Y = 900;

Enemy::Enemy(){
    _x = 0;
    _y = 0;
    _width = 20;
    _height = 10;
    _color = sf::Color::Red;
    _speed = .25;
}

Enemy::Enemy(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double speed){
    _x = xPos;
    _y = yPos;
    _width = width;
    _height = height;
    _color = color;
    _speed = speed;
    
}

void Enemy::setSpeed(const double speed){
    _speed = speed;
}

bool Enemy::hitGround(){
    if(_y+_height > WIN_Y - 50){
        return true;
    }
    return false;
}

void Enemy::draw(sf::RenderWindow& window){
    sf::RectangleShape enemy;
    enemy.setSize( sf::Vector2f(_width, _height) );
    enemy.setPosition(_x, _y);
    enemy.setFillColor(_color);
    window.draw(enemy);
}

void Enemy::updatePosition(){
    _y = _y + _speed;
}