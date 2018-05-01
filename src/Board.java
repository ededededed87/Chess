import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {

    static Piece[] chessboard = new Piece[64];
    private Piece[] preMoveGameState = new Piece[64];
    private static String playerToMove = "White";

    static List<Integer> positionsAttackedByWhite = new ArrayList<>();
    static List<Integer> positionsAttackedByBlack = new ArrayList<>();

    static List<Integer> temporaryPositionsAttackedByWhiteCheck = new ArrayList<>();
    static List<Integer> temporarypositionsAttackedByBlackCheck = new ArrayList<>();

    private int whiteKingsPosition = 4;
    private int blackKingsPosition = 60;

    public void setWhiteKingsPosition(int whiteKingsPosition) {
        this.whiteKingsPosition = whiteKingsPosition;
    }

    public void setBlackKingsPosition(int blackKingsPosition) {
        this.blackKingsPosition = blackKingsPosition;
    }

    String getPlayerToMove() {
        return playerToMove;
    }

    public void setUpBoard() {
        chessboard[0] = new Rook("White");
        chessboard[1] = new Knight("White");
        chessboard[2] = new Bishop("White");
        chessboard[3] = new Queen("White");
        chessboard[4] = new King("White");
        chessboard[5] = new Bishop("White");
        chessboard[6] = new Knight("White");

        chessboard[7] = new Rook("White");
        for (int i = 8; i < 16; i++) {
            chessboard[i] = new Pawn("White");
        }

        chessboard[56] = new Rook("Black");
        chessboard[57] = new Knight("Black");
        chessboard[58] = new Bishop("Black");
        chessboard[59] = new Queen("Black");
        chessboard[60] = new King("Black");
        chessboard[61] = new Bishop("Black");
        chessboard[62] = new Knight("Black");
        chessboard[63] = new Rook("Black");
        for (int i = 48; i < 56; i++) {
            chessboard[i] = new Pawn("Black");
        }
    }

    void printBoard() {
        for (int i = 8; i >= 1; i--) {
            printLine();
            printRow(i);
        }
        printLine();

        System.out.println("  A  B  C  D  E  F  G  H");
    }

    private void printLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void printRow(int rowNumber) {

        System.out.print(rowNumber + " ");

        for (int i = ((rowNumber - 1) * 8); i < (((rowNumber - 1) * 8) + 8); i++) {
            if (chessboard[i] != null) {
                System.out.print(getSymbol(chessboard[i].getColourAndType()) + "|");
            }
            else {
                System.out.print("  |");
            }
        }
        System.out.println();
    }

    void changePlayer() {

        playerToMove = (playerToMove.equals("White")) ? "Black" : "White";
        System.out.println("It is now " + playerToMove + "'s turn.");
    }

    Piece getPiece(String reference) {
        return chessboard[positionFromReference(reference)];
    }

    Piece getPiece(int position) {
        return chessboard[position];
    }

    int getWhiteKingsPosition(Piece[] board) {
        for (Piece piece : board) {
            if (piece != null && piece.getType().equals("King")) {
                if (piece.getColour().equals("White")) {
                    return piece.position;
                }
            }
        }
        System.out.println("Shouldn't print - White");
        return 64;
    }

    int getBlackKingsPosition(Piece[] board) {
        for (Piece piece : board) {
            if (piece != null && piece.getType().equals("King")) {
                if (piece.getColour().equals("Black")) {
                    return piece.position;
                }
            }
        }
        System.out.println("Shouldn't print - Black");
        return 64;
    }

    void movePiece(String startReference, String endReference) {

        System.arraycopy(chessboard, 0, preMoveGameState, 0, 64);

        int position = positionFromReference(startReference);
        int destination = positionFromReference(endReference);


        changePiecePosition(position, destination);


        if (!Arrays.deepEquals(chessboard, preMoveGameState)) {
            chessboard[destination].hasMoved = true;
        }

        if (chessboard[destination].getType().equals("King")) {
            chessboard[destination].setPosition(destination);
        }
    }

    private void changePiecePosition(int position, int destination) {
        chessboard[destination] = chessboard[position];
        chessboard[position] = null;
    }


    // Summing column and row integers gives board array index
    int positionFromReference(String reference) {
        return getColumnFromReference(reference) + getRowReference(reference);
    }

    // Converts column character to int
    private int getColumnFromReference(String reference) {
        int a = (reference.toLowerCase().charAt(0));
        return ((a - 1) % 8);
    }


    // Converts ASCII character to int
    private int getRowReference(String reference) {
        int a = (reference.toLowerCase().charAt(1));
        return ((a - 49) * 8);
    }

    int getRowFromPosition(int position) {
        return position / 8;
    }

    int getColumnFromPosition(int position) {
        return position % 8;
    }

    Boolean correctColourSelected(String reference) {
        try {
            if (chessboard[positionFromReference(reference)].getColour().equals(playerToMove)) {
                return true;
            }
            else {
                System.out.println("That is not your piece");
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println("That square is empty");
            return false;
        }

    }

    Boolean squareOccupied(Piece[] board, int position) {
        return board[position] != null;
    }

    Boolean squareOccupied(String reference) {
        return chessboard[positionFromReference(reference)] != null;
    }

    // Returns unicode symbol for each chess piece
    private String getSymbol(String piece) {

        String[] words = piece.split(" ");

        if (words[0].equals("White")) {
            switch (words[1]) {
                case "King":
                    return "\u2654";
                case "Queen":
                    return "\u2655";
                case "Rook":
                    return "\u2656";
                case "Bishop":
                    return "\u2657";
                case "Knight":
                    return "\u2658";
                case "Pawn":
                    return "\u2659";
                default:
                    return "\u2001";
            }
        }
        else {
            switch (words[1]) {
                case "King":
                    return "\u265A";
                case "Queen":
                    return "\u265B";
                case "Rook":
                    return "\u265C";
                case "Bishop":
                    return "\u265D";
                case "Knight":
                    return "\u265E";
                case "Pawn":
                    return "\u265F";
                default:
                    return "\u2001";
            }
        }
    }

    void getAllAttackedSquares(Piece[] board, boolean permanent) {

        List<Integer> whiteList = new ArrayList();
        List<Integer> blackList = new ArrayList();



        for (int i = 0; i <= 63; i++) {
            for (int j = 0; j <= 63; j++) {
                if (squareOccupied(board, i)) {
                    if (i != j && squareOccupied(board, i) && board[i].moveAllowed(i, j)) {
                        if (squareOccupied(board, i) && board[i].getColour().equals("White")) {
                            whiteList.add(j);
                        }
                        else {
                            blackList.add(j);
                        }
                    }
                }
            }
        }

        if (permanent) {
            positionsAttackedByWhite = whiteList;
            positionsAttackedByBlack =  blackList;
        }
        else {
            temporaryPositionsAttackedByWhiteCheck = whiteList;
            temporarypositionsAttackedByBlackCheck  = blackList;
        }


    }


    private boolean whiteHasAvailableMoves() {
        for (int i = 0; i <= 63; i++) {
            for (int j = 0; j <= 63; j++) {
                if (squareOccupied(chessboard, i)) {
                    if (i != j && chessboard[i].getColour().equals("White")) {
                        if (chessboard[i].moveAllowed(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean blackHasAvailableMoves() {
        for (int i = 0; i <= 63; i++) {
            for (int j = 0; j <= 63; j++) {
                if (squareOccupied(chessboard,i)) {
                    if (chessboard[i].getColour().equals("Black")) {
                        if (chessboard[i].moveAllowed(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isWhiteCheckmate() {
        return chessboard[getBlackKingsPosition(chessboard)].isInCheck(chessboard, true) && !blackHasAvailableMoves();
    }

    boolean isBlackCheckmate() {
        return chessboard[getBlackKingsPosition(chessboard)].isInCheck(chessboard, true) && !whiteHasAvailableMoves();
    }

}





