/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 196 "model.ump"
// line 294 "model.ump"
public class Estate
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Estate Attributes
  private String name;
  private List<Character> playersInside;
  private List<Weapon> weaponsInside;
  private List<Entrance> entrancesToThisEstate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Estate(String aName)
  {
    name = aName;
    playersInside = new ArrayList<Character>();
    weaponsInside = new ArrayList<Weapon>();
    entrancesToThisEstate = new ArrayList<Entrance>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addPlayersInside(Character aPlayersInside)
  {
    boolean wasAdded = false;
    wasAdded = playersInside.add(aPlayersInside);
    return wasAdded;
  }

  public boolean removePlayersInside(Character aPlayersInside)
  {
    boolean wasRemoved = false;
    wasRemoved = playersInside.remove(aPlayersInside);
    return wasRemoved;
  }
  /* Code from template attribute_SetMany */
  public boolean addWeaponsInside(Weapon aWeaponsInside)
  {
    boolean wasAdded = false;
    wasAdded = weaponsInside.add(aWeaponsInside);
    return wasAdded;
  }

  public boolean removeWeaponsInside(Weapon aWeaponsInside)
  {
    boolean wasRemoved = false;
    wasRemoved = weaponsInside.remove(aWeaponsInside);
    return wasRemoved;
  }
  /* Code from template attribute_SetMany */
  public boolean addEntrancesToThisEstate(Entrance aEntrancesToThisEstate)
  {
    boolean wasAdded = false;
    wasAdded = entrancesToThisEstate.add(aEntrancesToThisEstate);
    return wasAdded;
  }

  public boolean removeEntrancesToThisEstate(Entrance aEntrancesToThisEstate)
  {
    boolean wasRemoved = false;
    wasRemoved = entrancesToThisEstate.remove(aEntrancesToThisEstate);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetMany */
  public Character getPlayersInside(int index)
  {
    Character aPlayersInside = playersInside.get(index);
    return aPlayersInside;
  }

  public Character[] getPlayersInside()
  {
    Character[] newPlayersInside = playersInside.toArray(new Character[playersInside.size()]);
    return newPlayersInside;
  }

  public int numberOfPlayersInside()
  {
    int number = playersInside.size();
    return number;
  }

  public boolean hasPlayersInside()
  {
    boolean has = playersInside.size() > 0;
    return has;
  }

  public int indexOfPlayersInside(Character aPlayersInside)
  {
    int index = playersInside.indexOf(aPlayersInside);
    return index;
  }
  /* Code from template attribute_GetMany */
  public Weapon getWeaponsInside(int index)
  {
    Weapon aWeaponsInside = weaponsInside.get(index);
    return aWeaponsInside;
  }

  public Weapon[] getWeaponsInside()
  {
    Weapon[] newWeaponsInside = weaponsInside.toArray(new Weapon[weaponsInside.size()]);
    return newWeaponsInside;
  }

  public int numberOfWeaponsInside()
  {
    int number = weaponsInside.size();
    return number;
  }

  public boolean hasWeaponsInside()
  {
    boolean has = weaponsInside.size() > 0;
    return has;
  }

  public int indexOfWeaponsInside(Weapon aWeaponsInside)
  {
    int index = weaponsInside.indexOf(aWeaponsInside);
    return index;
  }
  /* Code from template attribute_GetMany */
  public Entrance getEntrancesToThisEstate(int index)
  {
    Entrance aEntrancesToThisEstate = entrancesToThisEstate.get(index);
    return aEntrancesToThisEstate;
  }

  public Entrance[] getEntrancesToThisEstate()
  {
    Entrance[] newEntrancesToThisEstate = entrancesToThisEstate.toArray(new Entrance[entrancesToThisEstate.size()]);
    return newEntrancesToThisEstate;
  }

  public int numberOfEntrancesToThisEstate()
  {
    int number = entrancesToThisEstate.size();
    return number;
  }

  public boolean hasEntrancesToThisEstate()
  {
    boolean has = entrancesToThisEstate.size() > 0;
    return has;
  }

  public int indexOfEntrancesToThisEstate(Entrance aEntrancesToThisEstate)
  {
    int index = entrancesToThisEstate.indexOf(aEntrancesToThisEstate);
    return index;
  }

  public void delete()
  {}


  /**
   * 
   * Check that at the given coordinates there is or is not an entrance Item
   * 
   */
  // line 206 "model.ump"
   public boolean onEntrance(int x, int y){
    return false;
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}
