import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jack on 9/14/2016.
 */
public class Problem5 {
    static String ar = "7\n5 8 1 3 7 9 2";

    public static void main(String[] args) {
        String[] arSplit = ar.split("\\n");
        int length = Integer.parseInt(arSplit[0]);

        // get int array from input
        String[] arString = arSplit[1].split(" ");
        int[] intAr = new int[length];
        for (int i = 0; i < length; i++) {
            intAr[i] = Integer.parseInt(arString[i]);
        }

        partition(intAr);
    }

    public static int[] partition(int[] intAr) {

        int length = intAr.length;

        // Base case
        if (length <= 1) {
            return intAr;
        } else {

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

            // recurse
            return merge(partition(convertIntegers(left)), partition(convertIntegers(equal)), partition(convertIntegers(right)));
        }
    }

    public static int[] merge(int[] left, int[] pivot, int[] right ) {
        int[] mergedArray = new int[left.length + pivot.length + right.length];

        Arrays.sort(left);
        Arrays.sort(pivot);
        Arrays.sort(right);

        int mergedIndex = 0;

        for (int i : left) {
            mergedArray[mergedIndex] = i;
            mergedIndex++;
        }
        for (int i : pivot) {
            mergedArray[mergedIndex] = i;
            mergedIndex++;
        }
        for (int i : right) {
            mergedArray[mergedIndex] = i;
            mergedIndex++;
        }

        // print merge
        for (int i = 0; i < mergedArray.length; i++) {
            if (i == mergedArray.length-1) {
                System.out.println(mergedArray[i]);
                continue;
            }
            System.out.print(mergedArray[i] + " ");
        }

        return mergedArray;
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
