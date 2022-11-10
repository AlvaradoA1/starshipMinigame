package edu.nyu.cs;

import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.event.MouseEvent;
import processing.sound.*; // import the processing sound library

/**
 * Two player game using A and D for player ones ship to rotate and arrow keys for player two's ship to rotate. First to collect 5 coins wins. Press P to play again.
 * 
 * @author Andres Alvarado
 * @version 1.0
 */
public class Game extends PApplet {

  private SoundFile soundStartup; // will refer to a sound file to play when the program first starts
  private PImage imgMe; // will hold a photo of deathstar 
  private PImage imgYou; //will hold a photo of a blown up deathstar
  private Coin theCoin;  // whats collected to score
  private int player1score = 0; // player 1 score
  private int player2score= 0;  //player 2 score
  private Ship ships[]= new Ship[2];   // will hold both players ships
  private boolean keys[]= {false,false,false,false};    // array of booleans for determing movement of both ships
  private boolean noWinner=true;   //boolean for if there is a winner


	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
    

    // load up a sound file and play it once when program starts up
    this.soundStartup = new SoundFile(this, "sounds\\March.mp3"); // if you're on Windows, you may have to change this to "sounds\\vibraphon.mp3"
    this.soundStartup.play();

  
    
 
    // load up an image of me
    this.imgMe = loadImage("images/deathstar.png"); // if you're on Windows, you may have to change this to "images\\me.png"
    this.imgYou = loadImage("images/boom.png");
    // some basic settings for when we draw shapes
    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

    Ship player1 = new Ship(this, "images/xwing.png", this.width/4, this.height/2);
    Ship player2 = new Ship(this, "images/tiefighter.png", 3*(this.width)/4, this.height/2);
    this.ships[0]= player1;
    this.ships[1]=player2;

    this.theCoin=new Coin(this, "images/bigCoin.png", (int) (Math.random() * (width)), (int)(Math.random() * (height)));

    /*coins = new ArrayList<Coin>();
    Coin theCoin = new Coin(this, "images/coin.png", (int) (Math.random() * (width)), (int)(Math.random() * (height)));
    this.coins.add(theCoin);
    */
  }
   

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(0, 0, 0); // fill the background with the specified r, g, b color.

    // show an image of me that wanders around the window
   
    if(this.noWinner){
    image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window
    
    if (keys[0]){
      float x=this.ships[0].getAngle()-(float)0.04;
        this.ships[0].setAngle(x);
    }
    if (keys[1]){
      float y=this.ships[0].getAngle()+(float)0.04;
      this.ships[0].setAngle(y);
    }
    if (keys[2]){
      float w=this.ships[1].getAngle()-(float)0.04;
        this.ships[1].setAngle(w);
    }
    if (keys[3]){
      float z=this.ships[1].getAngle()+(float)0.04;
        this.ships[1].setAngle(z);
    }

    // draw ships to starting position
    
    for (Ship a:ships) {
      a.move();
      a.draw(); 
      a.constrainToMap();
      
    
    }
    // show the score at the bottom of the window
    textSize(32);
    String scoreString = String.format("REBEL SCORE: %d EMPIRE SCORE: %s", this.player1score, this.player2score);
    
    text(scoreString, 300, 700);
    

    this.theCoin.draw();

    if (Ship.isCollision(this.ships[0], this.theCoin)){
      System.out.println("ships 1 collided");
      this.player1score++;
      this.theCoin.setX((int) (Math.random() * (width)));
      this.theCoin.setY((int)(Math.random() * (height)));

    }

    if (Ship.isCollision(this.ships[1], this.theCoin)){
      System.out.println("ships 2 collided");

      this.player2score++;
      this.theCoin.setX((int) (Math.random() * (width)));
      this.theCoin.setY((int)(Math.random() * (height)));
  
    }
  }
  
    if (this.player1score==5){
      image(this.imgYou, this.width / 2, this.height/2);
      String player1wins= "You Have Brought Balance To The Force\n                Rebels Win";
      textSize(50);
      fill(0,0,255);
      text(player1wins, 100,400);
      this.noWinner=false;
     }
     if (this.player2score==5){
      image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window
      String player2wins= "You Underestimate The Power Of The Dark Side\n            The Empire Wins";
      textSize(50);
      fill(255,0,0);
      text(player2wins, 50,400);
      this.noWinner=false;
     }
  }
   
    
    
	

	/**
	 * This method is automatically called by Processing every time the user presses a key while viewing the map.
	 * - The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * - The `keyCode` variable (type int) is automatically is assigned the numeric ASCII/Unicode code of the key that was pressed.
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code for that key.
		//System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
    switch (this.key) {
      case 'a':
        keys[0]=true;
        break;
      case 'd':
        keys[1]=true;
        break;
      case 'p':
        this.reset();
        break;
    
    }
    switch (this.keyCode){
      case LEFT:
        keys[2]=true;
        break;
      case RIGHT:
         keys[3]=true;
         break;

    }
	} 
  public void keyReleased(){
    switch (this.key) {
      case 'a':
        keys[0]=false;
        break;
      case 'd':
        keys[1]=false;
        break;
    
    
    }
    switch (this.keyCode){
      case LEFT:
        keys[2]=false;
        break;
      case RIGHT:
         keys[3]=false;
         break;
   

    }
  }
  public void reset(){
    this.noWinner=true;
    this.player1score=0;
    this.player2score=0;
  }

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(1200, 800); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}





  }

}
