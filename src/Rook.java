class Rook extends Piece {


    Rook(String colour) {
        super(colour);
        super.type = "Rook";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

        if (moveIsBlocked(position, destination)) {
            System.out.println("Blocked");
            return false;
        }

        boolean staysOnSameRow = (position / 8 == destination / 8);
        boolean stayOnSameColumn = (Math.abs(position - destination) % 8 == 0);
        System.out.println((position != destination) && (stayOnSameColumn || staysOnSameRow));
        return (position != destination) && (stayOnSameColumn || staysOnSameRow);

    }

    @Override
    boolean moveIsBlocked(int position, int destination) {

        boolean movesAlongRow = (position / 8 == destination / 8);
        boolean movingUp = destination > position;
        boolean movingRight = movingUp;
        boolean movingDown = destination < position;
        boolean movingLeft = movingDown;

        try {
            if (getPiece(destination).getColour().equals(getPlayerToMove())) {
                return false;
            }
        } catch (NullPointerException e) {}
        boolean playersOwnPieceAtDestination = getPiece(destination).getColour().equals(getPlayerToMove());

        if (squareOccupied(destination)) {
            if (playersOwnPieceAtDestination) {
                return true;
            }
        }

        if (squareOccupied(destination)) {
            if (getPiece(destination).getColour().equals(getPlayerToMove())) {
                return true;
            }
        }

        if (movesAlongRow) {
            if (movingRight) {
                for (int i = position + 1; i < destination - 1; i++) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingLeft) {
                for (int i = position - 1; i > destination - 1; i--) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
        }

        if (!movesAlongRow) {
            if (movingUp) {
                for (int i = position + 8; i < destination - 8; i += 8) {
                    System.out.println(i);
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingDown) {
                for (int i = position - 8; i > destination + 8; i -= 8) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }
}




