/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.Serializable;

public class User implements Serializable {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // constant that represents the version number of the User class.
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // attribute that represents the nickname of a user.
    private String nickname;
    // attribute that represents the score that a user obtains after winning the game or after returning to the menu without finishing/winning it.
    private double score;
    // attribute that represents the number of rows the user chose to create the grid.
    private int rowsChosen;
    // attribute that represents the number of columns the user chose to create the grid.
    private int columnsChosen;
    // attribute that represents the number of mirrors the user chose and that will be placed randomly in the grid.
    private int mirrorsChosen;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the parent of a node in the binary search tree.
    private User parent;
    // relation to the left child of a parent node in the binary search tree.
    private User left;
    // relation to the right child of a parent node in the binary search tree.
    private User right;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: User
	 * Constructor method of a user. <br>
	 * @param nickname - User nickname - nickname = String, nickname != null, nickname != "".
     * @param score - User score - score = double, score != null
     * @param rowsChosen - Rows that the user chose to create the grid - rowsChosen = int, rowsChosen != null, rowsChosen != 0.
     * @param columnsChosen - Columns that the user chose to create the grid - columnsChosen = int, columnsChosen != null, columnsChosen != 0.
     * @param mirrorsChosen - Mirrors that the user chose to create the grid - mirrorsChosen = int, mirrorsChosen != null, mirrorsChosen != 0.
	*/
    public User(String nickname, double score, int rowsChosen, int columnsChosen, int mirrorsChosen) {
        this.nickname = nickname;
        this.score = score;
        this.rowsChosen = rowsChosen;
        this.columnsChosen = columnsChosen;
        this.mirrorsChosen = mirrorsChosen;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRowsChosen() {
        return rowsChosen;
    }

    public void setRowsChosen(int rowsChosen) {
        this.rowsChosen = rowsChosen;
    }

    public int getColumnsChosen() {
        return columnsChosen;
    }

    public void setColumnsChosen(int columnsChosen) {
        this.columnsChosen = columnsChosen;
    }

    public int getMirrorsChosen() {
        return mirrorsChosen;
    }

    public void setMirrorsChosen(int mirrorsChosen) {
        this.mirrorsChosen = mirrorsChosen;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }

    public User getLeft() {
        return left;
    }

    public void setLeft(User left) {
        this.left = left;
    }

    public User getRight() {
        return right;
    }

    public void setRight(User right) {
        this.right = right;
    }
}