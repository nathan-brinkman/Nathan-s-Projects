#include "MovingObject.h"

MovingObject::MovingObject(){
    _x = 0;
    _y = 0;
    _width = 0;
    _height = 0;
    _color = sf::Color::White;
}

MovingObject::MovingObject(const double xPos, const double yPos, const int width, const int height, const sf::Color color){
    _x = xPos;
    _y = yPos;
    _width = width;
    _height = height;
    _color = color;
}

void MovingObject::setCoordinates(const double xPos, const double yPos){
    _x = xPos;
    _y = yPos;
}

void MovingObject::setColor(const sf::Color color){
    _color = color;
}

double MovingObject::getX() const{
    return _x;
}

double MovingObject::getY() const{
    return _y;
}

int MovingObject::getWidth() const{
    return _width;
}

int MovingObject::getHeight() const{
    return _height;
}