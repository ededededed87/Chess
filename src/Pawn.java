class Pawn extends Piece {



    Pawn(String colour) {
        super(colour);
        super.type = "Pawn";
    }

    @Override
    boolean moveAllowed(int position, int destination) {
        if (this.hasMoved) {
            if (super.getColour().equals("White")) {
                return (destination - position) == 8;
            } else {
                return (position - destination) == 8;
            }

        } else {
            if (super.getColour().equals("White")) {
                return (destination - position) == 8 || (destination - position) == 16;
            } else {
                return (position - destination) == 8 || (position - destination) == 16;
            }
        }
    }

}



