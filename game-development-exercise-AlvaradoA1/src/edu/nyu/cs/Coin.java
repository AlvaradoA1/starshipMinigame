package edu.nyu.cs;

import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library


/**
 * Program to create coin objects to be collected by ships
 *
 * @author Andres Alvarado
 * @version 1.0
 */
public class Coin extends PApplet  {
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of a coin
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen



     /**
     * Constructor to create a coin object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePath a reference to the location of an images file
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Coin(Game app, String imgFilePath, int x, int y) {
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.img = app.loadImage(imgFilePath);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
    }

      /**
	 * Get the width of this spaceship, based on  the width of its image.
     * @return the object image width
	 */
	public int getWidth() {
		return this.img.width;
	}
	
	/**
	 * Get the height of this spaceship, based  on the width of its image.
     *  @return the object image height
	 */
	public int getHeight() {
		return this.img.height;
	}

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        if (0<x && x<this.app.width){
            this.x = x; 
        }
        
    }

    public void setY(int x){
        if (0<x && x<this.app.height){
            this.y = x; 
        }
    }
    /**
     * Draw this coin's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, this.x, this.y);
    }

}
