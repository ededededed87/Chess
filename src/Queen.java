class Queen extends Piece {


    Queen(String colour) {
        super(colour);
        super.type = "Queen";
    }


    @Override
    boolean moveAllowed(int position, int destination) {
        if (isBlocked(position, destination)) {
            return false;
        }
        return canMoveAsBishop(position, destination) || canMoveAsRook(position, destination);

    }


    Boolean canMoveAsBishop(int position, int destination) {

        int move = (Math.abs(position - destination));
        Boolean pieceHasMoved = (move != 0);
        Boolean movingDiagonally = ((move % 9 == 0) || (move % 7 == 0));
        int changeInRowNumber = Math.abs((position / 8) - (destination / 8));
        Boolean rowChangeIsCorrect = ((move / 9 == changeInRowNumber) || (move / 7 == changeInRowNumber));

        return pieceHasMoved && movingDiagonally && rowChangeIsCorrect;
    }

    Boolean canMoveAsRook(int position, int destination) {

        return (position != destination) && (staysOnSameColumn(position, destination) || staysOnSameRow(position, destination));
    }

    boolean staysOnSameRow(int position, int destination) {
        return (position / 8 == destination / 8);
    }

    boolean staysOnSameColumn(int position, int destination) {
        return (Math.abs(position - destination) % 8 == 0);
    }

    private boolean isBlocked(int position, int destination) {

        boolean movingAsRook = staysOnSameRow(position, destination) || staysOnSameColumn(position, destination);


        if (movingAsRook) {
            return isBlockedRookMove(position, destination);
        }
        else {
            return isBlockedBishopMove(position, destination);
        }
    }

    boolean isBlockedRookMove(int position, int destination) {

        boolean movesAlongRow = (position / 8 == destination / 8);
        boolean movingUp = destination > position;
        boolean movingRight = movingUp;
        boolean movingDown = destination < position;
        boolean movingLeft = movingDown;

        if (squareOccupied(destination) && getPiece(destination).getColour().equals(getPlayerToMove())) {
            return true;
        }


        if (squareOccupied(destination)) {
            if (getPiece(destination).getColour().equals(getPlayerToMove())) {
                return true;
            }
        }

        if (movesAlongRow) {
            if (movingRight) {
                for (int i = position + 1; i <= destination - 1; i++) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingLeft) {
                for (int i = position - 1; i >= destination - 1; i--) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
        }

        else {
            if (movingUp) {
                for (int i = position + 8; i <= destination - 8; i += 8) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingDown) {
                for (int i = position - 8; i >= destination + 8; i -= 8) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    boolean isBlockedBishopMove(int position, int destination) {

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
                for (int i = position - 7; i >= destination + 7; i -= 7) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }

            if (movingLeft) {
                for (int i = position - 9; i >= destination + 9; i -= 9) {
                    if (squareOccupied(i)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}




