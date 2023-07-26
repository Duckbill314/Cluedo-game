/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 167 "model.ump"
// line 271 "model.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private String displayIcon;
  private int x;
  private int y;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, String aDisplayIcon, int aX, int aY)
  {
    name = aName;
    displayIcon = aDisplayIcon;
    x = aX;
    y = aY;
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

  public boolean setDisplayIcon(String aDisplayIcon)
  {
    boolean wasSet = false;
    displayIcon = aDisplayIcon;
    wasSet = true;
    return wasSet;
  }

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDisplayIcon()
  {
    return displayIcon;
  }

  /**
   * represents the row index of the tile the item occupies in the Board's internal collection (collection[x][y])
   */
  public int getX()
  {
    return x;
  }

  /**
   * represents the column index of the tile the item occupies in the Board's internal collection (collection[x][y])
   */
  public int getY()
  {
    return y;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "displayIcon" + ":" + getDisplayIcon()+ "," +
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]";
  }
}