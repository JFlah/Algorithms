/**
 * Created by Jack on 12/8/2016.
 */
public class Problem1 {

    public static void main(String[] args) {
        String input1 = "Some kinds of writing work best in" +
                " long paragraphs, and others move through many " +
                "short paragraphs. Newspaper reporters usually write in " +
                "very short paragraphs. In addition, their examples " +
                "and explanations are not always tightly tied in " +
                "related clusters. This style of writing is addressed " +
                "to readers who are skimming and looking for the main " +
                "points in the first few inches of print, so reporters " +
                "dont develop each idea fully in clear sequence. " +
                "Information in newspaper articles sometimes " +
                "has to be reorganized to make a standard essay.";

        String input2 = "Tushar Roy likes to code";

        breakLines(input1);
    }

    static void breakLines(String input) {
        int width = 80;
        String[] inSplit = input.split(" ");

        // populate cost matrix
        double[][] costMatrix = populateCostMatrix(inSplit, width);

        // create min cost and final result arrays
        double[] minCost = new double[inSplit.length];
        double[] finResult = new double[inSplit.length];


        // populate arrays
        for (int i = minCost.length-1; i >= 0; i--) {
            int j = minCost.length-1;

            double currCost = costMatrix[i][j];
            if (currCost != -1) {
                minCost[i] = currCost;
                finResult[i] = j+1;
            } else {
                // begin decrementing j to i and find best cost
                double lowestCost = 99999;
                double lowestJ = 0;
                while (j > i) {
                    double firstCost = costMatrix[i][j-1];
                    if (firstCost == -1) {
                        j--;
                        continue;
                    }
                    double totalCost = firstCost + minCost[j];
                    if (totalCost < lowestCost) {
                        lowestCost = totalCost;
                        lowestJ = j;
                    }
                    j--;
                }
                minCost[i] = lowestCost;
                finResult[i] = lowestJ;
            }
        }

        // print result
        for (int i = 0; i < finResult.length;) {
            double endAt = finResult[i];
            while (i < endAt) {
                System.out.print(inSplit[i++] + " ");
            }
            System.out.println();
        }

    }

    static double[][] populateCostMatrix(String[] inSplit, int width) {
        double[][] costMatrix = new double[inSplit.length][inSplit.length];
        for (int i = 0; i < inSplit.length; i++) {
            for (int j = i; j < inSplit.length; j++) {

                int length = 0;
                int words = 0;
                // add up lengths from i -> j
                for (int k = i; k <= j; k++) {
                    length += inSplit[k].length();
                    words++;
                }
                // add spaces between words to length
                length += (words-1);

                // if len > width, not possible
                if (length > width) {
                    costMatrix[i][j] = -1;
                } else {
                    int nonEmptySpaces = width-length;
                    costMatrix[i][j] = Math.pow(nonEmptySpaces, 2);
                }
            }
        }
        return costMatrix;
    }
}
