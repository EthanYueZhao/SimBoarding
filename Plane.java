import greenfoot.*;
import java.util.*;

/**
 * Write a description of class plane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plane extends World
{
    public static final int SEAT_NUMBER=137;
    public static ArrayList<Seat> seats;//=new ArrayList();
    public static ArrayList<Passenger> passengers;//=new ArrayList();

    /**
     * Constructor for objects of class plane.
     * 
     */
    public Plane()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1400, 700, 1); 
        seats=new ArrayList();
        passengers=new ArrayList();
        initSeats();
        prepare();
    }

    private void prepare()
    {
        Timer timer=new Timer();
        addObject(timer, 40,30 );
        Strategy strategy=new Strategy();
        addObject(strategy,40,60);
        Collections.shuffle(seats);
        for(int i=0;i<SEAT_NUMBER;i++){
            Seat s=seats.get(i);
            Passenger p=new Passenger(s);
            passengers.add(p);
            //             int x=200+i*20;
            //             int y=530;
            //             if(x>=1380){
            //                 x=x-1180;
            //                 y=y+25;
            //             }
            //             if(x>=1380){
            //                 x=x-1180;
            //                 y=y+25;
            //             }
            //addObject(p, x, y);
        }

    }

    private void initSeats()
    {
        for(int i=0;i<3;i++)
        {
            int x=202+i*45;
            int a=345;
            int b=305;
            int c=235;
            int d=195;            
            Seat s1=new Seat(x,a);
            seats.add(s1);
            Seat s2=new Seat(x,b);
            seats.add(s2); 
            Seat s3=new Seat(x,c);
            seats.add(s3); 
            Seat s4=new Seat(x,d);
            seats.add(s4);
        }
        for(int i=0;i<7;i++)
        {
            int x=340+i*43;
            int a=350;
            int b=325;
            int c=300;
            int d=245;
            int e=215;
            int f=190;
            Seat s1=new Seat(x,a);
            seats.add(s1);
            Seat s2=new Seat(x,b);
            seats.add(s2); 
            Seat s3=new Seat(x,c);
            seats.add(s3); 
            Seat s4=new Seat(x,d);
            seats.add(s4);
            Seat s5=new Seat(x,e);
            seats.add(s5);
            Seat s6=new Seat(x,f);
            seats.add(s6);
        }
        for(int i=0;i<13;i++)
        {
            int x=650+i*42;
            int a=350;
            int b=325;
            int c=300;
            int d=245;
            int e=215;
            int f=190;
            Seat s1=new Seat(x,a);
            seats.add(s1);
            Seat s2=new Seat(x,b);
            seats.add(s2); 
            Seat s3=new Seat(x,c);
            seats.add(s3); 
            Seat s4=new Seat(x,d);
            seats.add(s4);
            Seat s5=new Seat(x,e);
            seats.add(s5);
            Seat s6=new Seat(x,f);
            seats.add(s6);
        }
        for(int i=0;i<1;i++)
        {
            int x=1190;
            int a=350;
            int b=325;
            int c=300;
            int d=235;
            int e=210;            
            Seat s1=new Seat(x,a);
            seats.add(s1);
            Seat s2=new Seat(x,b);
            seats.add(s2); 
            Seat s3=new Seat(x,c);
            seats.add(s3); 
            Seat s4=new Seat(x,d);
            seats.add(s4);
            Seat s5=new Seat(x,e);
            seats.add(s5);           
        }        
    }
}
