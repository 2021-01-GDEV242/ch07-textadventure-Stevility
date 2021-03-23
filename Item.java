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
    private ArrayList<Item> list = null;

  public void items() {
    list = new ArrayList<Item>();
  }

  public void addItem(Item item) {
     list.add(item);
  }

  // get methods

    
    /**
     * USed to get the description of the item.
     */
    public Item(String description)
    {
        this.description = description;
    }
    
    /**
     * Return the description of the item.
     */
    public String getShortDescription()
    {
        return description;
    }    
    
    /**
     * A long description of the item. (Will add just incase I want to add more details to the item.)
     */
    public String toString()
    {
        return "Collectable " + description;
    }
    
    /**
     * Used for adding an item to the room for when it is created.
     */
    public void addItem(String description,int weight)
    {
        list.add(new Item (description));
    }
    
    /**
     * This will allow me to get the item.
     */
    public ArrayList<Item> getItems()
    {
        return list;
    }
    
    /**
     * This will allow me to create a list of items.
     */
    public void add(Item sItem)
    {
        list.add(sItem);
    }
}