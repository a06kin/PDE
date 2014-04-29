import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gifAnimation.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class spiral extends PApplet {



GifMaker gifExport;

int fr = 0;

int allFr = 256;

float E = (float) Math.E;

Box boxx;

Tourch t;


public void setup() {
	smooth();
	background(255);
	size(512, 512);

	gifExport = new GifMaker(this, getDate() + ".gif", 100);
	gifExport.setRepeat(0); // make it an "endless" animation

	boxx = new Box(20, 255, 1, 1.5f, 2.5f, 0.01f, 90);
	t = new Tourch();

}


public void draw() {
	noStroke();
	rectMode(CORNER);
	//fill(255, 10); // semi-transparent white
	fill(0);
	rect(0, 0, width, height);

	stroke(255);

	translate(height/2, width/2);


	boxx.nextPath();
	boxx.display();

	t.display();

	export();
}


public void export() {
	if(fr < allFr) {
		gifExport.setDelay(20);
		gifExport.addFrame();
		fr++;
	} else {
		gifExport.finish();
		fr++;
		println("gif saved");
		exit();
	}
}

public String getDate(){
	int d = day();    // Values from 1 - 31
	int mo = month();  // Values from 1 - 12
	int y = year();   // 2003, 2004, 2005, etc.

	String s = String.valueOf(d);
	s += "." + String.valueOf(mo);
	s += "." +String.valueOf(y);

	int sec = second();  // Values from 0 - 59
	int mi = minute();  // Values from 0 - 59
	int h = hour();    // Values from 0 - 23

	s += "_" + String.valueOf(h);
	s += ":" + String.valueOf(mi);
	s += ":" + String.valueOf(sec);

	return s;
}

class Box { 
	int strokeS;
	float size;
	PVector data;

	float a;  //= 1;
	float b;  //= 1.5;

	float r;

	float step;  //= 0.01;
	float now; //= 2.5;

	float angle;

	Box(float size, int strokeS, float a, float b, float now, float step, float angle){
		this.strokeS = strokeS;
		this.size = size;
		this.a = a;
		this.b = b;
		this.step = step;
		this.now = now;
		this.angle = angle;

		this.r = pow(a * E, b * now);
		float x = r * cos(now);

		float y = r * sin(now);

		this.data = new PVector(x, y);
	}

	public void display() {
		//fill(c);
		noFill();
		stroke(strokeS);
		rectMode(CENTER);

		rect(data.x,data.y,size,size);
	}	

	public void nextPath(){
		now += step;

		r = pow(a * E, b * now);

		float xs = r * cos(now);
		float ys = r * sin(now);

		data.x = xs * cos(angle) - ys * sin(angle);
		data.y = xs * sin(angle) + ys * cos(angle);
	}
};

class Tourch{

	int inner_radius = 10;
	int c_radius = 100;
	int distance = 5;
	float stroke_weight = 0.5f;

	Tourch(){

	}

	public void display(){
		noStroke();
		for (int i = 0; i < c_radius; i+=distance){
			fill(0xffFFFFFF, i/inner_radius);
			ellipse(0, 0, c_radius - i, c_radius - i);
		}
	}
};
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "spiral" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
