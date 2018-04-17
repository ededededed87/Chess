class Pawn extends Piece {


    Pawn(String colour) {
        super(colour);
        super.type = "Pawn";
    }

    @Override
    boolean moveAllowed(int position, int destination) {

        boolean movingStraight = (destination - position) % 8 == 0;
        boolean movingDiagonally = (destination - position) == 7 || (destination - position) == 9 || (position - destination) == 7 || (position - destination) == 9;


        System.out.println("Moving idagonally: " + movingDiagonally);
        System.out.println("Moving straight: " + movingStraight);
        System.out.println("destination - position: " + (destination - position));
        System.out.println("position - destination: " + (position - destination));

        if (moveIsBlocked(position, destination)) {
            System.out.println("Blocked");
            return false;
        }

        if (movingStraight) {

            if (this.hasMoved) {
                if (super.getColour().equals("White")) {
                    return (destination - position) == 8;
                }
                else {
                    return (position - destination) == 8;
                }

            }
            else {
                if (super.getColour().equals("White")) {
                    return (destination - position) == 8 || (destination - position) == 16;
                } else {
                    return (position - destination) == 8 || (position - destination) == 16;
                }
            }
        }
        else {
            return (movingDiagonally && squareOccupied(destination));
        }
    }

    @Override
    boolean moveIsBlocked(int position, int destination) {

        boolean movingStraight = (destination - position) % 8 == 0;
        boolean movingTwoSquares = (destination - position) == 16 || (position - destination) == -16;


        if (movingStraight) {
            if (movingTwoSquares) {
                System.out.println("Moviting Tow SQUARES");
                if (super.getColour().equals("White")) {
                    return (squareOccupied(destination) || squareOccupied(destination - 8));
                }
                else {
                    return (squareOccupied(destination) || squareOccupied(destination + 8));
                }
            }
            else {
                return squareOccupied(destination);
            }
        }
        else {
            System.out.println(squareOccupied(destination));
            System.out.println(getPiece(destination).getColour().equals(getPlayerToMove()));
            return !(squareOccupied(destination) && !getPiece(destination).getColour().equals(getPlayerToMove()));
        }


    }

}



