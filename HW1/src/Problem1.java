
/**
 * Created by Jack on 9/14/2016.
 */
public class Problem1 {
    static int[] ar = {5, 3, 1, 5, 6, 9};

    public static void main(String[] args) {
        mergeSort(ar);
    }

    public static int[] mergeSort(int[] ar) {
        int length = ar.length;
        if (length <= 1) {
            if (length > 0) {
                System.out.println("[" + ar[0] + "]");
            } else {
                System.out.println("[]");
            }
            return ar;
        } else {
            int middleElement = length/2;
            int left[] = new int[middleElement];
            int right[] = new int[length-middleElement];

            for (int i = 0; i < middleElement; i++) {
                left[i] = ar[i];
            }
            for (int i = middleElement; i < length; i++) {
                right[i-middleElement] = ar[i];
            }

            return merge(mergeSort(left), mergeSort(right));
        }
    }

    public static int[] merge(int[] first, int[] second) {
        int[] mergedArray = new int[first.length + second.length];

        int firstIndex = 0, secondIndex = 0, mergedIndex = 0;

        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] < second[secondIndex]) {
                mergedArray[mergedIndex] = first[firstIndex];
                firstIndex++;
            } else {
                mergedArray[mergedIndex] = second[secondIndex];
                secondIndex++;
            }
            mergedIndex++;
        }

        while (firstIndex < first.length) {
            mergedArray[mergedIndex] = first[firstIndex];
            firstIndex++;
            mergedIndex++;
        }
        while (secondIndex < second.length) {
            mergedArray[mergedIndex] = second[secondIndex];
            secondIndex++;
            mergedIndex++;
        }

        System.out.print("[");

        for (int i = 0; i < mergedArray.length; i++) {
            if (mergedArray.length <= 1) {
                System.out.print(mergedArray[i]);
                break;
            }

            if (i==0) {
                System.out.print(mergedArray[i] + ", ");
                continue;
            }
            if (i==mergedArray.length-1) {
                System.out.print(mergedArray[i]);
                continue;
            }
            System.out.print(mergedArray[i] + ", ");

        }
        System.out.println("]");

        return mergedArray;
    }
}
