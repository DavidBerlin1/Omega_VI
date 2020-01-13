import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.Math;

public class Omega7 {

    public Omega7()
    {
	x = y = 0.0;
    }

    /**
     * The main function sets up the input source and calls execute().
     *
     * If the program is run with no command line arguments, then input is taken
     * from standard input (System.in), usually the keyboard. If a command line
     * argument is supplied, it is treated as the name of a file to use for input.
     *
     * @param args If non-empty, the name of a file to use for input.
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException{
	BufferedReader input = null;
        if (args.length == 0){
            input = new BufferedReader(new InputStreamReader(System.in));
        } else {
            input = new BufferedReader(new FileReader (args[0]));
        }
        new Omega7().execute(input);
    }

    // Current position of the boat
    // (0,0) is a point halfway between the beacons
    public double x, y;

    /**
     * Runs the main algorithm
     *
     * @param input
     */
    private void execute(BufferedReader input){
    	Scanner in = new Scanner (input);
    	int numSteps = in.nextInt();

    	// starting position
    	x = 0.0;
    	y = 0.0;

    	for (int step = 0; step < numSteps; ++step){
    	    //Read the next leg
    		
    	    String bearing = in.next();
    	  //  System.out.println("bearing: "+bearing);
    	    double distance = in.nextDouble();
    	   // System.out.println("distance: "+distance);
    	    double degrees = convertBearingToDegrees(bearing);
    	   // System.out.println("degrees" + degrees);
    	    computePositionAtEndOfLeg (degrees, distance);
    	    int angle1 = angleToBeacon (-50.0, 0.0);
    	    int angle2 = angleToBeacon (50.0, 0.0);
    	    System.out.println (angle1 + " " + angle2);

    	}
    }



    String[] CompassRoseBearings = {"N", "NNE", "NE", "ENE", "E",
				    "ESE", "SE", "SSE", "S",
				    "SSW", "SW", "WSW", "W",
				    "WNW", "NW", "NNW"};

    /**
     * Given a bearing (one of the CompassRoseBearings above), return the
     * corresponding angle in degrees, assuming that N is 0 degrees and
     * degrees increase clockwise.
     *
     * @param bearing name of a bearing on the compass rose
     * @return corresponding angle in degrees, measured clockwise from North
     */
    public double convertBearingToDegrees(String bearing){

        //may need to reference the CompassRoseBearing string above

        double degree = this.x;


        for(int i = 0; i < CompassRoseBearings.length; i++)


            this.CompassRoseBearings[i] = bearing;
          
            switch(bearing){
                case "N":
                    degree = 0.0;
                    break;
                case "NNE":
                    degree = 22.5;
                    break;
                case "NE":
                    degree = 45;
                    break;
                case  "ENE":
                    degree = 67.5;
                    break;
                case "E":
                    degree = 90;
                    break;
                case "ESE":
                    degree = 112.5;
                    break;
                case "SE":
                    degree = 135;
                    break;
                case "SSE":
                    degree = 157.5;
                    break;
                case "S":
                    degree = 180;
                    break;
                case "SSW":
                    degree = 202.5;
                    break;
                case "SW":
                    degree = 225;
                    break;
                case "WSW":
                    degree = 247.5;
                    break;
                case "W":
                    degree = 270;
                    break;
                case "WNW":
                    degree = 292.5;
                    break;
                case "NW":
                    degree = 315;
                    break;
                case "NNW":
                    degree = 337.5;
                    break;
             }
           //System.out.println("The values of degree within the function: "+ degree);
       return degree;
    }

    /**
     * Compute the angle, measured clockwise from the North, of the point
     * (targetX, targetY) as seen from the current position of the boat.
     *
     * @param targetX   x-coordinate of target position
     * @param targetY   y-coordinate of target position
     * @return angle of that target position, rounded to the closest integer degree
     */
    private int angleToBeacon(double targetX, double targetY){
    	if(targetX <0)
    	{
    		 int tanCal = 360-( (int) Math.toDegrees(Math.atan2((this.x-targetX), (this.y))));
    		 return tanCal;
    	}
    	else
    	{
    		int tanCal =  ((int) Math.toDegrees(Math.atan2((targetX-this.x), (this.y))));
    		 return tanCal;
    	}
    		 
      
        

       
    }


    /**
     * Update the (x,y) position of the boat given that it travels a given distance
     * and direction
     *
     * @param direction expressed in degrees, measured clockwise from the North
     * @param distance  a distance in stadia
     */
    public void computePositionAtEndOfLeg(double direction, double distance){
    	double newX;
    	double newY;
    	double newD;
    	//System.out.println("direction= "+ direction);
    	if(direction == 180.0)
    	{
    		newX=0;
    		newY = distance;
    		this.x = this.x + newX;
    		this.y= this.y +newY;
    		//System.out.println("after this.x: "+ this.x);
    		//System.out.println("after this.y: "+ this.y);
    		if(this.y <0)
    		{
    			this.y = this.y * -1;
    		}
    	}
    	else if (direction == 0)
    	{
    		newX=0;
    		newY = distance;
    		this.x = this.x + newX;
    		this.y= this.y -newY;
    		//System.out.println("after this.x: "+ this.x);
    		//System.out.println("after this.y: "+ this.y);
    		if(this.y <0)
    		{
    			this.y = this.y * -1;
    		}
    	}
    	else if (direction == 90)
    	{
    		newY=0;
    		newX = distance;
    		this.y = this.y + newY;
    		this.x= this.x + newX;
    	}
    	else if (direction == 270.0)
    	{
    		newY=0;
    		newX = distance;
    		this.y = this.y + newY;
    		this.x= this.x - newX;
    		
    	}
    	
    	else if(direction >90 && direction <180)
    	{
    		newD = 180 - direction;
    		
    		this.y = this.y + (distance * Math.cos(Math.toRadians(newD)));
    		this.x = this.x + (distance * Math.sin(Math.toRadians(newD)));
    	}
    	else if (direction >180 && direction<270)
    	{
    		newD = 270 - direction;
    		
    		this.y = this.y + (distance * Math.cos(Math.toRadians(newD)));
    		this.x = this.x + (distance * Math.sin(Math.toRadians(newD)));
    	}
    	else if (direction >270 && direction < 360)
    	{
    		newD = 360 - direction;
    		
    		this.y = this.y + (distance * Math.cos(Math.toRadians(newD)));
    		this.x = this.x + (distance * Math.sin(Math.toRadians(newD)));
    	}
        
     ;
        

    }
}