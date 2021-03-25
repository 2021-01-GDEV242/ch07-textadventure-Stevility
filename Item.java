import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

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
   private String description;
   private String name;
   private ArrayList<Item> items;   // stores Items
 
    /**
    * Constructor for the Class Items
    */
    public Item(String description,int weight, String name)
    {
    this.description = description;
    this.name = name;
    ArrayList items = new ArrayList (20);
   }
   
    /**
     * Item description
     */
   public String getDescription()
   {
        return description;
   }
   
    /**
     * Returns the name of the items
     */
    public String getName()
    {
        return name;
    }  
}