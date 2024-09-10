#ifndef GUN_H
#define GUN_H

#include "MovingObject.h"

#include <SFML/Graphics.hpp>
#include <cmath>
#include <iostream>

class Gun: public MovingObject{

    public:

        /**
         * @brief Construct a new Gun object
         * 
         */
        Gun();

        /**
         * @brief Construct a new Gun object
         * 
         * @param xPos 
         * @param yPos 
         * @param width 
         * @param height 
         * @param color 
         * @param angle 
         */
        Gun(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double angle);

        /**
         * @brief Set the Angle object
         * 
         * @param mouseX 
         * @param mouseY 
         * @param winX 
         * @param winY 
         */
        void setAngle(const double mouseX, const double mouseY, const int winX, const int winY);

        /**
         * @brief Get the Angle object
         * 
         * @return double 
         */
        double getAngle() const;

        /**
         * @brief draws the gun object
         * 
         * @param window 
         */
        void draw(sf::RenderWindow& window) override;

        /**
         * @brief updates the gun position
         * 
         */
        void updatePosition() override;

    private:

        /**
         * @brief angle of gun
         * 
         */
        double _angle;

};


#endif