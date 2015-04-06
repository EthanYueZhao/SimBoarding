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
    public static final int STATE_SEATED=1;
    public static final int STATE_MOVING=2;
    private int current_state;
    private Seat destination;
    private Point checkPoint1=new Point(145,500);
    private Point checkPoint2=new Point(145,267);
    private Point checkPoint3;
    private Point targetPoint;
    private int move_speed;
    public Passenger(Seat destination){
        this.destination=destination;
        this.current_state=STATE_IDLE;
        checkPoint3=new Point(destination.x-20,267);
        targetPoint=checkPoint1;
        move_speed= Greenfoot.getRandomNumber(4);
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
            checkBlocked();
            break;
            case STATE_MOVING:            
            checkCurrentPoint();
            turnTowards(targetPoint.x,targetPoint.y);
            move(1);
            checkBlocked();
            break;

            case STATE_SEATED:
            break;

        }
        // Add your action code here.
    }

    private void checkBlocked()
    {
        Actor p = getOneObjectAtOffset(17, 0, Passenger.class);
        if(p!=null)
        {    
            current_state=STATE_IDLE;   
        }else
        {
            current_state=STATE_MOVING;  
        }
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
        if(x==destination.x & y==destination.y)
        {
            current_state=STATE_SEATED;
        }
    }
}
