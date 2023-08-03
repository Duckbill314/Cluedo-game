/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 222 "model.ump"
// line 300 "model.ump"
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private boolean isMurder;
  private Player owner;
  private String name;
  private String type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Card(boolean aIsMurder, Player aOwner, String aName,String aType)
  {
    isMurder = aIsMurder;
    owner = aOwner;
    name = aName;
    type = aType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setOwner(Player aOwner) {
    owner = aOwner;
  }

  public void setIsMurder(boolean aIsMurder) {
    isMurder = aIsMurder;
  }

  /**
   * is this card one of the three murder cards?
   */
  public boolean getIsMurder()
  {
    return isMurder;
  }

  /**
   * the player who holds the card
   */
  public Player getOwner()
  {
    return owner;
  }

  public String getName()
  {
    return name;
  }

   public String getType()
  {
    return type;
  }

  public String toString()
  {
    String res = String.format("[name: %s | type: %s | owner: %s | murder: %s]", getName(), getType(), getOwner(), getIsMurder());
    return res;
  }
}
