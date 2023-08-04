import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class Game {

    public enum TurnOrder {Lucilla, Bert, Malina, Percy}

    private TurnOrder currentTurn = TurnOrder.Lucilla;
    private int diceTotal = 0;
    Board board = new Board();

    private final List<Player> players = new ArrayList<>();
    private final List<Card> cards = new ArrayList<>();
    private List<GameTile> usedGameTiles = new ArrayList<>();

    public Game() {
        board.draw();

        System.out.print('\u000C');
        System.out.println("Welcome to Hobby Detectives!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to start the game?");
        System.out.println();

        System.out.println("Enter 1 for yes.");
        System.out.println("Enter 2 for no.");


        String startGameInput = scanner.nextLine();
        System.out.print('\u000C');
        if (!startGameInput.equalsIgnoreCase("1")) {
            System.out.println("Game aborted. Goodbye!");
            scanner.close();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            endGame();
        }

        //get the number of players
        int numPlayers = 0;

        System.out.print("Please select the number of players.\n");
        System.out.println();
        System.out.println("Enter 1 to play with three players and a bot.");
        System.out.println("Enter 2 to play with four players.");
        startGameInput = scanner.nextLine();
        if (!startGameInput.equalsIgnoreCase("1")) {
            numPlayers = 4;
        } else {
            numPlayers = 3;
        }

        //get player names
        System.out.print('\u000C');
        List<String> names = new ArrayList<>();
        while (numPlayers != names.size()) {
            System.out.println("Setting up game.\n");
            for (String n : names) {
                System.out.println(n);
            }
            System.out.println();
            System.out.print("Player " + (names.size() + 1) + " please enter your name : ");
            String name = scanner.nextLine();
            System.out.print('\u000C');
            if (name.length() > 15) {
                System.out.println("Sorry, your name can't exceed 15 characters");
            } else {
                names.add(name);
            }
        }
        // making the players
        assignCharacters(names);
        while (true) {
            System.out.println("Allocating roles for " + numPlayers + " players.");
            System.out.println();


            String first = "";
            for (Player p : players) {
                System.out.println(p.getName() + " will be playing as " + p.getCharacter().getName());
                if (p.getCharacter().getName().equals("Lucilla")) {
                    first = p.getName();
                }
            }
            System.out.println();
            System.out.println("Lucilla will be starting first, please pass the tablet to " + first + ".\n");
            System.out.println("\nBegin the your first round?");
            System.out.println();

            System.out.println("Enter 1 for yes.");

            String input = scanner.nextLine();
            if (input.equals("1")) {
                break;
            }
            System.out.print('\u000C');
        }
        scanner.close();
        board.draw();
        //draw the characters on the board
        for (Player p : players) {
            Tile t = board.getTile(p.getCharacter().getY(), p.getCharacter().getX());
            if (t instanceof GameTile) {
                ((GameTile) t).setStored(p.getCharacter());
            }
        }
        // making the cards
        makeCards();
        System.out.println();
        for (Card c : cards) {
            if (c.getIsMurder()) {
                System.out.println("murder card : " + c.getName() + " " + c.getType());
            }
        }
        gameManager();
        endGame();
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public int numberOfCards() {
        return cards.size();
    }

    public void delete() {
        players.clear();
        cards.clear();
    }

    // JAMES' WORKING CODE

    /**
     * assigns each player a character randomly (must be called after makeCards())
     */
    // line 85 "model.ump"
    private void assignCharacters(List<String> names) {
        switch (names.size()) {
            case 3:
                players.add(new Player(new Character("Lucilla", "L", 11, 1), new Worksheet(), names.get(0), true));
                players.add(new Player(new Character("Bert", "B", 1, 9), new Worksheet(), names.get(1), true));
                players.add(new Player(new Character("Malina", "M", 9, 22), new Worksheet(), names.get(2), true));
                break;
            case 4:
                players.add(new Player(new Character("Lucilla", "L", 11, 1), new Worksheet(), names.get(0), true));
                players.add(new Player(new Character("Bert", "B", 1, 9), new Worksheet(), names.get(1), true));
                players.add(new Player(new Character("Malina", "M", 9, 22), new Worksheet(), names.get(2), true));
                players.add(new Player(new Character("Percy", "P", 22, 11), new Worksheet(), names.get(3), true));
                break;
            default:
                System.out.println("Fail");
        }
    }

    /**
     * creates the random cards that are excluded from the pool and creates the main pool of available cards and distributes them
     */
    private void makeCards() {
        cards.add(new Card(false, null, "Lucilla", "character"));
        cards.add(new Card(false, null, "Bert", "character"));
        cards.add(new Card(false, null, "Malina", "character"));
        cards.add(new Card(false, null, "Percy", "character"));
        cards.add(new Card(false, null, "Broom", "weapon"));
        cards.add(new Card(false, null, "Scissors", "weapon"));
        cards.add(new Card(false, null, "Knife", "weapon"));
        cards.add(new Card(false, null, "Shovel", "weapon"));
        cards.add(new Card(false, null, "iPad", "weapon"));
        cards.add(new Card(false, null, "Haunted House", "estate"));
        cards.add(new Card(false, null, "Manic Manor", "estate"));
        cards.add(new Card(false, null, "Visitation Villa", "estate"));
        cards.add(new Card(false, null, "Calamity Castle", "estate"));
        cards.add(new Card(false, null, "Peril Palace", "estate"));


        List<String> typesPickedForMurder = new ArrayList<>();
        System.out.println();
        while (typesPickedForMurder.size() != 3) {
            Random random = new Random();
            int randomIndex = random.nextInt(cards.size());
            if (!typesPickedForMurder.contains(cards.get(randomIndex).getType())) {
                cards.get(randomIndex).setIsMurder(true);
                typesPickedForMurder.add(cards.get(randomIndex).getType());
            }
        }
        // list of remaining cards that are not the murder cards
        List<Card> remainingCards = new ArrayList<>();
        for (Card c : cards) {
            if (!c.getIsMurder()) {
                remainingCards.add(c);
            }
        }
        //assigning those cards to players randomly

        while (!remainingCards.isEmpty()) {
            List<Player> playerHolder = new ArrayList<>();
            for (Player p : players) {
                playerHolder.add(p);
            }
            while (!playerHolder.isEmpty()) {
                Random random = new Random();
                int randomPlayer = random.nextInt(playerHolder.size());

                Player currentPlayer = playerHolder.get(randomPlayer);
                playerHolder.remove(randomPlayer);

                if (remainingCards.size() == 0) {
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

        for (Player p : players) {
            for (Card c : p.getCards()) {
                p.getWorksheet().addCard(c);
            }
        }
    }

    public void displayLocations() {
        //locational infomation
        System.out.println("Location information: \n");
        for (Player p : players) {
            String s = " is on the main board";
            if (p.getCharacter().getEstate() != null) {
                s = " is in " + p.getCharacter().getEstate().getName();
            }
            System.out.println(p.getCharacter().getName() + s);
        }
        System.out.println("");
    }

    /**
     * let the player do a guess (assumed check for in a estate has already passed)
     * 0 for failed guess and 1 for correct guess
     */
    // line 66 "model.ump"
    private int guess(Player p) {
        //guess UI

        String input = "";
        boolean characterSelected = false;
        boolean weaponSelected = false;
        String character = "";
        String weapon = "";
        Scanner scanner = new Scanner(System.in);

        while (!characterSelected) {
            System.out.print('\u000C');
            System.out.println("WARNING, by guessing you forfit any remaning moves you have. Your turn will end once you guess.\n");
            System.out.println("Estate : " + p.getCharacter().getEstate().getName());
            System.out.println("\nSelect a Character to guess.\n");

            System.out.println("Enter 1 to select Lucilla.");
            System.out.println("Enter 2 to select Bert.");
            System.out.println("Enter 3 to select Malina.");
            System.out.println("Enter 4 to select Percy.");

            System.out.println("\nEnter 5 to cancel this guess.");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    character = "Lucilla";
                    break;
                case "2":
                    character = "Bert";
                    break;
                case "3":
                    character = "Malina";
                    break;
                case "4":
                    character = "Percy";
                    break;
                case "5":
                    return 2;
            }
            if (!character.equals("")) {
                characterSelected = true;
            }
        }
        while (!weaponSelected) {
            System.out.print('\u000C');
            System.out.println("Estate : " + p.getCharacter().getEstate().getName());
            System.out.println("Character : " + character);
            System.out.println("\nSelect a Weapon to guess.\n");

            System.out.println("Enter 1 to select Broom.");
            System.out.println("Enter 2 to select Scissors.");
            System.out.println("Enter 3 to select Knife.");
            System.out.println("Enter 4 to select Shovel.");
            System.out.println("Enter 5 to select iPad.");

            System.out.println("\nEnter 6 to cancel this guess.");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    weapon = "Broom";
                    break;
                case "2":
                    weapon = "Scissors";
                    break;
                case "3":
                    weapon = "Knife";
                    break;
                case "4":
                    weapon = "Shovel";
                    break;
                case "5":
                    weapon = "iPad";
                    break;
                case "6":
                    return 2;
            }
            if (!weapon.equals("")) {
                weaponSelected = true;
            }
        }
        input = "0";
        while (!input.equals("1") || !input.equals("2")) {
            System.out.print('\u000C');
            System.out.println("Estate : " + p.getCharacter().getEstate().getName());
            System.out.println("Character : " + character);
            System.out.println("Weapon : " + weapon);
            System.out.println("\nGuess : " + character + " commited the murder in the " + p.getCharacter().getEstate().getName() + " with the " + weapon.toLowerCase() + ".\n");
            System.out.println("place guess?\n");
            System.out.println("Enter 1 for yes.");
            System.out.println("Enter 2 to cancel this guess.");
            input = scanner.nextLine();
            switch (input) {

                case "1":
                    diceTotal = 0;
                    // if win (only works on final guesses)
                    boolean win = true;
                    for (Card c : cards) {
                        if (c.getIsMurder()) {
                            switch (c.getType()) {
                                case "estate":
                                    if (!c.getName().equals(p.getCharacter().getEstate().getName())) {
                                        win = false;
                                    }
                                    break;
                                case "character":
                                    if (!c.getName().equals(character)) {
                                        win = false;
                                    }
                                    break;
                                case "weapon":
                                    if (!c.getName().equals(weapon)) {
                                        win = false;
                                    }
                                    break;
                            }
                        }
                    }
                    if (win) {
                        return 1;
                    }
                    // first, we need to find the ordering of player turns at the moment, dont modify the enum beacause these are not real turns
                    int turn = 0;
                    input = "0";
                    boolean gotMatch = false;
                    String cardName = "";
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).getCharacter().getName().equals(p.getCharacter().getName())) {
                            turn = i;
                        }
                    }
                    // then we need to pass this guess onto the refute method for the next 3 players
                    // but only if the next player actually has a card in the guess
                    for (int i = 0; i < 3; i++) {
                        int playerId = i + turn + 1;
                        if (playerId > 3) {
                            playerId = playerId - 4;
                        }

                        for (Card c : players.get(playerId).getCards()) {
                            if (c.getName().equals(weapon) || c.getName().equals(character) || c.getName().equals(p.getCharacter().getEstate().getName())) {
                                cardName = refute(players.get(playerId), p, character, weapon);
                                input = "0";
                                while (!input.equals("1")) {
                                    System.out.print('\u000C');
                                    System.out.println(players.get(playerId).getName() + " revealed the " + cardName + " card was in their hand.\n");
                                    p.getWorksheet().addShownCard(c);
                                    System.out.println("Please pass the tablet back to " + p.getName());
                                    System.out.println("\nEnter 1 to continue.");
                                    input = scanner.nextLine();
                                }
                                return 0;
                            }
                        }

                    }
                    System.out.print('\u000C');
                    input = "0";
                    while (!input.equals("1")) {
                        System.out.print('\u000C');
                        System.out.println("No other player is holding any of those cards.");
                        System.out.println("\nEnter 1 to continue.");
                        input = scanner.nextLine();
                    }
                    return 0;
                case "2":
                    return 2;
            }
        }
        scanner.close();
        return 0;
    }

    private String refute(Player p, Player guesser, String character, String weapon) {
        Scanner scanner = new Scanner(System.in);
        String input = "0";

        while (!input.equals("1")) {
            System.out.print('\u000C');
            System.out.println(guesser.getName() + " has called a guess!\n");
            //System.out.println("The guess was : "+character+" commited the murder in the "+p.getCharacter().getEstate().getName()+" with the "+weapon.toLowerCase()+".\n");
            System.out.println("Pass the tablet to " + p.getName() + " so they can refute\n");
            System.out.println("Enter 1 to start your refute.");
            input = scanner.nextLine();
        }
        input = "0";
        System.out.print('\u000C');
        p.getWorksheet().printWorksheet();
        System.out.println("The guess was : " + character + " commited the murder in the " + guesser.getCharacter().getEstate().getName() + " with the " + weapon.toLowerCase() + ".\n");
        int count = 1;
        List<String> refuteableCards = new ArrayList<>();
        for (Card c : p.getCards()) {
            if (c.getName().equals(weapon) || c.getName().equals(character) || c.getName().equals(guesser.getCharacter().getEstate().getName())) {

                System.out.println("Enter " + count + " to refute with " + c.getName() + ".");
                refuteableCards.add(c.getName());
                count++;
            }
        }
        boolean enteredValidNumber = false;
        while (!enteredValidNumber) {
            input = scanner.nextLine();
            for (int i = 0; i < refuteableCards.size(); i++) {
                if (i + 1 == Integer.parseInt(input)) {
                    enteredValidNumber = true;
                }
            }
        }
        scanner.close();
        return refuteableCards.get(Integer.parseInt(input) - 1);
    }

    /**
     * placeholder for solve method, should invoke guess method in some way but also cost the player their guess attempt
     */
    private int solve(Player p) {
        return guess(p);
    }

    /**
     * Method to randomly return a number 1-6
     */
    private int rollDice() {
        double max = 6;
        double min = 1;
        return (int) (Math.random() * (max - min + 1) + min); // implement random number 1-6 to simulate dice roll
    }

    // END OF JAMES' WORKING CODE

    /**
     * User Interactable method to start the program.
     * Manages bootGame() return values of 0 & 1 respectively for successful or failed attempts to boot
     */
    public static void main(String... args) {
        Game game = new Game();
    }


    /**
     * ends the game for a when a player wins
     */
    private void endGame() {
        new Game();
    }

    /**
     * Contains the primary gameLoop mechanics. Fails and returns 0 if error is detected, 1 if the game concludes successfully
     * Is the central hub for method calling and contains the main loop.
     */

    private void gameManager() {
        Player turn = players.get(0);
        String input = "0";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (turn.getIsEligible()) {
                boolean diceRolled = false;
                while (diceTotal != 0 || !diceRolled) {
                    System.out.print('\u000C');
                    board.draw();
                    //work sheet print
                    turn.getWorksheet().printWorksheet();
                    displayLocations();

                    //normal movement or action
                    if (diceRolled && !input.equals("2")) {

                        System.out.println("You have " + diceTotal + " moves remaining!");
                        System.out.println("Do you want to move your character or do an action?");
                        System.out.println();

                        System.out.println("Enter 1 to open the movement menu");
                        System.out.println("Enter 2 to do an action.");

                        input = scanner.nextLine();
                        if (input.equals("1")) {
                            takePlayerInput(turn);
                            input = "0";
                        }
                        if (!input.equals("0")) {
                            System.out.print('\u000C');
                            board.draw();
                            //work sheet print
                            turn.getWorksheet().printWorksheet();
                            displayLocations();
                        }
                    }

                    if (input.equals("1") && !diceRolled) {//roll dice since dice has not been roled yet
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
                        int dice1 = rollDice();
                        int dice2 = rollDice();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("\nROLLED " + dice1);
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("ROLLED " + dice2);
                        diceTotal = dice1 + dice2;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        diceRolled = true;
                    }

                    if (input.equals("2")) {//open action menu

                        System.out.println("What action do you want to do?");
                        System.out.println();

                        System.out.println("Enter 1 to make a guess.");
                        System.out.println("Enter 2 to make a solve attempt.");
                        System.out.println("Enter 3 to toggle grid on / grid off.");
                        System.out.println("Enter 4 to return to the previous menu.");

                        input = scanner.nextLine();

                        if (input.equals("1") && turn.getCharacter().getEstate() != null) {
                            guess(turn);
                        }
                        if (input.equals("2") && turn.getCharacter().getEstate() != null) {
                            int i = solve(turn);
                            if (i == 1) {
                                System.out.print('\u000C');
                                System.out.println(turn.getName() + " guessed correctly!");
                                System.out.println(turn.getName() + " Wins the game!");
                                System.out.println("\ngame will restart in 30 seconds");
                                try {
                                    Thread.sleep(30000);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                                endGame();
                            } else if (i == 0) {
                                turn.setIsEligible(false);
                            }
                        }
                        if (input.equals("3")) {
                            board.gridOn();
                        }
                        if (input.equals("4")) {
                            input = "0";
                        } else {
                            input = "2";
                        }

                    } else if (!diceRolled) {//default role dice or action assuming dice have not been rolled yet
                        System.out.println("Do you want to roll your dice or do an action?");
                        System.out.println();

                        System.out.println("Enter 1 to roll your dice.");
                        System.out.println("Enter 2 to do an action.");

                        input = scanner.nextLine();
                    }

                }
            }
            switch (currentTurn) {
                case Lucilla:

                    currentTurn = TurnOrder.Bert;
                    turn = players.get(1);
                    break;
                case Bert:


                    currentTurn = TurnOrder.Malina;
                    turn = players.get(2);
                    break;
                case Malina:


                    currentTurn = TurnOrder.Percy;
                    turn = players.get(3);
                    break;
                case Percy:


                    currentTurn = TurnOrder.Lucilla;
                    turn = players.get(0);
                    break;
            }
            for (GameTile t : usedGameTiles) {
                t.setStored(null);
            }
            while (!input.equals("1")) {
                System.out.print('\u000C');
                board.draw();
                System.out.println("Turn is over! It is now " + turn.getName() + "'s turn.\n");
                System.out.println("Begin " + turn.getName() + "'s turn?\n");
                System.out.println("Enter 1 for yes.");
                input = scanner.nextLine();
            }
            input = "0";
        }
    }

    // WILL'S CODE - check it out and ask me if anything is unclear!

    private void teleportItem(Item item, Estate toEstate) {
        Estate fromEstate = item.getEstate();
        fromEstate.removeItem(item);
        fromEstate.updateContents();
        toEstate.addItem(item);
        toEstate.updateContents();
        item.setEstate(toEstate);
    }

    public int moveCharToEstate(Character character, Estate estate) {
        estate.addItem(character);
        estate.updateContents();
        character.setEstate(estate);
        GameTile current = (GameTile) board.getTile(character.getY(), character.getX());
        current.setStored(new Item("Used", "+", character.getX(), character.getY()));
        usedGameTiles.add(current);
        return 1;
    }

    public int moveCharOutOfEstate(Character character, EntranceTile exit) {
        if (board.isSafeMove(exit.exitY, exit.exitX)) {
            GameTile next = (GameTile) board.getTile(exit.exitY, exit.exitX);
            next.setStored(character);
            character.setX(exit.exitX);
            character.setY(exit.exitY);
            exit.estate.removeItem(character);
            exit.estate.updateContents();
            character.setEstate(null);
            return 1;
        }
        return 0;
    }

    private int moveChar(Character character, int newY, int newX) {
        if (board.isSafeMove(newY, newX)) {
            GameTile next = (GameTile) board.getTile(newY, newX);
            next.setStored(character);
            GameTile current = (GameTile) board.getTile(character.getY(), character.getX());
            current.setStored(new Item("Used", "+", character.getX(), character.getY()));
            usedGameTiles.add(current);
            character.setX(newX);
            character.setY(newY);
            return 1;
        }
        return 0;
    }

    private int moveUp(Character character) {
        if (character.getEstate() != null) {
            return moveCharOutOfEstate(character, character.getEstate().getEntranceTiles().get(0));
        }
        int newY = character.getY() - 1;
        int newX = character.getX();
        Tile next = board.getTile(newY, newX);
        if (next instanceof EntranceTile) {
            return moveCharToEstate(character, ((EntranceTile) next).estate);
        }
        return moveChar(character, newY, newX);
    }

    private int moveRight(Character character) {
        if (character.getEstate() != null) {
            return moveCharOutOfEstate(character, character.getEstate().getEntranceTiles().get(1));
        }
        int newY = character.getY();
        int newX = character.getX() + 1;
        Tile next = board.getTile(newY, newX);
        if (next instanceof EntranceTile) {
            return moveCharToEstate(character, ((EntranceTile) next).estate);
        }
        return moveChar(character, newY, newX);
    }

    private int moveDown(Character character) {
        if (character.getEstate() != null) {
            return moveCharOutOfEstate(character, character.getEstate().getEntranceTiles().get(2));
        }
        int newY = character.getY() + 1;
        int newX = character.getX();
        Tile next = board.getTile(newY, newX);
        if (next instanceof EntranceTile) {
            return moveCharToEstate(character, ((EntranceTile) next).estate);
        }
        return moveChar(character, newY, newX);
    }

    private int moveLeft(Character character) {
        if (character.getEstate() != null) {
            return moveCharOutOfEstate(character, character.getEstate().getEntranceTiles().get(3));
        }
        int newY = character.getY();
        int newX = character.getX() - 1;
        Tile next = board.getTile(newY, newX);
        if (next instanceof EntranceTile) {
            return moveCharToEstate(character, ((EntranceTile) next).estate);
        }
        return moveChar(character, newY, newX);
    }


    /**
     * Method to get the next player input (up down left right) using a scanner (or if they are in a estate guess is a valid input)
     * Handle the Input (tell board to move if up/down/left/right) or call guess() if guess is called and player is in a estate
     * also needs to handle "roll" for rolling the dice (update diceTotal to the return value of rollDice())
     * needs to handle "grid on" "grid off" and capital versions of all commands + errors like invalid inputs.
     */
    private void takePlayerInput(Player p) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (diceTotal != 0 && !input.equals("5")) {
            System.out.print('\u000C');
            board.draw();
            p.getWorksheet().printWorksheet();
            displayLocations();
            System.out.println(String.format("You have %d moves remaining. You are playing as %s (%s).", diceTotal, p.getCharacter().getName(), p.getCharacter().getDisplayIcon()));
            System.out.println("What direction will you move?\n");
            System.out.println("Enter 1 to move up.");
            System.out.println("Enter 2 to move right.");
            System.out.println("Enter 3 to move down.");
            System.out.println("Enter 4 to move left.");
            System.out.println("Enter 5 to return to the previous menu.");

            input = scanner.nextLine();

            // handling direction move
            switch (input) {
                case "1":
                    diceTotal -= moveUp(p.getCharacter());
                    break;
                case "2":
                    diceTotal -= moveRight(p.getCharacter());
                    break;
                case "3":
                    diceTotal -= moveDown(p.getCharacter());
                    break;
                case "4":
                    diceTotal -= moveLeft(p.getCharacter());
                    break;
                case "5":
                    break;
                default:
                    System.out.println("\nYour input was not one of the possible actions, please try again!");
            }
        }
        scanner.close();
    }

    // END OF WILL'S CODE
}


