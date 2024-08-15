package boardgame;

public abstract class Piece {
    protected Position position;

    public Piece(Board board) {
        position = null;
        this.board = board;
    }
    private Board board;

   
    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position p ){
        return possibleMoves()[p.getRow()][p.getColumn()];
    }

    public boolean isThereAnyPossibleMoves(){
        boolean[][] mat = possibleMoves();
        for(int i = 0;i<mat.length;i++){
            for (int j = 0;j<mat.length;j++){
                if (mat[i][j])
                    return true;
            }
        }
        return false;
    }
    
}
