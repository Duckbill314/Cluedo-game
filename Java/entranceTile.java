public class entranceTile extends Tile {

    public Estate estate;

    entranceTile() {
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
