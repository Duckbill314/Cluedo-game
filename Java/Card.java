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

  public boolean setIsMurder(boolean aIsMurder)
  {
    boolean wasSet = false;
    isMurder = aIsMurder;
    wasSet = true;
    return wasSet;
  }

  public boolean setOwner(Player aOwner)
  {
    boolean wasSet = false;
    owner = aOwner;
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

  /**
   * is this card one of the three     murder cards?
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "isMurder" + ":" + getIsMurder()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "owner" + "=" + (getOwner() != null ? !getOwner().equals(this)  ? getOwner().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
