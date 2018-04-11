class Bishop extends Piece {


    Bishop(String colour) {
        super(colour);
        super.type = "Bishop";
    }



    @Override
    boolean moveAllowed(int position, int destination) {

        return canMoveAsBishop(position,destination);
    }
}

