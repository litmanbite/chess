package pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color cor) {
        super(board, cor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "R";
    }
    
}
