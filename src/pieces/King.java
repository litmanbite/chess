package pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color cor) {
        super(board, cor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "K";
    } 
    
}
