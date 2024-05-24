import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

    public static void createUserList(ArrayList<User> userList){

        userList.clear();

        File userFile = new File("users.txt");
        Scanner readUsers;

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

        readUsers.close();
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
            for(int j = 0; j < userList.size() - 1; j++){
                int activity1 = Integer.parseInt(usernameList.get(j).get(1));
                int activity2 = Integer.parseInt(usernameList.get(j + 1).get(1));

                if(activity1 < activity2){
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

    public static boolean availableUsername(ArrayList<User> userList, String name){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).username.equals(name)){
                return false;
            }
        }

        return true;
    }

    public static void addUsername(ArrayList<User> userList, Scanner input){
        System.out.print("Enter username (@example): ");
        String username = input.next();

        if(username.charAt(0) != '@'){
            System.out.println("Invalid username format.");
        } else if(availableUsername(userList, username)){
            User u = new User(username);
            String text = "\n"+ username;

            System.out.print("Enter game (or '***' to exit): ");
            String game = input.next();
            System.out.print("Enter hours played (or '***' to exit): ");
            String hours = input.next();

            while(!game.equals("***") && !hours.equals("***")){

                text = text + "\n" + game + "\n" + hours;

                u.addGame(game, hours);

                System.out.print("Enter game (or '***' to exit): ");
                game = input.next();
                System.out.print("Enter hours played (or '***' to exit): ");
                hours = input.next();
            }

            userList.add(u);

            text = text + "\n***";
            try {
                Files.write(Paths.get("users.txt"), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("This Username already exists in the system.");
        }
    }

    public static void deleteUsername(String username){
        File userFile = new File("users.txt");
        Scanner readUsers;
        PrintWriter writer;
        try {
            readUsers = new Scanner(userFile);
            writer = new PrintWriter(userFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String text = "";

        while(readUsers.hasNextLine()){
            String line = readUsers.nextLine();
            if(line.equals(username)){
                while(!line.equals("***")){
                    line = readUsers.nextLine();
                }
            } else {
                text = text + line + '\n';
            }
        }

        writer.append("");
        writer.flush();

        try {
            Files.write(Paths.get("users.txt"), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void showInfoByUsername(ArrayList<User> userList, String user){

        int index = -1;

        for(int i = 0; i < userList.size(); i++){
            if(user.equals(userList.get(i).username)){
                index = i;
                break;
            }
        }

        if(index == -1){
            System.out.println("This user doesn't exist.");
        } else {
            userList.get(index).printUserData();
        }
    }

    public static void createGameList(ArrayList<Game> gameList){

        gameList.clear();

        File gameFile = new File("games.txt");
        Scanner readGames;

        try {
            readGames = new Scanner(gameFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(readGames.hasNextLine()){
            String name = readGames.nextLine();
            Double price = Double.valueOf(readGames.nextLine());
            int downloads = Integer.valueOf(readGames.nextLine());
            String description = readGames.nextLine();
            String separator = readGames.nextLine();
            Game g = new Game(name, price, downloads, description);

            gameList.add(g);
        }

        readGames.close();
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

        for(int i = 0; i < gameList.size(); i++){
            System.out.println((i + 1) + ". " + gameList.get(i).name + " (" + gameList.get(i).price + " lv.)");
        }
    }

    public static void mostBoughtGame(ArrayList<Game> gameList, ArrayList<User> userList) {
        int[] counter = new int[gameList.size()];

        for(int i = 0; i < gameList.size(); i++){
            counter[i] = 0;
        }

        for(int i = 0; i < gameList.size(); i++){
            for(int j = 0; j < userList.size(); j++){
                for(int k = 0; k < userList.get(j).userData.size(); k++){
                    if(userList.get(j).userData.get(k).get(0).equals(gameList.get(i).name)){
                        counter[i]++;
                    }
                }
            }
        }

        for(int i = 0; i < gameList.size(); i++){
            for(int j = 0; j < gameList.size() - 1; j++){
                if(counter[j] < counter[j + 1]){
                    Game g = new Game(gameList.get(j).name, gameList.get(j).price, gameList.get(j).downloads, gameList.get(j).description);
                    gameList.get(j).setGame(gameList.get(j + 1).name, gameList.get(j + 1).price, gameList.get(j + 1).downloads, gameList.get(j + 1).description);
                    gameList.get(j + 1).setGame(g.name, g.price, g.downloads, g.description);

                    int b = counter[j];
                    counter[j] = counter[j + 1];
                    counter[j + 1] = b;
                }
            }
        }

        for(int i = 0; i < gameList.size(); i++){
            System.out.println(gameList.get(i).name + " (" + counter[i] + " Users)");
        }

    }

    public static boolean availableGameName(ArrayList<Game> gameList, String gameName){
    for(int i = 0; i < gameList.size(); i++){
        if(gameList.get(i).name.equals(gameName)){
            return false;
        }
    }

    return true;
}

    public static void addGame(ArrayList<Game> gameList, Scanner input){
        System.out.print("Enter Game: ");
        String name = input.next();

        if(availableGameName(gameList, name)){
            System.out.print("Enter Price: ");
            double price = input.nextDouble();

            System.out.print("Enter Downloads: ");
            int downloads = input.nextInt();

            input.nextLine();

            System.out.print("Enter Description: ");
            String description = input.nextLine();

            Game g = new Game(name, price, downloads, description);
            gameList.add(g);

            String text = "\n" + name + "\n" + price + "\n" + downloads + "\n" + description + "\n***";

            try {
                Files.write(Paths.get("games.txt"), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("This Game already exists in the system.");
        }
    }

}