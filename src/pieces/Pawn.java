package pieces;

import boardgame.Board;
import boardgame.Position;
import chess.Chessmatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private Chessmatch chessMatch;
	
	public Pawn(Board board, Color color, Chessmatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0, 0);
    
        if (getCor() == Color.WHITE) {
            // Movimento simples para frente
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionE(p) && !getBoard().pieceE(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Movimento duplo para frente, apenas se o peão não foi movido ainda
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionE(p) && !getBoard().pieceE(p) &&
                getBoard().positionE(p2) && !getBoard().pieceE(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Captura na diagonal esquerda
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionE(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Captura na diagonal direita
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionE(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else {
            // Movimento simples para frente
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionE(p) && !getBoard().pieceE(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Movimento duplo para frente, apenas se o peão não foi movido ainda
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p3 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionE(p) && !getBoard().pieceE(p) &&
                getBoard().positionE(p3) && !getBoard().pieceE(p3) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Captura na diagonal esquerda
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionE(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            
            // Captura na diagonal direita
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionE(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
    
        return mat;
    }
    
	
	@Override
	public String toString() {
		return "P";
	}
	
}