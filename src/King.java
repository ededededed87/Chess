import java.util.ArrayList;

class King extends Piece {


    King(String colour) {
        super(colour);
        super.type = "King";
        super.position = (colour.equals("White")) ? 4 : 60;
    }



    @Override
    boolean moveAllowed(int position, int destination) {

        boolean isInCheck = (getColour().equals("White") && positionsAttackedByBlack.contains(position)) || (getColour().equals("Black") && positionsAttackedByWhite.contains(position));
        boolean movesIntoCheck = (getColour().equals("White") && positionsAttackedByBlack.contains(destination)) || (getColour().equals("Black") && positionsAttackedByWhite.contains(destination));

        if (moveIsBlocked(destination)) {
            return false;
        }

//        if (movesIntoCheck) {
//            return false;
//        }

        if (endsMoveInCheck(position,destination)) {
            System.out.println("You need to move out of check");
            return false;
        }

        int move = Math.abs(position - destination);
        int changeInRowNumber = Math.abs((position / 8) - (destination / 8));

        ArrayList<Integer> verticalMoves = new ArrayList<>();
        verticalMoves.add(7);
        verticalMoves.add(8);
        verticalMoves.add(9);

        Boolean isHorizontalMove = (move == 1);
        Boolean isVerticalMove = verticalMoves.contains(move);

        return (isHorizontalMove && changeInRowNumber == 0) || (isVerticalMove && changeInRowNumber == 1);
    }

    private boolean moveIsBlocked(int destination) {
        return squareOccupied(chessboard, destination) && getPiece(destination).getColour().equals(getPlayerToMove());
    }



}
