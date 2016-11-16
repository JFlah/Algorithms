/**
 * Created by Jack on 10/18/2016.
 */
public class Problem1 {
    static int[] G = {0,1,2,3,4,5};
    static int size = G.length;

    public static void main(String[] args) {
        union(1, 2, size, G);
        int[] G2 = {0,2,2,3,4,5};
        union(2,5,size,G2);
        int[] G3 = {0,2,5,3,4,5};
        union(1,5,size,G3);
    }

    private static void union(int a, int b, int size, int[] G) {
        // get true parents of a and b
        while (a != G[a]) {
            a = G[a];
            System.out.println(a);
        }

        while (b != G[b]) {
            b = G[b];
            System.out.println(b);
        }

        if (b == a) {
            System.out.println("Cycle!");
        } else {
            G[a] = G[b];
            System.out.println("Parent(a) is now parent(b)");
        }

        for (int g : G) {
            System.out.print(g + " ");
        }
    }
}
