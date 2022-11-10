package edu.nyu.cs;

import javafx.scene.transform.Rotate;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *ship class takes an image and allows it to move, rotate, and collide with coins
 * 
 * @author Andres Alvarado
 * @version 1.0
 */
public class Ship {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of the ship
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen
    private float angle=0;   //will hold the angle of the ship
    private int speed= -5;  // will hold the speed at which the ship moves
    

    /**
     * Constructor to create a ship object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Ship(Game app, String imgFilePath, int x, int y) {
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.img = app.loadImage(imgFilePath);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
        
    }
    /**
	 * Get the width of this spaceship, based on  the width of its image.
	 */
	public int getWidth() {
		return this.img.width;
	}
	
	/**
	 * Get the height of this spaceship, based  on the height of its image.
	 */
	public int getHeight() {
		return this.img.height;
	}


    /**
	 * Sets the angle of the spaceship
	 */
    public void setAngle(float x){
        this.angle= x;    // no need for validation any float works
    }


     /**
	 * Get the angle of this spaceship, based  on the angle of its image.
	 */
    public float getAngle(){
        return this.angle;
    }
       /**
	 * Get the x value of the spaceships location
	 */
    public int getX(){
        return this.x;
    }
/**
	 * Get the y value of the spaceships location
	 */
    public int getY(){
        return this.y;
    }

    /**
     * Draw this ships image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.pushMatrix();
        this.app.translate(this.x +img.width/2,this.y+img.height/2);
        this.app.rotate(angle);
        this.app.translate(-this.img.width/2,-this.img.height/2);
        this.app.imageMode(PApplet.CENTER);
        this.app.image(this.img, 0, 0);
        this.app.popMatrix();
       

    }
    /**
     * Moves the ship in the direction its facing at its current speed
     */
    public void move(){
        this.x += -this.speed * Math.sin(this.angle);
        this.y += this.speed * Math.cos(this.angle);
    }
    /**
     * sends the ship to the opposite side of the screen if it crosses over
     */
    public void constrainToMap() {
        if (this.x < 0) {
            this.x = this.app.width;
        } else if (this.x > this.app.width) {
            this.x = 0;
        } 
        
        if (this.y > this.app.height) {
            this.y = 0;
        } else if (this.y < 0) {
            this.y = this.app.height;
        }
      }
   

  
 /**
    checks if a given coin is within the bounds of the ship
     */
    public static boolean isCollision(Ship ship, Coin coin) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		
		//check whether coin is within the coin
		if (coin.getX() >= ship.getX()-5 && coin.getX() +  coin.getWidth() <= ship.getX() + ship.getWidth()+5 && coin.getY() >= ship.getY()-5 && coin.getY()  + coin.getHeight() <= ship.getY() +  ship.getHeight()+5) {
           collision=true;
		}

       
        if (ship.getX() >= coin.getX() && ship.getX()  <= coin.getX() + coin.getWidth()&& ship.getY() >= coin.getY() && ship.getY() <= coin.getY() +  coin.getHeight()) {
          
                collision = true;
            
		}	
		return collision;
	}

}
