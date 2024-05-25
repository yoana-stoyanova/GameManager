import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMethods {

    AvailabilityMethods am = new AvailabilityMethods();
    public void createGameList(ArrayList<Game> gameList){

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

    public void printGameList(ArrayList<Game> gameList){
        for(int i = 0; i < gameList.size(); i++){
            System.out.println("Game: " + gameList.get(i).name);
            System.out.println("Game: " + gameList.get(i).price);
            System.out.println("Game: " + gameList.get(i).downloads);
            System.out.println("Game: " + gameList.get(i).description);

        }
    }

    public void sortGamesByPrice(ArrayList<Game> gameList){
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

    public void mostBoughtGame(ArrayList<Game> gameList, ArrayList<User> userList) {
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

    public void addGame(Scanner input, ArrayList<Game> gameList){
        System.out.print("Enter [Game]: ");
        String name = input.next();

        if(am.availableGameName(gameList, name)){
            System.out.print("Enter [Price]: ");
            double price = input.nextDouble();

            System.out.print("Enter [Downloads]: ");
            int downloads = input.nextInt();

            input.nextLine();

            System.out.print("Enter [Description]: ");
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
            System.out.println("This [Game] already exists in the system.");
        }
    }

    public void deleteGame(Scanner input, ArrayList<Game> gameList){

        System.out.print("Enter [Game]: ");
        String game = input.next();

        if(!am.availableGameName(gameList, game)){
            File gameFile = new File("games.txt");
            Scanner readGames;

            try {
                readGames = new Scanner(gameFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            String text = "";

            while(readGames.hasNextLine()){
                String line = readGames.nextLine();
                if(line.equals(game)){
                    line = readGames.nextLine();//price
                    line = readGames.nextLine();//downloads
                    line = readGames.nextLine();//description
                    line = readGames.nextLine();//separator

                } else {
                    text = text + line + '\n';
                }
            }

            readGames.close();

            PrintWriter writer = null;
            try {
                writer = new PrintWriter("games.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            writer.append(text);
            writer.flush();

            createGameList(gameList);
        } else {
            System.out.println("This [Game] doesn't exist in the system.");
        }

    }

}
