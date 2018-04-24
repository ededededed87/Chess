class Piece extends Board {


    private String colour;
    String type;
    public boolean hasMoved = false;





    Piece(String colour) {
        this.colour = colour;
    }


    public String getColour() {
        return this.colour;
    }

    private String getType() {
        return this.type;
    }

    String getColourAndType() {
        return this.colour + " " + this.getType();
    }

    boolean moveAllowed(int position, int destination) {
        return true;
    }

    boolean moveIsBlocked(int position, int destination) {
        return true;
    }

    boolean isInCheck() {

        if (!getType().equals("King")) {
            return false;
        }

        if (getColour().equals("White")) {
            return positionsAttackedByBlack.contains(getWhiteKingsPosition());
        }
        else {
            return positionsAttackedByWhite.contains(getBlackKingsPosition());
        }
    }




}




