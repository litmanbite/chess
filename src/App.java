
import boardgame.Position;
import chess.ChessExcep;
import chess.ChessPiece;
import chess.ChessPos;
import chess.Chessmatch;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        Chessmatch cs = new Chessmatch();

        while (true){
            try{
                UI.clearScreen();
                UI.printBoard(cs.getPieces());
                System.out.println();
                System.out.print("From :");
                ChessPos from = UI.readChessPos(sc);
                System.out.println();
                System.out.print("To :");
                ChessPos to = UI.readChessPos(sc);

                ChessPiece cp = cs.performMove(from,to);
            }
            catch(ChessExcep e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(RuntimeException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
