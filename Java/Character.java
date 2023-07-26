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
  private String estateName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Character(String aName, String aDisplayIcon, int aX, int aY)
  {
    super(aName, aDisplayIcon, aX, aY);
    estateName = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEstateName(String aEstateName)
  {
    boolean wasSet = false;
    estateName = aEstateName;
    wasSet = true;
    return wasSet;
  }

  /**
   * name of the estate the  Character is in, null for not in estate
   */
  public String getEstateName()
  {
    return estateName;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "estateName" + ":" + getEstateName()+ "]";
  }
}