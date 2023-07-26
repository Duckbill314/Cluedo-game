/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 244 "model.ump"
public class Game
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TurnOrder { Lucilla, Bert, Malina, Percy }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Associations
  private List<Player> players;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game()
  {
    players = new ArrayList<Player>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    players.add(aPlayer);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (players.contains(aPlayer))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    players.clear();
  }


  /**
   * object using the turn order enum
   * private turnOrder currentTurn = turnOrder.Lucilla;
   * private int diceTotal = 0;
   * the number of move turns the player has left
   * private Board mainBoard = new Board();
   * 
   * Calls to, and initializes, fields and values that require generation upon a game's creation.  
   * Finally, begin the game loop by calling gameManager().  
   * If the game is fully 'booted' return gameManager() (also int), else return zero to account for errors in generation. 
   * 
   */
  // line 29 "model.ump"
   private int bootGame(){
    //demo 
  /**
  if( someValue != expectedValue || someValue != null ){
    return 0;
    for ( int i = 0; i < ( 24*24 ); i++; ) {
      
    }
  }
  **/
  return gameManager();
  }


  /**
   * 
   * Contains the primary gameLoop mechanics. Fails and returns 0 if error is detected, 1 if the game concludes successfully
   * Is the central hub for method calling and contains the main loop. 
   * 
   */
  // line 45 "model.ump"
   private int gameManager(){
    return 0;
  }


  /**
   * 
   * Method to randomly return a number 1-6
   * 
   */
  // line 51 "model.ump"
   private int rollDice(){
    return 0 // implement random number 1-6 to simulate dice roll
  }


  /**
   * 
   * Method to get the next player input (up down left right) using a scanner (or if they are in a estate guess is a valid input)
   * Handle the Input (tell board to move if up/down/left/right) or call guess() if guess is called and player is in a estate
   * also needs to handle "roll" for rolling the dice (update diceTotal to the return value of rollDice()) 
   * needs to handle "grid on" "grid off" and capital versions of all commands + errors like invalid inputs. 
   * 
   */
  // line 60 "model.ump"
   private void takePlayerInput(Player p){
    
  }


  /**
   * 
   * let the player do a guess (assumed check for in a estate has already passed)
   * 0 for failed guess and 1 for correct guess
   * 
   */
  // line 66 "model.ump"
   private int guess(Player p){
    return 0;
  }


  /**
   * placeholder for refute method
   */
  // line 70 "model.ump"
   private int refute(Player p){
    return 0;
  }


  /**
   * placeholder for solve method, should invoke guess method
   */
  // line 74 "model.ump"
   private int solve(Player p){
    return 0;
  }


  /**
   * 
   * creates the random cards that are excluded from the pool and creates the main pool of available cards and distributes them
   * 
   */
  // line 80 "model.ump"
   private void makeCards(){
    
  }


  /**
   * 
   * assigns each player a character randomly (must be called after makeCards())
   * 
   */
  // line 85 "model.ump"
   private void assignCharacters(){
    
  }


  /**
   * 
   * ends the game for a when a player wins
   * 
   */
  // line 90 "model.ump"
   private void endGame(){
    
  }


  /**
   * 
   * User Interactable method to start the program. 
   * Manages bootGame() return values of 0 & 1 respectively for successful or failed attempts to boot
   * 
   */
  // line 97 "model.ump"
   public static  void main(String... args){
    bootGame();//implement 0 & 1 handling
  }

}