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

    // attribute that represents the number of rows of a grid.
    private int rows;
    // attribute that represents the number of columns of a grid.
    private int columns;
    // attribute that represents the number of mirrors chosen by the user that will be placed randomly in a grid.
    private int mirrors;
    // attribute used in the randomMirrorsDown() and the randomMirrorsRight() method to count the number of mirrors that have already been placed randomly in a grid.
    private int counterMirrors;
    // attribute to determine if the traversal of a laser beam has finished or not.
    private boolean finish;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the first cell of a grid.
    private Cell first;

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

    public Cell getFirst() {
        return first;
    }

    public void setFirst(Cell first) {
        this.first = first;
    }

    /**
     * Name: createGridDown
     * Recursive method used to create the first cell (node) of each row of the grid. <br>
     * <b>pre: </b> The first cell (node) of the grid was already created. <br>
     * <b>post: </b> All first boxes (nodes) of each row of the grid in question were created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @param currentFirstRow - Current cell  - currentFirstRow = Cell object
    */
    public void createGridDown(int i, int j, Cell currentFirstRow) {  // Method taken from Juan Manuel Reyes' video about the matrix of linked lists.  https://www.youtube.com/watch?v=jA9jLQDcfbI&feature=youtu.be
        createGridRight(i, j + 1, currentFirstRow, currentFirstRow.getUp());
        if (i + 1 < rows) {
            Cell downFirstRow = createBox(i + 1, j);
            downFirstRow.setUp(currentFirstRow);
            currentFirstRow.setDown(downFirstRow);
            createGridDown(i + 1, j, downFirstRow);
        }
    }

    /**
     * Name: createGridRight
     * Recursive method used to create the boxes (nodes) of a same row of the grid (the columns in other words). <br>
     * <b>pre: </b> The first cell (node) of the grid was already created. <br>
     * <b>post: </b> All the boxes (nodes) of a same row of the grid in question were created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @param prev - Previous cell  - prev = Cell object
     * @param rowPrev - Last cell in the previous row  - rowPrev = Cell object
    */
    private void createGridRight(int i, int j, Cell prev, Cell rowPrev) {  // Method taken from Juan Manuel Reyes' video about the matrix of linked lists.  https://www.youtube.com/watch?v=jA9jLQDcfbI&feature=youtu.be
        if (j < columns) {
            Cell current = createBox(i, j);
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
     * Method used to create a cell (node) of the grid (linked matrix). <br>
     * <b>pre: </b> Grid already initialized. <br>
     * <b>post: </b> Cell created. <br>
     * @param i - Current numerical row - i = int, i != null
     * @param j - Current numerical column - j = int, j != null
     * @return A new Cell object (node) to link it to the grid.
    */
    public Cell createBox(int i, int j) {
        char value = j < 0 || j > 25 ? '?' : (char) ('A' + j); // line taken from  https://stackoverflow.com/questions/10813154/how-do-i-convert-a-number-to-a-letter-in-java
        String nomenclature = String.valueOf(i + 1) + value;
        boolean isCorner = false;
        boolean isEdge = false;
        String typeCorner = "";
        String typeEdge = "";
        if (i == 0 && j == 0) {
            isCorner = true;
            typeCorner = Cell.SUPERIOR_LEFT;
        }
        if (i == 0 && j == columns - 1) {
            isCorner = true;
            typeCorner = Cell.SUPERIOR_RIGHT;
        }
        if (i == rows - 1 && j == 0) {
            isCorner = true;
            typeCorner = Cell.INFERIOR_LEFT;
        }
        if (i == rows - 1 && j == columns - 1) {
            isCorner = true;
            typeCorner = Cell.INFERIOR_RIGHT;
        }
        if (i == 0 && j > 0 && j < columns - 1) {
            isEdge = true;
            typeEdge = Cell.TOP_EDGE;
        }
        if (j == 0 && i > 0 && i < rows - 1) {
            isEdge = true;
            typeEdge = Cell.LEFT_EDGE;
        }
        if (i == rows - 1 && j > 0 && j < columns - 1) {
            isEdge = true;
            typeEdge = Cell.BOTTOM_EDGE;
        }
        if (j == columns - 1 && i > 0 && i < rows - 1) {
            isEdge = true;
            typeEdge = Cell.RIGHT_EDGE;
        }
        Cell cell = new Cell(nomenclature, false, ' ', isCorner, isEdge, typeCorner, typeEdge, ' ', ' ', false, false);
        return cell;
    }

    /**
     * Name: randomMirrorsDown
     * Method used to go to the next row of the grid, while evoking the recursive method randomMirrorsRight for it to randomly assign or not a mirror to a cell in a same row of the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The random process of assigning mirrors to the boxes (nodes) was done. <br>
     * @param cell - First cell (node) of a row - cell = Cell object
    */
    public void randomMirrorsDown(Cell cell) {
        if (counterMirrors == mirrors)
            return;
        Cell down = cell;
        if (down != null) {
            Cell right = down;
            randomMirrorsRight(right);
            randomMirrorsDown(down.getDown());
        }
        if (counterMirrors != mirrors) {
            Cell again = first;
            randomMirrorsDown(again);
        }
    }

    /**
     * Name: randomMirrorsRight
     * Recursive method used to randomly assign or not a mirror to a cell in a same row of the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> The random process of assigning mirrors to the boxes (nodes) in a same row was done. <br>
     * @param right - Current cell (node) in a row - right = Cell object
    */
    private void randomMirrorsRight(Cell right) {
        if (right != null) {
            Random random = new Random();
            boolean hasAMirror = random.nextBoolean();
            if (hasAMirror == true) {
                if (right.getHasMirror() == false && counterMirrors < mirrors) {
                    char mirror;
                    boolean inclination = random.nextBoolean();
                    if (inclination == true)
                        mirror = Cell.MIRROR_LEFT;
                    else {
                        mirror = Cell.MIRROR_RIGHT;
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
     * @param first - First cell (node) of a row - first = Cell object
    */
    public void displayDown(Cell first) {
        Cell down = first;
        if (down != null) {
            Cell right = down;
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
     * @param right - Current cell (node) in a row - right = Cell object
    */
    private void displayRight(Cell right) {
        if (right != null) {
            if (right.getMirrorGuessed()) {
                if (right.getMirror() == Cell.MIRROR_LEFT)
                    System.out.print("[\\]");
                else if (right.getMirror() == Cell.MIRROR_RIGHT)
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
     * @param first - First cell (node) of a row - first = Cell object
     * @param chosen - Chosen cell (node) to begin the laser shot - chosen = Cell object - chosen != null
    */
    public void displayDownTemp(Cell first, Cell chosen) {
        finish = true;
        Cell down = first;
        if (down != null) {
            Cell right = down;
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
     * @param right - Current cell (node) in a row - right = Cell object
     * @param chosen - Chosen cell (node) to begin the laser shot - chosen = Cell object - chosen != null
    */
    private void displayRightTemp(Cell right, Cell chosen) {
        if (right != null) {
            if (right.getIsStart() == Cell.START && !chosen.getNomenclature().equals(right.getNomenclature()))
                System.out.print("[" + Cell.START + "]");
            else if (right.getIsStart() == Cell.START && chosen.getNomenclature().equals(right.getNomenclature()))
                System.out.print("[" + chosen.getIsEnd() + "]");
            else if (right.getNomenclature().equals(chosen.getNomenclature()) && right.getMirror() != Cell.START)
                System.out.print("[" + Cell.END + "]");
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
     * @param first - First cell (node) of a row - first = Cell object
    */
    public void showMirrorsDown(Cell first) {
        Cell down = first;
        if (down != null) {
            Cell right = down;
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
     * @param right - Current cell (node) in a row - right = Cell object
    */
    private void showMirrorsRight(Cell right) {
        if (right != null) {
            if (right.getMirror() == Cell.MIRROR_LEFT)
                System.out.print("[\\]");
            else if (right.getMirror() == Cell.MIRROR_RIGHT)
                System.out.print("[/]");
            else {
                System.out.print("[ ]");
            }
            showMirrorsRight(right.getNext());
        }
    }

    /**
     * Name: searchBoxDown
     * Method used to go to the next row of the grid, while evoking the recursive method searchBoxRight for it to search in a same row the existence of a cell (node) in the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> Searching process determined of a cell (node) from the grid. <br>
     * @param nomenclature - Cell nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
     * @param first - First cell (node) of a row - first = Cell object
     * @return A Cell object different from null if the cell (node) in question was found in the grid, or with null if not.
    */
    public Cell searchBoxDown(String nomenclature, Cell first) {
        Cell objSearch = null;
        Cell down = first;
        if (down != null) {
            Cell right = down;
            objSearch = searchBoxRight(right, nomenclature);
            if (objSearch == null)
                objSearch = searchBoxDown(nomenclature, down.getDown());
        }
        return objSearch;
    }

    /**
     * Name: searchBoxRight
     * Recursive method used to search in a same row the existence of a cell (node) in the grid. <br>
     * <b>pre: </b> The grid was already created with all its boxes (nodes). <br>
     * <b>post: </b> Searching process determined of a cell (node) in a same row of the grid. <br>
     * @param right - Current cell (node) in a row - right = Cell object
     * @param nomenclature - Cell nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
    */
    private Cell searchBoxRight(Cell right, String nomenclature) {
        Cell objSearch = null;
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
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a cell (node) of the grid. <br>
     * @param chosen - Chosen cell (node) to begin the laser shot, that then will become the current cell in the traversal - chosen = Cell object
    */
    public void moveLeft(Cell chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Cell.MIRROR_LEFT)
                    moveUp(chosen.getUp());
                else if (chosen.getMirror() == Cell.MIRROR_RIGHT)
                    moveDown(chosen.getDown());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Cell.TOP_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveLeft(chosen.getPrev());
                    } else if (chosen.getTypeEdge().equals(Cell.BOTTOM_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveLeft(chosen.getPrev());
                    } else if (chosen.getTypeEdge().equals(Cell.LEFT_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveLeft(chosen.getPrev());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Cell.SUPERIOR_RIGHT) || chosen.getTypeCorner().equals(Cell.SUPERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Cell.INFERIOR_RIGHT) || chosen.getTypeCorner().equals(Cell.INFERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_RIGHT)
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
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a cell (node) of the grid. <br>
     * @param chosen - Chosen cell (node) to begin the laser shot, that then will become the current cell in the traversal - chosen = Cell object
    */
    public void moveRight(Cell chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Cell.MIRROR_LEFT)
                    moveDown(chosen.getDown());
                else if (chosen.getMirror() == Cell.MIRROR_RIGHT)
                    moveUp(chosen.getUp());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Cell.TOP_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveRight(chosen.getNext());
                    } else if (chosen.getTypeEdge().equals(Cell.BOTTOM_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveRight(chosen.getNext());
                    } else if (chosen.getTypeEdge().equals(Cell.RIGHT_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveRight(chosen.getNext());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Cell.SUPERIOR_LEFT) || chosen.getTypeCorner().equals(Cell.SUPERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Cell.INFERIOR_LEFT) || chosen.getTypeCorner().equals(Cell.INFERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_LEFT)
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
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a cell (node) of the grid. <br>
     * @param chosen - Chosen cell (node) to begin the laser shot, that then will become the current cell in the traversal - chosen = Cell object
    */
    public void moveUp(Cell chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Cell.MIRROR_LEFT)
                    moveLeft(chosen.getPrev());
                else if (chosen.getMirror() == Cell.MIRROR_RIGHT)
                    moveRight(chosen.getNext());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Cell.LEFT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveUp(chosen.getUp());
                    } else if (chosen.getTypeEdge().equals(Cell.RIGHT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveUp(chosen.getUp());
                    } else if (chosen.getTypeEdge().equals(Cell.TOP_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveUp(chosen.getUp());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Cell.INFERIOR_LEFT) || chosen.getTypeCorner().equals(Cell.SUPERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_LEFT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Cell.INFERIOR_RIGHT) || chosen.getTypeCorner().equals(Cell.SUPERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_RIGHT)
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
     * <b>pre: </b> The grid was already created with all its boxes (nodes). The user has already fired the laser beam in a cell (node) of the grid. <br>
     * @param chosen - Chosen cell (node) to begin the laser shot, that then will become the current cell in the traversal - chosen = Cell object
    */
    public void moveDown(Cell chosen) {
        if (chosen != null) {
            if (chosen.getHasMirror() == true) {
                if (chosen.getMirror() == Cell.MIRROR_LEFT)
                    moveRight(chosen.getNext());
                else if (chosen.getMirror() == Cell.MIRROR_RIGHT)
                    moveLeft(chosen.getPrev());
            }
            if (!finish) {
                if (chosen.getIsEdge() == true) {
                    if (chosen.getTypeEdge().equals(Cell.LEFT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_RIGHT)
                            finishRoad(chosen);
                        else
                            moveDown(chosen.getDown());
                    } else if (chosen.getTypeEdge().equals(Cell.RIGHT_EDGE)) {
                        if (chosen.getHasMirror() == true && chosen.getMirror() == Cell.MIRROR_LEFT)
                            finishRoad(chosen);
                        else
                            moveDown(chosen.getDown());
                    } else if (chosen.getTypeEdge().equals(Cell.BOTTOM_EDGE) && !chosen.getHasMirror())
                        finishRoad(chosen);
                    else if (!chosen.getHasMirror())
                        moveDown(chosen.getDown());
                } else if (chosen.getIsCorner()) {
                    if ((chosen.getTypeCorner().equals(Cell.SUPERIOR_LEFT) || chosen.getTypeCorner().equals(Cell.INFERIOR_LEFT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_RIGHT)
                        finishRoad(chosen);
                    else if ((chosen.getTypeCorner().equals(Cell.SUPERIOR_RIGHT) || chosen.getTypeCorner().equals(Cell.INFERIOR_RIGHT)) && chosen.getHasMirror() && chosen.getMirror() == Cell.MIRROR_LEFT)
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
     * Method to be invoked when the traversal did by the laser beam through the grid finished. Updates of the cell object entered by parameter are made, and the displayDownTemp method is invoked. <br>
     * <b>pre: </b> The laser beam traversal through the grid finished. <br>
     * @param chosen - Cell (node) where the laser beam finished its traversal - chosen = Cell object, chosen != null
    */
    public void finishRoad(Cell chosen) {
        if (chosen.getIsStart() != 'S')
            chosen.setIsEnd(Cell.END);
        else {
            chosen.setIsEnd('M');
        }
        Cell firstTemp = first;
        displayDownTemp(firstTemp, chosen);
        chosen.setIsEnd(' ');
    }
}