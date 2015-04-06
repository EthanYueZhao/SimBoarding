import greenfoot.*;
import java.util.*;

/**
 * Write a description of class passenger here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Passenger extends Actor
{
    public static final int STATE_IDLE=0;
    public static final int STATE_SEATED=1;
    public static final int STATE_MOVING=2;
    public int current_state;
    private Seat destination;
    private Point checkPoint1=new Point(140,530);
    private Point checkPoint2=new Point(140,270);
    private Point checkPoint3;
    private Point targetPoint;
    private int move_speed;
    public Passenger(Seat destination){
        this.destination=destination;
        this.current_state=STATE_IDLE;
        checkPoint3=new Point(destination.x-20,270);
        targetPoint=checkPoint1;
        move_speed= Greenfoot.getRandomNumber(4)+1;
    }

    private void addedToWorld(){

    }

    /**
     * Act - do whatever the passenger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {
        switch(current_state)
        {
            case STATE_IDLE:
            checkCurrentPoint();
            turnTowards(targetPoint.x,targetPoint.y);
                        if(!checkBlocked())
            {
                current_state=STATE_MOVING;
            }
            break;
            case STATE_MOVING: 
            checkCurrentPoint();
            turnTowards(targetPoint.x,targetPoint.y);
            move(1);
            if(checkBlocked())
            {
                current_state=STATE_IDLE; 
            }
            break;

            case STATE_SEATED:
            return;

        }       
    }

    private boolean checkBlocked()
    {
        //Actor p =getOneObjectAtOffset(2,1 , Passenger.class);
        List<Passenger> ps= getIntersectingObjects( Passenger.class);
        if(!ps.isEmpty())
        {   
            for(Passenger p: ps)   
            {   
                if(p.getX()>this.getX() & p.getX()<this.destination.x & p.getY()>this.getY() & p.getY()<this.destination.y
                || p.getX()>this.getX() & p.getX()<this.destination.x & p.getY()<this.getY() & p.getY()>this.destination.y
                ) 
                {
                    return true;                    
                }else
                {
                    continue;
                }
            }
        }
        return false;
    }

    private void checkCurrentPoint()
    {
        int x= getX();
        int y= getY();
        if(x==checkPoint1.x & y==checkPoint1.y)
        {
            targetPoint=checkPoint2;
        }
        if(x==checkPoint2.x & y==checkPoint2.y)
        {
            targetPoint=checkPoint3;
        }
        if(x==checkPoint3.x & y==checkPoint3.y)
        {
            targetPoint.x=destination.x;
            targetPoint.y=destination.y;
        }
        if(x==destination.x && y==destination.y)
        {
            current_state=STATE_SEATED;
            return;
        }
    }
}
