/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Author: TheLordJuanes
 * @Date: November, 4th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

public class Cell {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // constant to identify a cell as the start point of the laser beam traversal through the grid.
    public static final char START = 'S';
    // constant to identify a cell as the end point of the laser beam traversal through the grid.
    public static final char END = 'E';
    // constant that represents the left inclination of an eventual mirror present in a cell.
    public static final char MIRROR_LEFT = 'L';
    // constant that represents the right inclination of an eventual mirror present in a cell.
    public static final char MIRROR_RIGHT = 'R';
    // constant to identify a cell in the upper left corner of the grid.
    public static final String SUPERIOR_LEFT = "SL";
    // constant to identify a cell in the upper right corner of the grid.
    public static final String SUPERIOR_RIGHT = "SR";
    // constant to identify a cell in the lower left corner of the grid.
    public static final String INFERIOR_LEFT = "IL";
    // constant to identify a cell in the lower right corner of the grid.
    public static final String INFERIOR_RIGHT = "IR";
    // constant to identify a cell on the left edge of the grid.
    public static final String LEFT_EDGE = "LE";
    // constant to identify a cell on the right edge of the grid.
    public static final String RIGHT_EDGE = "RE";
    // constant to identify a cell on the upper edge of the grid.
    public static final String TOP_EDGE = "TE";
    // constant to identify a cell on the lower edge of the grid.
    public static final String BOTTOM_EDGE = "BE";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // attribute that represents the nomenclature/identifier of a cell.
    private String nomenclature;
    // attribute that represents the inclination of an eventual mirror present in a cell.
    private char mirror;
    // attribute to determine if a cell is in a corner of the grid or not.
    private boolean isCorner;
    // attribute to determine if a cell is on an edge of the grid or not.
    private boolean isEdge;
    // attribute that represents the corner type of an eventual cell present in a corner of the grid.
    private String typeCorner;
    // attribute that represents the edge type of an eventual cell present on an edge of the grid.
    private String typeEdge;
    // attribute to identify a cell chosen by the user as the start point of a laser beam traversal.
    private char isStart;
    // attribute to identify if in a cell the laser beam ended its traversal.
    private char isEnd;
    // attribute to determine if a cell has a mirror or not.
    private boolean hasMirror;
    // attribute to determine if a mirror present in a cell was guessed by the user.
    private boolean mirrorGuessed;
    // attribute to determine if the user guessed wrongly a mirror present in a cell.
    private boolean mirrorWrong;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    // relation to the right cell of a current node.
    private Cell next;
    // relation to the left cell of a current node.
    private Cell prev;
    // relation to the upwards cell of a current node.
    private Cell up;
    // relation to the downwards cell of a current node.
    private Cell down;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: Cell
	 * Constructor method of a cell. <br>
	 * @param nomenclature - Cell nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
     * @param hasMirror - Attribute to determine if a cell has a mirror - hasMirror = boolean, hasMirror != null, hasMirror begins in false once the Cell object is created.
     * @param mirror - Mirror inclination - mirror = char, mirror != null, mirror begins empty once the Cell object is created.
     * @param isCorner - Attribute to determine if a cell is in a corner of the grid - isCorner = boolean, isCorner != null
     * @param isEdge - Attribute to determine if a cell is on an edge of the grid - isEdge = boolean, isEdge != null
     * @param typeCorner - Corner type if a cell is in a corner of the grid - typeCorner = String, typeCorner != null
     * @param typeEdge - Edge type if a cell is on an edge of the grid - typeEdge = String, typeEdge != null
     * @param isStart - Attribute to identify if a cell was chosen to begin a laser shot in it - isStart = char, isStart != null, isStart begins empty once the Cell object is created.
     * @param isEnd - Attribute to identify in which cell the laser beam ended its traversal - isEnd = char, isEnd != null, isEnd begins empty once the Cell object is created.
     * @param mirrorGuessed - Attribute to determine if a mirror present in a cell was guessed - mirrorGuessed = boolean, mirrorGuessed != null, mirrorGuessed begins in false once the Cell object is created.
     * @param mirrorWrong - Attribute to determine if a user guessed wrongly a mirror present in a cell - mirrorWrong = boolean, mirrorWrong != null, mirrorWrong begins in false once the Cell object is created.
	*/
    public Cell(String nomenclature, boolean hasMirror, char mirror, boolean isCorner, boolean isEdge, String typeCorner, String typeEdge, char isStart, char isEnd, boolean mirrorGuessed, boolean mirrorWrong) {
        this.nomenclature = nomenclature;
        this.hasMirror = hasMirror;
        this.mirror = mirror;
        this.isCorner = isCorner;
        this.isEdge = isEdge;
        this.typeCorner = typeCorner;
        this.typeEdge = typeEdge;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.mirrorGuessed = mirrorGuessed;
        this.mirrorWrong = mirrorWrong;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public boolean getHasMirror() {
        return hasMirror;
    }

    public void setHasMirror(boolean hasMirror) {
        this.hasMirror = hasMirror;
    }

    public char getMirror() {
        return mirror;
    }

    public void setMirror(char mirror) {
        this.mirror = mirror;
    }

    public boolean getIsCorner() {
        return isCorner;
    }

    public void setIsCorner(boolean isCorner) {
        this.isCorner = isCorner;
    }

    public boolean getIsEdge() {
        return isEdge;
    }

    public void setIsEdge(boolean isEdge) {
        this.isEdge = isEdge;
    }

    public String getTypeCorner() {
        return typeCorner;
    }

    public void setTypeCorner(String typeCorner) {
        this.typeCorner = typeCorner;
    }

    public String getTypeEdge() {
        return typeEdge;
    }

    public void setTypeEdge(String typeEdge) {
        this.typeEdge = typeEdge;
    }

    public char getIsStart() {
        return isStart;
    }

    public void setIsStart(char isStart) {
        this.isStart = isStart;
    }

    public char getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(char isEnd) {
        this.isEnd = isEnd;
    }

    public boolean getMirrorGuessed() {
        return mirrorGuessed;
    }

    public void setMirrorGuessed(boolean mirrorGuessed) {
        this.mirrorGuessed = mirrorGuessed;
    }

    public boolean getMirrorWrong() {
        return mirrorWrong;
    }

    public void setMirrorWrong(boolean mirrorWrong) {
        this.mirrorWrong = mirrorWrong;
    }

    public Cell getNext() {
        return next;
    }

    public void setNext(Cell right) {
        this.next = right;
    }

    public Cell getPrev() {
        return prev;
    }

    public void setPrev(Cell left) {
        this.prev = left;
    }

    public Cell getUp() {
        return up;
    }

    public void setUp(Cell up) {
        this.up = up;
    }

    public Cell getDown() {
        return down;
    }

    public void setDown(Cell down) {
        this.down = down;
    }
}