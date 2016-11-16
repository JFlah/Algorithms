import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jack on 9/14/2016.
 */
public class Problem3 {

    static String votes = "10\nVictor\nVeronica\nRyan\nDave\nMaria\nMaria\nFarah\nFarah\nRyan\nVeronica";

    public static void main(String[] args) {
        String[] voteArr = votes.split("\\n");
        electionWinner(voteArr);
    }

    public static void electionWinner(String[] votes) {
        int numVotes = Integer.parseInt(votes[0]);

        // create hashmap
        HashMap<String, Integer> candidateMap = new HashMap<String, Integer>();

        for (int i = 1; i <= numVotes; i++) {
            String currentCandidate = votes[i];

            if (!candidateMap.containsKey(currentCandidate)) {
                candidateMap.put(currentCandidate, 1);
            } else {
                int currentVotes = candidateMap.get(currentCandidate);
                candidateMap.put(currentCandidate, currentVotes+1);
            }
        }

        // get highest number of votes
        int highestVotes = 0;
        for (Integer voteNum : candidateMap.values()) {
            if (voteNum > highestVotes) {
                highestVotes = voteNum;
            }
        }

        // get list of candidates with high vote num
        List<String> winners = new ArrayList<String>();
        for (String candidate : candidateMap.keySet()) {
            if (candidateMap.get(candidate) == highestVotes) {
                winners.add(candidate);
            }
        }

        // sort list of winners
        winners.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        System.out.println(winners.get(winners.size()-1));

    }

}
