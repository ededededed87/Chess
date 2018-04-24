class Bishop extends Queen {


    Bishop(String colour) {
        super(colour);
        super.type = "Bishop";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

        if (isBlockedBishopMove(position, destination)) {
            return false;
        }
        return canMoveAsBishop(position, destination);
    }
}






