import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GameManager {
    public static void main(String[] args) {

        UserMethods um = new UserMethods();
        GameMethods gm = new GameMethods();

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Game> gameList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        um.createUserList(userList);
        gm.createGameList(gameList);

    }


}