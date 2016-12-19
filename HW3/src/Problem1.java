import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by Jack on 11/17/2016.
 */
public class Problem1 {
    public static class Complex {
        private double real;
        private double imag;

        public Complex(double real) {
            this.real = real;
            this.imag = 0d;
        }
        public Complex (double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public double getReal() {
            return real;
        }

        public double getImag() {
            return imag;
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("##.##");
            if (imag > 0) {
                return df.format(real) + "+" + df.format(imag) + "i";
            } else if (imag == 0) {
                return df.format(real);
            }
            else {
                return df.format(real) + "-" + df.format(-imag) + "i";
            }
        }
    }

    private static Complex[] getRow(String row) {
        String[] rowSplit = row.split(",");
        Complex[] compRow = new Complex[rowSplit.length];
        for (int i = 0; i < rowSplit.length; i++) {
            compRow[i] = new Complex(Double.parseDouble(rowSplit[i]));
        }
        return compRow;
    }

    public static Complex[] fft(Complex[] ar) {
        if (ar.length == 1) {
            return ar;
        }

        Complex[] evens = new Complex[ar.length / 2];
        Complex[] odds = new Complex[ar.length / 2];

        int evenIndex = 0;
        int oddIndex = 0;
        for (int i = 0; i < ar.length; i++) {
            if (i % 2 == 0) {
                evens[evenIndex++] = ar[i];
            } else {
                odds[oddIndex++] = ar[i];
            }
        }

        return combine(fft(evens), fft(odds));
    }

    public static Complex[] combine(Complex[] evens, Complex[] odds) {
        Complex[] combined = new Complex[evens.length + odds.length];
        int N = combined.length;

        for (int k = 0; k < N/2; k++) {
            Complex resultK;
            Complex resultKPlusNOver2;
            Complex even = evens[k];
            Complex odd = odds[k];

            // get eComp  => e^-ix = cosx - sinxi
            double eReal = Math.cos(((2*Math.PI)*k)/N);
            double eImag = Math.sin(((2*Math.PI)*k)/N);
            Complex eComp = new Complex(eReal, -eImag);

            // multiply eComp by Fodd
            Complex eCompByFodd = multiply(eComp, odd);

            // add/subtract Feven to result above
            resultK = add(even, eCompByFodd);

            resultKPlusNOver2 = subtract(even, eCompByFodd);

            combined[k] = resultK;
            int indexForKPlusNOver2 = k + (N/2);
            combined[indexForKPlusNOver2] = resultKPlusNOver2;
        }
        return combined;
    }

    private static Complex subtract(Complex a, Complex b) {
        double real = a.getReal() - b.getReal();
        double imag = a.getImag() - b.getImag();

        return new Complex(real, imag);
    }

    private static Complex add(Complex a, Complex b) {
        double real = a.getReal() + b.getReal();
        double imag = a.getImag() + b.getImag();

        return new Complex(real, imag);
    }

    private static Complex multiply(Complex a, Complex b) {
        double F = a.getReal() * b.getReal();
        double O = a.getReal() * b.getImag();
        double I = a.getImag() * b.getReal();
        double L = (a.getImag() * b.getImag())*-1;

        double real = F + L;
        double imag = O + I;

        return new Complex(real, imag);
    }

    public static void main(String[] args) {
        // rows
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(input);

            String firstLine = sc.nextLine();
            Complex[] firstLineRow = getRow(firstLine);
            int rows = getRow(firstLine).length;
            int cols = rows;

            Complex[][] fullArray = new Complex[rows][cols];
            int rowAt = 0;

            while (sc.hasNextLine()) {
                Complex[] row;
                if (rowAt == 0) {
                    row = firstLineRow;
                } else {
                    row = getRow(sc.nextLine());
                }
                Complex[] fftd = fft(row);
                fullArray[rowAt++] = fftd;
                for (int i = 0; i < fftd.length; i++) {
                    Complex c = fftd[i];
                    System.out.print(c.toString());
                    if (i < fftd.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }

            for (int i = 0; i < cols; i++) {
                Complex[] colTemp = new Complex[cols];
                for (int j = 0; j < rows; j++) {
                    colTemp[j] = fullArray[j][i];

                }
                Complex[] fftCol = fft(colTemp);
                for (int j = 0; j < rows; j++) {
                    fullArray[j][i] = fftCol[j];
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Complex c = fullArray[i][j];
                    System.out.print(c.toString());
                    if (j < fullArray[i].length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
            
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }

    }
}
