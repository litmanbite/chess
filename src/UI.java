import chess.ChessExcep;
import chess.ChessPiece;
import chess.ChessPos;
import chess.Chessmatch;
import chess.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}
	
	public static ChessPos readChessPos (Scanner sc){
		try{
			String str = sc.nextLine();
			char letra = str.charAt(0);
			int num =  Integer.parseInt(str.substring(1));
			return new ChessPos(num, letra);
		}
		catch(RuntimeException e){
			throw new ChessExcep("Error reading ChessPosition");
		}
	}

    public static void printBoard(ChessPiece[][] pieces) {
		System.out.println(ANSI_CYAN + "   _________________" + ANSI_RESET);
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + ANSI_CYAN + " | " + ANSI_RESET);
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
		}
		System.out.println(ANSI_CYAN + "   _________________" + ANSI_RESET);
		System.out.println("    a b c d e f g h");
	}
	public static void printBoard(ChessPiece[][] pieces,boolean [][] possibleMoves) {
		System.out.println(ANSI_CYAN + "   _________________" + ANSI_RESET);
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + ANSI_CYAN + " | " + ANSI_RESET);
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
		}
		System.out.println(ANSI_CYAN + "   _________________" + ANSI_RESET);
		System.out.println("    a b c d e f g h");
	}

	public static void printMatch(Chessmatch cm,List<ChessPiece> lcp){
		printBoard(cm.getPieces());
		System.out.println();
		listCap(lcp);
		System.out.println();
		System.out.println("Turn :"+cm.getTurn());
		if (!cm.getCheckMate()) {
			System.out.println("Waiting player: " + cm.getBw());
			if (cm.isCheck()) {
				System.out.println("CHECK!");
			}
		} else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + cm.getBw());
		}
	} 


   private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_PURPLE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getCor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}	
		}
		System.out.print(" ");
	}

	private static void listCap(List<ChessPiece> lcp){
		List<ChessPiece> w = lcp.stream().filter(p -> p.getCor() == Color.WHITE).toList();
		List<ChessPiece> b = lcp.stream().filter(p -> p.getCor() == Color.BLACK).toList();
		
			System.out.println(ANSI_YELLOW);
			System.out.print(Arrays.toString(w.toArray()));		
		System.out.println(ANSI_RESET);
		
			System.out.println(ANSI_WHITE);
			System.out.print(Arrays.toString(b.toArray()));
		
		System.out.println(ANSI_RESET);
	}

}
