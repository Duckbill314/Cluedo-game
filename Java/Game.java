/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

// line 2 "model.ump"
// line 244 "model.ump"
public class Game {

    //-----------------------
    // ENUMERATIONS
    //------------------------

    public enum TurnOrder {Lucilla, Bert, Malina, Percy}
  
    //------------------------
    // MEMBER VARIABLES
    //------------------------
    private TurnOrder currentTurn = TurnOrder.Lucilla;
    private int diceTotal = 0;
    Board board = new Board();
    //Game Associations
    private final List<Player> players =  = new ArrayList<>();
    private final List<Card> cards =  = new ArrayList<>();

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Game() {
        bootGame();
        //players = new ArrayList<>();
        //cards = new ArrayList<>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public boolean hasPlayers() {
        return players.size() > 0;
    }

    public int indexOfPlayer(Player aPlayer) {
        return players.indexOf(aPlayer);
    }
     
        /* Code from template association_GetMany */

    public Card getCard(int index)

    {
        Card aCard = cards.get(index);
        return aCard;
    } 

    public List<Card> getCards()

    {
        List<Card> newCards = Collections.unmodifiableList(cards);
        return newCards;
    }

    public int numberOfCards()
    {
        int number = cards.size();
        return number;
    }

    public boolean hasCards()

    {
        boolean has = cards.size() > 0;
        return has;
    }

    public int indexOfCard(Card aCard)
    {
        int index = cards.indexOf(aCard);
        return index;
    }
    
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfPlayers = 0;

    /* Code from template association_AddUnidirectionalMany */
    public boolean addPlayer(Player aPlayer) {
        boolean wasAdded = false;
        if (players.contains(aPlayer)) {
            return false;
        }
        players.add(aPlayer);
        wasAdded = true;
        return wasAdded;
    }

