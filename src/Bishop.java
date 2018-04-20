class Bishop extends Piece {


    Bishop(String colour) {
        super(colour);
        super.type = "Bishop";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

        if (moveIsBlocked(position, destination)) {
            return false;
        }

        return canMoveAsBishop(position, destination);
    }


    @Override
    boolean moveIsBlocked(int position, int destination) {

        boolean movingUp = getRowFromPosition(destination) > getRowFromPosition(position);
        boolean movingDown = getRowFromPosition(destination) < getRowFromPosition(position);
        boolean movingRight = getColumnFromPosition(destination) > getColumnFromPosition(position);
        boolean movingLeft = getColumnFromPosition(destination) < getColumnFromPosition(position);

        if (squareOccupied(destination) && getPiece(destination).getColour().equals(getPlayerToMove())) {
            return true;
        }

        if (movingUp) {

            if (movingRight) {
                for (int i = position + 9; i <= destination - 9; i += 9) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
            if (movingLeft) {
                for (int i = position + 7; i <= destination - 7; i += 7) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
        }

        if (movingDown) {

            if (movingRight) {
                for (int i = position - 7; i <= destination + 7; i -= 7) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingLeft) {
                for (int i = position - 9; i <= destination + 9; i -= 9) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}



