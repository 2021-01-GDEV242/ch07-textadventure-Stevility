import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Timer - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Timer" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Timer 
{
    private int timer;
    private int update;
    private int low;
    
    public Timer(int defaultTime, int defaultUpdate, int defaultLow) {
        timer = defaultTime;
        update = defaultUpdate;
        low = defaultLow;
    }
    
    /**
     * prints out the timer.
     */
    public String toString() {
        return Integer.toString(timer);
    }
    
    /**
     * updates the timer
     */
    public void updateTimer() {
        timer += update;
    }
    
    /**
     * alter timer data
     */
    public void setTime(int time) {
        timer = time;
    }
    
    public void setUpdate(int update) {
        this.update = update;
    }
    
    public void setLow(int low) {
        this.low = low;
    }
    
    /**
     * This will check if the timer is low
     */
    public boolean isLow() {
        if (timer <= low) {
            return true;
        }
        return false;
    }
    
    /**
     * This will check if the timer is 0
     */
    public boolean hasExpired() {
        if (timer <= 0) {
            return true;
        }
        return false;
    }
}