// JAMES' OLD DEFUNCT CODE

/**
 * Calls to, and initializes, fields and values that require generation upon a game's creation.
 * Finally, begin the game loop by calling gameManager().
 * If the game is fully 'booted' return gameManager() (also int), else return zero to account for errors in generation.
 */
// line 29 "model.ump"
//    public void bootGame() {
//        //filling the board with empty tiles
//        for (int i = 0; i < 24; i++) {
//            List<gameTile> row = new ArrayList<>();
//            for (int j = 0; j < 24; j++) {
//                row.add(new gameTile());
//            }
//            board.add(row);
//        }
//        //filling those tiles in the board with items
//        try {
//            File file = new File("board.txt");
//            Scanner scanner = new Scanner(file);
//            int countX = 0;
//            int countY = 0;
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                for (char c : line.toCharArray()) {
//                    gameTile currentGameTile = board.getTile(countX, countY);
//                    switch (c) {
//                        case 'X':
//                            currentGameTile.setStored(new Wall("Wall", "X", countX, countY));
//                            break;
//                        case 'G':
//                            currentGameTile.setStored(new Wall("Grayspace", "G", countX, countY));
//                            break;
//                        case 'E':
//                            currentGameTile.setStored(new Entrance("Entrance", " ", countX, countY));
//                            break;
//                    }
//                    countX++;
//                }
//                countX = 0;
//                countY++;
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + "board.txt");
//            e.printStackTrace();
//        }
//        //adding the estates
//
//        board.addEstate(new Estate("Haunted House"));
//        board.addEstate(new Estate("Manic Manor"));
//        board.addEstate(new Estate("Visitation Villa"));
//        board.addEstate(new Estate("Calamity Castle"));
//        board.addEstate(new Estate("Peril Palace"));
//
//        board.setEntrance();
//        System.out.print('\u000C');
//        System.out.println("Welcome to Hobby Detectives!");
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Do you want to start the game?");
//        System.out.println();
//
//        System.out.println("Enter 1 for yes.");
//        System.out.println("Enter 2 for no.");
//
//
//        String startGameInput = scanner.nextLine();
//        System.out.print('\u000C');
//        if (!startGameInput.equalsIgnoreCase("1")) {
//            System.out.println("Game aborted. Goodbye!");
//            scanner.close();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            endGame();
//        }
//
//        //get the number of players
//        int numPlayers = 0;
//
//        System.out.print("Please select the number of players.\n");
//        System.out.println();
//        System.out.println("Enter 1 to play with three players and a bot.");
//        System.out.println("Enter 2 to play with four players.");
//        startGameInput = scanner.nextLine();
//        if (!startGameInput.equalsIgnoreCase("1")) {
//            numPlayers = 4;
//        } else {
//            numPlayers = 3;
//        }
//
//        //get player names
//        System.out.print('\u000C');
//        List<String> names = new ArrayList<>();
//        while (numPlayers != names.size()) {
//            System.out.println("Setting up game.\n");
//            for (String n : names) {
//                System.out.println(n);
//            }
//            System.out.println();
//            System.out.print("Player " + (names.size() + 1) + " please enter your name : ");
//            String name = scanner.nextLine();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            System.out.print('\u000C');
//            if (name.length() > 15) {
//                System.out.println("Sorry, your name can't exceed 15 characters");
//            } else {
//                names.add(name);
//            }
//        }
//        // making the players
//        assignCharacters(names);
//        while (true) {
//            System.out.println("Allocating roles for " + numPlayers + " players.");
//            System.out.println();
//
//
//            String first = "";
//            for (Player p : players) {
//                System.out.println(p.getName() + " will be playing as " + p.getCharacter().getName());
//                if (p.getCharacter().getName().equals("Lucilla")) {
//                    first = p.getName();
//                }
//            }
//            System.out.println();
//            System.out.println("Lucilla will be starting first, please pass the tablet to " + first + ".\n");
//            System.out.println("\nBegin the your first round?");
//            System.out.println();
//
//            System.out.println("Enter 1 for yes.");
//
//            String input = scanner.nextLine();
//            if (input.equals("1")) {
//                break;
//            }
//            System.out.print('\u000C');
//        }
//        scanner.close();
//        board.draw();
//        //draw the characters on the board
//        for (Player p : players) {
//            gameTile t = board.getTile(p.getCharacter().getX(), p.getCharacter().getY());
//            t.setStored(p.getCharacter());
//        }
//        // makeing the cards
//        makeCards();
//        System.out.println();
//        for (Card c : cards) {
//            if (c.getIsMurder()) {
//                System.out.println("murder card : " + c.getName() + " " + c.getType());
//            }
//        }
//        gameManager();
//        endGame();
//    }