    public boolean removePlayer(Player aPlayer) {
        boolean wasRemoved = false;
        if (players.contains(aPlayer)) {
            players.remove(aPlayer);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_AddIndexControlFunctions */
    public boolean addPlayerAt(Player aPlayer, int index) {
        boolean wasAdded = false;
        if (addPlayer(aPlayer)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfPlayers()) {
                index = numberOfPlayers() - 1;
            }
            players.remove(aPlayer);
            players.add(index, aPlayer);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMovePlayerAt(Player aPlayer, int index) {
        boolean wasAdded = false;
        if (players.contains(aPlayer)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfPlayers()) {
                index = numberOfPlayers() - 1;
            }
            players.remove(aPlayer);
            players.add(index, aPlayer);
            wasAdded = true;
        } else {
            wasAdded = addPlayerAt(aPlayer, index);
        }
        return wasAdded;
    }
    /* Code from template association_MinimumNumberOfMethod */

    public static int minimumNumberOfCards()

    {
        return 0;
    }

    /* Code from template association_AddUnidirectionalMany */

    public boolean addCard(Card aCard)

    {
        boolean wasAdded = false;
        if (cards.contains(aCard)) { return false; }
        cards.add(aCard);
        wasAdded = true;
        return wasAdded;
    }


    public boolean removeCard(Card aCard)

    {
        boolean wasRemoved = false;
        if (cards.contains(aCard))

        {
            cards.remove(aCard);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_AddIndexControlFunctions */

    public boolean addCardAt(Card aCard, int index)

    { 
        boolean wasAdded = false;
        if(addCard(aCard))

        {

            if(index < 0 ) { index = 0; }

            if(index > numberOfCards()) { index = numberOfCards() - 1; }

            cards.remove(aCard);

            cards.add(index, aCard);

            wasAdded = true;

        }
        return wasAdded;
    }


    public boolean addOrMoveCardAt(Card aCard, int index)

    {
        boolean wasAdded = false;
        if(cards.contains(aCard))

        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfCards()) { index = numberOfCards() - 1; }
            cards.remove(aCard);
            cards.add(index, aCard);
            wasAdded = true;
        }

        else

        {
            wasAdded = addCardAt(aCard, index);
        }
        return wasAdded;
    }

    public void delete() {
        players.clear();
        cards.clear();
    }


    /**
     * object using the turn order enum
     * private turnOrder currentTurn = turnOrder.Lucilla;
     * private int diceTotal = 0;
     * the number of move turns the player has left
     * private Board board = new Board();
     * <p>
     * Calls to, and initializes, fields and values that require generation upon a game's creation.
     * Finally, begin the game loop by calling gameManager().
     * If the game is fully 'booted' return gameManager() (also int), else return zero to account for errors in generation.
     */
    // line 29 "model.ump"
    public void bootGame() {
     //filling the board with empty tiles
        for(int i = 0;i<24;i++){
            List<Tile> row = new ArrayList<>();
            for(int j = 0;j<24;j++){
                row.add(new Tile());
            }
            board.add(row);
        }
        //filling those tiles in the board with items
        try {
            File file = new File("board.txt");
            Scanner scanner = new Scanner(file);
            int countX = 0;
            int countY = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); 
                for (char c : line.toCharArray()) {
                    Tile currentTile = board.getTile(countX, countY);
                    switch(c){
                        case 'X' : currentTile.setStored(new Wall("Wall","X",countX,countY));
                            break;
                        case 'G' : currentTile.setStored(new Wall("Grayspace","G",countX,countY));
                            break;
                        case 'E' : currentTile.setStored(new Entrance("Entrance"," ",countX,countY));
                            break;
                    }
                    countX++;
                }
                countX=0;
                countY++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + "board.txt");
            e.printStackTrace();
        }
         System.out.println("Welcome to Hobby Detectives!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to start the game? (yes/no): ");
        String startGameInput = scanner.nextLine();
        System. out. print('\u000C');
        if (!startGameInput.equalsIgnoreCase("yes")) {
            System.out.println("Game aborted. Goodbye!");
            scanner.close();
            endGame();
            }
        
        //get the number of players
        int numPlayers = 0;
        while (numPlayers < 1) {
            System.out.print("Enter the number of players (1-4): ");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());
                System. out. print('\u000C');
                if (numPlayers < 1 || numPlayers > 4) {
                    System.out.println("Invalid number of players. Please enter a number between 1 and 4.");
                    numPlayers = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
  
      //get player names
        List<String> names = new ArrayList<>();
        while (numPlayers != names.size()) {
            System.out.print("Player "+(names.size()+1)+" please enter your name : ");
            String name = scanner.nextLine();
            System. out. print('\u000C');
            if (name.length()>15) {       
                System.out.println("Sorry, your name can't exceed 15 characters");
            }else{
            names.add(name);
            }
        }
        // making the players
        System.out.println("Allocating roles for " + numPlayers + " players.");
        System.out.println();
        
        
        assignCharacters(names);
        
       String first = "";
        for (Player p : players){
        System.out.println(p.getName() + " will be playing as "+p.getCharacter().getName());
        if(p.getCharacter().getName().equals("Lucilla")){
        first = p.getName();
        }
        }
        System.out.println();
        System.out.println("Lucilla will be starting first, please pass the tablet to "+ first+".\n");
        System.out.println("\nBegin the your first round? (yes/no): ");
        startGameInput = scanner.nextLine();
        System.out.print('\u000C');
        
        if (!startGameInput.equalsIgnoreCase("yes")) {
            System.out.println("Game aborted. Goodbye!");
            scanner.close();
            endGame();
        }
        scanner.close();
        // makeing the cards
        makeCards();    
        System.out.println();
          for(Card c : cards){
            if(c.getIsMurder()){
            System.out.println("murder card : "+c.getName()+" "+c.getType());
            }  
          }
    }


    /**
     * Contains the primary gameLoop mechanics. Fails and returns 0 if error is detected, 1 if the game concludes successfully
     * Is the central hub for method calling and contains the main loop.
     */
    // line 45 "model.ump"
    private void gameManager() {
          while(true){
            System.out.print('\u000C');
            mainBoard.draw();
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print('\u000C');
                mainBoard.draw();
                System.out.print("Waiting for player to role dice... (roll): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("roll")) {
                    System.out.println("Rolling ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } 
                    System.out.print(". ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } 
                    System.out.print(". ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } 
                    System.out.print(". ");
                    break;
                }
            }
            int dice1 = rollDice();
            int dice2 = rollDice();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
            System.out.println("\nROLLED "+dice1);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
            System.out.println("ROLLED "+dice2);
            diceTotal = dice1+dice2;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } 
            switch (currentTurn) {
                case Lucilla:
                    takePlayerInput(players.get(0));
                    currentTurn = TurnOrder.Bert;
                    break;
                case Bert:
                    takePlayerInput(players.get(1));
                    currentTurn = TurnOrder.Malina;
                    break;
                case Malina:
                    takePlayerInput(players.get(2));
                    currentTurn = TurnOrder.Percy;
                    break;
                case Percy:
                    takePlayerInput(players.get(3));
                    currentTurn = TurnOrder.Lucilla;
                    break;
            }
        }
    }


    /**
     * Method to randomly return a number 1-6
     */
    // line 51 "model.ump"
    private int rollDice() {
        double max = 6;
        double min = 1;
        return (int) (Math.random() * (max - min + 1) + min); // implement random number 1-6 to simulate dice roll
    }


    /**
     * Method to get the next player input (up down left right) using a scanner (or if they are in a estate guess is a valid input)
     * Handle the Input (tell board to move if up/down/left/right) or call guess() if guess is called and player is in a estate
     * also needs to handle "roll" for rolling the dice (update diceTotal to the return value of rollDice())
     * needs to handle "grid on" "grid off" and capital versions of all commands + errors like invalid inputs.
     */
    // line 60 "model.ump"
    private void takePlayerInput(Player p) {
    }


    /**
     * let the player do a guess (assumed check for in a estate has already passed)
     * 0 for failed guess and 1 for correct guess
     */
    // line 66 "model.ump"
    private int guess(Player p) {
        return 0;
    }


    /**
     * placeholder for refute method
     */
    // line 70 "model.ump"
    private int refute(Player p) {
        return 0;
    }


    /**
     * placeholder for solve method, should invoke guess method
     */
    // line 74 "model.ump"
    private int solve(Player p) {
        return 0;
    }


    /**
     * creates the random cards that are excluded from the pool and creates the main pool of available cards and distributes them
     */
    // line 80 "model.ump"
    private void makeCards() {
           try {
            File file = new File("cards.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String cardName = scanner.nextLine();
                String type = scanner.nextLine();
                cards.add(new Card(false, null, cardName,type));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + "cards.txt");
            e.printStackTrace();
        }
        List<String> typesPickedForMurder = new ArrayList<>();
        System.out.println();
          while( typesPickedForMurder.size()!=3 ){
            Random random = new Random();
            int randomIndex = random.nextInt(cards.size());
            if(!typesPickedForMurder.contains(cards.get(randomIndex).getType())){
            cards.get(randomIndex).setIsMurder(true);
            typesPickedForMurder.add(cards.get(randomIndex).getType());
            }
          }
        // list of remaining cards that are not the murder cards
 List<Card> remainingCards = new ArrayList<>();
          for(Card c : cards){
             if(!c.getIsMurder()){
              remainingCards.add(c);
             }
          }
        //assigning those cards to players randomly
         
           while( !remainingCards.isEmpty() ){
            List<Player> playerHolder = new ArrayList<>();
            for(Player p : players){
               playerHolder.add(p); 
            }
            while ( !playerHolder.isEmpty() ){
               Random random = new Random();
               int randomPlayer = random.nextInt(playerHolder.size());
               
               Player currentPlayer = playerHolder.get(randomPlayer);
               playerHolder.remove(randomPlayer);
               
               if(remainingCards.size() == 0){
               break; 
               }
               random = new Random();
               int randomCard = random.nextInt(remainingCards.size());
               
               Card currentCard = remainingCards.get(randomCard);
               remainingCards.remove(randomCard);
               
               currentPlayer.addCard(currentCard);
               currentCard.setOwner(currentPlayer);
            }   
           }
           
           System.out.println();
          for(Card c : cards){
            if(!c.getIsMurder()){  
            System.out.println(c.getName()+" "+c.getType()+" is owned by "+c.getOwner().getCharacter().getName());
            }  
          }
    }


    /**
     * assigns each player a character randomly (must be called after makeCards())
     */
    // line 85 "model.ump"
    private void assignCharacters(List<String> names) {
      switch (names.size()){
            case 1 : 
                players.add(new Player(new Character("Lucilla","L",11,1),new Worksheet(),names.get(0),true));
                break;
            case 2 :
                players.add(new Player(new Character("Lucilla","L",11,1),new Worksheet(),names.get(0),true));
                players.add(new Player(new Character("Bert","B",1,9),new Worksheet(),names.get(1),true));
                break;
            case 3 :
                players.add(new Player(new Character("Lucilla","L",11,1),new Worksheet(),names.get(0),true));
                players.add(new Player(new Character("Bert","B",1,9),new Worksheet(),names.get(1),true));
                players.add(new Player(new Character("Malina","M",9,22),new Worksheet(),names.get(2),true));
                break;
            case 4 :
                players.add(new Player(new Character("Lucilla","L",11,1),new Worksheet(),names.get(0),true));
                players.add(new Player(new Character("Bert","B",1,9),new Worksheet(),names.get(1),true));
                players.add(new Player(new Character("Malina","M",9,22),new Worksheet(),names.get(2),true));
                players.add(new Player(new Character("Percy","P",22,11),new Worksheet(),names.get(3),true));
                break;
                default : System.out.println("Fail");
        }
    }


    /**
     * ends the game for a when a player wins
     */
    // line 90 "model.ump"
    private void endGame() {
      new Game();
    }


    /**
     * User Interactable method to start the program.
     * Manages bootGame() return values of 0 & 1 respectively for successful or failed attempts to boot
     */
    // line 97 "model.ump"
    public static void main(String... args) {
        Game game = new Game();
    }

}
