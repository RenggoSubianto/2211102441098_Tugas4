import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Font;
import java.util.concurrent.Phaser;

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{
    int speed = 2;
    int length; 
    MyWorld myWorld;
    
    class Direction {
        public static final int UP = 270;
        public static final int DOWN = 90;
        public static final int LEFT = 180;
        public static final int RIGHT = 0;
    }
    
    public Character(){
        length = getImage().getWidth();
    }
    
    public void addedToWorld(World w){
        myWorld = (MyWorld)w;
    }
    
    public void eatTarget(){
        Actor t = getOneIntersectingObject(Target.class);
        if(t!=null){
            myWorld.removeObject(t);
            Greenfoot.playSound("makan.mp3");
            MyWorld myw1 = (MyWorld)getWorld();
            myw1.updSkor(10);
            if(myw1.getSkorTikus()==50)
            {
                Greenfoot.stop();
                Greenfoot.setWorld(new Win());
                Greenfoot.playSound("menang.mp3");
            }
        }
    }
    
    public void KenaUlar()
    {
        if(isTouching(Enemy.class)){
          MyWorld myw1 = (MyWorld)getWorld();
            myw1.updNyawa();
            setLocation(60,540); 
            Greenfoot.playSound("ular.mp3"); 
            if(myw1.getNyawaTikus()<1)
            {
           removeTouching(Phaser.class);
           Greenfoot.stop();
           Greenfoot.setWorld(new GameOver());
            
          }
    }   
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown("up")){
             setRotation(Direction.UP);
             movePlayer();
        } else if(Greenfoot.isKeyDown("down")){
             setRotation(Direction.DOWN);
             movePlayer();
        } else if(Greenfoot.isKeyDown("left")){
             setRotation(Direction.LEFT);
             movePlayer();
        } else if(Greenfoot.isKeyDown("right")){
             setRotation(Direction.RIGHT);
             movePlayer();
        }
        KenaUlar();
        eatTarget();  
        
        MyWorld myw1 = (MyWorld)getWorld();
        getWorld().showText("Score : "+myw1.getSkorTikus(),90,25);
        getWorld().showText("Live : "+myw1.getNyawaTikus(),150,25);
          
    }
    
    public void movePlayer(){
        int currentX = getX();
        int currentY = getY(); 
        int direction = getRotation();
        int changeX = getChangeX(direction);
        int changeY = getChangeY(direction);
        int adjustedChangeX = adjustOffset(changeX);
        int adjustedChangeY = adjustOffset(changeY);
        Actor SpecialObject = getOneObjectAtOffset(adjustedChangeX, adjustedChangeY, SpecialObject.class);
        if(SpecialObject==null){
           setLocation(currentX + changeX,currentY + changeY);
        }
    }

    private int getChangeX(int direction){
        if(direction == Direction.RIGHT){
            return speed;   
        }
        if(direction == Direction.LEFT){
            return -speed;
        }
        return 0;
    }
    
    private int getChangeY(int direction){
        if(direction == Direction.DOWN){
            return speed;   
        }
        if(direction == Direction.UP){
            return -speed;
        }
        return 0;
    }
    
    private int adjustOffset(int offset){
        int signOfOffset = (int)Math.signum(offset);
        int distanceToFront = length/2;
        int adjustAmount = distanceToFront * signOfOffset;
        return offset + adjustAmount;
    }
}
