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

    public String printGameList(ArrayList<Game> gameList){
        String allText = "";
        for(int i = 0; i < gameList.size(); i++){
            allText = allText + gameList.get(i).name + "\n";
            allText = allText + "Price: " + gameList.get(i).price + "\n";
            allText = allText + "Downloads: " + gameList.get(i).downloads + "\n";
            allText = allText + "Description: " + gameList.get(i).description + "\n";
            allText += "\n";

        }

        return allText;
    }

    public String sortGamesByPrice(ArrayList<Game> gameList){
        String allText = "";

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
            allText = allText + String.valueOf(i + 1) + ". " + gameList.get(i).name + " (" + gameList.get(i).price + " lv.)" + "\n";
        }

        return allText;
    }

    public String mostBoughtGame(ArrayList<Game> gameList, ArrayList<User> userList) {
        String allText = "";

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
            allText = allText + String.valueOf(i + 1) + gameList.get(i).name + " (" + counter[i] + " Users)" + "\n";
        }

        return allText;
    }

    public String addGame(String name, String price, String downloads, String description, ArrayList<Game> gameList){

        if(am.availableGameName(gameList, name)){

            Game g = new Game(name, Double.parseDouble(price), Integer.parseInt(downloads), description);
            gameList.add(g);

            String text = "\n" + name + "\n" + price + "\n" + downloads + "\n" + description + "\n***";

            try {
                Files.write(Paths.get("games.txt"), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "Game added successfully!";

        } else {
            return "This game already exists in the system.";
        }
    }

    public String deleteGame(String name, ArrayList<Game> gameList){

        if(!am.availableGameName(gameList, name)){
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
                if(line.equals(name)){
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

            return "Game deleted successfully";
        } else {
            return "This game doesn't exist in the system.";
        }

    }

}
