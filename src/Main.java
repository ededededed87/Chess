import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        board.setUpBoard();
        board.printBoard();

        while (!board.isWhiteCheckmate() && !board.isWhiteCheckmate()) {

            board.getAllAttackedSquares();

            String selection = selectPiece();
            if (!isValidReference(selection)){
                System.out.println("Invalid square reference");
                continue;
            }
            if (!board.correctColourSelected(selection)) {
                 continue;
            }

            String destination = selectDestination();
            if (!isValidReference(destination)){
                System.out.println("Invalid square reference");
                continue;
            }

            if (board.getPiece(selection).moveAllowed(board.positionFromReference(selection),board.positionFromReference(destination))) {
                board.movePiece(selection, destination);
            }
            else {
                System.out.println("Illegal Move");
                continue;
            }




            board.printBoard();
            board.changePlayer();
        }

        if (board.isWhiteCheckmate()) {
            System.out.println("White wins!");
        }

        if (board.isBlackCheckmate()) {
            System.out.println("Black wins!");
        }
    }

    private static Boolean isValidReference(String reference) {

        return reference.toLowerCase().matches("[a-h][1-8]");
    }

    private static String selectPiece() {

        System.out.println("Enter the square of the piece you would like to move");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static String selectDestination(){

        Scanner input = new Scanner(System.in);
        System.out.println("Where would like to move to?");
        return input.nextLine();

    }
}
