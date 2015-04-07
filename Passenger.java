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
    public static final int STATE_WAITING=6;
    public int current_state;
    private Seat destination;
    private Point checkPoint1=new Point(140,530);
    private Point checkPoint2=new Point(140,270);
    public Point checkPoint3;
    public Point targetPoint;
    private int move_speed;
    private int putting_time;
    private int timer=0;
    public Passenger notifier;
    public Passenger(Seat destination){
        this.destination=destination;
        this.current_state=STATE_IDLE;
        checkPoint3=new Point(destination.x-20,270);
        targetPoint=checkPoint1;
        move_speed= Greenfoot.getRandomNumber(4)+1;
        putting_time=Greenfoot.getRandomNumber(100);
        this.notifier=null;
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
            destination.occupied=false;
            checkCurrentPoint();
            turnTowards(targetPoint.x,targetPoint.y);
            move(1);
                        
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
            case STATE_WAITING:

            break;

            case STATE_SEATED:
            destination.occupied=true;
            break;

        }       
    }

    private void putLuggage()
    {
        if(timer<putting_time)
        {
            timer++;
        }else
        {
            prepareToSeat();
            //current_state=STATE_MOVING;
            timer=0;
        }
    }

    private void prepareToSeat()
    {
        switch(destination.y)
        {
            // first class
            case 345:
            checkSeated(destination.x,305);
            break;
            case 305:
            current_state=STATE_MOVING;
            break;
            case 235:
            current_state=STATE_MOVING;
            break;
            case 195:
            checkSeated(destination.x,235);
            break;
            // economy class
            case 350:
            checkSeated(destination.x,325);
            checkSeated(destination.x,300);
            break;
            case 325:
            checkSeated(destination.x,300);
            break;
            case 300:
            current_state=STATE_MOVING;
            break;
            case 245:
            current_state=STATE_MOVING;
            break;
            case 215:
            checkSeated(destination.x,245);
            break;
            case 190:
            checkSeated(destination.x,245);
            checkSeated(destination.x,215);
            break;
            // last row
            case 210:
            checkSeated(destination.x,235);
            break;
        }        
    }

    private void checkSeated(int x, int y)
    {
        List<Passenger> ps= getWorld().getObjectsAt(x,y, Passenger.class);
        if(ps.isEmpty())
        {
            current_state=STATE_MOVING;
        }else
        {
            //ps.get(0).moveOut(this);
            Passenger p=ps.get(0);            
            p.targetPoint.x=10;
            p.targetPoint.y=270; 
            p.notifier=this;
            p.current_state=Passenger.STATE_MOVING;
            current_state=STATE_WAITING;
        }
    }

//     private boolean checkBlocked()
//     {
//         //Actor p =getOneObjectAtOffset(2,1 , Passenger.class);
//         List<Passenger> ps= getObjectsInRange(20, Passenger.class);
//         if(!ps.isEmpty())
//         {   
//             for(Passenger p: ps)   
//             {   
//                 if(p.getX()>this.getX() & p.getX()<this.destination.x & p.getY()>this.getY() & p.getY()<this.destination.y
//                 || p.getX()>this.getX() & p.getX()<this.destination.x & p.getY()<this.getY() & p.getY()>this.destination.y
//                 ) 
//                 {
//                     return true;                    
//                 }else
//                 {
//                     continue;
//                 }
//             }
//         }
//         return false;
//     }

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
        else if(x==checkPoint2.x & y==checkPoint2.y)
        {
            targetPoint=checkPoint3;
            current_state=STATE_ArrivedPoint2;
        }
        else if(x==checkPoint3.x & y==checkPoint3.y)
        {
            targetPoint.x=destination.x;
            targetPoint.y=destination.y;
            current_state=STATE_PUTTING;
        }
        else if(x==targetPoint.x & y==targetPoint.y)
        {
            if(targetPoint.x==destination.x & targetPoint.y==destination.y)
            {
                current_state=STATE_SEATED;
            }else
            {
                current_state=STATE_WAITING;
            }

        }
    }

    public void moveOut(Passenger notifier)
    {
        this.notifier=notifier;
        targetPoint.x=checkPoint3.x-1;
        targetPoint.y=checkPoint3.y;        
        this.current_state=STATE_MOVING;
    }
}
