package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
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

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }
    
    public Piece pieces(int x,int y){
        return pieces[x][y];
    }

    public Piece pieces(Position pos){
        return pieces[pos.getRow()][pos.getColumn()];
    }

    public void placePiece(Piece p,Position pp){
        pieces[pp.getRow()][pp.getColumn()]=p;
        p.position=pp;
    }
}
