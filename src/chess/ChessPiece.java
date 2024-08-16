package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private Color cor;
    private int moveCount;

    public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public ChessPiece(Board board,Color cor) {
        super(board);
        this.cor=cor;
    }

    protected boolean isThereOpponentPiece(Position p){
        ChessPiece cp = (ChessPiece) getBoard().pieces(p);
        return cp != null && cor != cp.getCor();
    }

    public ChessPos getChessPos(){
        return ChessPos.fromPosition(position);
    }

}
