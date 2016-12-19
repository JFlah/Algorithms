import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Jack on 12/11/2016.
 * Grade output of finalproject based on filenames.
 *
 * Idea: read output file of script <e.g. temp>, read each line -
 * each line should have category name, then just check how many
 * other things have that category
 * Each line receives a 1 or 0 (1 -> most of the matches are correct)
 * Function returns how many were correct out of total as double:
 * (e.g. NumOnes/Total)
 */

public class Grade {

    public static void main(String[] args) {
        System.out.println(gradeFile(args[0]));
    }

    static double gradeFile(String fileName) {
        double numLines = 0;
        int linesCorrect = 0;
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                numLines++;
                String line = sc.nextLine();
                String[] splitLine = line.split("\\s+");

                // retrieve identifier of first fileId
                // this is the actual answer
                String fullActual = splitLine[0];
                String actual = fullActual.split("_")[0];

                // compare actual with rest, tally up
                int total = splitLine.length-1;
                int correct = 0;

                for (int i = 1; i < splitLine.length; i++) {
                    String fullRes = splitLine[i];
                    if (!fullRes.equals(fullActual)) {
                        String currentResult = fullRes.split("_")[0];
                        if (currentResult.equals(actual)) {
                            correct++;
                        }
                    } else {
                        // disregard an entry if its a repeat of the actual
                        total--;
                    }
                }

                if (correct >= total/2) {
                    linesCorrect++;
                }
            }

            // return ratio of "correct" lines to total lines
            return linesCorrect/numLines;

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        return -1;
    }
}
