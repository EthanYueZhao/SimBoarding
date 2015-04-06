import greenfoot.*;

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    public int seated_number=0;
    public int elapsed_time=0;
    public int total_time=0;
    private boolean done=false;

    public Timer(){
    }

    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        seated_number=0;
        elapsed_time++;
        for(Seat s: Plane.seats)
        {
            if(s.occupied)
            {
                seated_number++;
            }
        }
        if(!done & seated_number==Plane.SEAT_NUMBER )
        {
            total_time=elapsed_time;  
            done=true;
        }        
    }    
}
