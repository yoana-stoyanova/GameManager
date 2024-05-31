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

    public String printUserData(){
        String userText = "";
        userText = userText + "Username: " + username + "\n";

        for(int i = 0; i < userData.size(); i++){
            userText = userText + "Played [" + userData.get(i).get(0) + "] for " + userData.get(i).get(1) + " hours." + "\n";
            //System.out.println(text);
        }

        userText += "\n";

        return userText;
    }

    public int userActivity(){
        int activitySum = 0;

        for(int i = 0; i < userData.size(); i++){
            int h = Integer.parseInt(userData.get(i).get(1));
            activitySum += h;
        }

        return activitySum;
    }

}
