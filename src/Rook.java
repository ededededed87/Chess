class Rook extends Queen {


    Rook(String colour) {
        super(colour);
        super.type = "Rook";
    }


    @Override
    boolean moveAllowed(int position, int destination) {

        if (isBlockedRookMove(position, destination)) {
            return false;
        }

        boolean staysOnSameRow = (position / 8 == destination / 8);
        boolean stayOnSameColumn = (Math.abs(position - destination) % 8 == 0);
        System.out.println((position != destination) && (stayOnSameColumn || staysOnSameRow));
        return (position != destination) && (stayOnSameColumn || staysOnSameRow);

    }

}
