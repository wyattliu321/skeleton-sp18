/**
 * Planet
 */

public class Planet {
    public double xxPos; /* Its current x position */
    public double yyPos; /* Its current y position */
    public double xxVel; /* Its current velocity in the x direction */
    public double yyVel; /* Its current velocity in the y direction */
    public double mass;  /* Its mass */
    public String imgFileName;  /* The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif) */

    public Planet(double xP, double yP, double xV,
                  double yV, double m,  String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;

    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass  = p.mass;
        imgFileName = p.imgFileName;

    }

    public double calcDistance(Planet p2){
        double dx = this.xxPos - p2.xxPos;
        double dy = this.yyPos - p2.yyPos;
        /* double r = Math.hypot(dx,dy); */
        double r  = Math.sqrt(dx*dx + dy*dy);
        return r ;

    }
    public double calcForceExertedBy(Planet p2){
        double dis = this.calcDistance(p2);
        double G = 6.67e-11;
        double F = (G * this.mass * p2.mass)/Math.pow(dis, 2);
        return F;
    }

    public double calcForceExertedByX(Planet p2){
        double dx = -(this.xxPos - p2.xxPos);
        double dis = this.calcDistance(p2);
        double F = calcForceExertedBy(p2);
        double Fx = F* (dx/dis);
        /* double dy = -(this.yyPos - p2.yyPos); */
        return Fx;
    }
    public double calcForceExertedByY(Planet p2){
        double dy = -(this.yyPos - p2.yyPos);
        double dis = this.calcDistance(p2);
        double F = calcForceExertedBy(p2);
        double Fy = F* (dy/dis);
        /* double dy = -(this.yyPos - p2.yyPos); */
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allplanets){
        double NetForceExertedByX = 0;
        for (Planet element : allplanets){
            if (!this.equals(element)){
                NetForceExertedByX += this.calcForceExertedByX(element);
            }
        }
        return NetForceExertedByX;
    }


    public double calcNetForceExertedByY(Planet[] allplanets){
        double NetForceExertedByY = 0;
        for (Planet element : allplanets){
            if (!this.equals(element)){
                NetForceExertedByY += this.calcForceExertedByY(element);
            }
        }
        return NetForceExertedByY;
    }

    public void update(double dt, double fX, double fY){
        double aNetX = fX/this.mass;
        double aNetY = fY/this.mass;
        this.xxVel = this.xxVel + dt*aNetX;
        this.yyVel = this.yyVel + dt*aNetY;
        this.xxPos = this.xxPos + dt*this.xxVel;
        this.yyPos = this.yyPos + dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }

}
