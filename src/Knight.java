import java.util.ArrayList;

class Knight extends Piece {

    Knight(String colour) {
        super(colour);
        super.type = "Knight";
    }

    @Override
    boolean moveAllowed(int position, int destination) {

        if (moveIsBlocked(destination)) {
            return false;
        }

        if (endsMoveInCheck(position,destination)) {
            System.out.println("You need to move out of check");
            return false;
        }

        int move = Math.abs(position - destination);
        int changeInRowNumber = Math.abs((position / 8) - (destination / 8));


        ArrayList<Integer> horizontalMoves = new ArrayList<>();
        horizontalMoves.add(6);
        horizontalMoves.add(10);

        ArrayList<Integer> verticalMoves = new ArrayList<>();
        verticalMoves.add(15);
        verticalMoves.add(17);

        Boolean isHorizontalMove = horizontalMoves.contains(move);
        Boolean isVerticalMove = verticalMoves.contains(move);

        return (isHorizontalMove && changeInRowNumber == 1) || (isVerticalMove && changeInRowNumber == 2);
    }


    private boolean moveIsBlocked(int destination) {
        return squareOccupied(chessboard,destination) && getPiece(destination).getColour().equals(getPlayerToMove());

    }
}
