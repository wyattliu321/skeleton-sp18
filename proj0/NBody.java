import sun.management.counter.perf.PerfLongArrayCounter;

public class NBody {
    public static double readRadius(String filepath){
        In in = new In(filepath);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }


    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);
        int plantNum= in.readInt();
        in.readDouble();
        Planet[] PArr = new Planet[plantNum];

        for(int i =0 ; i < plantNum ; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            PArr[i] = new Planet(xP,yP,xV,yV,m,img);
        }
        return PArr;
    }

    /* Draw initial Universe state */
    public static void main(String[] args){
        /* get the data */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double uniRadius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        /* Draw the background */
        StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");

        /* Draw planets */
        for (Planet planet : planets){
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();

        for (double time=0; time<=T ;time+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int i =0 ; i < planets.length; i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }

            for(int j = 0; j < planets.length; j++){
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            /* Draw the background */
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet planet: planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            /* Printing the Universe */
            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", uniRadius);
            for (int i=0; i< planets.length; i++){
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }

        }
    }

}
