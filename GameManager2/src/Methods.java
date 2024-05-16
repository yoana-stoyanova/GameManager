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

    public static void sortUsersByActivity(ArrayList<User> userList){
        ArrayList<ArrayList<String>> usernameList = new ArrayList<>();

        for(int i = 0; i < userList.size(); i++) {
            ArrayList<String> u = new ArrayList<>();
            u.add(userList.get(i).username);
            u.add(String.valueOf(userList.get(i).userActivity()));

            usernameList.add(u);

        }

        for(int i = 0; i < userList.size(); i++){
            for(int j = i; j < userList.size() - 1; j++){
                    if(Integer.parseInt(usernameList.get(j).get(1)) < Integer.parseInt(usernameList.get(j + 1).get(1))){
                        String a = usernameList.get(j).get(0);
                        String b = usernameList.get(j).get(1);
                        usernameList.get(j).set(0, usernameList.get(j + 1).get(0));
                        usernameList.get(j).set(1, usernameList.get(j + 1).get(1));
                        usernameList.get(j + 1).set(0, a);
                        usernameList.get(j + 1).set(1, b);
                }
            }
        }

        for(int i = 0; i < usernameList.size(); i++){
            System.out.println((i + 1) + ". " + usernameList.get(i).get(0) + " (" + usernameList.get(i).get(1) + " hours)");
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

    public static void sortGamesByPrice(ArrayList<Game> gameList){
        for(int i = 0; i < gameList.size(); i++){
            for(int j = i; j < gameList.size() - 1; j++){
                if (gameList.get(j).price > gameList.get(j + 1).price){
                    Game g = new Game(gameList.get(j).name, gameList.get(j).price, gameList.get(j).downloads, gameList.get(j).description);
                    gameList.get(j).setGame(gameList.get(j + 1).name, gameList.get(j + 1).price, gameList.get(j + 1).downloads, gameList.get(j + 1).description);
                    gameList.get(j + 1).setGame(g.name, g.price, g.downloads, g.description);
                }
            }
        }
    }
}