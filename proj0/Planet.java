public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;	
	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){

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
		double dx = this.xxPos-p.xxPos;
		double dy = this.yyPos-p.yyPos;
		double rSquare = (dx * dx) + (dy * dy);
		return Math.sqrt(rSquare);
	}


	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		double force = (G * p.mass * this.mass) / (r * r);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		double r = calcDistance(p);
		double force = calcForceExertedBy(p);
		double forceX = force * dx / r;
		return forceX;
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		double r = calcDistance(p);
		double force = calcForceExertedBy(p);
		double forceY = force * dy / r;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] Planets){
		double netForceX = 0;
		for(Planet p : Planets){
			if(p == this){
				continue;
			}
			else{
				netForceX += calcForceExertedByX(p);
			}
		}
		return netForceX;
	}



	public double calcNetForceExertedByY(Planet[] Planets){
		double netForceY = 0;
		for(Planet p : Planets){
			if(p == this){
				continue;
			}
			else{
				netForceY += calcForceExertedByY(p);
			}
		}
		return netForceY;
	}


	public void update(double dt,double fX,double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += aX * dt;
		this.yyVel += aY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;

	}

	public void draw(){
		String filename = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, filename);
	}

}