import processing.sound.*;   // Import the library 
SoundFile file;          
boolean isPlaying;

float radiusCentral;            //radius for central globe
float radiusCorner = width/2;  // radius for globe 1 & 3
float angle = 0;
float rotationY = 0;

float[] xStars = new float[100];    
float[] yStars = new float[100];
float[] vStars = new float[100];
float radiusStars;
float colourStars;

int MaxPoints = 1000;
int nPoints = 900;
float phi = (sqrt(5)+1)/2 - 1;   // golden ratio
float ga = phi*2*PI;             // golden angle

class SpherePoint  {
  float  lat, lon;
  SpherePoint(float lat, float lon)  {
    this.lat = lat;
    this.lon = lon;
  }
}

SpherePoint[] pts = new SpherePoint[MaxPoints];

void initSphere() {
  for (int i = 1; i <= min (nPoints, pts.length); i= i + 1) {
    float lon = ga*i;
    float lat = asin (-1 + 2*i/(float)nPoints);   // Convert dome height to latitude    
    pts[i] = new SpherePoint(lat, lon);
  }
}

void setup() {
  size(800, 600, P3D);
  background(0);
  
  radiusCentral = .5 * height/2;    
  initSphere();            // Initialise Sphere - central
  colorMode(HSB, 1);

  radiusStars = 2;          // Stars
  colourStars   = color(#9AF1FA);
  int i = 0;
  while (i < 100) {
    xStars[i] = random (0, width);
    yStars[i] = random (0, height);
    vStars[i] = random (1, 4);
    i= i +1;
  }
  
  file  = new SoundFile(this, "Space_Travel.mp3");  // Import file
  file.play();
  isPlaying = true;
}

void draw() {  
  background(0); 
  
 renderGlobe();    // Draw sphere - central 

  pushMatrix();    // Draw sphere - lower left corner
  translate(90, 500, 0);
  noFill();
  strokeWeight(0.2);
  stroke(#9AF1FA);
  rotateY(radians(angle));    // Rotate sphere - to the right  
  sphere(radiusCorner*3);             
  popMatrix();  
  angle = angle + 0.4;

  pushMatrix();   //  Draw sphere - upper right corner
  translate(700, 90, 0);
  fill(10);
  strokeWeight(1);
  stroke(#9AF1FA);
  rotateY(radians(angle));     //  Rotate sphere - to the right
  sphere(radiusCorner*3);               
  popMatrix();  
  angle = angle + 0.4;

  colourStars   = color(154, 241, 250);   // Draw Stars
  fill(#9AF1FA);
  noStroke();
  ellipse( random(width), random(height), 4, 4);         // Background "Sparkles"
  int i = 0;                //  Stars - from right to left
  while (i < 100) {    
    ellipse(xStars[i], yStars[i], random(radiusStars/2, radiusStars*3), random(radiusStars/2, radiusStars*3));
    xStars[i] = xStars[i] - vStars[i];
    if (xStars[i] < 0) {
      xStars[i] =  width;
    }
    i = i +1;
  }
}
void renderGlobe() {       //  Draw Central Sphere
  pushMatrix();
  translate(width/2, height/2, 0);
  rotateY(radians(angle)); 
  strokeWeight(3);  
  angle = angle + 0.1;

  for (int i = 1; i <= min(nPoints, pts.length); i = i+1) {
    float lat = pts[i].lat;
    float lon = pts[i].lon;

    pushMatrix();
    rotateY(lon);
    rotateZ(lat);
    float lum = (cos(pts[i].lon+PI*.33+radians(angle))+1)/2;
    stroke(0.5, 0.9*lum, 0.5+lum*1);
    point(radiusCentral, 0, 0);
    popMatrix();
  }
  popMatrix();
}

void mousePressed()  {     // mousePressed - Increase speed
  int i = 0;          
  while (i < 100) {    
    vStars[i] = random(1,15);
    if (xStars[i] < 0) {
      xStars[i] =  width;
    } else {
    i = i +1;
    }
  }
  }

void mouseClicked()  {      // mouseClicked - d]Decrease speed
  int i = 0;   
  while (i < 100) { 
    vStars[i] = random(1,4);
    if (xStars[i] < 0) {
      xStars[i] =  width;
    } else {
    i = i +1;
  }
  }
} 

void keyPressed()
{
  if (key == ' ')  // Pause on or off
  {
    if (isPlaying)
    {
      file.pause();
      isPlaying = false;
    }
    else
    {
      file.play();
      isPlaying = true;
    }
  }
}
 
