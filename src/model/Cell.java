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

    public static final char START = 'S';
    public static final char END = 'E';
    public static final char MIRROR_LEFT = 'L';
    public static final char MIRROR_RIGHT = 'R';
    public static final String SUPERIOR_LEFT = "SL";
    public static final String SUPERIOR_RIGHT = "SR";
    public static final String INFERIOR_LEFT = "IL";
    public static final String INFERIOR_RIGHT = "IR";
    public static final String LEFT_EDGE = "LE";
    public static final String RIGHT_EDGE = "RE";
    public static final String TOP_EDGE = "TE";
    public static final String BOTTOM_EDGE = "BE";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String nomenclature;
    private char mirror;
    private boolean isCorner;
    private boolean isEdge;
    private String typeCorner;
    private String typeEdge;
    private char isStart;
    private char isEnd;
    private boolean hasMirror;
    private boolean mirrorGuessed;
    private boolean mirrorWrong;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Cell next;
    private Cell prev;
    private Cell up;
    private Cell down;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: Cell
	 * Constructor method of a cell. <br>
	 * @param nomenclature - Cell nomenclature - nomenclature = String, nomenclature != null, nomenclature != ""
     * @param hasMirror - Attribute to identify if a cell has a mirror - hasMirror = boolean, hasMirror != null, hasMirror begins in false once the Cell object is created.
     * @param mirror - Mirror inclination - mirror = char, mirror != null, mirror begins empty once the Cell object is created.
     * @param isCorner - Attribute to identify if a cell is in a corner of the grid - isCorner = boolean, isCorner != null
     * @param isEdge - Attribute to identify if a cell is on an edge of the grid - isEdge = boolean, isEdge != null
     * @param typeCorner - Corner type if a cell is in a corner of the grid - typeCorner = String, typeCorner != null
     * @param typeEdge - Edge type if a cell is on an edge of the grid - typeEdge = String, typeEdge != null
     * @param isStart - Attribute to identify if a cell was chosen to begin a laser shot in it - isStart = char, isStart != null, isStart begins empty once the Cell object is created.
     * @param isEnd - Attribute to identify in which cell the laser beam ended its traversal - isEnd = char, isEnd != null, isEnd begins empty once the Cell object is created.
     * @param mirrorGuessed - Attribute to identify if a mirror present in a cell was guessed - mirrorGuessed = boolean, mirrorGuessed != null, mirrorGuessed begins in false once the Cell object is created.
     * @param mirrorWrong - Attribute to identify if a user guessed wrongly a mirror present in a cell - mirrorWrong = boolean, mirrorWrong != null, mirrorWrong begins in false once the Cell object is created.
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