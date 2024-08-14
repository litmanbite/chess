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
        placeNewPiece('b',6,new Rook(board, Color.BLACK));
        placeNewPiece('a',4,new King(board, Color.WHITE));
    }

    private void placeNewPiece(char c,int r,ChessPiece p){
        board.placePiece(p, new ChessPos(r, c).toPos());
    }
}
