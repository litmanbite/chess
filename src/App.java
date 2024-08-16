
import boardgame.Position;
import chess.ChessExcep;
import chess.ChessPiece;
import chess.ChessPos;
import chess.Chessmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> cap = new ArrayList<>();
        Chessmatch cs = new Chessmatch();

        while (!cs.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(cs, cap);
                System.out.println();
                System.out.print("From :");
                ChessPos from = UI.readChessPos(sc);
                boolean[][] pm = cs.possibleMoves(from);
                UI.clearScreen();
                UI.printBoard(cs.getPieces(), pm);

                System.out.println();
                System.out.print("To :");
                ChessPos to = UI.readChessPos(sc);

                ChessPiece cp = cs.performMove(from, to);

                if (cp != null) {
                    cap.add(cp);
                }

            } catch (ChessExcep e) {
                System.out.println(e.getMessage());
                sc.nextLine(); // Clear the buffer
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                sc.nextLine(); // Clear the buffer
            }

        }
        UI.clearScreen();
        UI.printMatch(cs, cap);
    }
}
