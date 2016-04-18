package sample;

import static java.awt.event.MouseEvent.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.awt.*;


import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;


public class Main extends Application {

    static int WIDTH = 1800;
    static int HEIGHT = 800;
    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static HashSet<String> currentlyActiveKeys = new HashSet<String>();

    static KnowledgeBase knowledgeBase;
    static GraphPoints graphPoints;

    // Forklifts
    static  Image forklift;
    static  Image forklift2;

    // Etc
    static  Image background;
    static  Image conveyor;
    static Image cover;

    // Cases
    static Image caseOne;
    static Image caseTwo;
    static Image caseThree;
    static Image caseFour;
    static Image caseFive;
    static Image caseSix;
    static Image caseSeven;
    static Image caseEight;
    static double casePoints[][] = new double[120][2];

    // Randoms for cases-spawns
    static Random caseSpawn = new Random();
    static Random caseNumber = new Random();
    static Image casesToSpawn[] = new Image[20];

    // actual case on forklift
    static Image actualCase;
    static int locOfCases[] = new int[20];

    // state if forklift is busy
    static Boolean caseNotToSpawn = false;

    // number of case that is on forklift
    static int numberOfCase;

    static double actualPositionH = 500;
    static double actualPositionW = 100;
    static double conveyorPos = 0.0;


    // True if right, false if left
    static boolean leftOrRight = true;

    static int[] move;

