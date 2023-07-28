/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 119 "model.ump"
// line 261 "model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  
  private final int ROWS = 24;
  private final int COLS = 24;
  
  //Board Associations
  private List<Item> items;
  private List<Estate> estates;

  List<List<Tile>> board = new ArrayList<>();
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board()
  {
    items = new ArrayList<Item>();
    estates = new ArrayList<Estate>();
    //
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetMany */
  public Estate getEstate(int index)
  {
    Estate aEstate = estates.get(index);
    return aEstate;
  }

  public List<Estate> getEstates()
  {
    List<Estate> newEstates = Collections.unmodifiableList(estates);
    return newEstates;
  }

  public int numberOfEstates()
  {
    int number = estates.size();
    return number;
  }

  public boolean hasEstates()
  {
    boolean has = estates.size() > 0;
    return has;
  }

  public int indexOfEstate(Estate aEstate)
  {
    int index = estates.indexOf(aEstate);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    items.add(aItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    if (items.contains(aItem))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEstates()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addEstate(Estate aEstate)
  {
    boolean wasAdded = false;
    if (estates.contains(aEstate)) { return false; }
    estates.add(aEstate);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEstate(Estate aEstate)
  {
    boolean wasRemoved = false;
    if (estates.contains(aEstate))
    {
      estates.remove(aEstate);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEstateAt(Estate aEstate, int index)
  {  
    boolean wasAdded = false;
    if(addEstate(aEstate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEstates()) { index = numberOfEstates() - 1; }
      estates.remove(aEstate);
      estates.add(index, aEstate);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEstateAt(Estate aEstate, int index)
  {
    boolean wasAdded = false;
    if(estates.contains(aEstate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEstates()) { index = numberOfEstates() - 1; }
      estates.remove(aEstate);
      estates.add(index, aEstate);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEstateAt(aEstate, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    items.clear();
    estates.clear();
  }


  /**
   * List<List<Tile>> board = new ArrayList<>();
   * board should be filled by the bootGame() method in Game upon launch
   * list of items already exists as Umple auto generates a list of items
   * 
   * draws the board  
   * 
   */
  // line 128 "model.ump"
   public void draw(){
    // draws the Board including the players and items
     for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            System.out.print("|");
            board.get(col).get(row).draw();
        }
        System.out.println("|");
    }
  }


  /**
   * 
   * Turns every border on the board invisible so players can get a better grasp of their respective positions.
   * 
   */
  // line 134 "model.ump"
   public void gridOff(){
    
  }
  /**
   * adder
   * UNIMPLEMENTED ADDER IN UMPLE
   */
   public void add(List<Tile> toAdd){
    board.add(toAdd);
  }

  public void setEntrance(){
    for(Estate e : estates){
      switch(e.getName()){
        case "Haunted House":   
            e.addItemsToThisEstate(board.get(6).get(3).getStored()); 
            e.addItemsToThisEstate(board.get(5).get(6).getStored());
        break;
        case "Manic Manor":
            e.addItemsToThisEstate(board.get(17).get(6).getStored()); 
            e.addItemsToThisEstate(board.get(20).get(5).getStored());
        break;
        case "Visitation Villa":
            e.addItemsToThisEstate(board.get(9).get(12).getStored()); 
            e.addItemsToThisEstate(board.get(11).get(13).getStored());
            e.addItemsToThisEstate(board.get(12).get(10).getStored()); 
            e.addItemsToThisEstate(board.get(14).get(11).getStored());
        break;
        case "Calamity Castle":
            e.addItemsToThisEstate(board.get(3).get(17).getStored()); 
            e.addItemsToThisEstate(board.get(6).get(18).getStored());
        break;
        case "Peril Palace":
            e.addItemsToThisEstate(board.get(17).get(20).getStored()); 
            e.addItemsToThisEstate(board.get(18).get(17).getStored());
        break;
      }
    }
  }

  /**
   * 
   * Turns the grid back on so players can see what spaces they can traverse to.(grid is on by default) 
   * 
   */
  // line 139 "model.ump"
   public void gridOn(){
    
  }


  /**
   * 
   * makes sure that the value in board at x and y is not occupied by a wall or grayspace or player
   * 
   */
  // line 144 "model.ump"
   public int isSafeMove(int x, int y){
    Tile target = getTile(x,y);
    if(target.getStored()==null){//means it's free spalce
      return 1;
    }else if(target.getStored().getName().equals("Entrance")){//means it's entrance
       // target.getStored().setEstateName(target.);
        return 2;     
    }else if (target.getStored().getName().equals("Used")){//means player already used it
      return 0;
    }
    return 0;
  }


  /**
   * getter
   */
  // line 148 "model.ump"
   public Tile getTile(int x, int y){
    return board.get(x).get(y);
  }


  /**
   * setter
   */
  // line 153 "model.ump"
   public void setTile(int x, int y, Tile t){
    board.get(x).set(y, t);
  }


  /**
   * clear tile
   */
  // line 157 "model.ump"
   public void clearTile(int x, int y){
    board.get(x).remove(y);
  }


  /**
   * estate checker
   */
  // line 161 "model.ump"
   public Estate checkEstate(int x, int y){
    Tile t = board.get(x).get(y);
    for(Estate e : estates){
    if(e.getItemsToThisEstate().contains(t.getStored())){
      return e;   
    }
    }
    return null;
  }

}
