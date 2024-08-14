package chess;

import boardgame.Board;
import boardgame.Position;
import pieces.King;
import pieces.Rook;

public class Chessmatch {
    private Board board;

    public Chessmatch(){
        board = new Board(8,8);
        inicialize();
    }
    
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i<board.getRows();i++)
        {
            for (int j=0;j<board.getColumns();j++){
                mat[i][j]=(ChessPiece) board.pieces(i,j);
            }
        }
        return mat;
    }

    private void inicialize(){
        board.placePiece(new Rook(board, Color.BLACK), new Position(2, 2));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

    }
}
