import java.util.*;
public class Estate
{
  private String name;
  private List<Character> playersInside = new ArrayList<>();
  private List<Weapon> weaponsInside = new ArrayList<>();
  private List<Item> itemsToThisEstate = new ArrayList<>();
  private Map<String,Item> pathsOut = new HashMap<>();

  public Estate(String aName)
  {
    name = aName;
  }

  public Map<String,Item> getPathsOut()
  {
    return pathsOut;
  }
  public String getName()
  {
    return name;
  }

  /**
   * Check that at the given coordinates there is or is not an item
   */
  // line 206 "model.ump"
   public boolean onItem(int x, int y){
    return false;
  }

  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}
