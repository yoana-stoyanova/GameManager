import java.util.ArrayList;

public class User {
    public String username = "";
    public ArrayList<ArrayList<String>> userData = new ArrayList<>();

    public User(){};
    public User(String username){
        this.username = username;
    }

    public void addGame(String gameName, String hoursPlayed){
        ArrayList<String> gameData = new ArrayList<>();
        gameData.add(gameName);
        gameData.add(hoursPlayed);
        userData.add(gameData);
    }
}
