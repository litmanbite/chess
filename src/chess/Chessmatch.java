package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import pieces.King;
import pieces.Pawn;
import pieces.Rook;

public class Chessmatch {
    private Board board;
    private Color bw;
    private int turn;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Chessmatch(){
        board = new Board(8,8);
        turn = 1;
        bw = Color.WHITE;
        initialize();
    }
    private void nextTurn(){
        turn++;
        if (bw == Color.WHITE)
            bw = Color.BLACK;
        else 
            bw = Color.WHITE;
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
        validateTo(f,t);
        Piece capturedPiece = makeMove(f,t);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }
    public boolean [][] possibleMoves(ChessPos from){
        Position pos = from.toPos();
        validateFrom(pos);
        return board.pieces(pos).possibleMoves();
    }
   private void validateFrom(Position p){
        if (!board.pieceE(p))
            throw new ChessExcep("There is no piece at starting spot !");
        if (!board.pieces(p).isThereAnyPossibleMoves())
            throw new ChessExcep("No available moves !");
        if (bw != ((ChessPiece)board.pieces(p)).getCor())
            throw new ChessExcep("Not your turn !");


   }

   private Piece makeMove(Position from,Position to){
        ChessPiece p = (ChessPiece) board.rmPiece(from);
        p.increaseMoveCount();
        Piece captured = board.rmPiece(to);
        board.placePiece(p, to);
        return captured;
   }

   private void validateTo(Position from,Position to){
        if (!board.pieces(from).possibleMove(to))
            throw new ChessExcep("Not a possible move for this piece");
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
         placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
       //   placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
      //    placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
      //    placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
       //   placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        //  placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

    }

    private void placeNewPiece(char c,int r,ChessPiece p){
        board.placePiece(p, new ChessPos(r, c).toPos());
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Color getBw() {
        return bw;
    }

    public void setBw(Color bw) {
        this.bw = bw;
    }
    private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.rmPiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}

}
}