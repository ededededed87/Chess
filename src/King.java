import java.util.ArrayList;

class King extends Piece {



    King(String colour) {
        super(colour);
        super.type = "King";
    }

    @Override
    boolean moveAllowed(int position, int destination) {

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
}
