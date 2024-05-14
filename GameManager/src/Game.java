public class Game {
    public String name = "";
    public double price = 0.00;
    public int downloads = 0;
    public String description = "";

    public Game(){};
    public Game(String name, double price, int downloads, String description){
        this.name = name;
        this.price = price;
        this.downloads = downloads;
        this.description = description;
    }
}
