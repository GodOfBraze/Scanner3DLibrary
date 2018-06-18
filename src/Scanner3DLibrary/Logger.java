package Scanner3DLibrary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

final class Logger {
    private FileWriter writer;
    private ArraysOfPoints arrays;
    private File file;

    protected Logger(ArraysOfPoints arrays){
        this.arrays = arrays;
    }

    protected Logger(){}

    protected final FileWriter getWriter() {
        return writer;
    }

    protected final void setWriter(FileWriter writer) {
        this.writer = writer;
    }

    protected final ArraysOfPoints getArrays() {
        return arrays;
    }

    protected final void setArrays(ArraysOfPoints arrays) {
        this.arrays = arrays;
    }

    protected final File getFile() {
        return file;
    }

    protected final void setFile(File file) {
        this.file = file;
    }

    protected final void start(String name) throws IOException {
        file = new File(name);
        writer = new FileWriter(name);
        file.createNewFile();
        writer.write("solid\n");
    }

    protected final void finish() throws IOException {
        writer.write("endsolid");
        writer.flush();
        writer.close();
    }

    protected final void writeLog() throws IOException{
        for (int i = 0; i < arrays.getLineLong() - 1; i++) {
            writer.write("\tfacet normal 0.000000e+000 0.000000e+000 0.000000e+000\n");
            writer.write("\t\touter loop\n");
            writer.write("\t\t\tvertex " + arrays.getLine1(i)[0] + "e+003 "
                    + arrays.getLine1(i)[1] + "e+003 " + arrays.getLine1(i)[2] + "e+003\n");
            writer.write("\t\t\tvertex " + arrays.getLine2((arrays.getLineLong() - 1) - i)[0]
                    + "e+003 " + arrays.getLine2((arrays.getLineLong() - 1) - i)[1] + "e+003 "
                    + arrays.getLine2((arrays.getLineLong() - 1) - i)[2] + "e+003\n");
            writer.write("\t\t\tvertex " + arrays.getLine1(i + 1)[0] + "e+003 "
                    + arrays.getLine1(i + 1)[1] + "e+003 " + arrays.getLine1(i + 1)[2] + "e+003\n");
            writer.write("\t\tendloop\n");
            writer.write("\tendfacet\n");
        }

        for (int i = 0; i < arrays.getLineLong() - 1; i++) {
            writer.write("\tfacet normal 0.000000e+000 0.000000e+000 0.000000e+000\n");
            writer.write("\t\touter loop\n");
            writer.write("\t\t\tvertex " + arrays.getLine2((arrays.getLineLong() - 1) - i)[0]
                    + "e+003 " + arrays.getLine2((arrays.getLineLong() - 1) - i)[1] + "e+003 "
                    + arrays.getLine2((arrays.getLineLong() - 1) - i)[2] + "e+003\n");
            writer.write("\t\t\tvertex " + arrays.getLine2((arrays.getLineLong() - 1) - (i + 1))[0]
                    + "e+003 " + arrays.getLine2((arrays.getLineLong() - 1) - (i + 1))[1] + "e+003 "
                    + arrays.getLine2((arrays.getLineLong() - 1) - (i + 1))[2] + "e+003\n");
            writer.write("\t\t\tvertex " + arrays.getLine1(i + 1)[0] + "e+003 "
                    + arrays.getLine1(i + 1)[1] + "e+003 " + arrays.getLine1(i + 1)[2] + "e+003\n");
            writer.write("\t\tendloop\n");
            writer.write("\tendfacet\n");
        }
    }
}
