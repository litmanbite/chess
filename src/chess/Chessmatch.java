package chess;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collector;
import java.util.stream.Collectors;


import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Chessmatch {
    private Board board;
    private Color bw;
    private int turn;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
    private boolean check;
    private boolean checkMate;
    private List<ChessPiece> piecesOn = new ArrayList<>();
    private List<ChessPiece> piecesOff = new ArrayList<>();


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
    private void nextTurn() {
        turn++;
        bw = (bw == Color.WHITE) ? Color.BLACK : Color.WHITE;
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

         if (testC(bw)){
            undoMove(f, t, capturedPiece);
            throw new ChessExcep("You cannot put yourself in check");            
        }
        
        check = (testC(opp(bw)))? true : false;
        if (testCheckMate(opp(bw)))
            checkMate = true;
        nextTurn();
        return (ChessPiece) capturedPiece;
    }
    public boolean [][] possibleMoves(ChessPos from){
        Position pos = from.toPos();
        validateFrom(pos);
        return board.pieces(pos).possibleMoves();
    }
    private boolean testCheckMate(Color color) {
		if (!testC(color)) {
			return false;
		}
		List<Piece> list = piecesOn.stream().filter(x -> ((ChessPiece)x).getCor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPos().toPos();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testC(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
    public boolean getCheckMate() {
		return checkMate;
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
        if (captured!=null){
            piecesOff.add((ChessPiece) captured);
            piecesOn.remove(captured);
        }
        return captured;
   }
   
   private void undoMove(Position f,Position t,Piece cap){
        Piece p = board.rmPiece(t);
        board.placePiece(p, f);
        if (cap != null){
            board.placePiece(cap, t);
            piecesOff.remove(cap);
            piecesOn.add((ChessPiece) cap);}
   }

   private void validateTo(Position from,Position to){
        if (!board.pieces(from).possibleMove(to))
            throw new ChessExcep("Not a possible move for this piece");
   }

    private void initialize() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}

    

    private Color opp(Color oppC){
        return (oppC == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece kPiece(Color color) {
		List<Piece> list = piecesOn.stream().filter(x -> ((ChessPiece)x).getCor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}

     private boolean testC(Color color) {
		Position kingPosition = kPiece(color).getChessPos().toPos();
		List<Piece> opponentPieces = piecesOn.stream().filter(x -> ((ChessPiece)x).getCor() == opp(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

    private void placeNewPiece(char c,int r,ChessPiece p){
        board.placePiece(p, new ChessPos(r, c).toPos());
        piecesOn.add(p);
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




    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public List<ChessPiece> getPiecesOn() {
        return piecesOn;
    }

    public void setPiecesOn(List<ChessPiece> piecesOn) {
        this.piecesOn = piecesOn;
    }

    public List<ChessPiece> getPiecesOff() {
        return piecesOff;
    }

    public void setPiecesOff(List<ChessPiece> piecesOff) {
        this.piecesOff = piecesOff;
    }
}

