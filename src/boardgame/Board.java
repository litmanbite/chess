package boardgame;

import chess.ChessPiece;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new BoardExcep("There has to be atleast 1 column and row");
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

    public Piece[][] getPieces(ChessPiece p) {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }
    
    public Piece pieces(int x,int y){
        if (!posE(x, y))
            throw new BoardExcep("Not on the board!");
        return pieces[x][y];
    }

    public Piece pieces(Position pos){
        if (!positionE(pos))
            throw new BoardExcep("Not on the board!");
        return pieces[pos.getRow()][pos.getColumn()];
    }

    public void placePiece(Piece p,Position pp){
        if (pieceE(pp))
            throw new BoardExcep("A piece is there already on position :"+pp);
        pieces[pp.getRow()][pp.getColumn()]=p;
        p.position=pp;
    }

    private boolean posE(int r,int c){
       return  r >=  0 && r < getRows() && c >=0 && c < getColumns();
    }

    public boolean pieceE(Position p){
        if (!positionE(p))
            throw new BoardExcep("Not on the board!");
        return pieces(p)!=null;

    }


    public boolean positionE(Position p){
        return posE(p.getRow(),p.getColumn());
    }

    public Piece rmPiece(Position pos){
        if (!positionE(pos))
            throw new BoardExcep("Not on the board!");
        if (pieces(pos)==null)
            return null;
        Piece aux = pieces(pos);
        aux.position=null;
        pieces[pos.getRow()][pos.getColumn()] = null;
        return aux;
    }
}
