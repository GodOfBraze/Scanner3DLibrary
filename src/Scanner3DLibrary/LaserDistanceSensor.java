package Scanner3DLibrary;

final class LaserDistanceSensor {
    private Board board;

    protected LaserDistanceSensor(Board board){
        this.board = board;
    }

    protected LaserDistanceSensor(){}

    protected final Board getBoard() {
        return board;
    }

    protected final void setBoard(Board board) {
        this.board = board;
    }

    protected final void open(){
        board.setData("O");
    }

    protected final void close(){
        board.setData("C");
    }

    protected final double getDistance(String mode){
        if (mode.equals("F") || mode.equals("D") || mode.equals("M")){
            board.setData(mode);
            return Double.parseDouble(board.getData());
        }

        return 0;
    }

    protected final double[] getCoordinates(String mode, double angleA, double angleB){
        double[] coordinates = new double[3];
        double x, y, z;
        double distance = getDistance(mode);

        x = distance * Math.cos(Math.toRadians(angleA)) * Math.sin(Math.toRadians(angleB));
        y = distance * Math.cos(Math.toRadians(angleA)) * Math.cos(Math.toRadians(angleB));
        z = distance * Math.sin(Math.toRadians(angleA));

        coordinates[0] = Double.parseDouble(String.valueOf(Math.round(x * 1000000.0))) / 1000000.0;
        coordinates[1] = Double.parseDouble(String.valueOf(Math.round(y * 1000000.0))) / 1000000.0;
        coordinates[2] = Double.parseDouble(String.valueOf(Math.round(z * 1000000.0))) / 1000000.0;

        return coordinates;
    }
}
