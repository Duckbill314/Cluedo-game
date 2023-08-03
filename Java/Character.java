/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 175 "model.ump"
// line 276 "model.ump"
public class Character extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Character Attributes
  private Estate estate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Character(String aName, String aDisplayIcon, int aX, int aY)
  {
    super(aName, aDisplayIcon, aX, aY);
    estate = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEstate(Estate aEstate)
  {
    boolean wasSet = false;
    estate = aEstate;
    wasSet = true;
    return wasSet;
  }

  /**
   * name of the estate the  Character is in, null for not in estate
   */
  public Estate getEstate()
  {
    return estate;
  }
}
