/**
 * Created by Jack on 9/14/2016.
 */
public class Problem6 {
    static String ar = "7\n1 3 9 8 2 7 5";

    public static void main(String[] args) {
        String[] arSplit = ar.split("\\n");
        int length = Integer.parseInt(arSplit[0]);

        String[] arString = arSplit[1].split(" ");
        int[] intAr = new int[length];
        for (int i = 0; i < length; i++) {
            intAr[i] = Integer.parseInt(arString[i]);
        }

        quicksort(intAr, 0, intAr.length-1);
    }

    public static void quicksort(int[] ar, int start, int end) {
        if ((end-start) >= 1) {
            int newPivot = partition(ar, start, end);

            // print array
            for (int i = 0; i < ar.length; i++) {
                if (i == ar.length-1) {
                    System.out.print(Integer.toString(ar[i]));
                    continue;
                }
                System.out.print(Integer.toString(ar[i]) + " ");
            }
            System.out.println();

            quicksort(ar, start, newPivot-1);
            quicksort(ar, newPivot+1, end);
        }
    }

    public static int partition(int[] ar, int start, int end) {
        int pivot = ar[end];
        int beginningIndex = start;

        for (int current = start; current < end; ++current) {
            if (ar[current] < pivot) {
                swap(ar, beginningIndex, current);
                ++beginningIndex;
            }
        }
        swap(ar, beginningIndex, end);
        return beginningIndex;
    }

    public static void swap(int[] ar, int a, int b) {
        int tmp = ar[a];
        ar[a] = ar[b];
        ar[b] = tmp;
    }
}
