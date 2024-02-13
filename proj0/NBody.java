public class NBody{
	/** NBody is a class that will actually run your simulation. 
	  * This class will have NO constructor. 
	  * The goal of this class is to simulate a universe specified in one of the data files. */
	/** Read the Radius of the given Universe. */
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		double R = in.readDouble();
		return R;
	}

	/** Given a filename, return an array of planets. */
	public static Planet[] readPlanets(String fileName){

		In in = new In(fileName);
		int N = in.readInt();

		Planet[] planets = new Planet[N];
		double R = in.readDouble();

		for(int i = 0; i < N; i += 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			//different from cpp, constructors should declared by "new" keyword.
			planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}

		return planets;
	}

	public static void main(String[] args){
		/** Collecting all needed input. */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double uni_radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/** Drawing the background. */
		String background_img = "images/starfield.jpg";
		StdDraw.setScale(-uni_radius, uni_radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, background_img);

		/** Drawing one planet: back to Planet.java */
		/** Drawing all of the planets. */
		for(int i = 0; i < planets.length; i += 1){
			planets[i].draw();
		}
		StdDraw.show();

		/** Animation */
		StdDraw.enableDoubleBuffering();

		for(int t = 0; t < T; t += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i += 1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < planets.length; i += 1){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			//Draw background image.
			StdDraw.setScale(-uni_radius, uni_radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, background_img);

			//Draw all planets.
			for(int i = 0; i < planets.length; i += 1){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		/** for autograder: print the final state out.  */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", uni_radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	}

}