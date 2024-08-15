package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import pieces.King;
import pieces.Rook;

public class Chessmatch {
    private Board board;

    public Chessmatch(){
        board = new Board(8,8);
        initialize();
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

    public ChessPiece performMove(ChessPos from,ChessPos to){
        Position f = from.toPos();
        Position t = to.toPos();
        validateFrom(f);
        Piece capturedPiece = makeMove(f,t);
        return (ChessPiece) capturedPiece;
    }

   private void validateFrom(Position p){
        if (!board.pieceE(p))
            throw new ChessExcep("There is no piece at starting spot !");
        if (!board.pieces(p).isThereAnyPossibleMoves())
            throw new ChessExcep("No available moves !");
   }

   private Piece makeMove(Position from,Position to){
        Piece p = board.rmPiece(from);
        Piece captured = board.rmPiece(to);
        board.placePiece(p, to);
        return captured;
   }

    private void initialize() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
       // placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        //  placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
       //   placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        //  placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
       //   placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        //  placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
     //   placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        //  placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
       //   placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
      //    placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
      //    placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
       //   placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        //  placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

    }

    private void placeNewPiece(char c,int r,ChessPiece p){
        board.placePiece(p, new ChessPos(r, c).toPos());
    }
}
