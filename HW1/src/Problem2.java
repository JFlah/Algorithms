/**
 * Created by Jack on 9/14/2016.
 */
public class Problem2 {

    static String s1 = "hellothere", s2 = "matt";

    public static void main(String[] args) {
        mergeStrings(s1, s2);
    }

    public static void mergeStrings(String s1, String s2) {
        StringBuilder mergedString = new StringBuilder();

        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        int s1Length = s1Array.length;
        int s2Length = s2Array.length;
        int minLength = Math.min(s1Length, s2Length);


        int s1Index = 0;
        int s2Index = 0;

        for (int i = 0; i < minLength; i++) {
            mergedString.append(s1Array[i]);
            mergedString.append(s2Array[i]);
            s1Index++;
            s2Index++;
        }

        if (s1Index < s1Array.length) {
            for (int i = s1Index; i < s1Length; i++) {
                mergedString.append(s1Array[i]);
            }
        }
        if (s2Index < s2Array.length) {
            for (int i = s2Index; i < s2Length; i++) {
                mergedString.append(s2Array[i]);
            }
        }

        System.out.println(mergedString.toString());
    }
}
