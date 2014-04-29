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
}