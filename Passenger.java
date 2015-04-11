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
    public static final int STATE_WAITING1=6;
    public static final int STATE_WAITING2=7;
    public static final int STATE_INIT=8;
    public int current_state;
    public Seat destination;
    private Point checkPoint1=new Point(140,530);
    private Point checkPoint2=new Point(140,270);
    public Point checkPoint3;
    public Point targetPoint;
    private int move_speed;
    private int putting_time;
    private int timer=0;
    public Passenger notifier;
    public Passenger notifyee1;
    public Passenger notifyee2;
    private boolean hasInformed;

    public Passenger(Seat destination){
        this.destination=destination;
        this.current_state=STATE_INIT;
        checkPoint3=new Point(destination.x-20,270);
        targetPoint=new Point(checkPoint1.x,checkPoint1.y);
        move_speed= Greenfoot.getRandomNumber(4)+1;
        putting_time=Greenfoot.getRandomNumber(500);
        this.notifier=null;
        hasInformed=false;
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
            case STATE_INIT:
            break;

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

            case STATE_WAITING1:
            waiting1();
            break;

            case STATE_WAITING2:
            waiting2();
            break;

            case STATE_SEATED:
            destination.occupied=true;
            break;

        }       
    }

    private void waiting1()
    {
        if(notifyee1!=null & notifyee2!=null)
        {
            if(notifyee1.current_state==Passenger.STATE_WAITING2 & notifyee2.current_state==Passenger.STATE_WAITING2)
            { current_state=STATE_MOVING;}
        }
        if(notifyee1!=null)
        {
            if(notifyee1.current_state==Passenger.STATE_WAITING2)
            { current_state=STATE_MOVING;}      
        }
        if(notifyee2!=null)
        {
            if(notifyee2.current_state==Passenger.STATE_WAITING2)
            { current_state=STATE_MOVING;}  
        }        
    }

    private void waiting2()
    {
        if(notifier.current_state==Passenger.STATE_SEATED)
        {
            targetPoint.x=destination.x;
            targetPoint.y=destination.y;
            current_state=STATE_MOVING;
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
            checkSeated2(destination.x,325,300);
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
            checkSeated2(destination.x,245,215);            
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
            notifyee1=ps.get(0);            
            notifyee1.targetPoint.x=destination.x-15;
            notifyee1.targetPoint.y=270; 
            notifyee1.notifier=this;
            notifyee1.current_state=Passenger.STATE_MOVING;
            current_state=STATE_WAITING1;
        }
    }

    private void checkSeated2(int x, int y1,int y2)
    {
        List<Passenger> ps1= getWorld().getObjectsAt(x,y1, Passenger.class);
        List<Passenger> ps2= getWorld().getObjectsAt(x,y2, Passenger.class);
        if(!ps1.isEmpty())
        {
            notifyee1=ps1.get(0);
            notifyee1.targetPoint.x=destination.x-15;
            notifyee1.targetPoint.y=270; 
            notifyee1.notifier=this;
            notifyee1.current_state=Passenger.STATE_MOVING;
        }
        if(!ps2.isEmpty())
        {
            notifyee2=ps2.get(0);
            notifyee2.targetPoint.x=destination.x-15;
            notifyee2.targetPoint.y=270; 
            notifyee2.notifier=this;
            notifyee2.current_state=Passenger.STATE_MOVING;
        }
        if(notifyee1 ==null & notifyee2==null)
        {
            current_state=STATE_MOVING;
        }else
        {
            current_state=STATE_WAITING1;
        }
    }

    private void arrivedCheckPoint1()
    {
        if(!hasInformed)
        {
            Strategy.counter++;
            hasInformed=true;
        }
        
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
            targetPoint.x=checkPoint2.x;
            targetPoint.y=checkPoint2.y;
            current_state=STATE_ArrivedPoint1;
        }
        else if(x==checkPoint2.x & y==checkPoint2.y)
        {
            targetPoint.x=checkPoint3.x;
            targetPoint.y=checkPoint3.y;
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
            if(x==destination.x & y==destination.y)
            {
                current_state=STATE_SEATED;
            }else
            {
                current_state=STATE_WAITING2;
            }

        }
    }

}
