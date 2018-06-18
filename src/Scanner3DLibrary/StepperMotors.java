package Scanner3DLibrary;

final class StepperMotors {
    private Board board;

    protected StepperMotors(Board board){
        this.board = board;
    }

    protected StepperMotors(){}

    protected final Board getBoard() {
        return board;
    }

    protected final void setBoard(Board board) {
        this.board = board;
    }

    protected final void makeStep(String direction) throws InterruptedException {
        if (direction.equals("up") || direction.equals("down")
                || direction.equals("left") || direction.equals("right")) {
            switch (direction) {
                case "up":
                    board.setData("y");
                    break;
                case "down":
                    board.setData("Y");
                    break;
                case "left":
                    board.setData("x");
                    break;
                case "right":
                    board.setData("X");
            }

            Thread.sleep(10);
        }
    }

    protected final void makeSteps(String direction, int count) throws InterruptedException {
        if (count > 0)
            for (int i = 0; i < count; i++) {
                makeStep(direction);
            }
    }

    protected final void makeCircle(String direction, int micro) throws InterruptedException {
        if (micro > 0)
            makeSteps(direction, 200 * micro);
    }
}
