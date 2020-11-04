/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.io.IOException;
import java.util.Scanner;
import model.Cell;
import model.Game;

public class Menu {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // constant that represents the separator used when the user enters his/her nickname, the number of rows of the grid, the number of columns of the grid, and the number of mirrors of the grid.
    public final static String SEPARATOR = " ";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // scanner to read inputs by console.
    public Scanner scanner = new Scanner(System.in);

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the main model class.
    private Game game;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Name: Menu
     * Constructor method of a menu. <br>
    */
    public Menu() {
        String nameGame = "Mirrors: The Game";
        try {
            game = new Game(nameGame);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Name: startMenu
     * Method used to show the main menu. <br>
     * @param menu - Menu activation or deactivation - menu = boolean, menu != null, menu begins in true.
    */
    public void startMenu(boolean menu) {
        game.setGameOver(false);
        game.setAttemptsFailed(0);
        game.setLasersFired(0);
        if (menu) {
            System.out.println("\n--------------------------------------------");
            System.out.println("Institute of Mirrors, Lasers and Labyrinths®");
            System.out.println("--------------------------------------------\n");
            System.out.println(game.getNameGame());
            System.out.println("\n**********");
            System.out.println("Start Menu");
            System.out.println("**********\n");
            System.out.println("Type 1 to play the game.\nType 2 to see the leaderboard.\nType 3 to end the program.\n");
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        playTheGame();
                        startMenu(true);
                        break;
                    case 2:
                        showLeaderboardScores();
                        startMenu(true);
                        break;
                    case 3:
                        System.out.print("\nLeaving the menu...\n\nEnd of menu.\nGoodbye!\n\n");
                        break;
                    default:
                        System.out.println("\nOption not available. Try again.\n");
                        startMenu(true);
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("\nError! You must enter a whole number. Try again.");
                startMenu(true);
            }
        }
    }

    /**
     * Name: playTheGame
     * Method used to play the game. <br>
    */
    private void playTheGame() {
        System.out.println("\nEnter your nickname, the rows number, the columns number and the mirrors number: ");
        try {
            String[] values = scanner.nextLine().split(SEPARATOR);
            int rows = Integer.parseInt(values[1]);
            int columns = Integer.parseInt(values[2]);
            int mirrors = Integer.parseInt(values[3]);
            if (columns <= 26) {
                if (mirrors <= rows * columns) {
                    game.buildGrid(rows, columns, mirrors);
                    shootLaserBeam(values[0], rows, columns, mirrors);
                } else
                    System.out.println("\nThe number of mirrors can't be greater than the number of boxes from the grid.");
            } else
                System.out.println("\nThe number of columns can't be greater than 26 due to the 26 letters of the English alphabet.\n");
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println("\nYou must enter a one line String only, with the four values requested, and separated by a space between them.\n");
            playTheGame();
        }
    }

    /**
     * Name: shootLaserBeam
     * Method used to shoot the laser beam at an edge or corner location of the grid so that it goes through it bouncing off the mirrors. <br>
     * <b>pre: </b> Grid already created with all its boxes (nodes) and mirrors present. <br>
     * <b>post: </b> Shooting start location and shooting end location of the laser beam in question determined and printed in the grid with 'S' ("Start") and 'E' ("End"). <br>
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the grid - mirrors = int, mirrors != null, mirrors != 0.
    */
    private void shootLaserBeam(String nickname, int rows, int columns, int mirrors) {
        System.out.println("\n" + nickname + ": " + game.getGrid().getMirrors() + " mirror(s) remaining.\n");
        game.getGrid().displayDown(game.getGrid().getFirst());
        System.out.print("\nEnter a command to make a laser shot: ");
        String shot = scanner.nextLine().toUpperCase();
        String nomenclature = shot;
        System.out.println();
        if (shot.substring(shot.length() - 2, shot.length() - 1).matches("[A-Z]") && shot.substring(shot.length() - 1, shot.length()).matches("[A-Z]")) {
            String row = shot.substring(0, shot.length() - 2);
            String column = shot.substring(shot.length() - 2, shot.length() - 1);
            nomenclature = row + column;
        }
        Cell objSearch = game.getGrid().searchBoxDown(nomenclature, game.getGrid().getFirst());
        if (objSearch != null) {
            if (objSearch.getIsCorner()) {
                if (shot.length() >= 3) {
                    if ((shot.endsWith("H") && shot.charAt(shot.length() - 2) != 'H') || (shot.endsWith("H") && shot.charAt(shot.length() - 2) == 'H')) {
                        objSearch.setIsStart(Cell.START);
                        if (objSearch.getTypeCorner().equals(Cell.SUPERIOR_LEFT) || objSearch.getTypeCorner().equals(Cell.INFERIOR_LEFT))
                            game.getGrid().moveRight(objSearch);
                        else if (objSearch.getTypeCorner().equals(Cell.SUPERIOR_RIGHT) || objSearch.getTypeCorner().equals(Cell.INFERIOR_RIGHT)) {
                            game.getGrid().moveLeft(objSearch);
                        }
                        objSearch.setIsStart(' ');
                        game.getGrid().setFinish(false);
                        game.setLasersFired(game.getLasersFired() + 1);
                        subMenu(nickname, true, rows, columns, mirrors);
                    } else if ((shot.endsWith("V") && shot.charAt(shot.length() - 2) != 'V') || (shot.endsWith("V") && shot.charAt(shot.length() - 2)== 'V')) {
                        objSearch.setIsStart(Cell.START);
                        if (objSearch.getTypeCorner().equals(Cell.SUPERIOR_LEFT) || objSearch.getTypeCorner().equals(Cell.SUPERIOR_RIGHT))
                            game.getGrid().moveDown(objSearch);
                        else if (objSearch.getTypeCorner().equals(Cell.INFERIOR_LEFT) || objSearch.getTypeCorner().equals(Cell.INFERIOR_RIGHT)) {
                            game.getGrid().moveUp(objSearch);
                        }
                        objSearch.setIsStart(' ');
                        game.getGrid().setFinish(false);
                        game.setLasersFired(game.getLasersFired() + 1);
                        subMenu(nickname, true, rows, columns, mirrors);
                    } else {
                        System.out.println("\n" + shot + " is not a valid command. Try again.");
                        shootLaserBeam(nickname, rows, columns, mirrors);
                    }
                }
            } else if (objSearch.getIsEdge()) {
                objSearch.setIsStart(Cell.START);
                if (objSearch.getTypeEdge().equals(Cell.LEFT_EDGE))
                    game.getGrid().moveRight(objSearch);
                else if (objSearch.getTypeEdge().equals(Cell.RIGHT_EDGE))
                    game.getGrid().moveLeft(objSearch);
                else if (objSearch.getTypeEdge().equals(Cell.TOP_EDGE))
                    game.getGrid().moveDown(objSearch);
                else if (objSearch.getTypeEdge().equals(Cell.BOTTOM_EDGE)) {
                    game.getGrid().moveUp(objSearch);
                }
                objSearch.setIsStart(' ');
                game.getGrid().setFinish(false);
                game.setLasersFired(game.getLasersFired() + 1);
                subMenu(nickname, true, rows, columns, mirrors);
            } else {
                System.out.println("\nA shot can only be fired from a cell on an edge or in a corner of the grid. Try again.");
                shootLaserBeam(nickname, rows, columns, mirrors);
            }
        } else {
            System.out.println("\nError. Invalid cell nomenclature. Try again.");
            shootLaserBeam(nickname, rows, columns, mirrors);
        }
    }

    /**
     * Name: subMenu
     * Method used to show a sub-menu every time a laser beam is fired or a mirror is tried to guess. <br>
     * <b>pre: </b> At least one laser beam has been fired. <br>
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @param run - Sub menu activation or deactivation - run = boolean, run != null.
     * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the grid - mirrors = int, mirrors != null, mirrors != 0.
    */
    private void subMenu(String nickname, boolean run, int rows, int columns, int mirrors) {
        if (run) {
            if (game.getGrid().getMirrors() > 0) {
                System.out.println("\n" + nickname + ": " + game.getGrid().getMirrors() + " mirror(s) remaining.\n");
                game.getGrid().displayDown(game.getGrid().getFirst());
                System.out.println("\nWhat do you want to do?\n(1) Shoot laser beam again.\n(2) Guess the location of a mirror.\n(3) Cheat mode.\nOr write 'menu' to return to the menu.\n");
                try {
                    String option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            shootLaserBeam(nickname, rows, columns, mirrors);
                            break;
                        case "2":
                            guessAMirror(nickname);
                            subMenu(nickname, true, rows, columns, mirrors);
                            break;
                        case "3":
                            System.out.println("\nxX Cheat Mode Xx\n");
                            game.getGrid().showMirrorsDown(game.getGrid().getFirst());
                            subMenu(nickname, true, rows, columns, mirrors);
                            break;
                        case "menu":
                            double score = game.scoreCalculation(rows, columns, mirrors);
                            try {
                                game.getBstUsers().addUser(nickname, score, rows, columns, mirrors);
                            } catch (IOException ioe) {
                                ioe.printStackTrace();
                            }
                            System.out.println("\nYour score is: " + score);
                            subMenu(nickname, false, rows, columns, mirrors);
                            break;
                        default:
                            System.out.println("Option not available. Try again.");
                            subMenu(nickname, true, rows, columns, mirrors);
                            break;
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("\nError! Invalid command format. Try again.\n");
                    subMenu(nickname, false, rows, columns, mirrors);
                }
            }
            if (game.getGrid().getMirrors() == 0 && !game.getGameOver()) {
                gameWon(nickname, rows, columns, mirrors);
            }
        }
    }

    /**
     * Name: gameWon
     * Method used to end the game, with the calculation of a score for the user, and adding it in a binary search tree, ordered by score. <br>
     * <b>pre: </b> The number of mirrors remaining must be 0 and the gameOver attribute must be in false in a first instance. <br>
     * <b>post: </b> The game ends and a congratulations message with the user score is printed, for winning the game. <br>
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the grid - mirrors = int, mirrors != null, mirrors != 0.
    */
    public void gameWon(String nickname, int rows, int columns, int mirrors) {
        double score = game.scoreCalculation(rows, columns, mirrors);
        try {
            game.getBstUsers().addUser(nickname, score, rows, columns, mirrors);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("\nCongratulations! You won! Your score is: " + game.scoreCalculation(rows, columns, mirrors) + " points.");
        game.setGameOver(true);
    }

    /**
     * Name: guessAMirror
     * Method used to guess the location of a mirror in the grid, invoking the guessAMirror method from the Game class. <br>
     * <b>pre: </b> The number of mirrors remaining must be different from 0. The user has already fired the laser beam for the first time. <br>
     * <b>post: </b> Guessing process of a mirror. <br>
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
    */
    private void guessAMirror(String nickname) {
        System.out.print("\nEnter 'L' (Locate), followed by the cell nomenclature, followed by 'L' or 'R' (Left or Right): ");
        String guess = scanner.nextLine().toUpperCase();
        System.out.println(game.guessMirror(guess, nickname));
    }

    /**
     * Name: showLeaderboardScores
     * Method used to show the leaderboard with the different scores from the users that have played, invoking the printInorder method from the BSTUsers class. <br>
    */
    private void showLeaderboardScores() {
        if (game.getBstUsers().getRoot() == null)
            System.out.println("\nThere are no registered scores yet to show them.\n");
        else {
            System.out.println("\n-----------------------------Leaderboard-----------------------------\n");
            System.out.println("Rank | Nickname | Score | Grid rows | Grid columns | Mirrors to find\n");
            System.out.println(game.getBstUsers().printInorder(game.getBstUsers().getRoot()));
            game.getBstUsers().setPosition(1);
        }
    }
}