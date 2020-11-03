/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String nickname;
    private double score;
    private int rowsChosen;
    private int columnsChosen;
    private int mirrorsChosen;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private User parent;
    private User left;
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