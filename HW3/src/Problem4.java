import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Jack on 11/21/2016.
 */
public class Problem4 {
    static double[] xArray;
    static double[] yArray;
    static int problemType;
    static Scanner sc;

    static class Output {
        double a;
        double b;

        public Output(double a, double b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
            return df.format(a) + " " + df.format(b);
        }
    }

    public static void main(String[] args) {
        getPoints();
        if (problemType == 1) {
            p1();
        } else {
            p2();
        }
    }

    private static double sumAllX() {
        double sum = 0d;
        for (double i : xArray) {
            sum += Math.log(i);
        }
        return sum;
    }

    private static double sumAllY() {
        double sum = 0d;
        for (double i : yArray) {
            sum += i;
        }
        return sum;
    }

    private static double sumAllLogY() {
        double sum = 0d;
        for (double i : yArray) {
            sum += Math.log(i);
        }
        return sum;
    }

    private static double sumAllYTimesX() {
        double sum = 0d;
        for (int i = 0; i < xArray.length; i++) {
            double currX = xArray[i];
            double currY = yArray[i];

            sum += currY * Math.log(currX);
        }
        return sum;
    }
    private static double sumAllLogYTimesX() {
        double sum = 0d;
        for (int i = 0; i < xArray.length; i++) {
            double currX = xArray[i];
            double currY = yArray[i];

            sum += Math.log(currY) * Math.log(currX);
        }
        return sum;
    }

    private static double sumAllOnes() {
        return xArray.length;
    }

    private static double sumAllXSq() {
        double sum = 0d;
        for (double i : xArray) {
            sum += Math.log(i)*Math.log(i);
        }
        return sum;
    }

    private static void p1() {
        double a = 0d;
        double b = 0d;

        a = (-sumAllYTimesX()+((sumAllX()*sumAllY())/sumAllOnes()))/(((sumAllX()*sumAllX())/sumAllOnes())-sumAllXSq());
        b = (sumAllYTimesX() - a*(sumAllXSq()))/sumAllX();

        System.out.println(new Output(a, b).toString());
    }


    private static void p2() {
        double a = 0d;
        double b = 0d;

        a = (-sumAllLogYTimesX()+((sumAllX()*sumAllLogY())/sumAllOnes()))/(((sumAllX()*sumAllX())/sumAllOnes())-sumAllXSq());
        b = (sumAllLogYTimesX() - a*(sumAllXSq()))/sumAllX();

        double aActual = Math.pow(Math.E, b);
        double bActual = a;

        System.out.println(new Output(aActual, bActual).toString());
    }

    private static void getPoints() {
        File f = new File("inputLS.txt");
        try {
            sc = new Scanner(f);
            String problemLine = sc.nextLine();
            problemType = Integer.parseInt(problemLine.substring(problemLine.length()-1, problemLine.length()));

            double[] xAr = new double[10000];
            int xEle = 0, yEle = 0;
            double[] yAr = new double[10000];
            while (sc.hasNext()) {
                xAr[xEle++] = sc.nextDouble();
                yAr[yEle++] = sc.nextDouble();
            }
            double[] xArTrim = new double[xEle];
            double[] yArTrim = new double[yEle];

            for (int i = 0; i < xEle; i++) {
                xArTrim[i] = xAr[i];
                yArTrim[i] = yAr[i];
            }

            xArray = xArTrim;
            yArray = yArTrim;

        } catch (FileNotFoundException ef) {
            ef.printStackTrace();
        }
    }
}
