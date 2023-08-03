public class wallTile extends Tile
{
    private String output = "X";

    public wallTile setG(){
        output = "G";
        return this;
    }
  public void draw(){
       System.out.print(output);
     }
  }
