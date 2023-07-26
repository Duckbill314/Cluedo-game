/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 102 "model.ump"
// line 252 "model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private List<Card> cards;
  private Character character;
  private Worksheet worksheet;
  private String name;
  private boolean isEligible;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Character aCharacter, Worksheet aWorksheet, String aName, boolean aIsEligible)
  {
    cards = new ArrayList<Card>();
    character = aCharacter;
    worksheet = aWorksheet;
    name = aName;
    isEligible = aIsEligible;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetMany */
  public boolean addCard(Card aCard)
  {
    boolean wasAdded = false;
    wasAdded = cards.add(aCard);
    return wasAdded;
  }

  public boolean removeCard(Card aCard)
  {
    boolean wasRemoved = false;
    wasRemoved = cards.remove(aCard);
    return wasRemoved;
  }

  public boolean setCharacter(Character aCharacter)
  {
    boolean wasSet = false;
    character = aCharacter;
    wasSet = true;
    return wasSet;
  }

  public boolean setWorksheet(Worksheet aWorksheet)
  {
    boolean wasSet = false;
    worksheet = aWorksheet;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsEligible(boolean aIsEligible)
  {
    boolean wasSet = false;
    isEligible = aIsEligible;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_GetMany */
  public Card getCard(int index)
  {
    Card aCard = cards.get(index);
    return aCard;
  }

  public Card[] getCards()
  {
    Card[] newCards = cards.toArray(new Card[cards.size()]);
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

  /**
   * the character the player controls
   */
  public Character getCharacter()
  {
    return character;
  }

  /**
   * visual tool to display known cards to the player
   */
  public Worksheet getWorksheet()
  {
    return worksheet;
  }

  /**
   * the Player name (serves as ID)
   */
  public String getName()
  {
    return name;
  }

  /**
   * whether the player has used their solve attempt or not
   */
  public boolean getIsEligible()
  {
    return isEligible;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsEligible()
  {
    return isEligible;
  }

  public void delete()
  {}


  /**
   * getters and setters for all values are auto generated
   * 
   * prints the cards in the players hand 
   * 
   */
  // line 114 "model.ump"
   public void printHand(){
    System.out.print("\n"); //output should be "playernames card's are 'cardname','cardname'" etc \n
  }


  public String toString()
  {
    return super.toString() + "["+
            "character" + ":" + getCharacter()+ "," +
            "name" + ":" + getName()+ "," +
            "isEligible" + ":" + getIsEligible()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "worksheet" + "=" + (getWorksheet() != null ? !getWorksheet().equals(this)  ? getWorksheet().toString().replaceAll("  ","    ") : "this" : "null");
  }
}