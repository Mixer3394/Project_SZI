package sample;

/**
 * Created by Mariusz on 18.04.2016.
 */
public class Paths {

    public static void main(String[] args) {
// Przyklad ze strony http://www.algorytm.org/algorytmy-grafowe/przeszukiwanie-grafu-wszerz-bfs-i-w-glab-dfs.html
// UWAGA: graf skierowany zostal przerobiony na graf nieskierowany
// stan na dzien 2013-04-08
// grafy nieskierowane
        Graph graph = new Graph(37);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
        graph.addEdge(10, 11);
        graph.addEdge(11, 12);
        graph.addEdge(12, 13);
        graph.addEdge(13, 14);
        graph.addEdge(14, 15);
        graph.addEdge(15, 16);
        graph.addEdge(16, 17);
        graph.addEdge(17, 18);
        graph.addEdge(18, 19);
        graph.addEdge(19, 20);
        graph.addEdge(20, 21);
        graph.addEdge(21, 22);
        graph.addEdge(22, 23);
        graph.addEdge(0, 23);
        graph.addEdge(23, 24);
        graph.addEdge(24, 25);
        graph.addEdge(25, 26);
        graph.addEdge(26, 27);
        graph.addEdge(27, 28);
        graph.addEdge(28, 29);
        graph.addEdge(29, 30);
        graph.addEdge(30, 31);
        graph.addEdge(31, 32);
        graph.addEdge(32, 33);
        graph.addEdge(33, 34);
        graph.addEdge(34, 35);
        graph.addEdge(11, 35);

        System.out.println("Graf nieskierowany: " + graph);

        System.out.println("\nBFS");

        System.out.println("\n----------");

// droga z 5 do 1
        BFSPaths dfs2 = new BFSPaths(graph, 0);
        for (int it : dfs2.getPathTo(35)) {

            System.out.print(it + " ");
        }

// --------------------------------------------
//
    }
}
