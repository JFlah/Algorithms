
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Jack on 10/18/2016.
 */
public class Problem3 {
    static final int LOGXR = 0;
    static final int XR = 1;
    static final int RX = 2;
    static final int XRLOGX = 3;
    static final int XLOGXR = 4;
    static final int LOGLOGXR = 5;

    static final int[] functions = {LOGXR, XR, RX, XRLOGX, XLOGXR, LOGLOGXR};
    static final int[] rTestRange = {1,2,3,4,5};

    static int[] sampleInputSizes = {100, 500, 1000, 5000, 10000, 50000, 100000, 500000};
    static double[] sampleInputRatios = new double[sampleInputSizes.length];

    static double[] currentFunctionRatios = new double[sampleInputRatios.length];

    public static void main(String[] args) {
        populateInputRatios();
        System.out.println(findLowestAbsoluteDifference());
    }

    private static String findLowestAbsoluteDifference() {
        double potentialLowestDifference;
        double currentLowestDifference = 0d;

        String potentialLowestDifferenceFcn = null;
        String currentLowestDifferenceFcn = null;

        for (int function : functions) {
            for (int r : rTestRange) {
                for (int i = 0; i < sampleInputSizes.length; i++) {
                    int sampleSize = sampleInputSizes[i];
                    switch (function) {
                        case LOGXR:
                            currentFunctionRatios[i] = calcRatioLOGXR(sampleSize, r);
                            potentialLowestDifferenceFcn = "log(x)^"+r;
                            break;

                        case XR:
                            currentFunctionRatios[i] = calcRatioXR(sampleSize, r);
                            potentialLowestDifferenceFcn = "x^"+r;
                            break;

                        case RX:
                            currentFunctionRatios[i] = calcRatioRX(sampleSize, r);
                            potentialLowestDifferenceFcn = r+"^x";
                            break;

                        case XRLOGX:
                            currentFunctionRatios[i] = calcRatioXRLOGX(sampleSize, r);
                            potentialLowestDifferenceFcn = "x^"+r+"(log(x))";
                            break;

                        case XLOGXR:
                            currentFunctionRatios[i] = calcRatioXLOGXR(sampleSize, r);
                            potentialLowestDifferenceFcn = "x(log(x)^"+r+")";
                            break;

                        case LOGLOGXR:
                            currentFunctionRatios[i] = calcRatioLOGLOGXR(sampleSize, r);
                            potentialLowestDifferenceFcn = "log(log(x^"+r+"))";
                            break;

                        default:
                            break;
                    }
                }

                double absoluteDifference = calcAbsoluteDifference();

                if (function == 0 && r == 1) {
                    currentLowestDifference = absoluteDifference;
                    currentLowestDifferenceFcn = potentialLowestDifferenceFcn;
                }
                potentialLowestDifference = absoluteDifference;
                if (potentialLowestDifference < currentLowestDifference) {
                    currentLowestDifference = potentialLowestDifference;
                    currentLowestDifferenceFcn = potentialLowestDifferenceFcn;
                }
            }
        }
        return currentLowestDifferenceFcn;
    }

    private static double calcAbsoluteDifference() {
        double returnTotal = 0d;
        for (int i = 0; i < sampleInputRatios.length; i++) {
            returnTotal += Math.abs(sampleInputRatios[i] - currentFunctionRatios[i]);
        }
        return returnTotal;
    }

    private static double calcRatioLOGXR(int x, int r) {
        double runtime =  Math.pow(Math.log(x), r);
        return runtime/x;
    }

    private static double calcRatioXR(int x, int r) {
        double runtime =  Math.pow(x, r);
        return runtime/x;
    }

    private static double calcRatioRX(int x, int r) {
        double runtime = Math.pow(r, x);
        return runtime/x;
    }

    private static double calcRatioXRLOGX(int x, int r) {
        double runtime = Math.pow(x, r) * Math.log(x);
        return runtime/x;
    }

    private static double calcRatioXLOGXR(int x, int r) {
        double runtime = x * (Math.pow(Math.log(x), r));
        return runtime/x;
    }

    private static double calcRatioLOGLOGXR(int x, int r) {
        double runtime = Math.log(Math.log(Math.pow(x, r)));
        return runtime/x;
    }

    private static void populateInputRatios() {
        try {
            Scanner scan = new Scanner(new File("input001.txt"));
            int index = 0;
            while (scan.hasNext() && index < sampleInputRatios.length) {
                if (scan.next().equals(Integer.toString(sampleInputSizes[index]))) {
                    sampleInputRatios[index] = Double.parseDouble(scan.next())/sampleInputSizes[index];
                    index++;
                } else {
                    scan.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
