/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Game {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // constant that represents the binary file where the root and so all the users (nodes) of the binary search tree are serialized.
    public final static String SAVE_USERS_PATH_FILE = "data/users.ap2";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // attribute that represents the name of this game.
    private String nameGame;
    // attribute to determine if the game is over.
    private boolean gameOver;
    // attribute to count the number of laser shots the user do during the game.
    private int lasersFired;
    // attribute to count the number of failed attempts the user has during the game.
    private int attemptsFailed;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the grid of the game.
    private Grid grid;
    // relation to the binary search tree of the program.
    private BSTUsers bstUsers;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: Game
	 * Constructor method of a game. <br>
	 * @param nameGame - name of the game - nameGame = String, nameGame != null, nameGame != ""
	 * @throws ClassNotFoundException - if the program tries to load in a class through its String name but no definition for the class with the specified name could be found.
	 * @throws IOException - if it cannot read the file properly while loading.
	*/
    public Game(String nameGame) throws ClassNotFoundException, IOException {
        this.nameGame = nameGame;
        bstUsers = new BSTUsers();
        gameOver = false;
        loadUsers();
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getLasersFired() {
        return lasersFired;
    }

    public void setLasersFired(int lasersFired) {
        this.lasersFired = lasersFired;
    }

    public int getAttemptsFailed() {
        return attemptsFailed;
    }

    public void setAttemptsFailed(int attemptsFailed) {
        this.attemptsFailed = attemptsFailed;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public BSTUsers getBstUsers() {
        return bstUsers;
    }

    public void setBstUsers(BSTUsers bstUsers) {
        this.bstUsers = bstUsers;
    }

    /**
     * Name: buildGrid
     * Method used to build a grid with all its boxes (nodes) and mirrors present. <br>
     * <b>pre: </b> The user has already specified the rows number, the columns number and the mirrors number to create the grid. <br>
     * <b>post: </b> Grid built and ready to play. <br>
     * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the game.getGrid() - mirrors = int, mirrors != null, mirrors != 0.
    */
    public void buildGrid(int rows, int columns, int mirrors) {
        grid = new Grid(rows, columns, mirrors);
        grid.setFirst(grid.createBox(0, 0));
        grid.createGridDown(0, 0, grid.getFirst());
        grid.randomMirrorsDown(grid.getFirst());
    }

    /**
     * Name: guessMirror
     * Method used to guess the location of a mirror in the grid. <br>
     * <b>pre: </b> The number of mirrors remaining must be different from 0. The user has already fired the laser beam for the first time. <br>
     * <b>post: </b> Guessing process of a mirror. <br>
     * @param guess - Command to guess the mirror - guess = String, guess != null, guess != "".
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @return A String with a message about the
    */
    public String guessMirror(String guess, String nickname) {
        String message = "";
        if (guess.length() >= 4) {
            if (guess.charAt(0) == 'L') {
                if (guess.charAt(guess.length() - 1) == Cell.MIRROR_LEFT || guess.charAt(guess.length() - 1) == Cell.MIRROR_RIGHT) {
                    String nomenclature = guess.substring(1, guess.length() - 1);
                    Cell objSearch = grid.searchBoxDown(nomenclature, grid.getFirst());
                    if (objSearch != null) {
                        if (objSearch.getHasMirror() == true && objSearch.getMirror() == guess.charAt(guess.length() - 1)) {
                            if (objSearch.getMirrorWrong()) {
                                objSearch.setMirrorWrong(false);
                            }
                            objSearch.setMirrorGuessed(true);
                            grid.setMirrors(grid.getMirrors() - 1);
                            message = "\nGreat!\n";
                        } else {
                            message = "\nWrong one.\n";
                            attemptsFailed += 1;
                            objSearch.setMirrorWrong(true);
                        }
                    } else
                        message = "\nInvalid cell identifier. Try again.\n";
                } else
                    message = "\nInvalid character " + "'" + guess.charAt(guess.length() - 1) + "'" + " in command " + "'" + guess + "' .\n";
            } else
                message = "\nInvalid character " + "'" + guess.charAt(0) + "'" + " in command " + "'" + guess + "' .\n";
        } else
            message = "\nInvalid command: " + "'" + guess + "' .\n";
        return message;
    }

    /**
     * Name: loadUsers
     * Method used to deserialize all the User nodes stored in the binary search tree, loading just its root. <br>
     * <b>post: </b> Loading process determined of all the User nodes stored in the binary search tree. <br>
     * @throws ClassNotFoundException - if the program tries to load in a class through its String name but no definition for the class with the specified name could be found.
     * @throws IOException - if it cannot read the file properly while loading.
    */
    public void loadUsers() throws ClassNotFoundException, IOException {
        File file = new File(SAVE_USERS_PATH_FILE);
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            bstUsers.setRoot((User) ois.readObject());
            ois.close();
        }
    }

    /**
     * Name: scoreCalculation
     * Method used to calculate the score of a user after winning, or after returning to the main menu without finishing the game. <br>
     * <b>pre: </b> The user won the game, or the user returned to the main menu without finishing or winning the game. <br>
     * <b>post: </b> User score calculated. <br>
     * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the game.getGrid() - mirrors = int, mirrors != null, mirrors != 0.
     * @return A double with the user score rounded to 4 decimal places.
    */
    public double scoreCalculation(int rows, int columns, int mirrors) {
        return Math.round((Math.log10(Math.pow(lasersFired * 50 + attemptsFailed * 100, mirrors)) / Math.log10(rows * columns)) * 10000d) / 10000d;
    }
}