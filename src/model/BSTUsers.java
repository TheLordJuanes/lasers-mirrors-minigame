/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BSTUsers implements Serializable {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // constant that represents the version number of the BSTUsers class.
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // attribute to rank the users' position in the leaderboard.
    private int position;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the first user (node) of the binary search tree.
    private User root;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: BSTUsers
	 * Constructor method of a binary search tree that stores User objects. <br>
	*/
    public BSTUsers() {
        position = 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public User getRoot() {
        return root;
    }

    public void setRoot(User root) {
        this.root = root;
    }

    /**
     * Name: addUser
     * Method used to add a new User node in a binary search tree. <br>
     * <b>pre: </b> The user won the game, or the user returned to the main menu without finishing or winning the game. <br>
     * <b>post: </b> Adding process of a user in the binary search tree determined. <br>
     * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @param score - User score - score = double, score != null
     * @param rowsChosen - Rows that the user chose to create the grid - rowsChosen = int, rowsChosen != null, rowsChosen != 0.
     * @param columnsChosen - Columns that the user chose to create the grid - columnsChosen = int, columnsChosen != null, columnsChosen != 0.
     * @param mirrorsChosen - Mirrors that the user chose to create the grid - mirrorsChosen = int, mirrorsChosen != null, mirrorsChosen != 0.
     * @throws IOException - if it cannot write the file properly while saving.
    */
    public void addUser(String nickname, double score, int rowsChosen, int columnsChosen, int mirrorsChosen) throws IOException {
        if (root == null) {
            root = new User(nickname, score, rowsChosen, columnsChosen, mirrorsChosen);
            saveDataUsers();
        } else
            addUser(root, new User(nickname, score, rowsChosen, columnsChosen, mirrorsChosen));
    }

    /**
     * Name: addUser
     * Recursive method used to add a new User node in a binary search tree, when the root is different from null. <br>
     * <b>pre: </b> The user won the game, or the user returned to the main menu without finishing or winning the game. <br>
     * <b>post: </b> Adding process of a user in the binary search tree determined. <br>
     * @param current - Current user - current = User object, current != null
     * @param newUser - New user in question - newUser = User object, newUser != null
     * @throws IOException - if it cannot write the file properly while saving.
    */
    private void addUser(User current, User newUser) throws IOException {
        if (newUser.getScore() < current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(newUser);
                newUser.setParent(current);
                saveDataUsers();
            } else
                addUser(current.getLeft(), newUser);
        } else {
            if (current.getRight() == null) {
                current.setRight(newUser);
                newUser.setParent(current);
                saveDataUsers();
            } else
                addUser(current.getRight(), newUser);
        }
    }

    /**
     * Name: printInorder
     * Method used to print the game data of each user who played the game, in the leaderboard after an inorder traversal through the binary search tree. <br>
     * <b>pre: </b> At least, the root of the binary search tree exists. <br>
     * <b>post: </b> An enumerated vertical list of the game data of each user who played the game has been printed. <br>
     * @param user - Current user - user = User object, user != null
     * @return A String with the game data of each user who played the game, or an empty String if the root was null.
    */
    public String printInorder(User user) { // Modified method. Original one taken from https://www.techiedelight.com/deletion-from-bst/
        String scores = "";
        if (user == null) {
            return scores;
        }
        scores += printInorder(user.getLeft());
        scores += position + "º " + user.getNickname() + " | " + user.getScore() + " points" + " | " + user.getRowsChosen() + " | " + user.getColumnsChosen() + " | " + user.getMirrorsChosen();
        position++;
        scores += printInorder(user.getRight());
        return scores;
    }

    /**
     * Name: saveDataUsers
     * Method used to serialize the User nodes from the binary search tree, saving just its root. <br>
     * <b>pre: </b> Binary search tree of users already initialized and at least the root exists. <br>
     * <b>post: </b> Binary search tree of users serialized. <br>
     * @throws IOException - if it cannot write the file properly while saving.
    */
    public void saveDataUsers() throws IOException {
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Game.SAVE_USERS_PATH_FILE));
       oos.writeObject(root);
       oos.close();
    }
}