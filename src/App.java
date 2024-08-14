
import boardgame.Position;
import chess.Chessmatch;

public class App {
    public static void main(String[] args) throws Exception {
        
        Chessmatch cs = new Chessmatch();
        UI.printBoard(cs.getPieces());
    }
}
