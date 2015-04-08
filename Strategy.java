import greenfoot.*;

/**
 * Write a description of class Strategy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Strategy extends Actor
{
    private boolean phase1;
    private boolean phase2;
    private boolean phase3;
    private boolean added;
    public static int counter=0;
    private boolean phase2_done;
    private boolean phase3_done;

    public Strategy()
    {
        phase1=true;
        phase2=false;
        phase3=false;
        added=false;
        phase2_done=false;
        phase3_done=false;
    }

    /**
     * Act - do whatever the Strategy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {
        //execRandom();
        execWinMidAil();
    }   

    private void execRandom()
    {
        if(!added)
        {
            for(int i=0;i<Plane.SEAT_NUMBER;i++){
                int x=200+i*20;
                int y=530;
                if(x>=1380){
                    x=x-1180;
                    y=y+25;
                }
                if(x>=1380){
                    x=x-1180;
                    y=y+25;
                }
                getWorld().addObject(Plane.passengers.get(i), x, y);
            }
            added=true;
        }
        if(phase1)
        {
            for(Passenger p: Plane.passengers)
            {
                p.current_state=Passenger.STATE_IDLE;
            }
            phase1=false;
        }
    }

    private void execWinMidAil()
    {
        if(!phase3_done)
        {
            if(!added)
            {
                int x=200;
                int y=530;
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==350 | p.destination.y==190 | p.destination.y==345 | p.destination.y==195 | p.destination.y==210)
                    {
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        p.current_state=Passenger.STATE_IDLE;
                        getWorld().addObject(p, x, y);
                        x+=20;
                    }                
                }
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==305 | p.destination.y==235 | p.destination.y==215 | p.destination.y==325)
                    {
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        p.current_state=Passenger.STATE_INIT;
                        getWorld().addObject(p, x, y);
                        x+=20;
                    }                
                }
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==300 | p.destination.y==245)
                    {
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        if(x>=1380){
                            x=x-1180;
                            y=y+25;
                        }
                        p.current_state=Passenger.STATE_INIT;
                        getWorld().addObject(p, x, y);
                        x+=20;
                    }                
                }
                added=true;
            }

            if(phase1)
            {
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==350 | p.destination.y==190 | p.destination.y==345 | p.destination.y==195)
                    {
                        p.current_state=Passenger.STATE_IDLE;
                    }                
                }
                phase1=false;
            }

            if(counter>47 & counter<97)
            {
                phase2=true;
            }        
            if(!phase2_done & phase2)
            {
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==305 | p.destination.y==235 | p.destination.y==215 | p.destination.y==325)
                    {
                        p.current_state=Passenger.STATE_IDLE;
                    }                
                }
                phase2=false;
                phase2_done=true;
            }

            if(counter>95)
            {
                phase3=true;
            }
            if(!phase3_done & phase3)
            {
                for(Passenger p: Plane.passengers)
                {
                    if(p.destination.y==300 | p.destination.y==245 )
                    {
                        p.current_state=Passenger.STATE_IDLE;
                    }                
                }
                phase3=false;
                phase3_done=true;
            }
        }
    }
}
