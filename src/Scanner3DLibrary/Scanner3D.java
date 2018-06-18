package Scanner3DLibrary;

import java.io.IOException;

public final class Scanner3D {
    private Board board;
    private LaserDistanceSensor sensor;
    private Logger logger;
    private StepperMotors motors;
    private ArraysOfPoints arrays;
    private int height;
    private boolean isFirst = true;
    private double angleA = 0.0, angleB = 0.0;

    public Scanner3D(String port, int speed, int lineLong, int height){
        board = new Board(port, speed);
        sensor = new LaserDistanceSensor(board);
        arrays = new ArraysOfPoints(lineLong);
        logger = new Logger(arrays);
        motors = new StepperMotors(board);
        this.height = height;
    }

    public final void startScanning() throws IOException, InterruptedException {
        board.connect();
        arrays.start();
        logger.start("logs.stl");

        for (int i = 0; i < Math.round(height/2); i++) {
            for (int j = 0; j < arrays.getLineLong(); j++) {
                if (isFirst)
                    arrays.setLine1(j, sensor.getCoordinates("F", angleA, angleB));
                else{
                    arrays.switchLine1ToLine2(j);
                    arrays.setLine2(j, sensor.getCoordinates("F", angleA, angleB));
                }

                motors.makeStep("right");
                angleB += 1.8;
            }

            if (!isFirst)
                logger.writeLog();

            motors.makeStep("up");
            angleA += 1.8;

            if (((i != Math.round(height/2) - 1) && (height%2 == 1)) || (height%2 == 0)) {
                for (int j = 0; j < arrays.getLineLong(); j++) {
                    if (isFirst)
                        arrays.setLine2(j, sensor.getCoordinates("F", angleA, angleB));
                    else {
                        arrays.switchLine1ToLine2(j);
                        arrays.setLine2(j, sensor.getCoordinates("F", angleA, angleB));
                    }

                    motors.makeStep("left");
                    angleB -= 1.8;
                }

                logger.writeLog();
                motors.makeStep("up");
                angleA += 1.8;

                if (getFirst())
                    setFirst(false);
            }
        }

        logger.finish();
        board.disconnect();
    }

    public final Board getBoard(){
        return board;
    }

    public final StepperMotors getMotors(){
        return motors;
    }

    public final LaserDistanceSensor getSensor(){
        return sensor;
    }

    public final Logger getLogger(){
        return logger;
    }

    public final ArraysOfPoints getArrays(){
        return arrays;
    }

    public final int getHeight(){
        return height;
    }

    public final boolean getFirst(){
        return isFirst;
    }

    public final void setFirst(boolean status){
        isFirst = status;
    }
}
