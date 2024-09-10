#include "Gun.h"

const int WIN_X = 1600;
const int WIN_Y = 900;

Gun::Gun(){
    _x = 0;
    _y = 0;
    _width = 0;
    _height = 0;
    _color = sf::Color::White;
    _angle = 0;
}

Gun::Gun(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double angle){
    _x = xPos;
    _y = yPos;
    _width = width;
    _height = height;
    _color = color;
    _angle = angle;
}

void Gun::setAngle(const double mouseX, const double mouseY, const int winX, const int winY){
    double y = (winY - 50) - mouseY;
    double x = winX / 2 - mouseX;
    _angle = atan2(y, x) * 180/M_PI - 90;
    //std::cout << _angle << std::endl;
}

double Gun::getAngle() const{
    //std::cout << _angle << std::endl;
    return _angle;
}

void Gun::draw(sf::RenderWindow& window){

    sf::RectangleShape gun;
    gun.setSize( sf::Vector2f(_width, _height) );
    gun.setFillColor(_color);
    gun.setOrigin(_width/2, _height);
    gun.setPosition(WIN_X / 2, WIN_Y - 50);
    gun.setRotation(_angle);
    window.draw(gun);

    //std::cout << "gun: " << _angle << std::endl;
}





















//ignore this lmao
//thought i would need this to rotate the gun
//i didnt feel like remaking the class
void Gun::updatePosition(){

}