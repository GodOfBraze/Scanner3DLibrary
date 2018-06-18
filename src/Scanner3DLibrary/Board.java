package Scanner3DLibrary;

import arduino.Arduino;

final class Board extends Arduino {
    private boolean isConnected = false;
    private String port = "COM3";
    private int speed = 9600;

    protected Board(){
        super("COM3", 9600);
    }

    protected Board(String port){
        super(port, 9600);
        this.port = port;
    }

    protected Board(int speed){
        super("COM3", speed);
        this.speed = speed;
    }

    protected Board(String port, int speed){
        super(port, speed);
        this.port = port;
        this.speed = speed;
    }

    protected final boolean getConnected() {
        return isConnected;
    }

    private void setConnected(boolean status) {
        isConnected = status;
    }

    protected final String getPort() {
        return port;
    }

    protected final int getSpeed() {
        return speed;
    }

    protected final void connect() throws InterruptedException {
        while (!Board.this.openConnection());
        setConnected(true);
        Thread.sleep(2000);
    }

    protected final void disconnect(){
        Board.this.closeConnection();
        setConnected(false);
    }

    protected final void setData(String data){
        if (getConnected())
            Board.this.serialWrite(data);
    }

    protected final String getData(){
        if (getConnected()){
            String data;

            do {
                data = Board.this.serialRead();
            }while (data.equals(""));

            return  data;
        }

        return "0";
    }
}
