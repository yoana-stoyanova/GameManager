import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMethods {

    AvailabilityMethods am = new AvailabilityMethods();

    public void createUserList(ArrayList<User> userList){

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

    public String printUserList(ArrayList<User> userList){
        String allText = "";

        for(int i = 0; i < userList.size(); i++){
            allText += userList.get(i).printUserData();
        }

        return allText;
    }

    public String sortUsersByActivity(ArrayList<User> userList){
        String allText = "";

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
            allText = allText + String.valueOf(i + 1) + ". " + usernameList.get(i).get(0) + " (" + usernameList.get(i).get(1) + " hours)\n";
        }

        return allText;
    }

    public String addUsername(String username, ArrayList<User> userList){

        if(username.charAt(0) != '@'){
            return "Invalid username format.";
        } else if(am.availableUsername(userList, username)){
            User u = new User(username);
            String text = "\n" + username + "\n***";

            userList.add(u);
            try {
                Files.write(Paths.get("users.txt"), text.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "Username added successfully!";

        } else {
            return "This username already exists in the system.";
        }
    }

    public String deleteUsername(String username, ArrayList <User> userList){

        if(username.charAt(0) != '@'){
            return "Invalid username format.";

        } else if(!am.availableUsername(userList, username)){
            File userFile = new File("users.txt");
            Scanner readUsers;

            try {
                readUsers = new Scanner(userFile);
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

            readUsers.close();

            PrintWriter writer = null;
            try {
                writer = new PrintWriter("users.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            writer.append(text);
            writer.flush();

            createUserList(userList);

            return "Username deleted successfully.";
        } else {
            return "This username doesn't exist in the system.";
        }

    }

    public String showInfoByUsername(String username, ArrayList<User> userList){

        if(username.charAt(0) != '@') {
           return "Invalid [Username] format.";
        } else {
            int index = -1;

            for(int i = 0; i < userList.size(); i++){
                if(username.equals(userList.get(i).username)){
                    index = i;
                    break;
                }
            }

            if(index == -1){
                return "This [User] doesn't exist in the system.";

            } else {
                return userList.get(index).printUserData();
            }
        }
    }

    public String addGameToUser(String username, String game, String hours, ArrayList<User> userList, ArrayList<Game> gameList){
        File userFile = new File("users.txt");
        Scanner readUsers;

        try {
            readUsers = new Scanner(userFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String text = "";

        if(!am.availableUsername(userList, username) && !am.availableGameName(gameList, game) && am.availableGameForUser(userList, username, game)){
            while(readUsers.hasNextLine()){
                String line = readUsers.nextLine();
                if(line.equals(username)){
                    text = text + username + '\n' + game + '\n' + hours + '\n';
                } else {
                    text = text + line + '\n';
                }
            }

            readUsers.close();

            PrintWriter writer = null;
            try {
                writer = new PrintWriter("users.txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            writer.append(text);
            writer.flush();

            createUserList(userList);

            return "User updated successfully!";
        } else {
            return "Invalid input";
        }

    }
}
