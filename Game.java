/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Steven Fabian
 * @version 2021.20.03
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom, prevRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        prevRoom = currentRoom;
    }

    /***
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, lab, office, checkpoint, east_dormitory, west_dormitory, bath_wash_room, store, chapel, yard,
        visitors_rooms, female_hospital, male_hospital, cafeteria, kitchen, boiler_room;
      
        // create the rooms
        lab = new Room("in a lab full of empty cabinets");
        outside = new Room("outside the main entrance of the Asylum");
        east_dormitory = new Room("in the east dormitory");
        west_dormitory = new Room("in the west dormitory");
        bath_wash_room = new Room("in the bathroom");
        store = new Room("in the Store Room full of rotten meat.");
        chapel = new Room("in the Chapel. In the middle there is a bible");
        yard = new Room("in the yard");
        visitors_rooms = new Room("in the visitor's room");
        female_hospital= new Room("in the female hospital yard");
        male_hospital= new Room("in the male hospital yard");
        cafeteria= new Room("in the cafeteria");
        kitchen= new Room("in the kitchen");
        boiler_room = new Room("in the boiler room");
        checkpoint = new Room("in the security checkpoint");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        lab.setExit("west", east_dormitory);
        lab.setExit("east", bath_wash_room);
        
        east_dormitory.setExit("north", visitors_rooms);
        east_dormitory.setExit("west", west_dormitory);
        
        west_dormitory.setExit("north", female_hospital);
        west_dormitory.setExit("east", east_dormitory);
        
        female_hospital.setExit("north", male_hospital);
        female_hospital.setExit("east", visitors_rooms);
        female_hospital.setExit("south", west_dormitory);
        
        male_hospital.setExit("east", cafeteria);
        male_hospital.setExit("south", female_hospital);
        
        visitors_rooms.setExit("north", cafeteria);
        visitors_rooms.setExit("south", east_dormitory);
        visitors_rooms.setExit("east", yard);
        visitors_rooms.setExit("west", female_hospital);
        
        cafeteria.setExit("west", male_hospital);
        cafeteria.setExit("south", visitors_rooms);
        cafeteria.setExit("east", kitchen);
        
        yard.setExit("west", visitors_rooms);
        yard.setExit("east", store);
        
        kitchen.setExit("west", cafeteria);
        kitchen.setExit("south", store);
        kitchen.setExit("east", boiler_room);
        
        store.setExit("north",kitchen);
        store.setExit("east",chapel);
        store.setExit("west",yard);
        store.setExit("south",bath_wash_room);
        
        bath_wash_room.setExit("north",store);
        bath_wash_room.setExit("east",office);
        bath_wash_room.setExit("west",lab);
        
        chapel.setExit("west",store);
        
        boiler_room.setExit("west", kitchen);
        
        office.setExit("west", bath_wash_room);
        
        outside.setExit("north", checkpoint);
        
        //items in the room
        //office.setItem("key", "A misterious key but to what door does it open?");
        
        
        currentRoom = lab;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Abandoned Asylum!");
        System.out.println("You have no memory or remember how you got here but you get the feeling you have to ESCAPE!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        //System.out.println(player.getInventoryString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
            
            /**case EAT:
                eat();
                break;
             **/  
            case BACK:
                goBack(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the room");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Used to go back to the previous room.
     */
    private void goBack(Command command)
    {
        if(!prevRoom.equals(currentRoom))
        {
            currentRoom = prevRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else
            System.out.println("You cannot go back any farther.");
    }
    
    /**
     * This is a command to look at the room.
     */
    private void look()
    {
       System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * This is a command to eat
     * private void eat()
    {
       System.out.println("You have eaten now and you are not hungry any more.");
    }
    **/
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
