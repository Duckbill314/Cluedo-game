/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 210 "model.ump"
// line 322 "model.ump"
public class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private Item stored;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile()
  {
    stored = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStored(Item aStored)
  {
    boolean wasSet = false;
    stored = aStored;
    wasSet = true;
    return wasSet;
  }

  public Item getStored()
  {
    return stored;
  }

  public void delete()
  {}

  // line 214 "model.ump"
  public void draw(){
    if(stored == null){
       System.out.print(" ");
       }else{
       System.out.print(stored.getDisplayIcon());
     }
  }
}
