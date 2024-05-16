import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GameManager extends Methods{
    public static void main(String[] args) {

        File usersFile = new File("users.txt");
        File gamesFile = new File("games.txt");

        try {
            Scanner usersReader = new Scanner(usersFile);
            Scanner gamesReader = new Scanner(gamesFile);

            ArrayList<User> userList = new ArrayList<>();
            ArrayList<Game> gameList = new ArrayList<>();

            createUserList(userList, usersReader);
            //printUserList(userList);
            sortUsersByActivity(userList);

            //createGameList(gameList, gamesReader);
            //printGameList(gameList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}