import java.util.ArrayList;

class Piece extends Board {


    private String colour;
    String type;
    public boolean hasMoved = false;

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    static Piece[] afterMoveBoard = new Piece[64];





    Piece(String colour) {
        this.colour = colour;
    }


    public String getColour() {
        return this.colour;
    }

    String getType() {
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

    boolean isInCheck(Piece[] board) {

        if (!getType().equals("King")) {
            return false;
        }

        if (getColour().equals("White")) {
            return positionsAttackedByBlack.contains(getWhiteKingsPosition(board));
        }
        else {
            return positionsAttackedByWhite.contains(getBlackKingsPosition(board));
        }
    }

    boolean isInCheck(int position, Piece[] board) {

        return chessboard[position].isInCheck(board);
    }

    boolean endsMoveInCheck(int position, int destination){

        System.arraycopy(chessboard, 0, afterMoveBoard, 0, 64);
        afterMoveBoard[destination] = afterMoveBoard[position];
        afterMoveBoard[position] =  null;

        boolean endsMoveInCheck = (getPlayerToMove().equals("White")) ? isInCheck(getWhiteKingsPosition(afterMoveBoard),afterMoveBoard) : isInCheck(getBlackKingsPosition(afterMoveBoard),afterMoveBoard);

        return (position != destination) && endsMoveInCheck;
    }




}




