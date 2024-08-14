package boardgame;

public class Piece {
    protected Position position;

    public Piece(Board board) {
        position = null;
        this.board = board;
    }
    private Board board;

   
    protected Board getBoard() {
        return board;
    }
    
}
