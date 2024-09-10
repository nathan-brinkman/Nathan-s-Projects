#ifndef BULLET_H
#define BULLET_H

#include "MovingObject.h"

#include <SFML/Graphics.hpp>
#include <cmath>
#include <iostream>

class Bullet: public MovingObject{

    public:

        /**
         * @brief Construct a new Bullet object
         * 
         */
        Bullet();

        /**
         * @brief Construct a new Bullet object
         * 
         * @param xPos 
         * @param yPos 
         * @param width 
         * @param height 
         * @param color 
         * @param angle 
         * @param speed 
         */
        Bullet(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double angle, const double speed);

        /**
         * @brief Set the Angle object
         * 
         * @param angle 
         */
        void setAngle(const double angle);

        /**
         * @brief checks if bullet has collided with enemy
         * 
         * @param object 
         * @return true 
         * @return false 
         */
        bool collide(MovingObject* object);

        /**
         * @brief draws the bullet objects
         * 
         * @param window 
         */
        void draw(sf::RenderWindow& window) override;

        /**
         * @brief updates the bullets position
         * 
         */
        void updatePosition() override;

    private:
        
        /**
         * @brief angle of the bullet
         * 
         */
        double _angle;
        /**
         * @brief speed of bullet
         * 
         */
        double _speed;

};

#endif