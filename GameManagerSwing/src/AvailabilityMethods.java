import java.util.ArrayList;

public class AvailabilityMethods {

    public boolean availableUsername(ArrayList<User> userList, String name){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).username.equals(name)){
                return false;
            }
        }

        return true;
    }

    public boolean availableGameName(ArrayList<Game> gameList, String gameName){
        for(int i = 0; i < gameList.size(); i++){
            if(gameList.get(i).name.equals(gameName)){
                return false;
            }
        }

        return true;
    }

    public boolean availableGameForUser(ArrayList<User> userList, String username, String gameName){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).username.equals(username)){
                for(int j = 0; j < userList.get(i).userData.size(); j++){
                    if(userList.get(i).userData.get(j).get(0).equals(gameName)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
