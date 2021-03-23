import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Item - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Item" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Item 
{
    private int weight;
    private String name;
    private String Idescription;
 
    public Item(int weight, String name, String Idescription)
    {
        this.weight = weight;
        this.name = name;
        this.Idescription = Idescription;
    }
 
    public String getName()
    {
        return name;
    }
 
    public String getDescription()
    {
        return Idescription;
    }
}