#ifndef ENEMY_H
#define ENEMY_H

#include "MovingObject.h"

#include <SFML/Graphics.hpp>

class Enemy: public MovingObject{

    public:
        
        /**
         * @brief Construct a new Enemy object
         * 
         */
        Enemy();

        /**
         * @brief Construct a new Enemy object
         * 
         * @param xPos 
         * @param yPos 
         * @param width 
         * @param height 
         * @param color 
         * @param speed 
         */
        Enemy(const double xPos, const double yPos, const int width, const int height, const sf::Color color, const double speed);

        /**
         * @brief Set the Speed object
         * 
         * @param speed 
         */
        void setSpeed(const double speed);

        /**
         * @brief checks if an enemy has reached the ground
         * 
         * @return true 
         * @return false 
         */
        bool hitGround();

        /**
         * @brief draws enemy object
         * 
         * @param window 
         */
        virtual void draw(sf::RenderWindow& window) override;

        /**
         * @brief updates enemy position
         * 
         */
        virtual void updatePosition() override;

    private:

        double _speed;


};


#endif