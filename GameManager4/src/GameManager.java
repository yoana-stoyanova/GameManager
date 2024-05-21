import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GameManager extends Methods{
    public static void main(String[] args) {

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Game> gameList = new ArrayList<>();

        createUserList(userList);
        createGameList(gameList);

        //showInfoByUsername(userList, "@eva-hristova");

        //addUsername(userList, "HAHAHAHHAHHAHA");

        //sortUsersByActivity(userList);

        mostBoughtGame(gameList, userList);

    }


}