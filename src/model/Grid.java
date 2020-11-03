/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.util.Random;

public class Grid {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private int rows;
    private int columns;
    private int mirrors;
    private int counterMirrors;
    private boolean finish;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Box first;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: Grid
	 * Constructor method of a grid. <br>
	 * @param rows - Grid rows - rows = int, rows != null, nickname != 0.
     * @param columns - Grid columns - columns = int, columns != null, columns != 0.
     * @param mirrors - Number of mirrors in the game.getGrid() - mirrors = int, mirrors != null, mirrors != 0.
	*/
    public Grid(int rows, int columns, int mirrors) {
        this.rows = rows;
        this.columns = columns;
        this.mirrors = mirrors;
        counterMirrors = 0;
        finish = false;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMirrors() {
        return mirrors;
    }

    public void setMirrors(int mirrors) {
        this.mirrors = mirrors;
    }

    public int getCounterMirrors() {
        return counterMirrors;
    }

    public void setCounterMirrors(int counterMirrors) {
        this.counterMirrors = counterMirrors;
    }

    public boolean getFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public Box getFirst() {
        return first;
    }

    public void setFirst(Box first) {
        this.first = first;
    }

    /**
     * Name: createGridDown
     * Recursive method used to create the first box (node) of each row of the grid. <br>
     * <b>pre: </b> The first box (node) of the grid was already created. <br>
     * <b>post: </b> All first boxes (nodes) of each row of the grid in question were created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @param currentFirstRow - Current box  - currentFirstRow = Box object
    */
    public void createGridDown(int i, int j, Box currentFirstRow) {  // Method taken from Juan Manuel Reyes' video about the matrix of linked lists.  https://www.youtube.com/watch?v=jA9jLQDcfbI&feature=youtu.be
        createGridRight(i, j + 1, currentFirstRow, currentFirstRow.getUp());
        if (i + 1 < rows) {
            Box downFirstRow = createBox(i + 1, j);
            downFirstRow.setUp(currentFirstRow);
            currentFirstRow.setDown(downFirstRow);
            createGridDown(i + 1, j, downFirstRow);
        }
    }

    /**
     * Name: createGridRight
     * Recursive method used to create the boxes (nodes) of a same row of the grid (the columns in other words). <br>
     * <b>pre: </b> The first box (node) of the grid was already created. <br>
     * <b>post: </b> All the boxes (nodes) of a same row of the grid in question were created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @param prev - Previous box  - prev = Box object
     * @param rowPrev - Last box in the previous row  - rowPrev = Box object
    */
    private void createGridRight(int i, int j, Box prev, Box rowPrev) {  // Method taken from Juan Manuel Reyes' video about the matrix of linked lists.  https://www.youtube.com/watch?v=jA9jLQDcfbI&feature=youtu.be
        if (j < columns) {
            Box current = createBox(i, j);
            current.setPrev(prev);
            prev.setNext(current);
            if (rowPrev != null) {
                rowPrev = rowPrev.getNext();
                current.setUp(rowPrev);
                rowPrev.setDown(current);
            }
            createGridRight(i, j + 1, current, rowPrev);
        }
    }

    /**
     * Name: createBox
     * Method used to create a box (node) of the grid (linked matrix). <br>
     * <b>pre: </b> Grid already initialized. <br>
     * <b>post: </b> Box created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @return A new Box object (node) to link it to the grid.
    */
    public Box createBox(int i, int j) {
        char value = j < 0 || j > 25 ? '?' : (char) ('A' + j); // line taken from  https://stackoverflow.com/questions/10813154/how-do-i-convert-a-number-to-a-letter-in-java
        String nomenclature = String.valueOf(i + 1) + value;
        boolean isCorner = false;
        boolean isEdge = false;
        String typeCorner = "";
        String typeEdge = "";
        if (i == 0 && j == 0) {
            isCorner = true;
            typeCorner = Box.SUPERIOR_LEFT;
        }
        if (i == 0 && j == columns - 1) {
            isCorner = true;
            typeCorner = Box.SUPERIOR_RIGHT;
        }
        if (i == rows - 1 && j == 0) {
            isCorner = true;
            typeCorner = Box.INFERIOR_LEFT;
        }
        if (i == rows - 1 && j == columns - 1) {
            isCorner = true;
            typeCorner = Box.INFERIOR_RIGHT;
        }
        if (i == 0 && j > 0 && j < columns - 1) {
            isEdge = true;
            typeEdge = Box.TOP_EDGE;
        }
        if (j == 0 && i > 0 && i < rows - 1) {
            isEdge = true;
            typeEdge = Box.LEFT_EDGE;
        }
        if (i == rows - 1 && j > 0 && j < columns - 1) {
            isEdge = true;
            typeEdge = Box.BOTTOM_EDGE;
        }
        if (j == columns - 1 && i > 0 && i < rows - 1) {
            isEdge = true;
            typeEdge = Box.RIGHT_EDGE;
        }
        Box box = new Box(nomenclature, false, ' ', isCorner, isEdge, typeCorner, typeEdge, ' ', ' ', false, false);
        return box;
    }

    /**
     * Name: randomMirrorsDown
     * Method used to go to the next row of the grid, while evoking the recursive method randomMirrorsRight for it to randomly assign or not a mirror to a box in a same row of the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The random process of assigning mirrors to the boxes (nodes) was done. <br>
     * @param box - First box (node) of a row - box = Box object
    */
    public void randomMirrorsDown(Box box) {
        if (counterMirrors == mirrors)
            return;
        Box down = box;
        if (down != null) {
            Box right = down;
            randomMirrorsRight(right);
            randomMirrorsDown(down.getDown());
        }
        if (counterMirrors != mirrors) {
            Box again = first;
            randomMirrorsDown(again);
        }
    }

    /**
     * Name: randomMirrorsRight
     * Recursive method used to randomly assign or not a mirror to a box in a same row of the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The random process of assigning mirrors to the boxes (nodes) in a same row was done. <br>
     * @param right - Current box (node) in a row - right = Box object
    */
    private void randomMirrorsRight(Box right) {
        if (right != null) {
            Random random = new Random();
            boolean hasAMirror = random.nextBoolean();
            if (hasAMirror == true) {
                if (right.getHasMirror() == false && counterMirrors < mirrors) {
                    char mirror;
                    boolean inclination = random.nextBoolean();
                    if (inclination == true)
                        mirror = Box.MIRROR_LEFT;
                    else {
                        mirror = Box.MIRROR_RIGHT;
                    }
                    right.setHasMirror(true);
                    right.setMirror(mirror);
                    counterMirrors++;
                }
            }
            randomMirrorsRight(right.getNext());
        }
    }

    /**
     * Name: displayDown
     * Method used to go to the next row of the grid, while evoking the recursive method displayRight for it to display the boxes from a same row of the grid through brackets with a symbol inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from the grid were displayed through brackets with a symbol inside or not. <br>
     * @param first - First box (node) of a row - first = Box object
    */
    public void displayDown(Box first) {
        Box down = first;
        if (down != null) {
            Box right = down;
            displayRight(right);
            System.out.println();
            displayDown(down.getDown());
        }
    }

    /**
     * Name: displayRight
     * Recursive method used to display the boxes (nodes) from a same row of the grid through brackets with a symbol inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from a same row of the grid were displayed through brackets with a symbol inside or not. <br>
     * @param right - Current box (node) in a row - right = Box object
    */
    private void displayRight(Box right) {
        if (right != null) {
            if (right.getMirrorGuessed()) {
                if (right.getMirror() == Box.MIRROR_LEFT)
                    System.out.print("[\\]");
                else if (right.getMirror() == Box.MIRROR_RIGHT)
                    System.out.print("[/]");
            } else if (right.getMirrorWrong())
                System.out.print("[X]");
            else {
                System.out.print("[ ]");
            }
            displayRight(right.getNext());
        }
    }

    /**
     * Name: displayDownTemp
     * Method used to go to the next row of the grid, while evoking the recursive method displayRightTemp for it to display the boxes from a same row of the grid through brackets with 'S' ("Start"), 'E' ("End") or even 'M' (Same start and end) inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from the grid were displayed through brackets with 'S' ("Start"), 'E' ("End") or even 'M' (Same start and end) inside or not. <br>
     * @param first - First box (node) of a row - first = Box object
     * @param chosen - Chosen box (node) to begin the laser shot - chosen = Box object - chosen != null
    */
    public void displayDownTemp(Box first, Box chosen) {
        finish = true;
        Box down = first;
        if (down != null) {
            Box right = down;
            displayRightTemp(right, chosen);
            System.out.println();
            displayDownTemp(down.getDown(), chosen);
        }
    }

    /**
     * Name: displayRightTemp
     * Recursive method used to display the boxes from a same row of the grid through brackets with 'S' ("Start"), 'E' ("End") or even 'M' (Same start and end) inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from a same row of the grid were displayed through brackets with 'S' ("Start"), 'E' ("End") or even 'M' (Same start and end) inside or not. <br>
     * @param right - Current box (node) in a row - right = Box object
     * @param chosen - Chosen box (node) to begin the laser shot - chosen = Box object - chosen != null
    */
    private void displayRightTemp(Box right, Box chosen) {
        if (right != null) {
            if (right.getIsStart() == Box.START && !chosen.getNomenclature().equals(right.getNomenclature()))
                System.out.print("[" + Box.START + "]");
            else if (right.getIsStart() == Box.START && chosen.getNomenclature().equals(right.getNomenclature()))
                System.out.print("[" + chosen.getIsEnd() + "]");
            else if (right.getNomenclature().equals(chosen.getNomenclature()) && right.getMirror() != Box.START)
                System.out.print("[" + Box.END + "]");
            else {
                System.out.print("[ ]");
            }
            displayRightTemp(right.getNext(), chosen);
        }
    }

    /**
     * Name: showMirrorsDown
     * Method used to go to the next row of the grid, while evoking the recursive method showMirrorsRight for it to display the boxes from a same row of the grid through brackets, revealing the mirrors' location with '\' or '/' inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from the grid were displayed through brackets, revealing the mirrors' location with '\' or '/' inside or not. <br>
     * @param first - First box (node) of a row - first = Box object
    */
    public void showMirrorsDown(Box first) {
        Box down = first;
        if (down != null) {
            Box right = down;
            showMirrorsRight(right);
            System.out.println();
            showMirrorsDown(down.getDown());
        }
    }

    /**
     * Name: showMirrorsRight
     * Recursive method used to display the boxes from a same row of the grid through brackets, revealing the mirrors' location with '\' or '/' inside or not. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The boxes (nodes) from a same row of the grid were displayed through brackets, revealing the mirrors' location with '\' or '/' inside or not. <br>
     * @param right - Current box (node) in a row - right = Box object
    */
    private void showMirrorsRight(Box right) {
        if (right != null) {
            if (right.getMirror() == Box.MIRROR_LEFT)
                System.out.print("[\\]");
            else if (right.getMirror() == Box.MIRROR_RIGHT)
                System.out.print("[/]");
            else {
                System.out.print("[ ]");
            }
            showMirrorsRight(right.getNext());
        }
    }

    /**
     * Name: searchBoxDown
     * Method used to go to the next row of the grid, while evoking the recursive method searchBoxRight for it to search in a same row the existence of a box (node) in the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> Searching process determined of a box (node) from the grid. <br>
     * @param nomenclature - Box nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
     * @param first - First box (node) of a row - first = Box object
    */
    public Box searchBoxDown(String nomenclature, Box first) {
        Box objSearch = null;
        Box down = first;
        if (down != null) {
            Box right = down;
            objSearch = searchBoxRight(right, nomenclature);
            if (objSearch == null)
                objSearch = searchBoxDown(nomenclature, down.getDown());
        }
        return objSearch;
    }

    /**
     * Name: searchBoxRight
     * Recursive method used to search in a same row the existence of a box (node) in the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> Searching process determined of a box (node) in a same row of the grid. <br>
     * @param right - Current box (node) in a row - right = Box object
     * @param nomenclature - Box nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
    */
    private Box searchBoxRight(Box right, String nomenclature) {
        Box objSearch = null;
        if (right != null) {
            if (right.getNomenclature().equals(nomenclature))
                objSearch = right;
            if (objSearch == null)
                objSearch = searchBoxRight(right.getNext(), nomenclature);
        }
        return objSearch;
    }

    /**
     * Name: moveLeft
     * Recursive method used to the laser beam to do a left direction traversal through the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a box (node) of the grid. <br>
     * @param chosen - Chosen box (node) to begin the laser shot, that then will become the current box in the traversal - chosen = Box object
    */
    public void moveLeft(Box chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Box.MIRROR_LEFT)
                    moveUp(chosen.getUp());
                else if (chosen.getMirror() == Box.MIRROR_RIGHT)
                    moveDown(chosen.getDown());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Box.TOP_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveLeft(chosen.getPrev());
                    } else if (chosen.getTypeEdge().equals(Box.BOTTOM_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveLeft(chosen.getPrev());
                    } else if (chosen.getTypeEdge().equals(Box.LEFT_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveLeft(chosen.getPrev());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Box.SUPERIOR_RIGHT) || chosen.getTypeCorner().equals(Box.SUPERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Box.INFERIOR_RIGHT) || chosen.getTypeCorner().equals(Box.INFERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror() && chosen.getPrev() == null)
                        finishRoad(chosen);
                    else
                        moveLeft(chosen.getPrev());
                } else if (!chosen.getHasMirror())
                    moveLeft(chosen.getPrev());
            }
        }
    }

    /**
     * Name: moveRight
     * Recursive method used to the laser beam to do a right direction traversal through the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a box (node) of the grid. <br>
     * @param chosen - Chosen box (node) to begin the laser shot, that then will become the current box in the traversal - chosen = Box object
    */
    public void moveRight(Box chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Box.MIRROR_LEFT)
                    moveDown(chosen.getDown());
                else if (chosen.getMirror() == Box.MIRROR_RIGHT)
                    moveUp(chosen.getUp());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Box.TOP_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveRight(chosen.getNext());
                    } else if (chosen.getTypeEdge().equals(Box.BOTTOM_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveRight(chosen.getNext());
                    } else if (chosen.getTypeEdge().equals(Box.RIGHT_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveRight(chosen.getNext());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Box.SUPERIOR_LEFT) || chosen.getTypeCorner().equals(Box.SUPERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Box.INFERIOR_LEFT) || chosen.getTypeCorner().equals(Box.INFERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror() && chosen.getNext() == null)
                        finishRoad(chosen);
                    else
                        moveRight(chosen.getNext());
                } else if (!chosen.getHasMirror())
                    moveRight(chosen.getNext());
            }
        }
    }

    /**
     * Name: moveUp
     * Recursive method used to the laser beam to do an upward direction traversal through the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a box (node) of the grid. <br>
     * @param chosen - Chosen box (node) to begin the laser shot, that then will become the current box in the traversal - chosen = Box object
    */
    public void moveUp(Box chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Box.MIRROR_LEFT)
                    moveLeft(chosen.getPrev());
                else if (chosen.getMirror() == Box.MIRROR_RIGHT)
                    moveRight(chosen.getNext());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Box.LEFT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveUp(chosen.getUp());
                    } else if (chosen.getTypeEdge().equals(Box.RIGHT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveUp(chosen.getUp());
                    } else if (chosen.getTypeEdge().equals(Box.TOP_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveUp(chosen.getUp());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Box.INFERIOR_LEFT) || chosen.getTypeCorner().equals(Box.SUPERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Box.INFERIOR_RIGHT) || chosen.getTypeCorner().equals(Box.SUPERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror() && chosen.getUp() == null)
                        finishRoad(chosen);
                    else
                        moveUp(chosen.getUp());
                } else if (!chosen.getHasMirror())
                    moveUp(chosen.getUp());
            }
        }
    }

    /**
     * Name: moveDown
     * Recursive method used to the laser beam to do a downward direction traversal through the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a box (node) of the grid. <br>
     * @param chosen - Chosen box (node) to begin the laser shot, that then will become the current box in the traversal - chosen = Box object
    */
    public void moveDown(Box chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Box.MIRROR_LEFT)
                    moveRight(chosen.getNext());
                else if (chosen.getMirror() == Box.MIRROR_RIGHT)
                    moveLeft(chosen.getPrev());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Box.LEFT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveDown(chosen.getDown());
                    } else if (chosen.getTypeEdge().equals(Box.RIGHT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Box.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveDown(chosen.getDown());
                    } else if (chosen.getTypeEdge().equals(Box.BOTTOM_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveDown(chosen.getDown());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Box.SUPERIOR_LEFT) || chosen.getTypeCorner().equals(Box.INFERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Box.SUPERIOR_RIGHT) || chosen.getTypeCorner().equals(Box.INFERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Box.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror() && chosen.getDown() == null)
                        finishRoad(chosen);
                    else
                        moveDown(chosen.getDown());
                } else if (!chosen.getHasMirror())
                    moveDown(chosen.getDown());
            }
        }
    }

    /**
     * Name: finishRoad
     * Method to be invoked when the traversal did by the laser beam through the grid finished. Updates of the box object entered by parameter are made, and the displayDownTemp method is invoked. <br>
     * <b>pre: </b> The laser beam traversal through the grid finished. <br>
     * @param chosen - Box (node) where the laser beam finished its traversal - chosen = Box object, chosen != null
    */
    public void finishRoad(Box chosen) {
        if (chosen.getIsStart() != 'S')
            chosen.setIsEnd(Box.END);
        else {
            chosen.setIsEnd('M');
        }
        Box firstTemp = first;
        displayDownTemp(firstTemp, chosen);
        chosen.setIsEnd(' ');
    }
}