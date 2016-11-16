import java.util.Arrays;

/**
 * Created by Jack on 10/18/2016.
 */
public class Problem2 {

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] built = buildMaxHeap(input);
        deconstructMaxHeap(built);
    }

    private static int[] buildMaxHeap(int[] input) {
        int[] array = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int currI = i;
            array[currI] = input[currI];
            int fatherIndex = (int) Math.floor((currI - 1) / 2);
            while (array[currI] > array[fatherIndex]) {
                int tempFather = array[fatherIndex];
                array[fatherIndex] = array[currI];
                array[currI] = tempFather;
                currI = fatherIndex;
                fatherIndex = (int) Math.floor((currI - 1) / 2);
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
        return array;
    }

    private static void deconstructMaxHeap(int[] built) {
        while (built.length > 0) {
            int root = 0;
            int last = built.length-1;

            int rootVal = built[root];
            built[root] = built[last];
            built[last] = rootVal;

            built = Arrays.copyOf(built, built.length-1);

            int leftChild = (root*2)+1;
            boolean hasLeft = leftChild < built.length;
            int rightChild = (root*2)+2;
            boolean hasRight = rightChild < built.length;

            // sift root down
            while ((hasLeft && built[root] < built[leftChild]) || hasRight && built[root] < built[rightChild]){
                int tempRootVal = built[root];
                if (hasLeft && hasRight) {
                    // normal sift check
                    if (built[rightChild] > built[leftChild]) {
                        // sift right
                        built[root] = built[rightChild];
                        built[rightChild] = tempRootVal;
                        root = rightChild;
                    } else {
                        // sift left
                        built[root] = built[leftChild];
                        built[leftChild] = tempRootVal;
                        root = leftChild;
                    }
                } else if (!hasLeft) {
                    // sift right
                    built[root] = built[rightChild];
                    built[rightChild] = tempRootVal;
                    root = rightChild;
                } else {
                    // sift left
                    built[root] = built[leftChild];
                    built[leftChild] = tempRootVal;
                    root = leftChild;
                }

                leftChild = (2*root)+1;
                hasLeft = leftChild < built.length;
                rightChild = (2*root)+2;
                hasRight = rightChild < built.length;
            }
            for (int i = 0; i < built.length; i++) {
                System.out.print(built[i] + " ");
            }
            System.out.println();
        }
    }
}
