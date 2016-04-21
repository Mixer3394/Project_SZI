package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;


public class Main extends Application {

    static int WIDTH = 1800;
    static int HEIGHT = 800;
    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static HashSet<String> currentlyActiveKeys = new HashSet<String>();
    static Map<Integer, AstarPoints> algorithmAvailablePoints = new HashMap<Integer, AstarPoints>();

    static int[] tempArray = new int[20];
    // Mouse events
    static double mouseX = 0.0;
    static double mouseY = 0.0;

    static Map<Integer, AstarPoints> multiplePoints = new HashMap<Integer, AstarPoints>();
    static KnowledgeBase knowledgeBase;
    static Astar astar;
    static int fieldNumber[] = new int[100];
    // Forklifts
    static Image forklift;
    static Image forklift2;

    // Etc
    static Image background;
    static Image conveyor;
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
    static double casePoints[][] = new double[80][2];

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

    static double actualPositionH = 0;
    static double actualPositionW = 110;
    static double conveyorPos = 0.0;


    // True if right, false if left
    static boolean leftOrRight = true;
    static boolean unlockPack = false;
    static boolean returnMode = false;

    static int[][] astarBlockedPoints = {
            {12, 1}, {11, 1}, {10, 1}, {9, 1}, {8, 1}, {7, 1}, {6, 1}, {5, 1}, {4, 1}, {3, 1},
            {3, 2}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {8, 2}, {9, 2}, {10, 2}, {11, 2}, {12, 2},
            {12, 5}, {11, 5}, {10, 5}, {9, 5}, {8, 5}, {7, 5}, {6, 5}, {5, 5}, {4, 5}, {3, 5},
            {3, 6}, {4, 6}, {5, 6}, {6, 6}, {7, 6}, {8, 6}, {9, 6}, {10, 6}, {11, 6}, {12, 6},
            {12, 9}, {11, 9}, {10, 9}, {9, 9}, {8, 9}, {7, 9}, {6, 9}, {5, 9}, {4, 9}, {3, 9},
            {3, 10}, {4, 10}, {5, 10}, {6, 10}, {7, 10}, {8, 10}, {9, 10}, {10, 10}, {11, 10},
            {12, 10},
            {12, 13}, {11, 13}, {10, 13}, {9, 13}, {8, 13}, {7, 13}, {6, 13}, {5, 13}, {4, 13},
            {3, 13}, {3, 14}, {4, 14}, {5, 14}, {6, 14}, {7, 14}, {8, 14}, {9, 14}, {10, 14},
            {11, 14}, {12, 14},


    };
    public int iterator = 0;

