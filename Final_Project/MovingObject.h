#ifndef MOVINGOBJECT_H
#define MOVINGOBJECT_H   

#include <SFML/Graphics.hpp>

class MovingObject{

    public:

        /**
         * @brief Construct a new Brush object
         * 
         */
        MovingObject();

        /**
         * @brief Construct a new Brush object
         * 
         * @param x 
         * @param y 
         * @param c 
         */
        MovingObject(const double xPos, const double yPos, const int width, const int height, const sf::Color c);

        /**
         * @brief Set the Coordinates object
         * 
         * @param coords 
         */
        void setCoordinates(const double xPos, const double yPos);

        /**
         * @brief Set the Color object
         * 
         * @param color 
         */
        void setColor(const sf::Color c);

        /**
         * @brief get x
         * 
         * @return double 
         */
        double getX() const;

        /**
         * @brief get y
         * 
         * @return double 
         */
        double getY() const;

        /**
         * @brief Get the Width object
         * 
         * @return int 
         */
        int getWidth() const;

        /**
         * @brief Get the Height object
         * 
         * @return int 
         */
        int getHeight() const;

        /**
         * @brief draws child class objects
         * 
         * @param display 
         */
        virtual void draw(sf::RenderWindow& window) = 0;

        /**
         * @brief updates position for child classes
         * 
         * @param yDir 
         */
        virtual void updatePosition() = 0;
    

    protected:

        sf::Color _color;
        double _x;
        double _y;
        int _height;
        int _width;
    

};

#endif