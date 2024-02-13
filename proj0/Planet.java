public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dis,dis_square;
		dis_square = (xxPos-p.xxPos) * (xxPos-p.xxPos) + (yyPos-p.yyPos) * (yyPos-p.yyPos);
		dis = Math.sqrt(dis_square);
		return dis;
	}

	public double calcForceExertedBy(Planet p){
		double force, dis;
		dis = calcDistance(p);
		force = (G * mass * p.mass) / (dis * dis);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double force_x, dx, r;
		dx = p.xxPos - xxPos;
		r = calcDistance(p);
		force_x = calcForceExertedBy(p) * dx / r;
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double force_y, dy, r;
		dy = p.yyPos - yyPos;
		r = calcDistance(p);
		force_y = calcForceExertedBy(p) * dy / r;
		return force_y;
	}

	/** equals()是自带的. */

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netforce_x = 0;
		for(int i = 0; i < allPlanets.length; i += 1){
			if(this.equals(allPlanets[i])){
				continue;
			}
			netforce_x += calcForceExertedByX(allPlanets[i]);
		}
		return netforce_x;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netforce_y = 0;
		for(int i = 0; i < allPlanets.length; i += 1){
			if(this.equals(allPlanets[i])){
				continue;
			}
			netforce_y += calcForceExertedByY(allPlanets[i]);
		}
		return netforce_y;
	}

	public void update(double dt, double fX, double fY){
		double ax,ay;
		ax = fX / mass;
		ay = fY / mass;

		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw(){
		String name = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, name);
	}

}