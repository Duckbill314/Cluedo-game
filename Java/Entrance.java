/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 186 "model.ump"
// line 317 "model.ump"
public class Entrance extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
   Estate estate;
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Entrance(String aName, String aDisplayIcon, int aX, int aY)
  {
    super(aName, aDisplayIcon, aX, aY);
  }

  public Estate getEstate(){
   return estate;  
  }
  
  public void setEstate(Estate e){
   estate = e;
  }

  //------------------------
  // INTERFACE
  //------------------------
}
