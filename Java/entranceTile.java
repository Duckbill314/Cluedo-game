public class entranceTile extends Tile {

    public Estate estate;
    public boolean empty = false;

    entranceTile(){}

    entranceTile(String s) {
        if (s.equals("EMPTY")){
            empty = true;
        }
    }

    public boolean isEmpty(){
        return empty;
    }

    entranceTile(Estate estate) {
       this.estate = estate;
    }

    public void addEstate(Estate estate){
        this.estate = estate;
    }

    public void draw() {
        System.out.print(" ");
    }
}
