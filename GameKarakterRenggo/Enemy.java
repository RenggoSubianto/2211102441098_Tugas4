import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private static final int KANAN = 0;
    private static final int KIRI = 1;
    private static final int ATAS = 2;
    private static final int BAWAH = 3;
    public int ARAH=KANAN;
    public int GRK;
    public boolean status;

    public void act() 
    {
      gerak();
    }    
    
    public void gerak()
    {
        if (!bisa_gerak(ARAH))
        {
            GRK= Greenfoot.getRandomNumber(4);
            while (GRK==ARAH)
            GRK= Greenfoot.getRandomNumber(4);
            ARAH=GRK;
        }   

            
        if (ARAH==KANAN)
        {
           setLocation(getX()+1,getY());
           if (isSpecialObject())
               setLocation(getX()-1,getY());
        }
        
        if (ARAH==KIRI)
        {
           setLocation(getX()-1,getY());
           if (isSpecialObject())
               setLocation(getX()+1,getY());
        }
        
        if (ARAH==ATAS)
        {
           setLocation(getX(),getY()-1);
           if (isSpecialObject())
               setLocation(getX(),getY()+1);
        }
        
        if (ARAH==BAWAH)
        {
           setLocation(getX(),getY()+1);
           if (isSpecialObject())
               setLocation(getX(),getY()-1);
        }
    }
    
    public int getARAH()
    {
        return ARAH;
    }
    
    public boolean bisa_gerak(int ARAH_GRK)
    {
        Actor Obstacle;
        switch (ARAH_GRK)
        {
            case KANAN : Obstacle = getOneObjectAtOffset(1,0,SpecialObject.class);
                         if (Obstacle!=null || getX()==9) status=false; else status=true; break;
            
            case KIRI  : Obstacle = getOneObjectAtOffset(-1,0,SpecialObject.class);
                         if (Obstacle!=null || getX()==0) status=false; else status=true; break;
            
            case ATAS  : Obstacle = getOneObjectAtOffset(0,-1,SpecialObject.class);
                         if (Obstacle!=null || getY()==0) status=false; else status=true; break;
          
            case BAWAH : Obstacle = getOneObjectAtOffset(0,1,SpecialObject.class);
                         if (Obstacle!=null || getY()==6) status=false; else status=true; break;
                         
            default    : status=false;
        }
        return (status);
    }
    
    public boolean isSpecialObject()
    {
        Actor Obstacle = getOneObjectAtOffset(0,0,SpecialObject.class);
        if (Obstacle!=null)
            return true;
        else
            return false;
    }
}
