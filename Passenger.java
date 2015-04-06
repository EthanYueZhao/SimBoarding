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
    public static final int STATE_PUTTING=3;
    public static final int STATE_ArrivedPoint1=4;
    public static final int STATE_ArrivedPoint2=5;
    public int current_state;
    private Seat destination;
    private Point checkPoint1=new Point(140,530);
    private Point checkPoint2=new Point(140,270);
    private Point checkPoint3;
    private Point targetPoint;
    private int move_speed;
    private int putting_time;
    private int timer=0;
    public Passenger(Seat destination){
        this.destination=destination;
        this.current_state=STATE_IDLE;
        checkPoint3=new Point(destination.x-20,270);
        targetPoint=checkPoint1;
        move_speed= Greenfoot.getRandomNumber(4)+1;
        putting_time=Greenfoot.getRandomNumber(1000)+100;
    }

    private void addedToWorld(){

    }

    /**
     * Act - do whatever the passenger wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {
        //turnTowards(targetPoint.x,targetPoint.y);
        switch(current_state)
        {
            case STATE_IDLE:
            turnTowards(targetPoint.x,targetPoint.y);
            idle();                   
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
            case STATE_ArrivedPoint1:
            turnTowards(targetPoint.x,targetPoint.y);
            arrivedCheckPoint1();
            break;
            case STATE_ArrivedPoint2:
            turnTowards(targetPoint.x,targetPoint.y);
            arrivedCheckPoint2();
            break;
            case STATE_PUTTING:
            putLuggage();
            break;

            case STATE_SEATED:
            destination.occupied=true;
            return;

        }       
    }

    private void putLuggage()
    {
        if(timer<putting_time)
        {
            timer++;
        }else
        {
            current_state=STATE_MOVING;
            timer=0;
        }
    }
          
    private boolean checkBlocked()
    {
        //Actor p =getOneObjectAtOffset(2,1 , Passenger.class);
        List<Passenger> ps= getObjectsInRange(20, Passenger.class);
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

    private void arrivedCheckPoint1()
    {
        Actor p =getOneObjectAtOffset(0,-15 , Passenger.class); 
        if(p!=null)
        {
            return;
        }else
        {
            move(1);
            checkCurrentPoint();
        }
    }
    
    private void arrivedCheckPoint2()
    {
         Actor p =getOneObjectAtOffset(15,0 , Passenger.class); 
        if(p!=null)
        {
            return;
        }else
        {
            move(1);
            checkCurrentPoint();
        }
    }

    private void idle()
    {
         Actor p =getOneObjectAtOffset(-15,0 , Passenger.class); 
        if(p!=null)
        {
            return;
        }else
        {
            move(1);
            checkCurrentPoint();
        }
    }
    
    private void checkCurrentPoint()
    {
        int x= getX();
        int y= getY();
        if(x==checkPoint1.x & y==checkPoint1.y)
        {
            targetPoint=checkPoint2;
            current_state=STATE_ArrivedPoint1;
        }
        if(x==checkPoint2.x & y==checkPoint2.y)
        {
            targetPoint=checkPoint3;
            current_state=STATE_ArrivedPoint2;
        }
        if(x==checkPoint3.x & y==checkPoint3.y)
        {
            targetPoint.x=destination.x;
            targetPoint.y=destination.y;
            current_state=STATE_PUTTING;
        }
        if(x==destination.x && y==destination.y)
        {
            current_state=STATE_SEATED;
            return;
        }
    }
}
