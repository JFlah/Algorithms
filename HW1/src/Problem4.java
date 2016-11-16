import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 9/14/2016.
 */
public class Problem4 {

    static String ar = "5\n4 5 3 7 2";

    public static void main(String[] args) {
        quickSort(ar);
    }

    public static void quickSort(String ar) {
        String[] arSplit = ar.split("\\n");
        int length = Integer.parseInt(arSplit[0]);

        // get int array from input
        String[] arString = arSplit[1].split(" ");
        int[] intAr = new int[length];
        for (int i = 0; i < length; i++) {
            intAr[i] = Integer.parseInt(arString[i]);
        }

        int pivot = intAr[0];

        // populate arrays

        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        List<Integer> equal = new ArrayList<Integer>();

        for (int i = 0; i < length; i++) {
            if (intAr[i] < pivot) {
                left.add(intAr[i]);
            } else if (intAr[i] > pivot) {
                right.add(intAr[i]);
            } else {
                equal.add(intAr[i]);
            }
        }

        for (int i : left) {
            System.out.print(i + " ");
        }
        for (int i : equal) {
            System.out.print(i + " ");
        }
        for (int i = 0; i < right.size(); i++) {
            if (i == right.size()-1) {
                System.out.print(right.get(i));
            } else {
                System.out.print(right.get(i) + " ");
            }
        }
    }
}