    private static void prepareActionHandlers() {
        mainScene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        mainScene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    private static void loadGraphics() {
        forklift = new Image("images/forklift.png");
        forklift2 = new Image("images/forklift2.png");
        background = new Image("images/background_new.png");
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

    private static void tickAndRender() {
        // clear canvas
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        graphicsContext.drawImage(background, 0, 0);


        // Spawn Cases.
        IntStream.range(0, 20).forEach(
                n -> {
                    double distance = Math.sqrt(
                            (Math.pow((actualPositionH - casePoints[locOfCases[n]][1]), 2)) +
                                    (Math.pow((actualPositionW - casePoints[locOfCases[n]][0]),
                                            2)));

                    // if distance of forklift and case is greater than 30 draw all random cases
                    if (distance > 55) {
                        graphicsContext.drawImage(casesToSpawn[n], casePoints[locOfCases[n]][0],
                                casePoints[locOfCases[n]][1]);

                        // if distance of forklift and case < 30 change state that forklift is busy
                    } else if (distance < 55 && unlockPack) {
                        caseNotToSpawn = true;
                        numberOfCase = n;

                    }
                    if (casesToSpawn[numberOfCase] != null && unlockPack) {
                        actualCase = casesToSpawn[numberOfCase];
                    }
                    // if forklift is busy draw case on the forklift
                    if (caseNotToSpawn == true && unlockPack) {
                        graphicsContext
                                .drawImage(actualCase, actualPositionW + 10, actualPositionH);
                    }
                }
        );

        // delete case from bookstand
        casesToSpawn[numberOfCase] = null;

        // if the forklift approaches the tape, we remove the pack
        if (actualPositionW <= 60) {
            caseNotToSpawn = false;
            actualCase = null;
        }

        //leftOrRight = false;

        //graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);



        // Arrow keys moving
        if (currentlyActiveKeys.contains("LEFT"))
        {
            actualPositionW = actualPositionW - 1.5;
            graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            leftOrRight = false;
        }
        else if (currentlyActiveKeys.contains("RIGHT"))
        {
            actualPositionW = actualPositionW + 1.5;
            graphicsContext.drawImage(caseOne, actualPositionW , actualPositionH);
            leftOrRight = true;
        }
        else if (currentlyActiveKeys.contains("DOWN")) {
            actualPositionH = actualPositionH + 1.5;
            if (leftOrRight == true) {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            }
        }
        else if (currentlyActiveKeys.contains("UP")) {
            actualPositionH = actualPositionH - 1.5;
            if (leftOrRight == true) {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            }
       }
       else {
            if (leftOrRight == true) {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            } else {
                graphicsContext.drawImage(caseOne, actualPositionW, actualPositionH);
            }
        }

    }

    // Get points that algorithm returns [x,y] and change them to map points for example [ 15,15 ] -> 255
    private static void getFieldNumber() {
        int it = 0;
        for (int i = astar.pathXY.size() - 1; i >= 0; i--) {
            for (int j = 0; j < algorithmAvailablePoints.size(); j++) {
                if ((algorithmAvailablePoints.get(j).getX()) == (astar.pathXY.get(i).getX()) &&
                        (algorithmAvailablePoints.get(j).getY()) == (astar.pathXY.get(i).getY())) {
                    fieldNumber[it] = j;
                    it++;
                }
            }
        }
    }



    private static void setStatement() {
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
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
        graphicsContext.drawImage(cover, 5, 618);
        if (conveyorPos >= 600) {
            conveyorPos = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {


       /* ********************************* START ALGORITHM!!!!!!!!!!! *************************************************
        n - numboer of test
        a - array size (our map has 16x16)
        sy - start point y
        sx - start point x
        dy - destination point y
        dx - destination point x
        z - array with blocked points

                   n   a   a  sy  sx dy dx    z                       */
        astar.test(1, 16, 16, 0, 0, 10, 8, astarBlockedPoints);


        knowledgeBase = new KnowledgeBase();
        knowledgeBase.addData("car parts", "gray");
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


        int x = 0;
        int y = 0;
        for (int i = 0; i <= 255; i++) {
            algorithmAvailablePoints.put(i, new AstarPoints(x, y));
            x++;
            if (x > 15) {
                y++;
                x = 0;
            }
        }



        multiplePoints.put(0, new AstarPoints(110, 0));
        multiplePoints.put(1, new AstarPoints(110, 35));
        multiplePoints.put(2, new AstarPoints(110, 70));
        multiplePoints.put(3, new AstarPoints(110, 143));
        multiplePoints.put(4, new AstarPoints(110, 174));
        multiplePoints.put(5, new AstarPoints(110, 207));
        multiplePoints.put(6, new AstarPoints(110, 269));
        multiplePoints.put(7, new AstarPoints(110, 297));
        multiplePoints.put(8, new AstarPoints(110, 353));
        multiplePoints.put(9, new AstarPoints(110, 387));
        multiplePoints.put(10, new AstarPoints(110, 441));
        multiplePoints.put(11, new AstarPoints(110, 482));
        multiplePoints.put(12, new AstarPoints(110, 548));
        multiplePoints.put(13, new AstarPoints(110, 600));
        multiplePoints.put(14, new AstarPoints(110, 635));
        multiplePoints.put(15, new AstarPoints(110, 670));

        multiplePoints.put(16, new AstarPoints(150, 0));
        multiplePoints.put(17, new AstarPoints(150, 35));
        multiplePoints.put(18, new AstarPoints(150, 70));
        multiplePoints.put(19, new AstarPoints(150, 143));
        multiplePoints.put(20, new AstarPoints(150, 174));
        multiplePoints.put(21, new AstarPoints(150, 207));
        multiplePoints.put(22, new AstarPoints(150, 269));
        multiplePoints.put(23, new AstarPoints(150, 297));
        multiplePoints.put(24, new AstarPoints(150, 353));
        multiplePoints.put(25, new AstarPoints(150, 387));
        multiplePoints.put(26, new AstarPoints(150, 441));
        multiplePoints.put(27, new AstarPoints(150, 482));
        multiplePoints.put(28, new AstarPoints(150, 548));
        multiplePoints.put(29, new AstarPoints(150, 600));
        multiplePoints.put(30, new AstarPoints(150, 635));
        multiplePoints.put(31, new AstarPoints(150, 670));


        multiplePoints.put(32, new AstarPoints(190, 0));
        multiplePoints.put(33, new AstarPoints(190, 35));
        multiplePoints.put(34, new AstarPoints(190, 70));
        multiplePoints.put(35, new AstarPoints(190, 143));
        multiplePoints.put(36, new AstarPoints(190, 174));
        multiplePoints.put(37, new AstarPoints(190, 207));
        multiplePoints.put(38, new AstarPoints(190, 269));
        multiplePoints.put(39, new AstarPoints(190, 297));
        multiplePoints.put(40, new AstarPoints(190, 353));
        multiplePoints.put(41, new AstarPoints(190, 387));
        multiplePoints.put(42, new AstarPoints(190, 441));
        multiplePoints.put(43, new AstarPoints(190, 482));
        multiplePoints.put(44, new AstarPoints(190, 548));
        multiplePoints.put(45, new AstarPoints(190, 600));
        multiplePoints.put(46, new AstarPoints(190, 635));
        multiplePoints.put(47, new AstarPoints(190, 670));

        multiplePoints.put(48, new AstarPoints(240, 0));
        multiplePoints.put(49, new AstarPoints(240, 35));
        multiplePoints.put(50, new AstarPoints(240, 70));
        multiplePoints.put(51, new AstarPoints(240, 143));
        multiplePoints.put(52, new AstarPoints(240, 174));
        multiplePoints.put(53, new AstarPoints(240, 207));
        multiplePoints.put(54, new AstarPoints(240, 269));
        multiplePoints.put(55, new AstarPoints(240, 297));
        multiplePoints.put(56, new AstarPoints(240, 353));
        multiplePoints.put(57, new AstarPoints(240, 387));
        multiplePoints.put(58, new AstarPoints(240, 441));
        multiplePoints.put(59, new AstarPoints(240, 482));
        multiplePoints.put(60, new AstarPoints(240, 548));
        multiplePoints.put(61, new AstarPoints(240, 600));
        multiplePoints.put(62, new AstarPoints(240, 635));
        multiplePoints.put(63, new AstarPoints(240, 670));

        multiplePoints.put(64, new AstarPoints(265, 0));
        multiplePoints.put(65, new AstarPoints(265, 35));
        multiplePoints.put(66, new AstarPoints(265, 70));
        multiplePoints.put(67, new AstarPoints(265, 143));
        multiplePoints.put(68, new AstarPoints(265, 174));
        multiplePoints.put(69, new AstarPoints(265, 207));
        multiplePoints.put(70, new AstarPoints(265, 269));
        multiplePoints.put(71, new AstarPoints(265, 297));
        multiplePoints.put(72, new AstarPoints(265, 353));
        multiplePoints.put(73, new AstarPoints(265, 387));
        multiplePoints.put(74, new AstarPoints(265, 441));
        multiplePoints.put(75, new AstarPoints(265, 482));
        multiplePoints.put(76, new AstarPoints(265, 548));
        multiplePoints.put(77, new AstarPoints(265, 600));
        multiplePoints.put(78, new AstarPoints(265, 635));
        multiplePoints.put(79, new AstarPoints(265, 670));


        multiplePoints.put(80, new AstarPoints(315, 0));
        multiplePoints.put(81, new AstarPoints(315, 35));
        multiplePoints.put(82, new AstarPoints(315, 70));
        multiplePoints.put(83, new AstarPoints(315, 143));
        multiplePoints.put(84, new AstarPoints(315, 174));
        multiplePoints.put(85, new AstarPoints(315, 207));
        multiplePoints.put(86, new AstarPoints(315, 269));
        multiplePoints.put(87, new AstarPoints(315, 297));
        multiplePoints.put(88, new AstarPoints(315, 353));
        multiplePoints.put(89, new AstarPoints(315, 387));
        multiplePoints.put(90, new AstarPoints(315, 441));
        multiplePoints.put(91, new AstarPoints(315, 482));
        multiplePoints.put(92, new AstarPoints(315, 548));
        multiplePoints.put(93, new AstarPoints(315, 600));
        multiplePoints.put(94, new AstarPoints(315, 635));
        multiplePoints.put(95, new AstarPoints(315, 670));


        multiplePoints.put(96, new AstarPoints(355, 0));
        multiplePoints.put(97, new AstarPoints(355, 35));
        multiplePoints.put(98, new AstarPoints(355, 70));
        multiplePoints.put(99, new AstarPoints(355, 143));
        multiplePoints.put(100, new AstarPoints(355, 174));
        multiplePoints.put(101, new AstarPoints(355, 207));
        multiplePoints.put(102, new AstarPoints(355, 269));
        multiplePoints.put(103, new AstarPoints(355, 297));
        multiplePoints.put(104, new AstarPoints(355, 353));
        multiplePoints.put(105, new AstarPoints(355, 387));
        multiplePoints.put(106, new AstarPoints(355, 441));
        multiplePoints.put(107, new AstarPoints(355, 482));
        multiplePoints.put(108, new AstarPoints(355, 548));
        multiplePoints.put(109, new AstarPoints(355, 600));
        multiplePoints.put(110, new AstarPoints(355, 635));
        multiplePoints.put(111, new AstarPoints(355, 670));


        multiplePoints.put(112, new AstarPoints(395, 0));
        multiplePoints.put(113, new AstarPoints(395, 35));
        multiplePoints.put(114, new AstarPoints(395, 70));
        multiplePoints.put(115, new AstarPoints(395, 143));
        multiplePoints.put(116, new AstarPoints(395, 174));
        multiplePoints.put(117, new AstarPoints(395, 207));
        multiplePoints.put(118, new AstarPoints(395, 269));
        multiplePoints.put(119, new AstarPoints(395, 297));
        multiplePoints.put(120, new AstarPoints(395, 353));
        multiplePoints.put(121, new AstarPoints(395, 387));
        multiplePoints.put(122, new AstarPoints(395, 441));
        multiplePoints.put(123, new AstarPoints(395, 482));
        multiplePoints.put(124, new AstarPoints(395, 548));
        multiplePoints.put(125, new AstarPoints(395, 600));
        multiplePoints.put(126, new AstarPoints(395, 635));
        multiplePoints.put(127, new AstarPoints(395, 670));

        multiplePoints.put(128, new AstarPoints(425, 0));
        multiplePoints.put(129, new AstarPoints(425, 35));
        multiplePoints.put(130, new AstarPoints(425, 70));
        multiplePoints.put(131, new AstarPoints(425, 143));
        multiplePoints.put(132, new AstarPoints(425, 174));
        multiplePoints.put(133, new AstarPoints(425, 207));
        multiplePoints.put(134, new AstarPoints(425, 269));
        multiplePoints.put(135, new AstarPoints(425, 297));
        multiplePoints.put(136, new AstarPoints(425, 353));
        multiplePoints.put(137, new AstarPoints(425, 387));
        multiplePoints.put(138, new AstarPoints(425, 441));
        multiplePoints.put(139, new AstarPoints(425, 482));
        multiplePoints.put(140, new AstarPoints(425, 548));
        multiplePoints.put(141, new AstarPoints(425, 600));
        multiplePoints.put(142, new AstarPoints(425, 635));
        multiplePoints.put(143, new AstarPoints(425, 670));

        multiplePoints.put(144, new AstarPoints(470, 0));
        multiplePoints.put(145, new AstarPoints(470, 35));
        multiplePoints.put(146, new AstarPoints(470, 70));
        multiplePoints.put(147, new AstarPoints(470, 143));
        multiplePoints.put(148, new AstarPoints(470, 174));
        multiplePoints.put(149, new AstarPoints(470, 207));
        multiplePoints.put(150, new AstarPoints(470, 269));
        multiplePoints.put(151, new AstarPoints(470, 297));
        multiplePoints.put(152, new AstarPoints(470, 353));
        multiplePoints.put(153, new AstarPoints(470, 387));
        multiplePoints.put(154, new AstarPoints(470, 441));
        multiplePoints.put(155, new AstarPoints(470, 482));
        multiplePoints.put(156, new AstarPoints(470, 548));
        multiplePoints.put(157, new AstarPoints(470, 600));
        multiplePoints.put(158, new AstarPoints(470, 635));
        multiplePoints.put(159, new AstarPoints(470, 670));


        multiplePoints.put(160, new AstarPoints(515, 0));
        multiplePoints.put(161, new AstarPoints(515, 35));
        multiplePoints.put(162, new AstarPoints(515, 70));
        multiplePoints.put(163, new AstarPoints(515, 143));
        multiplePoints.put(164, new AstarPoints(515, 174));
        multiplePoints.put(165, new AstarPoints(515, 207));
        multiplePoints.put(166, new AstarPoints(515, 269));
        multiplePoints.put(167, new AstarPoints(515, 297));
        multiplePoints.put(168, new AstarPoints(515, 353));
        multiplePoints.put(169, new AstarPoints(515, 387));
        multiplePoints.put(170, new AstarPoints(515, 441));
        multiplePoints.put(171, new AstarPoints(515, 482));
        multiplePoints.put(172, new AstarPoints(515, 548));
        multiplePoints.put(173, new AstarPoints(515, 600));
        multiplePoints.put(174, new AstarPoints(515, 635));
        multiplePoints.put(175, new AstarPoints(515, 670));


        multiplePoints.put(176, new AstarPoints(550, 0));
        multiplePoints.put(177, new AstarPoints(550, 35));
        multiplePoints.put(178, new AstarPoints(550, 70));
        multiplePoints.put(179, new AstarPoints(550, 143));
        multiplePoints.put(180, new AstarPoints(550, 174));
        multiplePoints.put(181, new AstarPoints(550, 207));
        multiplePoints.put(182, new AstarPoints(550, 269));
        multiplePoints.put(183, new AstarPoints(550, 297));
        multiplePoints.put(184, new AstarPoints(550, 353));
        multiplePoints.put(185, new AstarPoints(550, 387));
        multiplePoints.put(186, new AstarPoints(550, 441));
        multiplePoints.put(187, new AstarPoints(550, 482));
        multiplePoints.put(188, new AstarPoints(550, 548));
        multiplePoints.put(189, new AstarPoints(550, 600));
        multiplePoints.put(190, new AstarPoints(550, 635));
        multiplePoints.put(191, new AstarPoints(550, 670));

        multiplePoints.put(192, new AstarPoints(580, 0));
        multiplePoints.put(193, new AstarPoints(580, 35));
        multiplePoints.put(194, new AstarPoints(580, 70));
        multiplePoints.put(195, new AstarPoints(580, 143));
        multiplePoints.put(196, new AstarPoints(580, 174));
        multiplePoints.put(197, new AstarPoints(580, 207));
        multiplePoints.put(198, new AstarPoints(580, 269));
        multiplePoints.put(199, new AstarPoints(580, 297));
        multiplePoints.put(200, new AstarPoints(580, 353));
        multiplePoints.put(201, new AstarPoints(580, 387));
        multiplePoints.put(202, new AstarPoints(580, 441));
        multiplePoints.put(203, new AstarPoints(580, 482));
        multiplePoints.put(204, new AstarPoints(580, 548));
        multiplePoints.put(205, new AstarPoints(580, 600));
        multiplePoints.put(206, new AstarPoints(580, 635));
        multiplePoints.put(207, new AstarPoints(580, 670));

        multiplePoints.put(208, new AstarPoints(625, 0));
        multiplePoints.put(209, new AstarPoints(625, 35));
        multiplePoints.put(210, new AstarPoints(625, 70));
        multiplePoints.put(211, new AstarPoints(625, 143));
        multiplePoints.put(212, new AstarPoints(625, 174));
        multiplePoints.put(213, new AstarPoints(625, 207));
        multiplePoints.put(214, new AstarPoints(625, 269));
        multiplePoints.put(215, new AstarPoints(625, 297));
        multiplePoints.put(216, new AstarPoints(625, 353));
        multiplePoints.put(217, new AstarPoints(625, 387));
        multiplePoints.put(218, new AstarPoints(625, 441));
        multiplePoints.put(219, new AstarPoints(625, 482));
        multiplePoints.put(220, new AstarPoints(625, 548));
        multiplePoints.put(221, new AstarPoints(625, 600));
        multiplePoints.put(222, new AstarPoints(625, 635));
        multiplePoints.put(223, new AstarPoints(625, 670));

        multiplePoints.put(224, new AstarPoints(670, 0));
        multiplePoints.put(225, new AstarPoints(670, 35));
        multiplePoints.put(226, new AstarPoints(670, 70));
        multiplePoints.put(227, new AstarPoints(670, 143));
        multiplePoints.put(228, new AstarPoints(670, 174));
        multiplePoints.put(229, new AstarPoints(670, 207));
        multiplePoints.put(230, new AstarPoints(670, 269));
        multiplePoints.put(231, new AstarPoints(670, 297));
        multiplePoints.put(232, new AstarPoints(670, 353));
        multiplePoints.put(233, new AstarPoints(670, 387));
        multiplePoints.put(234, new AstarPoints(670, 441));
        multiplePoints.put(235, new AstarPoints(670, 482));
        multiplePoints.put(236, new AstarPoints(670, 548));
        multiplePoints.put(237, new AstarPoints(670, 600));
        multiplePoints.put(238, new AstarPoints(670, 635));
        multiplePoints.put(239, new AstarPoints(670, 670));

        multiplePoints.put(240, new AstarPoints(705, 0));
        multiplePoints.put(241, new AstarPoints(705, 35));
        multiplePoints.put(242, new AstarPoints(705, 70));
        multiplePoints.put(243, new AstarPoints(705, 143));
        multiplePoints.put(244, new AstarPoints(705, 174));
        multiplePoints.put(245, new AstarPoints(705, 207));
        multiplePoints.put(246, new AstarPoints(705, 269));
        multiplePoints.put(247, new AstarPoints(705, 297));
        multiplePoints.put(248, new AstarPoints(705, 353));
        multiplePoints.put(249, new AstarPoints(705, 387));
        multiplePoints.put(250, new AstarPoints(705, 441));
        multiplePoints.put(251, new AstarPoints(705, 482));
        multiplePoints.put(252, new AstarPoints(705, 548));
        multiplePoints.put(253, new AstarPoints(705, 600));
        multiplePoints.put(254, new AstarPoints(705, 635));
        multiplePoints.put(255, new AstarPoints(705, 670));

        Map<String, List<String>> knowledgeBase = Main.knowledgeBase.getKnowledgeBase();
        //System.out.println(knowledgeBase.toString());



        // Declare random case spawn-points

        // X
//        for (int n = 0; n < 109; n += 12) casePoints[n][0] = 156.0;
//        for (int n = 1; n < 110; n += 12) casePoints[n][0] = 210.0;
//        for (int n = 2; n < 111; n += 12) casePoints[n][0] = 313.5;
//        for (int n = 3; n < 112; n += 12) casePoints[n][0] = 367.5;
//        for (int n = 4; n < 113; n += 12) casePoints[n][0] = 472.0;
//        for (int n = 5; n < 114; n += 12) casePoints[n][0] = 525.5;
//        for (int n = 6; n < 115; n += 12) casePoints[n][0] = 629.0;
//        for (int n = 7; n < 116; n += 12) casePoints[n][0] = 682.0;
//        for (int n = 8; n < 117; n += 12) casePoints[n][0] = 784.0;
//        for (int n = 9; n < 118; n += 12) casePoints[n][0] = 837.0;
//        for (int n = 10; n < 119; n += 12) casePoints[n][0] = 945.5;
//        for (int n = 11; n < 120; n += 12) casePoints[n][0] = 999.0;

        for (int n =0; n < 73; n += 8) casePoints[n][0] = 156.0;
        for (int n =1; n < 74; n += 8) casePoints[n][0] = 210.0;
        for (int n =2; n < 75; n += 8) casePoints[n][0] = 313.5;
        for (int n =3; n < 76; n += 8) casePoints[n][0] = 367.5;
        for (int n =4; n < 77; n += 8) casePoints[n][0] = 472.0;
        for (int n =5; n < 78; n += 8) casePoints[n][0] = 525.5;
        for (int n =6; n < 79; n += 8) casePoints[n][0] = 629.0;
        for (int n =7; n < 80; n += 8) casePoints[n][0] = 682.0;

        // Y

        IntStream.range(0, 80).forEach(
                n -> {
                    if (n < 8) {
                        casePoints[n][1] = 156.0;
                    } else if (n > 7 && n < 16) {
                        casePoints[n][1] = 195.0;
                    } else if (n > 15 && n < 24) {
                        casePoints[n][1] = 247.0;
                    } else if (n > 23 && n < 32) {
                        casePoints[n][1] = 282.0;
                    } else if (n > 31 && n < 40) {
                        casePoints[n][1] = 340.0;
                    } else if (n > 39 && n < 48) {
                        casePoints[n][1] = 372.0;
                    } else if (n > 47 && n < 56) {
                        casePoints[n][1] = 429.0;
                    } else if (n > 55 && n < 64) {
                        casePoints[n][1] = 466.0;
                    } else if (n > 63 && n < 72) {
                        casePoints[n][1] = 519.0;
                    } else if (n > 71 && n < 80) {
                        casePoints[n][1] = 554.0;
                    }
                }
        );

//        IntStream.range(0, 120).forEach(
//                n -> {
//                    if (n < 12) {
//                        casePoints[n][1] = 97.0;
//                    } else if (n > 11 && n < 24) {
//                        casePoints[n][1] = 135.0;
//                    } else if (n > 23 && n < 36) {
//                        casePoints[n][1] = 189.5;
//                    } else if (n > 35 && n < 48) {
//                        casePoints[n][1] = 226.0;
//                    } else if (n > 47 && n < 60) {
//                        casePoints[n][1] = 279.5;
//                    } else if (n > 59 && n < 72) {
//                        casePoints[n][1] = 316.0;
//                    } else if (n > 71 && n < 84) {
//                        casePoints[n][1] = 370.0;
//                    } else if (n > 83 && n < 96) {
//                        casePoints[n][1] = 408.0;
//                    } else if (n > 95 && n < 108) {
//                        casePoints[n][1] = 460.0;
//                    } else if (n > 107 && n < 120) {
//                        casePoints[n][1] = 496.5;
//                    }
//                }
//        );

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            mainStage.setTitle("Inteligentny wozek widlowy");
            //   Group root = new Group();
            mainScene = new Scene(page);
            mainStage.setScene(mainScene);

            Canvas canvas = new Canvas(WIDTH, HEIGHT);

            page.getChildren().add(canvas);

            prepareActionHandlers();
            graphicsContext = canvas.getGraphicsContext2D();
            loadGraphics();

            getFieldNumber();
            /**
             * Main "game" loop
             */
            setCase();
            new AnimationTimer() {
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
        IntStream.range(0, 20).forEach(
                n -> {
                    int i = 0;
                    int k = caseSpawn.nextInt(79) + 1;
                    boolean temp = true;

                    while (temp == true) {
                        if (!contains(tempArray, k)) {
                            tempArray[n] = k;
                            i = k;
                            temp = false;
                        } else {
                            k = caseSpawn.nextInt(79) + 1;

                        }
                    }
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

    private void setCase() {

        mainScene.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        System.out.print("X:" + mouseEvent.getX() + " " + "Y:" + mouseEvent.getY() + "\n");

                        if (iterator < astar.pathXY.size() && returnMode == false) {
                            actualPositionW = multiplePoints.get(fieldNumber[iterator]).getX();
                            actualPositionH = multiplePoints.get(fieldNumber[iterator]).getY();
                            iterator++;
                            unlockPack = false;
                        } else {
                            returnMode = true;
                            unlockPack = true;
                        }
                        if (iterator >= 0 && returnMode == true) {
                            iterator--;
                            actualPositionW = multiplePoints.get(fieldNumber[iterator]).getX();
                            actualPositionH = multiplePoints.get(fieldNumber[iterator]).getY();
                        }

                        if (iterator == 0) {
                            System.out.print("END");
                            returnMode = false;
                        }
                    }
                });

    }

    public static boolean contains(int[] arr, int targetValue) {
        for(int s: arr){
            if(s == targetValue)
                return true;
        }
        return false;
    }
}
