import greenfoot.*;

/**
 * Write a description of class passenger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Passenger extends Actor
{
    public static final int STATE_IDLE=0;
    private int state;
    private Seat destination;
    public Passenger(Seat destination){
        this.destination=destination;
        this.state=STATE_IDLE;
    }

    private void addedToWorld(){
       
    }
    
    /**
     * Act - do whatever the passenger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {
        switch(state)
        {
            case STATE_IDLE:
               turnTowards(destination.x,destination.y);
               move(1);
               break;
           
        }
      // Add your action code here.
    }    
}
