import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        um.createUserList(userList);
        gm.createGameList(gameList);

        um.printUserList(userList);

        Style style = new Style();
        style.addStyle();

        JFrame mainMenu = new JFrame("Game Manager_Home");
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(600, 400);
        mainMenu.setLayout(null);

        JLabel label = new JLabel("GAME MANAGER", SwingConstants.CENTER);
        label.setBounds(125, 10, 350, 30);
        mainMenu.add(label);

        JButton PUL_button = new JButton("All Users");
        PUL_button.setBounds(125, 50, 150, 30);
        mainMenu.add(PUL_button);

        PUL_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame outputWindow = new JFrame("All Users");
                outputWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                outputWindow.setSize(400, 300);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                String PUL_text = um.printUserList(userList);
                textArea.setText(PUL_text);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                outputWindow.add(scrollPane);

                outputWindow.setVisible(true);
            }
        });

        JButton SUBA_button = new JButton("Most Active Users");
        SUBA_button.setBounds(125, 90, 150, 30);
        mainMenu.add(SUBA_button);

        SUBA_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame outputWindow = new JFrame("Most Active Users");
                outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                outputWindow.setSize(300, 200);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                String SUBA_text = um.sortUsersByActivity(userList);
                textArea.setText(SUBA_text);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                outputWindow.add(scrollPane);

                outputWindow.setVisible(true);

            }
        });

        JButton AU_button = new JButton("Add New User");
        AU_button.setBounds(125, 130, 150, 30);
        mainMenu.add(AU_button);

        AU_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Enter Username (@example)");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(350, 200);
                inputFrame.setLayout(null);

                JTextField inputField = new JTextField();
                inputField.setBounds(70, 50, 200, 30);
                inputFrame.add(inputField);

                JButton add = new JButton("Add");
                add.setBounds(120, 100, 100, 30);
                inputFrame.add(add);

                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input = inputField.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Add " + input);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        String AU_text = um.addUsername(input, userList);

                        JLabel label = new JLabel(AU_text);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);


                        outputWindow.add(label);

                        outputWindow.setVisible(true);

                    }
                });


                inputFrame.setVisible(true);
            }
        });

        JButton DU_button = new JButton("Delete User");
        DU_button.setBounds(125, 170, 150, 30);
        mainMenu.add(DU_button);

        DU_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Enter Username (@example)");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(350, 200);
                inputFrame.setLayout(null);

                JTextField inputField = new JTextField();
                inputField.setBounds(70, 50, 200, 30);
                inputFrame.add(inputField);

                JButton delete = new JButton("Delete");
                delete.setBounds(120, 100, 100, 30);
                inputFrame.add(delete);

                delete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input = inputField.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Delete " + input);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        String DU_text = um.deleteUsername(input, userList);

                        JLabel label = new JLabel(DU_text);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);


                        outputWindow.add(label);

                        outputWindow.setVisible(true);

                    }
                });


                inputFrame.setVisible(true);
            }
        });

        JButton SIBU_button = new JButton("Find User");
        SIBU_button.setBounds(125, 210, 150, 30);
        mainMenu.add(SIBU_button);

        SIBU_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Enter Username (@example)");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(330, 200);
                inputFrame.setLayout(null);

                JTextField inputField = new JTextField();
                inputField.setBounds(50, 50, 200, 30);
                inputFrame.add(inputField);

                JButton search = new JButton("Search");
                search.setBounds(100, 100, 100, 30);
                inputFrame.add(search);

                search.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input = inputField.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Search: " + input);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        JTextArea textArea = new JTextArea();
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);

                        String SIBU_text = um.showInfoByUsername(input, userList);

                        textArea.setText(SIBU_text);

                        JScrollPane scrollPane = new JScrollPane(textArea);

                        outputWindow.add(scrollPane);

                        outputWindow.setVisible(true);

                    }
                });


                inputFrame.setVisible(true);
            }
        });

        JButton AGTU_button = new JButton("Update User");
        AGTU_button.setBounds(125, 250, 150, 30);
        mainMenu.add(AGTU_button);

        AGTU_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Update User");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(330, 400);
                inputFrame.setLayout(null);

                JLabel usernameLabel = new JLabel("Enter Username (@example):");
                JTextField inputField1 = new JTextField();

                JLabel gameLabel = new JLabel("Enter Game:");
                JTextField inputField2 = new JTextField();

                JLabel hoursLabel = new JLabel("Enter Hours Played:");
                JTextField inputField3 = new JTextField();

                usernameLabel.setBounds(50, 30, 200, 25);
                inputField1.setBounds(50, 60, 200, 25);

                gameLabel.setBounds(50, 90, 150, 25);
                inputField2.setBounds(50, 120, 200, 25);

                hoursLabel.setBounds(50, 150, 150, 25);
                inputField3.setBounds(50, 180, 200, 25);

                inputFrame.add(usernameLabel);
                inputFrame.add(inputField1);

                inputFrame.add(gameLabel);
                inputFrame.add(inputField2);

                inputFrame.add(hoursLabel);
                inputFrame.add(inputField3);

                JButton add = new JButton("Add");
                add.setBounds(100, 250, 100, 30);
                inputFrame.add(add);

                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input1 = inputField1.getText();
                        String input2 = inputField2.getText();
                        String input3 = inputField3.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Update " + input1);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        JTextArea textArea = new JTextArea();
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);

                        String AGTU_text = um.addGameToUser(input1, input2, input3, userList, gameList);

                        JLabel label = new JLabel(AGTU_text);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);

                        outputWindow.add(label);

                        outputWindow.setVisible(true);

                    }
                });

                inputFrame.setVisible(true);
            }
        });

        JButton PGL_button = new JButton("All Games");
        PGL_button.setBounds(325, 50, 150, 30);
        mainMenu.add(PGL_button);

        PGL_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame outputWindow = new JFrame("All Games");
                outputWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                outputWindow.setSize(400, 300);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                String PGL_text = gm.printGameList(gameList);

                textArea.setText(PGL_text);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                outputWindow.add(scrollPane);

                outputWindow.setVisible(true);
            }
        });

        JButton SGBP_button = new JButton("Games By Price");
        SGBP_button.setBounds(325, 90, 150, 30);
        mainMenu.add(SGBP_button);

        SGBP_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame outputWindow = new JFrame("Games By Price");
                outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                outputWindow.setSize(300, 200);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                String SGBP_text = gm.sortGamesByPrice(gameList);

                textArea.setText(SGBP_text);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                outputWindow.add(scrollPane);

                outputWindow.setVisible(true);

            }
        });

        JButton MBG_button = new JButton("Popular Games");
        MBG_button.setBounds(325, 130, 150, 30);
        mainMenu.add(MBG_button);

        MBG_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame outputWindow = new JFrame("Games By Popularity");
                outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                outputWindow.setSize(300, 200);

                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);

                String MBG_text = gm.mostBoughtGame(gameList, userList);

                textArea.setText(MBG_text);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                outputWindow.add(scrollPane);

                outputWindow.setVisible(true);

            }
        });

        JButton AG_button = new JButton("Add New Game");
        AG_button.setBounds(325, 170, 150, 30);
        mainMenu.add(AG_button);

        AG_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Add New Game");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(330, 450);
                inputFrame.setLayout(null);

                JLabel gameLabel = new JLabel("Enter Game:");
                JTextField inputField1 = new JTextField();

                JLabel priceLabel = new JLabel("Enter Price:");
                JTextField inputField2 = new JTextField();

                JLabel downloadsLabel = new JLabel("Enter Downloads:");
                JTextField inputField3 = new JTextField();

                JLabel descriptionLabel = new JLabel("Enter Description:");
                JTextField inputField4 = new JTextField();

                gameLabel.setBounds(50, 30, 200, 25);
                inputField1.setBounds(50, 60, 200, 25);

                priceLabel.setBounds(50, 90, 150, 25);
                inputField2.setBounds(50, 120, 200, 25);

                downloadsLabel.setBounds(50, 150, 150, 25);
                inputField3.setBounds(50, 180, 200, 25);

                descriptionLabel.setBounds(50, 210, 150, 25);
                inputField4.setBounds(50, 240, 200, 25);

                inputFrame.add(gameLabel);
                inputFrame.add(inputField1);

                inputFrame.add(priceLabel);
                inputFrame.add(inputField2);

                inputFrame.add(downloadsLabel);
                inputFrame.add(inputField3);

                inputFrame.add(descriptionLabel);
                inputFrame.add(inputField4);

                JButton add = new JButton("Add");
                add.setBounds(100, 310, 100, 30);
                inputFrame.add(add);

                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input1 = inputField1.getText();
                        String input2 = inputField2.getText();
                        String input3 = inputField3.getText();
                        String input4 = inputField4.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Add " + input1);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        JTextArea textArea = new JTextArea();
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);

                        String AG_text = gm.addGame(input1, input2, input3, input4, gameList);

                        JLabel label = new JLabel(AG_text);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);

                        outputWindow.add(label);

                        outputWindow.setVisible(true);

                    }
                });

                inputFrame.setVisible(true);
            }
        });

        JButton DG_button = new JButton("Delete Game");
        DG_button.setBounds(325, 210, 150, 30);
        mainMenu.add(DG_button);

        DG_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame inputFrame = new JFrame("Enter Game");
                inputFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                inputFrame.setSize(330, 200);
                inputFrame.setLayout(null);

                JTextField inputField = new JTextField();
                inputField.setBounds(50, 50, 200, 30);
                inputFrame.add(inputField);

                JButton delete = new JButton("Delete");
                delete.setBounds(100, 100, 100, 30);
                inputFrame.add(delete);

                delete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String input = inputField.getText();

                        inputFrame.dispose();

                        JFrame outputWindow = new JFrame("Delete " + input);
                        outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        outputWindow.setSize(300, 200);

                        String DG_text = gm.deleteGame(input, gameList);

                        JLabel label = new JLabel(DG_text);

                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);


                        outputWindow.add(label);

                        outputWindow.setVisible(true);

                    }
                });


                inputFrame.setVisible(true);
            }
        });

        JButton MORE_button = new JButton("More...");
        MORE_button.setBounds(325, 250, 150, 30);
        mainMenu.add(MORE_button);

        MORE_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame outputWindow = new JFrame("More...");
                outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                outputWindow.setSize(300, 200);

                JLabel label = new JLabel("Made by Yoana Stoyanova :)");

                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);


                outputWindow.add(label);

                outputWindow.setVisible(true);

            }
        });

        mainMenu.setVisible(true);

    }


}