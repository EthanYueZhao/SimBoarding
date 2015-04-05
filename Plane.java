import greenfoot.*;

/**
 * Write a description of class plane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plane extends World
{
 private static final int SEAT_NUMBER=137;
    /**
     * Constructor for objects of class plane.
     * 
     */
    public Plane()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1400, 700, 1); 

        prepare();
    }

    /**
     * 开始前先为你的剧本创建场景
     * 添加元件到你的场景
     */
    private void prepare()
    {
  
            
          for(int i=0;i<SEAT_NUMBER;i++){
            Passenger p=new Passenger(Passenger.STATE_IDLE);
            int x=10+i*20;
            int y=600;
            if(x>=1380){
                x=x-1380;
                y=y+30;
            }
                        
            addObject(p, x, y);
        }
  
    }
}
