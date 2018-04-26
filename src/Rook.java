class Rook extends Queen {


    Rook(String colour) {
        super(colour);
        super.type = "Rook";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

//        boolean startsTurnInCheck = (getPlayerToMove().equals("White")) ? isInCheck(getWhiteKingsPosition()) : isInCheck(getBlackKingsPosition());


        if (isBlockedRookMove(position, destination)) {
            return false;
        }

        if (endsMoveInCheck(position,destination)) {
            System.out.println("You need to move out of check");
            return false;
        }



        return canMoveAsRook(position, destination);

    }
}