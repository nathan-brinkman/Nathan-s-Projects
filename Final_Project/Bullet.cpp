#include "Bullet.h"

const int WIN_X = 1600;
const int WIN_Y = 900;

const double PI = 3.1415926535897932384626433832795028841971;

Bullet::Bullet(){
    _x = 0;
    _y = 0;
    _width = 0;
    _height = 0;
    _color = sf::Color::White;
    _angle = 0;
    _speed = 0;
}

Bullet::Bullet(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double angle, const double speed){
    _x = xPos;
    _y = yPos;
    _width = width;
    _height = height;
    _color = color;
    _angle = angle;
    _speed = speed;
}

void Bullet::setAngle(const double angle){
    _angle = angle;
}

bool Bullet::collide(MovingObject* object){

    if(_x+_width > object->getX() && _x < object->getX() + object->getWidth()){
        if(_y+_height > object->getY() && _y < object->getY() + object->getHeight()){
            return true;
        }
    }
    return false;
}

void Bullet::draw(sf::RenderWindow& window){

    //std::cout << _angle << std::endl;

    sf::RectangleShape bullet;
    bullet.setSize( sf::Vector2f(_width, _height) );
    bullet.setFillColor(_color);
    bullet.setOrigin(_width/2, _height);
    bullet.setPosition(_x, _y);
    bullet.setRotation(_angle);
    window.draw(bullet);
}

void Bullet::updatePosition(){
    _x = _x + cos(_angle * (PI/180) - PI/2) * _speed;
    _y = _y + sin(_angle * (PI/180) - PI/2) * _speed;
}