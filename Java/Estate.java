import java.util.*;

public class Estate {
    private String name;
    private List<Character> playersInside = new ArrayList<>();
    private List<Weapon> weaponsInside = new ArrayList<>();
    private List<entranceTile> entranceTiles = new ArrayList<>();
    private Map<String, entranceTile> pathsOut = new HashMap<>();

    public Estate(String aName, List<entranceTile> entranceTiles) {
        name = aName;
        this.entranceTiles = entranceTiles;
    }

    public Map<String, entranceTile> getPathsOut() {
        return pathsOut;
    }

    public String getName() {
        return name;
    }

    /**
     * Check that at the given coordinates there is or is not an item
     */
    public boolean onItem(int x, int y) {
        return false;
    }

    public String toString() {
        return super.toString() + "[" +
                "name" + ":" + getName() + "]";
    }
}
