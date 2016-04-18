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
        graph.addEdge(0, 23); // jestem na dole
        graph.addEdge(23, 24); // ide poziomo
        graph.addEdge(24, 25); // ide do góry
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
        graph.addEdge(11, 35); //ide poziomo
        graph.addEdge(35, 36); // ide poziomo
        graph.addEdge(36, 37); // ide w dół
        graph.addEdge(37, 38);
        graph.addEdge(38, 39);
        graph.addEdge(39, 40);
        graph.addEdge(40, 41);
        graph.addEdge(41, 42);
        graph.addEdge(42, 43);
        graph.addEdge(43, 44);
        graph.addEdge(44, 45);
        graph.addEdge(45, 46);
        graph.addEdge(46, 47);
        graph.addEdge(24, 47); // łącze dwie równoległe drogi
        graph.addEdge(47, 48); // ide pionowo
        graph.addEdge(48, 49); // ruszam do góry
        graph.addEdge(49, 50);
        graph.addEdge(50, 51);
        graph.addEdge(51, 52);
        graph.addEdge(52, 53);
        graph.addEdge(53, 54);
        graph.addEdge(54, 55);
        graph.addEdge(55, 56);
        graph.addEdge(56, 57);
        graph.addEdge(57, 58);
        graph.addEdge(58, 59);
        graph.addEdge(36, 59); // lacze dwie równoległe drogi
        graph.addEdge(59, 60); // ide poziomo
        graph.addEdge(60, 61); // ruszam w dół
        graph.addEdge(61, 62);
        graph.addEdge(62, 63);
        graph.addEdge(63, 64);
        graph.addEdge(64, 65);
        graph.addEdge(65, 66);
        graph.addEdge(66, 67);
        graph.addEdge(67, 68);
        graph.addEdge(68, 69);
        graph.addEdge(69, 70);
        graph.addEdge(70, 71);
        graph.addEdge(48, 71); // łącze dwie równoległem drogi
        graph.addEdge(71, 72); // ide poziomo
        graph.addEdge(72, 73); // ide w górę
        graph.addEdge(73, 74);
        graph.addEdge(74, 75);
        graph.addEdge(75, 76);
        graph.addEdge(76, 77);
        graph.addEdge(77, 78);
        graph.addEdge(78, 79);
        graph.addEdge(79, 80);
        graph.addEdge(80, 81);
        graph.addEdge(81, 82);
        graph.addEdge(82, 83);
        graph.addEdge(60, 83); // łącze dwie równoległe drogi
        graph.addEdge(83, 84); // idę poziomo
        graph.addEdge(84, 85); // ide w dół
        graph.addEdge(85, 86);
        graph.addEdge(86, 87);
        graph.addEdge(87, 88);
        graph.addEdge(88, 89);
        graph.addEdge(89, 90);
        graph.addEdge(90, 91);
        graph.addEdge(91, 92);
        graph.addEdge(92, 93);
        graph.addEdge(93, 94);
        graph.addEdge(94, 95);
        graph.addEdge(72, 95); // łącze dwie równoległe drogi
        graph.addEdge(95, 96); // idę poziomo
        graph.addEdge(96, 97); // ide do góry
        graph.addEdge(97, 98);
        graph.addEdge(98, 99);
        graph.addEdge(99, 100);
        graph.addEdge(100, 101);
        graph.addEdge(101, 102);
        graph.addEdge(102, 103);
        graph.addEdge(103, 104);
        graph.addEdge(104, 105);
        graph.addEdge(105, 106);
        graph.addEdge(106, 107);
        graph.addEdge(84, 107); // łącze dwie równoległe drogi
        graph.addEdge(107, 108); // idę poziomo
        graph.addEdge(108, 109); // ide w dół
        graph.addEdge(109, 110);
        graph.addEdge(110, 111);
        graph.addEdge(111, 112);
        graph.addEdge(112, 113);
        graph.addEdge(113, 114);
        graph.addEdge(114, 115);
        graph.addEdge(115, 116);
        graph.addEdge(116, 117);
        graph.addEdge(117, 118);
        graph.addEdge(118, 119);
        graph.addEdge(96, 119); // łącze dwie równoległe drogi
        graph.addEdge(119, 120); // idę poziomo
        graph.addEdge(120, 121); // idę w góre
        graph.addEdge(121, 122);
        graph.addEdge(122, 123);
        graph.addEdge(123, 124);
        graph.addEdge(124, 125);
        graph.addEdge(125, 126);
        graph.addEdge(127, 128);
        graph.addEdge(128, 129);
        graph.addEdge(129, 130);
        graph.addEdge(130, 131);
        graph.addEdge(108, 131); // łącze dwie równoległe drogi
        graph.addEdge(131, 132); // ide poziomo
        graph.addEdge(132, 133); // ide w dół
        graph.addEdge(133, 134);
        graph.addEdge(134, 135);
        graph.addEdge(135, 136);
        graph.addEdge(136, 137);
        graph.addEdge(137, 138);
        graph.addEdge(138, 139);
        graph.addEdge(139, 140);
        graph.addEdge(140, 141);
        graph.addEdge(141, 142);
        graph.addEdge(142, 143);
        graph.addEdge(120, 143);


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
