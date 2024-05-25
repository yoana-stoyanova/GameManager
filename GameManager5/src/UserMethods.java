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

    public void printUserList(ArrayList<User> userList){


        for(int i = 0; i < userList.size(); i++){
            userList.get(i).printUserData();
        }
    }

    public void sortUsersByActivity(ArrayList<User> userList){
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

    public void addUsername(ArrayList<User> userList, Scanner input){
        System.out.print("Enter [Username] (@example): ");
        String username = input.next();

        if(username.charAt(0) != '@'){
            System.out.println("Invalid [Username] format.");
        } else if(am.availableUsername(userList, username)){
            User u = new User(username);
            String text = "\n"+ username;

            System.out.print("Enter [Game] (or '***' to exit): ");
            String game = input.next();
            System.out.print("Enter [Hours Played] (or '***' to exit): ");
            String hours = input.next();

            while(!game.equals("***") && !hours.equals("***")){

                text = text + "\n" + game + "\n" + hours;

                u.addGame(game, hours);

                System.out.print("Enter [Game] (or '***' to exit): ");
                game = input.next();
                System.out.print("Enter [Hours Played] (or '***' to exit): ");
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
            System.out.println("This [Username] already exists in the system.");
        }
    }

    public void deleteUsername(Scanner input, ArrayList <User> userList){

        System.out.print("Enter [Username] (@example): ");
        String username = input.next();

        if(username.charAt(0) != '@'){
            System.out.println("Invalid [Username] format.");

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
        } else {
            System.out.println("This [Username] doesn't exist in the system.");
        }

    }

    public void showInfoByUsername(Scanner input, ArrayList<User> userList){
        System.out.print("Enter [Username] (@example): ");
        String username = input.next();

        if(username.charAt(0) != '@') {
            System.out.println("Invalid [Username] format.");
        } else {
            int index = -1;

            for(int i = 0; i < userList.size(); i++){
                if(username.equals(userList.get(i).username)){
                    index = i;
                    break;
                }
            }

            if(index == -1){
                System.out.println("This [User] doesn't exist in the system.");

            } else {
                userList.get(index).printUserData();
            }
        }
    }

    public void addGameToUser(Scanner input, ArrayList<User> userList, ArrayList<Game> gameList){
        System.out.print("Enter [Username]: ");
        String username = input.next();

        System.out.print("Enter [Game]: ");
        String game = input.next();

        System.out.print("Enter [Hours Played]: ");
        String hours = input.next();

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
        } else {
            System.out.println("Invalid input. This [User] or [Game] may not exist in the system.This [User] may already have have this [Game] on their profile.");
        }

    }
}
