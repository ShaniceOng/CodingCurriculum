/*
 * Name: 'The Rolling Stone'
 * 
 * Module: ENGS102P
 *
 * Instructions: Prevent the 'stone' (ball) from going off the screen by making it fall
 *               down through the gaps.
 *               Note: Watchout for the rotating screen.
 *
 * Controls: Press the right and left arrow key button to control the ball's movement.
 *           Once the bar at the top-right corner gets filled, you can slow down
 *           the speed by tapping on the spacebar.
 */

// Declaring gloabal variables
Ball ball;
Level levels[]; // Declare the array

int space;
int numLevels;
float speed = 1;
float gapLength = 45;

float ballShift = 9;

int score = 0;
int frameCount = 0;
int powerUp = 0;

float data;

int rotateCount = 0;
int rotation;
int count = 0;

// changed origin co-ordinates
int xo;
int yo;

void setup() {
  size(600, 600);
  
  // Properties of the levels
  numLevels = 7;
  space = height / (numLevels - 1);
  levels = new Level[numLevels]; // Create the array
  for(int i = 0; i < numLevels; i++) {
    float y = i * space;
    float gapStart = random(width - gapLength);
    
    // Create each object
    levels[i] = new Level(y, gapStart, i);
  }
  
  // Properties of the ball
  float dia = 20;
  ball = new Ball(dia/2, space * 6 - dia / 2, dia, 6);
  
  // Deal with the rotation of the screen
  rotation = (int)random(500, 1200);
  xo = width / 2;
  yo = height / 2;
}

void draw() {
  
  background(0);
  
  // Game Over if ball goes off the screen
  if(((ball.y + ball.diameter / 2 ) < 0) || (ball.y - ball.diameter / 2 > height)) {
    PFont font;
    font = loadFont("HelveticaNeue-35.vlw");
    textFont(font);
    fill(255);
    textAlign(CENTER, CENTER);
    text("Game Over", xo, yo);
    scoreBoard();
    noLoop();
  }
  
  powerUpMeter();
  scoreBoard();
  
  // Translates and vertically inverts the screen
  translate(xo, yo);
  if(rotateScreen() == 1) {
    rotate(radians(180));
  }
  
  // Updating positions of levels and displaying them
  for(int i = 0; i < numLevels; i++) {
    levels[i].move();
    levels[i].display();
  }
  
  // Ball falls when it reaches the gap
  if((ball.x >= levels[ball.currentLevel].gapBegin + ball.diameter /2) && 
      (ball.x <= levels[ball.currentLevel].gapEnd - ball.diameter /2))
      ball.fall();
  
  // Updates position of ball and displays it
  ball.move();
  ball.display();
  
  // Speed of the ball and levels increase after every 600th frame
  frameCount++;
  if(frameCount % 600 == 0) {
    frameCount = 0;
    speed += 0.45;
    ballShift += 0.3;
  }
}

// Maintains the scoreboard at top-left corner
void scoreBoard() {
  fill(255);
  textAlign(LEFT, TOP);
  text("Score: " + score, 10, 10);
}

// Updates the meter on the top right corner
void powerUpMeter() {
  float limit = 1200;
  if(powerUp < limit) {
    powerUp++;
  }
  
  fill(100);
  stroke(200);
  rect(475, 30, 100 * powerUp / limit, 30);
  noFill();
  rect(475 + (100 * powerUp / limit), 30, (limit - powerUp) * 100 / limit, 30);
    
  if(powerUp == limit) {
    
    // Changes the colour of meter when full
    fill(100, 100, 255);
    rect(475, 30, 100, 30);
    
    // Updates meter and speed if powerup is used  
    if(keyPressed && key == ' ') {
      powerUp = 0;
      speed -= 1.25;
      ballShift -= 0.7;
    }
  }
}

// Function to manage the rotation of the screen
int rotateScreen() {
  rotateCount++;
  if(rotateCount == rotation) {
    rotation = (int)random(500, 1200);
    rotateCount = 0;
    count = (count + 1) % 2; 
  }
  return count;
}

void keyPressed() {
  if (key == CODED) { 
    if ((keyCode == LEFT && count == 0) || (keyCode == RIGHT && count == 1)) {
      ball.moveLeft();
    } else if ((keyCode == LEFT && count == 1) || (keyCode == RIGHT && count == 0)) {
      ball.moveRight();
    }
  } 
}

class Level {
  float y;
  float gapBegin;
  float gapEnd;
  int level;
  
  Level(float yPos, float gapStart, int i) {
    y = yPos;
    gapBegin = gapStart;
    gapEnd = gapBegin + gapLength;
    level = i;
  }
  
  // Controls the upward movement of the levels
  void move() {
    y -= speed;
    if(y <= -space) {
       y = height; 
       gapBegin = random(width - gapLength);
       gapEnd = gapBegin + gapLength;
     }
  }
  
  // Displays the levels
  void display() {
    strokeWeight(5);
    stroke(255);
    line(-xo, y - yo, gapBegin - xo, y - yo);
    line(gapEnd - xo, y - yo, width - xo, y - yo);
  }
}

class Ball {
  float x;
  float y;
  float diameter;
  int currentLevel;
  
  Ball(float xPos, float yPos, float dia, int level) {
    x = xPos;
    y = yPos;
    diameter = dia;
    currentLevel = level;
  }
  
  // Controls upward movement of the ball
  void move() {
    y -= speed;
    if(y <= -space) {
       y = 0;   
    }
  }
  
  // Moves the ball towards the left
  void moveLeft() {
    if(x >= 2 + diameter / 2) {
       x -= ballShift;
    } else 
      x = width - diameter / 2;
  }
  
  // Moves the ball towards the right
  void moveRight() {
    if(x <= width - 2 - diameter / 2) { 
      x += ballShift;
    } else
      x = diameter / 2;
  }

  // Updates corresponding variables when ball falls
  void fall() {
      currentLevel = (currentLevel + 1) % numLevels;
      y += space;
      score++;
  }
  
  // Displays the ball
  void display() {
    fill(255);
    ellipse(x - xo, y - yo, diameter, diameter);
  }
}