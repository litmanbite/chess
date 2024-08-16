package chess;
import boardgame.Position;



public class ChessPos {
    private char c;
    private int r;

    public ChessPos(int r , char c){
        if (c<'a' || c>'h' || r<1 || r>8)
            throw new ChessExcep("Error, invalid position");
        this.c=c;
        this.r=r;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    protected Position toPos() {
        return new Position(8 - r, c - 'a');
    }
    
    protected static ChessPos fromPosition(Position position) {
        return new ChessPos(8 - position.getRow(),(char) ('a' + position.getColumn()));
    }

    @Override
    public String toString() {
        return "ChessPos [c=" + c + ", r=" + r + "]";
    }

    
}
