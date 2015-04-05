import greenfoot.*;

/**
 * Write a description of class Seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat extends Actor
{
    public boolean occupied;
    public int x;
    public int y;
    public Seat(int x,int y)
    {
        this.x=x;
        this.y=y;
        this.occupied=false;
    }
    /**
     * Act - do whatever the Seat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
