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

        if (endsMoveInCheck(position,destination)) {
            System.out.println("You need to move out of check");
            return false;
        }

        return canMoveAsBishop(position, destination);
    }
}






