class Rook extends Queen {


    Rook(String colour) {
        super(colour);
        super.type = "Rook";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

        if (isBlockedRookMove(position, destination)) {
            return false;
        }

        return canMoveAsRook(position, destination);

    }
}
