package Scanner3DLibrary;

final class ArraysOfPoints {
    private double[][] line1, line2;
    private int lineLong;

    protected ArraysOfPoints(int lineLong){
        if (lineLong > 0)
            this.lineLong = lineLong;
    }

    protected ArraysOfPoints(){}

    protected final void start(){
        line1 = new double[lineLong][3];
        line2 = new double[lineLong][3];
    }

    protected final double[] getLine1(int index) {
        return line1[index];
    }

    protected final void setLine1(int index, double[] coordinates) {
        if (index > 0)
            for (int i = 0; i < 3; i++)
                line1[index][i] = coordinates[i];
    }

    protected final double[] getLine2(int index) {
        return line2[index];
    }

    protected final void setLine2(int index, double[] coordinates) {
        if (index > 0)
            for (int i = 0; i < 3; i++)
                line2[index][i] = coordinates[i];
    }

    protected final int getLineLong(){
        return lineLong;
    }

    protected final void setLineLong(int lineLong){
        this.lineLong = lineLong;
    }

    protected final void switchLine1ToLine2(int index){
        setLine1(index, getLine2(index));
    }

    protected final void switchLine2ToLine1(int index){
        setLine2(index, getLine1(index));
    }
}
