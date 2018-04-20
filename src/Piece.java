class Piece extends Board {


    private static String colour;
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

    Boolean canMoveAsBishop(int position, int destination) {
        int move = (Math.abs(position - destination));
        Boolean pieceHasMoved = (move != 0);
        Boolean movingDiagonally = ((move % 9 == 0) || (move % 7 == 0));
        int changeInRowNumber = Math.abs((position / 8) - (destination / 8));
        Boolean rowChangeIsCorrect = ((move / 9 == changeInRowNumber) || (move / 7 == changeInRowNumber));

        return pieceHasMoved && movingDiagonally && rowChangeIsCorrect;
    }

    Boolean canMoveAsRook(int position, int destination) {

        Boolean staysOnSameRow = (position / 8 == destination / 8);
        Boolean stayOnSameColumn = (Math.abs(position - destination) % 8 == 0);
        return (position != destination) && (stayOnSameColumn || staysOnSameRow);
    }
}