    @Override
    public void start(Stage mainStage) throws Exception {
        knowledgeBase = new KnowledgeBase();
        knowledgeBase.addData("car parts", "gray" );
        knowledgeBase.addData("car parts", "metal");
        knowledgeBase.addData("car parts", "heavy");
        knowledgeBase.addData("car parts", "middleweight");
        knowledgeBase.addData("car parts", "solid");
        knowledgeBase.addData("wood", "brown");
        knowledgeBase.addData("wood", "wooden");
        knowledgeBase.addData("wood", "heavy");
        knowledgeBase.addData("wood", "solid");
        knowledgeBase.addData("paper", "brown");
        knowledgeBase.addData("paper", "white");
        knowledgeBase.addData("paper", "paper");
        knowledgeBase.addData("paper", "middleweight");
        knowledgeBase.addData("paper", "light");
        knowledgeBase.addData("paper", "solid");
        knowledgeBase.addData("explosives", "red");
        knowledgeBase.addData("explosives", "labelled");
        knowledgeBase.addData("explosives", "dangerous");
        knowledgeBase.addData("explosives", "middleweight");
        knowledgeBase.addData("explosives", "solid");
        knowledgeBase.addData("chemicals", "black");
        knowledgeBase.addData("chemicals", "yellow");
        knowledgeBase.addData("chemicals", "labelled");
        knowledgeBase.addData("chemicals", "dangerous");
        knowledgeBase.addData("chemicals", "middleweight");
        knowledgeBase.addData("chemicals", "liquid");
        knowledgeBase.addData("water", "blue");
        knowledgeBase.addData("water", "water");
        knowledgeBase.addData("water", "heavy");
        knowledgeBase.addData("water", "liquid");
        knowledgeBase.addData("oil", "yellow");
        knowledgeBase.addData("oil", "oil");
        knowledgeBase.addData("oil", "middleweight");
        knowledgeBase.addData("oil", "liquid");
        knowledgeBase.addData("glass", "blue");
        knowledgeBase.addData("glass", "transparent");
        knowledgeBase.addData("glass", "caution");
        knowledgeBase.addData("glass", "light");
        knowledgeBase.addData("glass", "delicate");
        knowledgeBase.addData("glass", "solid");
        knowledgeBase.addData("electronics", "gold");
        knowledgeBase.addData("electronics", "silver");
        knowledgeBase.addData("electronics", "light");
        knowledgeBase.addData("electronics", "middleweight");
        knowledgeBase.addData("electronics", "delicate");
        knowledgeBase.addData("electronics", "solid");
        /**
         * Edit by Kamil on 2016-03-30.
         */
        Map <Integer,GraphPoints> multiplePoints=new HashMap<Integer, GraphPoints>();
        multiplePoints.put(0, new GraphPoints(110, 548));
        multiplePoints.put(1, new GraphPoints(110, 482));
        multiplePoints.put(2, new GraphPoints(110, 441));
        multiplePoints.put(3, new GraphPoints(110, 387));
        multiplePoints.put(4, new GraphPoints(110, 353));
        multiplePoints.put(5, new GraphPoints(110, 297));
        multiplePoints.put(6, new GraphPoints(110, 269));
        multiplePoints.put(7, new GraphPoints(110, 207));
        multiplePoints.put(8, new GraphPoints(110, 174));
        multiplePoints.put(9, new GraphPoints(110, 115));
        multiplePoints.put(10, new GraphPoints(110, 78));
        multiplePoints.put(11, new GraphPoints(110, 12));

        multiplePoints.put(23, new GraphPoints(242, 548));
        multiplePoints.put(22, new GraphPoints(242, 482));
        multiplePoints.put(21, new GraphPoints(242, 441));
        multiplePoints.put(20, new GraphPoints(242, 387));
        multiplePoints.put(19, new GraphPoints(242, 353));
        multiplePoints.put(18, new GraphPoints(242, 297));
        multiplePoints.put(17, new GraphPoints(242, 269));
        multiplePoints.put(16, new GraphPoints(242, 207));
        multiplePoints.put(15, new GraphPoints(242, 174));
        multiplePoints.put(14, new GraphPoints(242, 115));
        multiplePoints.put(13, new GraphPoints(242, 78));
        multiplePoints.put(12, new GraphPoints(242, 12));

        multiplePoints.put(24, new GraphPoints(266, 548));
        multiplePoints.put(25, new GraphPoints(266, 482));
        multiplePoints.put(26, new GraphPoints(266, 441));
        multiplePoints.put(27, new GraphPoints(266, 387));
        multiplePoints.put(28, new GraphPoints(266, 353));
        multiplePoints.put(29, new GraphPoints(266, 297));
        multiplePoints.put(30, new GraphPoints(266, 269));
        multiplePoints.put(31, new GraphPoints(266, 207));
        multiplePoints.put(32, new GraphPoints(266, 174));
        multiplePoints.put(33, new GraphPoints(266, 115));
        multiplePoints.put(34, new GraphPoints(266, 78));
        multiplePoints.put(35, new GraphPoints(266, 12));

        multiplePoints.put(47, new GraphPoints(392, 548));
        multiplePoints.put(46, new GraphPoints(392, 482));
        multiplePoints.put(45, new GraphPoints(392, 441));
        multiplePoints.put(44, new GraphPoints(392, 387));
        multiplePoints.put(43, new GraphPoints(392, 353));
        multiplePoints.put(42, new GraphPoints(392, 297));
        multiplePoints.put(41, new GraphPoints(392, 269));
        multiplePoints.put(40, new GraphPoints(392, 207));
        multiplePoints.put(39, new GraphPoints(392, 174));
        multiplePoints.put(38, new GraphPoints(392, 115));
        multiplePoints.put(37, new GraphPoints(392, 78));
        multiplePoints.put(36, new GraphPoints(392, 12));

        multiplePoints.put(48, new GraphPoints(420, 548));
        multiplePoints.put(49, new GraphPoints(420, 482));
        multiplePoints.put(50, new GraphPoints(420, 441));
        multiplePoints.put(51, new GraphPoints(420, 387));
        multiplePoints.put(52, new GraphPoints(420, 353));
        multiplePoints.put(53, new GraphPoints(420, 297));
        multiplePoints.put(54, new GraphPoints(420, 269));
        multiplePoints.put(55, new GraphPoints(420, 207));
        multiplePoints.put(56, new GraphPoints(420, 174));
        multiplePoints.put(57, new GraphPoints(420, 115));
        multiplePoints.put(58, new GraphPoints(420, 78));
        multiplePoints.put(59, new GraphPoints(420, 12));

        multiplePoints.put(71, new GraphPoints(554, 548));
        multiplePoints.put(70, new GraphPoints(554, 482));
        multiplePoints.put(69, new GraphPoints(554, 441));
        multiplePoints.put(68, new GraphPoints(554, 387));
        multiplePoints.put(67, new GraphPoints(554, 353));
        multiplePoints.put(66, new GraphPoints(554, 297));
        multiplePoints.put(65, new GraphPoints(554, 269));
        multiplePoints.put(64, new GraphPoints(554, 207));
        multiplePoints.put(63, new GraphPoints(554, 174));
        multiplePoints.put(62, new GraphPoints(554, 115));
        multiplePoints.put(61, new GraphPoints(554, 78));
        multiplePoints.put(60, new GraphPoints(554, 12));

        multiplePoints.put(72, new GraphPoints(578, 548));
        multiplePoints.put(73, new GraphPoints(578, 482));
        multiplePoints.put(74, new GraphPoints(578, 441));
        multiplePoints.put(75, new GraphPoints(578, 387));
        multiplePoints.put(76, new GraphPoints(578, 353));
        multiplePoints.put(77, new GraphPoints(578, 297));
        multiplePoints.put(78, new GraphPoints(578, 269));
        multiplePoints.put(79, new GraphPoints(578, 207));
        multiplePoints.put(80, new GraphPoints(578, 174));
        multiplePoints.put(81, new GraphPoints(578, 115));
        multiplePoints.put(82, new GraphPoints(578, 78));
        multiplePoints.put(83, new GraphPoints(578, 12));

        multiplePoints.put(95, new GraphPoints(705, 548));
        multiplePoints.put(94, new GraphPoints(705, 482));
        multiplePoints.put(93, new GraphPoints(705, 441));
        multiplePoints.put(92, new GraphPoints(705, 387));
        multiplePoints.put(91, new GraphPoints(705, 353));
        multiplePoints.put(90, new GraphPoints(705, 297));
        multiplePoints.put(89, new GraphPoints(705, 269));
        multiplePoints.put(88, new GraphPoints(705, 207));
        multiplePoints.put(87, new GraphPoints(705, 174));
        multiplePoints.put(86, new GraphPoints(705, 115));
        multiplePoints.put(85, new GraphPoints(705, 78));
        multiplePoints.put(84, new GraphPoints(705, 12));

        multiplePoints.put(96, new GraphPoints(734, 548));
        multiplePoints.put(97, new GraphPoints(734, 482));
        multiplePoints.put(98, new GraphPoints(734, 441));
        multiplePoints.put(99, new GraphPoints(734, 387));
        multiplePoints.put(100, new GraphPoints(734, 353));
        multiplePoints.put(101, new GraphPoints(734, 297));
        multiplePoints.put(102, new GraphPoints(734, 269));
        multiplePoints.put(103, new GraphPoints(734, 207));
        multiplePoints.put(104, new GraphPoints(734, 174));
        multiplePoints.put(105, new GraphPoints(734, 115));
        multiplePoints.put(106, new GraphPoints(734, 78));
        multiplePoints.put(107, new GraphPoints(734, 12));

        multiplePoints.put(119, new GraphPoints(862, 548));
        multiplePoints.put(118, new GraphPoints(862, 482));
        multiplePoints.put(117, new GraphPoints(862, 441));
        multiplePoints.put(116, new GraphPoints(862, 387));
        multiplePoints.put(115, new GraphPoints(862, 353));
        multiplePoints.put(114, new GraphPoints(862, 297));
        multiplePoints.put(113, new GraphPoints(862, 269));
        multiplePoints.put(112, new GraphPoints(862, 207));
        multiplePoints.put(111, new GraphPoints(862, 174));
        multiplePoints.put(110, new GraphPoints(862, 115));
        multiplePoints.put(109, new GraphPoints(862, 78));
        multiplePoints.put(108, new GraphPoints(862, 12));

        multiplePoints.put(120, new GraphPoints(895, 548));
        multiplePoints.put(121, new GraphPoints(895, 482));
        multiplePoints.put(122, new GraphPoints(895, 441));
        multiplePoints.put(123, new GraphPoints(895, 387));
        multiplePoints.put(124, new GraphPoints(895, 353));
        multiplePoints.put(125, new GraphPoints(895, 297));
        multiplePoints.put(126, new GraphPoints(895, 269));
        multiplePoints.put(127, new GraphPoints(895, 207));
        multiplePoints.put(128, new GraphPoints(895, 174));
        multiplePoints.put(129, new GraphPoints(895, 115));
        multiplePoints.put(130, new GraphPoints(895, 78));
        multiplePoints.put(131, new GraphPoints(895, 12));

        multiplePoints.put(143, new GraphPoints(1024, 548));
        multiplePoints.put(142, new GraphPoints(1024, 482));
        multiplePoints.put(141, new GraphPoints(1024, 441));
        multiplePoints.put(140, new GraphPoints(1024, 387));
        multiplePoints.put(139, new GraphPoints(1024, 353));
        multiplePoints.put(138, new GraphPoints(1024, 297));
        multiplePoints.put(137, new GraphPoints(1024, 269));
        multiplePoints.put(136, new GraphPoints(1024, 207));
        multiplePoints.put(135, new GraphPoints(1024, 174));
        multiplePoints.put(134, new GraphPoints(1024, 115));
        multiplePoints.put(133, new GraphPoints(1024, 78));
        multiplePoints.put(132, new GraphPoints(1024, 12));

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

        BFSPaths dfs2 = new BFSPaths(graph, 0);
        for (int it : dfs2.getPathTo(35)) {
            GraphPoints coord=multiplePoints.get(it);
            actualPositionW = coord.getX();
            actualPositionH = coord.getY();
            System.out.print(it + " ");
        }

  //      GraphPoints coord=multiplePoints.get(1);
     //   System.out.println(coord.getX() +" : "+coord.getY());
   //     graphPoints.addData(1,110);
      //  graphPoints.addData(1,548);

        Map<String, List<String>> knowledgeBase = Main.knowledgeBase.getKnowledgeBase();
        System.out.println(knowledgeBase.toString());
        // Declare random case spawn-points
        // X
        /*
        IntStream.range(0, 10).forEach(
                n -> {
                    casePoints[n][0] = 156.5;
                    casePoints[n+10][0] = 210.0;
                    casePoints[n+20][0] = 313.5;
                    casePoints[n+30][0] = 367.5;
                    casePoints[n+40][0] = 472.0;
                    casePoints[n+50][0] = 525.5;
                    casePoints[n+60][0] = 629.0;
                    casePoints[n+70][0] = 682.0;
                    casePoints[n+80][0] = 784.0;
                    casePoints[n+90][0] = 837.0;
                    casePoints[n+100][0] = 945.5;
                    casePoints[n+110][0] = 999.0;
                }
        );*/

        for (int n = 0; n < 109; n += 12) casePoints[n][0] = 156.5;
        for (int n = 1; n < 110; n += 12) casePoints[n][0] = 210.0;
        for (int n = 2; n < 111; n += 12) casePoints[n][0] = 313.5;
        for (int n = 3; n < 112; n += 12) casePoints[n][0] = 367.5;
        for (int n = 4; n < 113; n += 12) casePoints[n][0] = 472.0;
        for (int n = 5; n < 114; n += 12) casePoints[n][0] = 525.5;
        for (int n = 6; n < 115; n += 12) casePoints[n][0] = 629.0;
        for (int n = 7; n < 116; n += 12) casePoints[n][0] = 682.0;
        for (int n = 8; n < 117; n += 12) casePoints[n][0] = 784.0;
        for (int n = 9; n < 118; n += 12) casePoints[n][0] = 837.0;
        for (int n = 10; n < 119; n += 12) casePoints[n][0] = 945.5;
        for (int n = 11; n < 120; n += 12) casePoints[n][0] = 999.0;

        // Y
        IntStream.range(0,120).forEach(
                n -> {
                    if(n<12){
                        casePoints[n][1] = 97.0;
                    } else if(n > 11 && n < 24 ) {
                        casePoints[n][1] = 135.0;
                    } else if(n > 23 && n < 36) {
                        casePoints[n][1] = 189.5;
                    } else if(n > 35 && n < 48) {
                        casePoints[n][1] = 226.0;
                    } else if(n > 47 && n < 60) {
                        casePoints[n][1] = 279.5;
                    } else if(n > 59 && n < 72) {
                        casePoints[n][1] = 316.0;
                    } else if(n > 71 && n < 84) {
                        casePoints[n][1] = 370.0;
                    } else if(n > 83 && n < 96) {
                        casePoints[n][1] = 408.0;
                    } else if(n > 95 && n < 108) {
                        casePoints[n][1] = 460.0;
                    } else if(n > 107 && n < 120) {
                        casePoints[n][1] = 496.5;
                    }

                }
        );





        try {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        mainStage.setTitle("Inteligentny wozek widlowy");
       // Group root = new Group();
        mainScene = new Scene(page);
        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        page.getChildren().add(canvas);

        prepareActionHandlers();
        graphicsContext = canvas.getGraphicsContext2D();
        loadGraphics();
            setCase();
        /**
         * Main "game" loop
         */
        new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
                tickAndRender();
                setStatement();
                conveyorAnimated();

            }
        }.start();

