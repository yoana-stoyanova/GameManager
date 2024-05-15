import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

    public static void createUserList(ArrayList<User> userList, Scanner readUsers){

        userList.clear();

        File userFile = new File("users.txt");

        try {
            readUsers = new Scanner(userFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(readUsers.hasNextLine()){
            User u = new User(readUsers.nextLine());

            String input = readUsers.nextLine();

            while(!input.equals("***")){
                u.addGame(input, readUsers.nextLine());


                input = readUsers.nextLine();

            }

            userList.add(u);
        }
    }

    public static void printUserList(ArrayList<User> userList){


        for(int i = 0; i < userList.size(); i++){
            userList.get(i).printUserData();
        }
    }

    public static void createGameList(ArrayList<Game> gameList, Scanner readGames){

        gameList.clear();

        File gameFile = new File("games.txt");

        try {
            readGames = new Scanner(gameFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(readGames.hasNextLine()){
            String name = readGames.nextLine();
            Double price = readGames.nextDouble();
            int downloads = readGames.nextInt();
            String description = readGames.nextLine();
            Game g = new Game(name, price, downloads, description);

            gameList.add(g);
        }
    }

    public static void printGameList(ArrayList<Game> gameList){
        for(int i = 0; i < gameList.size(); i++){
            System.out.println("Game: " + gameList.get(i).name);
            System.out.println("Game: " + gameList.get(i).price);
            System.out.println("Game: " + gameList.get(i).downloads);
            System.out.println("Game: " + gameList.get(i).description);

        }
    }
}
