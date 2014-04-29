import gifAnimation.*;

GifMaker gifExport;

int fr = 0;

int allFr = 256;

float minT = 2.5;
float step = 0.01;

float a = 1;

float alfa = 15.0;

float E = (float) Math.E;

float b1 = 1.5;

void setup() {
	smooth();
	background(255, 255, 255);
	size(512, 512);

	gifExport = new GifMaker(this, getDate() + ".gif", 100);
	gifExport.setRepeat(0); // make it an "endless" animation
}

void draw() {
	translate(height/2, width/2);

	float r = pow(a * E, b1 * minT);
	float x = r * cos(minT);
	float y = r * sin(minT);
	point(x, y);

	for (int j = 0; j < 360/15; ++j) {
		float xs = x * cos(alfa * j) - y * sin(alfa * j);
		float ys = x * sin(alfa * j) + y * cos(alfa * j);
		point(xs, ys);
	}
	
	minT+= step;

	export();
}

void export() {
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

String getDate(){
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