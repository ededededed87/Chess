class Queen extends Piece {


    Queen(String colour) {
        super(colour);
        super.type = "Queen";
    }

    @Override
    boolean moveAllowed(int position, int destination) {
        return canMoveAsBishop(position, destination) || canMoveAsRook(position, destination);

    }

}


