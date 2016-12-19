import java.util.*;

/**
 * Created by Jack on 12/10/2016.
 */
public class Q2 {

    static class Neighbor {
        int id;
        double weight;
        Node nodeEquivalent;
        public Neighbor(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return Integer.toString(id);
        }
    }

    static class Node implements Comparable {
        int id;
        Node previous;
        double minDist;
        List<Neighbor> neighbors = new ArrayList<>();
        public Node(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            if (minDist == n.minDist) {
                return 0;
            } else if (minDist < n.minDist) {
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return Integer.toString(id);
        }
    }

    static List<Node> processInput(String input) {
        List<Node> nodes = new ArrayList<>();
        Scanner sc = new Scanner(input);

        boolean partTwo = false;
        boolean finished = false;
        int currNode = 1;
        int currNeighborNode = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("*")) {
                partTwo = true;
                continue;
            }
            if (line.equals("#")) {
                finished = true;
            }
            // get all nodes and their neighbors
            if (!partTwo && !finished) {
                Node curr = new Node(currNode++);
                // add neighbors
                List<Neighbor> neighbors = new ArrayList<>();
                String[] splitLine = line.split("\\s+");
                if (!splitLine[0].equals("")) {
                    for (String s : splitLine) {
                        Neighbor cur = new Neighbor(Integer.parseInt(s));
                        neighbors.add(cur);
                    }
                }
                curr.neighbors = neighbors;
                curr.minDist = 99999;
                nodes.add(curr);
            }
            // get all weights and put into neighbors
            else if (partTwo && !finished) {
                String[] splitLine = line.split(" ");
                Node curr = nodes.get(currNeighborNode++);
                int neighborAt = 0;
                List<Neighbor> currNeighbors = curr.neighbors;
                if (!splitLine[0].equals("")) {
                    for (String s : splitLine) {
                        Neighbor currNeigh = currNeighbors.get(neighborAt++);
                        currNeigh.nodeEquivalent = nodes.get(currNeigh.id-1);
                        currNeigh.weight = Double.parseDouble(s);
                    }
                }
            }
        }

        return nodes;
    }

    static void dijkstraPaths(Node root) {
        Queue<Node> q = new PriorityQueue<>();
        root.minDist = 0;
        q.add(root);

        while(!q.isEmpty()) {
            Node curr = q.poll();
            for (Neighbor neigh : curr.neighbors) {
                double currentDistTotal = neigh.weight + curr.minDist;

                Node n = neigh.nodeEquivalent;
                if (currentDistTotal < n.minDist) {
                    q.remove(n);

                    n.minDist = currentDistTotal;
                    n.previous = curr;

                    q.add(n);
                }
            }
        }
    }

    static List<Node> getShortestPath(Node destination) {
        List<Node> path = new ArrayList<>();
        Node tmp = destination;
        while (tmp != null) {
            path.add(tmp);
            tmp = tmp.previous;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        String input = "6\n9 10\n7 8 9 10\n8 10\n\n1 9\n3\n3 4\n2 3 6\n2 3 4" +
                "\n*\n4.96465\n1.3587 1.36222\n2.64359 1.73246 1.70301 1.4006\n" +
                "1.37151 2.8793\n\n1.76054 1.20905\n2.5771\n7.23585 6.86486\n" +
                "10.2815 1.32755 1.11296\n9.78852 1.48675 2.53538\n#";

        List<Node> nodes = processInput(input);
        Node root = nodes.get(0);
        dijkstraPaths(root);

        List<Node> shortestPath = getShortestPath(nodes.get(nodes.size()-1));
        for (int i = 0; i < shortestPath.size(); i++) {
            Node node = shortestPath.get(i);
            if (i < shortestPath.size()-1) {
                System.out.print(node.toString() + " ");
            } else {
                System.out.print(node.toString());
            }
        }
    }
}
