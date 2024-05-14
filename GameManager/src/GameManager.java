import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class GameManager {
    public static void main(String[] args) {

        File usersFile = new File("users.txt");
        File gamesFile = new File("games.txt");

        try {
            Scanner usersReader = new Scanner(usersFile);
            Scanner gamesReader = new Scanner(gamesFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