        mainStage.show();
        } catch (IOException e) {
           e.printStackTrace();
        }

        // Get random spawn points and random cases.
        IntStream.range(0,20).forEach(
                n -> {
                    int i = caseSpawn.nextInt(119) + 1;
                    int j = caseNumber.nextInt(8) + 1;

                    switch (j) {
                        case 1:
                            casesToSpawn[n] = caseOne;
                            break;
                        case 2:
                            casesToSpawn[n] = caseTwo;
                            break;
                        case 3:
                            casesToSpawn[n] = caseThree;
                            break;
                        case 4:
                            casesToSpawn[n] = caseFour;
                            break;
                        case 5:
                            casesToSpawn[n] = caseFive;
                            break;
                        case 6:
                            casesToSpawn[n] = caseSix;
                            break;
                        case 7:
                            casesToSpawn[n] = caseSeven;
                            break;
                        case 8:
                            casesToSpawn[n] = caseEight;
                            break;
                        default:
                            casesToSpawn[n] = caseOne;
                            break;
                    }
                    locOfCases[n] = i;

                }
        );

    }

    private static void prepareActionHandlers()
    {
        mainScene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        mainScene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    private static void loadGraphics()
    {
        forklift = new Image("images/forklift.png");
        forklift2 = new Image("images/forklift2.png");
        background = new Image("images/background.png");
        conveyor = new Image("images/conveyor.png");
        cover = new Image("images/cover.png");
        caseOne = new Image("images/case1.png");
        caseTwo = new Image("images/case2.png");
        caseThree = new Image("images/case3.png");
        caseFour = new Image("images/case4.png");
        caseFive = new Image("images/case5.png");
        caseSix = new Image("images/case6.png");
        caseSeven = new Image("images/case7.png");
        caseEight = new Image("images/case8.png");

    }

    private static void tickAndRender()
    {
        // clear canvas
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        graphicsContext.drawImage(background, 0, 0);


        // Spawn Cases.
        IntStream.range(0, 19).forEach(
                n -> {
                    double distance = Math.sqrt(
                            (Math.pow((actualPositionH - casePoints[locOfCases[n]][1]), 2)) +
                                    (Math.pow((actualPositionW - casePoints[locOfCases[n]][0]),
                                            2)));

                    // if distance of forklift and case is greater than 30 draw all random cases
                    if (distance > 30) {
                        graphicsContext.drawImage(casesToSpawn[n], casePoints[locOfCases[n]][0],
                                casePoints[locOfCases[n]][1]);

                        // if distance of forklift and case < 30 change state that forklift is busy
                    } else {
                        caseNotToSpawn = true;
                        numberOfCase = n;

                    }
                    if(casesToSpawn[numberOfCase]!= null) {
                        actualCase = casesToSpawn[numberOfCase];
                    }
                    // if forklift is busy draw case on the forklift
                    if (caseNotToSpawn == true) {
                        graphicsContext
                                .drawImage(actualCase, actualPositionW + 10, actualPositionH);
                    }
                }
        );








        // delete case from bookstand
        casesToSpawn[numberOfCase] = null;

        // if the forklift approaches the tape, we remove the pack
        if(actualPositionW <= 60) {
            caseNotToSpawn = false;
            actualCase = null;
        }
        // Arrow keys moving
        if (currentlyActiveKeys.contains("LEFT"))
        {
            actualPositionW = actualPositionW - 1.5;
            graphicsContext.drawImage(forklift2, actualPositionW, actualPositionH);
            leftOrRight = false;
        }
        else if (currentlyActiveKeys.contains("RIGHT"))
        {
            actualPositionW = actualPositionW + 1.5;
            graphicsContext.drawImage(forklift, actualPositionW , actualPositionH);
            leftOrRight = true;
        }
        else if (currentlyActiveKeys.contains("DOWN")) {
            actualPositionH = actualPositionH + 1.5;
            if (leftOrRight == true) {
                graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(forklift2, actualPositionW, actualPositionH);
            }
        }
        else if (currentlyActiveKeys.contains("UP")) {
            actualPositionH = actualPositionH - 1.5;
            if (leftOrRight == true) {
                graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(forklift2, actualPositionW, actualPositionH);
            }
       }
        else {
            if (leftOrRight == true) {
                graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(forklift2, actualPositionW, actualPositionH);
            }
        }

    }

    private static void setCase() {
        mainScene.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {



                        drawCase(mouseEvent.getX(),mouseEvent.getY());
                        System.out.print(mouseEvent.getX()+" "+mouseEvent.getY()+"\n");
                    }

                });

    }



    private static void drawCase(double x, double y) {
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        graphicsContext.drawImage(casesToSpawn[0], 100, 500);
       // System.out.print(WIDTH+" "+HEIGHT);
    }

    private static void setStatement() {
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        graphicsContext.setFont(theFont);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);


        graphicsContext.fillText("X: " + actualPositionW, 1200, 50);
        graphicsContext.fillText("Y: " + actualPositionH, 1350, 50);

        graphicsContext.fillText("Output:", 1200, 150);
    }

    private static void conveyorAnimated() {

        graphicsContext.drawImage(conveyor, 5, conveyorPos - 600);
        graphicsContext.drawImage(conveyor, 5, conveyorPos);
        conveyorPos = conveyorPos + 0.5;
        graphicsContext.drawImage(cover, 5, 618 );
        if(conveyorPos >= 600) {
            conveyorPos = 0;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}
