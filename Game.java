import java.util.ArrayList;

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
    private Timer timer;
    private Item roomItem, roomMsg;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        prevRoom = currentRoom;
        timer = new Timer(600, -1, 5);
    }

    /***
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, lab, office, checkpoint, east_dormitory, west_dormitory, bath_wash_room, store, chapel, yard,
        visitors_rooms, female_hospital, male_hospital, cafeteria, kitchen, boiler_room;
                
        //Creating the items
        Item message_1 = new Item("Message 1: [There was no way out!]",1 , "message_1 ");
        Item message_2 = new Item("Message 2: [END THE SUFFERING!]",2 , "message_2");
        Item message_3 = new Item("Message 3: [WE ARE WATCHING YOU!]",3 , "message_3");
        Item message_4 = new Item("Message 4: [Does pineapple go on pizza?]",4 , "message_4");
        Item message_5 = new Item("Message 5: [TRAITOR!]",5 , "message_5");
        Item message_6 = new Item("Message 6: [WhAt TiME Is tI?]",6 , "message_6");
        Item message_7 = new Item("Message 7: [Appointment: 1:30 AM]",7 , "message_7");
        Item message_8 = new Item("Message 8: [Everyone is dead!]",8 , "message_8");
        Item message_9 = new Item("Message 9: [I MISS RICE!]",9 , "message_9");
        Item message_10 = new Item("Message 10: [I wonder what its like.....]",10 , "message_10");
        Item message_11 = new Item("Message 11: [Its a TRAP! Try again]",11 , "message_11");
        Item message_12 = new Item("Message 12: [Kill!]",12 , "message_12");
        Item message_13 = new Item("Message 13: [AIRPLANES!]",13 , "message_13");
        Item message_14 = new Item("Message 14: [DONUTS!]",14 , "message_14");
        Item message_15 = new Item("Message 15: [CAR!]",15 , "message_15");
        Item message_16 = new Item("Message 16: [THE WIND!]",16 , "message_16");
        Item Cigar = new Item("Burnt Cigar",17 , "Cigar");
        Item Rope = new Item("Rope",18 , "Rope");
        Item Bands = new Item("Tight band",19 , "Bands");
        Item agility_ladder = new Item("Agility ladder",20 , "Agility ladder");
        Item dumbbell = new Item("dumbbell",21 , "dumbbell");
        Item syringe = new Item("syringe",22 , "syringe");
        Item Medicine = new Item("Medicine",23 , "Medicine");
        Item Defibrillator = new Item("Defibrillators",24 , "Defibrillators");
        Item Sterilizers = new Item("Sterilizers",25 , "Sterilizers");
        Item Blanket = new Item("Blanket",26 , "Blanket");
        Item Stretchers = new Item("Stretchers",27 , "Stretchers");
        Item Monitors = new Item("Monitors",28 , "Monitors");
        Item Axe = new Item("Axe",29 , "Axe");
        Item Calendar = new Item("Calendar",30 , "Calendar");
        Item Mat = new Item("Mat",31 , "Mat");
        Item Broken_key = new Item("Broken key",32 , "Broken key");

        // create the rooms
        lab = new Room("in a lab full of empty cabinets", message_12, Cigar);
        outside = new Room("outside the main entrance of the Asylum", message_16, Broken_key);
        east_dormitory = new Room("in the east dormitory", message_13, Monitors);
        west_dormitory = new Room("in the west dormitory", message_1 , Blanket);
        bath_wash_room = new Room("in the bathroom", message_10, syringe);
        store = new Room("in the Store Room full of rotten meat.", message_5, Axe);
        chapel = new Room("in the Chapel. In the middle there is a bible", message_11, Medicine);
        yard = new Room("in the yard", message_6, dumbbell);
        visitors_rooms = new Room("in the visitor's room", message_2, Stretchers);
        female_hospital= new Room("in the female hospital yard", message_8, Defibrillator);
        male_hospital= new Room("in the male hospital yard", message_3, Sterilizers);
        cafeteria= new Room("in the cafeteria", message_9, Mat);
        kitchen= new Room("in the kitchen", message_4, Bands);
        boiler_room = new Room("in the boiler room", message_7, agility_ladder);
        checkpoint = new Room("in the security checkpoint", message_14, Calendar);
        office = new Room("in the computing admin office", message_15, Rope);
        
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
                
        boiler_room.setExit("west", kitchen);
        
        office.setExit("west", bath_wash_room);
        
        outside.setExit("north", checkpoint);
        
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
        System.out.println("You have "+ timer +"s to win.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        boolean updateTimer = true;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                updateTimer = false;
                break;

            case HELP:
                printHelp();
                updateTimer = false; 
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look(command);
                break;
            
            case EAT:
                eat(command);
                break;
            
            case TAKE: 
                take();
                break;
              
            case BACK:
                goBack(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                updateTimer = false;
                break;
            
            case TIME:
                System.out.println("You have "+ timer +"s left...");
                break;
        }
        //This will be used to notify the player.
            if (updateTimer) {
            timer.updateTimer();
            if (timer.hasExpired()) {
                System.out.println("Time's up - you lost!");
                wantToQuit = true;
            } else if (timer.isLow()) {
                System.out.println("Time is running low!");
                System.out.println("You have "+ timer +"s left...");
            }
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
        System.out.println("You have "+ timer +"s to win.");
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
            //go back to the previous room.
            currentRoom = prevRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        else
            System.out.println("You cannot go back any farther.");
    }
    
    /**
     * This is a command to look at the room.
     */
    private void look(Command command)
    {
        if(command.hasSecondWord()) 
        {
               System.out.println("You are looking at ");
               return;
        }
            System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Lets me pick up item.
     */
    private void take()
    {
        System.out.println("You have picked up an Item");
    }
    
    /**
     * This is a command to eat. 
     */
    private void eat(Command command)

    {
        if(command.hasSecondWord()) 

        {

            System.out.println("What would you like to eat?");
              return ;

        }
            System.out.println("You have eaten now and you are not hungry anymore!");
    }
    
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
