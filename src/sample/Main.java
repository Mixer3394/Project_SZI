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
    static Map <Integer, AstarPoints> algorithmAvailablePoints=new HashMap<Integer, AstarPoints>();


    static Map <Integer, AstarPoints> multiplePoints=new HashMap<Integer, AstarPoints>();
    static KnowledgeBase knowledgeBase;
    static Astar astar;
    static int fieldNumber[] = new int[100];
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

    static double actualPositionH = 12;
    static double actualPositionW = 110;
    static double conveyorPos = 0.0;


    // True if right, false if left
    static boolean leftOrRight = true;
    static boolean unlockPack = false;
    static boolean returnMode = false;

    static int[][] astarBlockedPoints = {{10,1},{9,1},{8,1},{7,1},{6,1},{5,1},{4,1},{3,1},{2,1},{1,1},{1,2},{2,2},{3,2},{4,2},{5,2},{6,2},{7,2},{8,2},{9,2},{10,2}};
    public int iterator = 0;
    @Override
    public void start(Stage mainStage) throws Exception {


        astar.test(1,15,15,0,0,6,0,astarBlockedPoints );

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



        algorithmAvailablePoints.put(0, new AstarPoints(0,0));
        algorithmAvailablePoints.put(1, new AstarPoints(1,0));
        algorithmAvailablePoints.put(2, new AstarPoints(2,0));
        algorithmAvailablePoints.put(3, new AstarPoints(3,0));
        algorithmAvailablePoints.put(4, new AstarPoints(4,0));
        algorithmAvailablePoints.put(5, new AstarPoints(5,0));
        algorithmAvailablePoints.put(6, new AstarPoints(6,0));
        algorithmAvailablePoints.put(7, new AstarPoints(7,0));
        algorithmAvailablePoints.put(8, new AstarPoints(8,0));
        algorithmAvailablePoints.put(9, new AstarPoints(9,0));
        algorithmAvailablePoints.put(10, new AstarPoints(10,0));
        algorithmAvailablePoints.put(11, new AstarPoints(11,0));
        algorithmAvailablePoints.put(12, new AstarPoints(11,1));
        algorithmAvailablePoints.put(13, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(14, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(15, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(16, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(17, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(18, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(19, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(20, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(21, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(22, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(23, new AstarPoints(0,1));
        algorithmAvailablePoints.put(24, new AstarPoints(0,2));
        algorithmAvailablePoints.put(25, new AstarPoints(-1,-1));

        algorithmAvailablePoints.put(26, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(27, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(28, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(29, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(30, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(31, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(32, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(33, new AstarPoints(-1,-1));
        algorithmAvailablePoints.put(34, new AstarPoints(-1,-1));



        algorithmAvailablePoints.put(35, new AstarPoints(11,2));
        algorithmAvailablePoints.put(36, new AstarPoints(11,3));


        algorithmAvailablePoints.put(37, new AstarPoints(10,3));
        algorithmAvailablePoints.put(38, new AstarPoints(9,3));
        algorithmAvailablePoints.put(39, new AstarPoints(8,3));
        algorithmAvailablePoints.put(40, new AstarPoints(7,3));
        algorithmAvailablePoints.put(41, new AstarPoints(6,3));
        algorithmAvailablePoints.put(42, new AstarPoints(5,3));
        algorithmAvailablePoints.put(43, new AstarPoints(4,3));
        algorithmAvailablePoints.put(44, new AstarPoints(3,3));
        algorithmAvailablePoints.put(45, new AstarPoints(2,3));
        algorithmAvailablePoints.put(46, new AstarPoints(1,3));
        algorithmAvailablePoints.put(47, new AstarPoints(0,3));


        multiplePoints.put(11, new AstarPoints(110, 548));
        multiplePoints.put(10, new AstarPoints(110, 482));
        multiplePoints.put(9, new AstarPoints(110, 441));
        multiplePoints.put(8, new AstarPoints(110, 387));
        multiplePoints.put(7, new AstarPoints(110, 353));
        multiplePoints.put(6, new AstarPoints(110, 297));
        multiplePoints.put(5, new AstarPoints(110, 269));
        multiplePoints.put(4, new AstarPoints(110, 207));
        multiplePoints.put(3, new AstarPoints(110, 174));
        multiplePoints.put(2, new AstarPoints(110, 115));
        multiplePoints.put(1, new AstarPoints(110, 78));
        multiplePoints.put(0, new AstarPoints(110, 12));

        multiplePoints.put(12, new AstarPoints(137, 548));
        multiplePoints.put(23, new AstarPoints(137, 12));
        multiplePoints.put(24, new AstarPoints(198, 12));
        multiplePoints.put(47, new AstarPoints(242, 12));
        multiplePoints.put(46, new AstarPoints(242, 78));
        multiplePoints.put(45, new AstarPoints(242, 115));
        multiplePoints.put(44, new AstarPoints(242, 174));
        multiplePoints.put(43, new AstarPoints(242, 207));
        multiplePoints.put(42, new AstarPoints(242, 269));
        multiplePoints.put(41, new AstarPoints(242, 297));
        multiplePoints.put(40, new AstarPoints(242, 353));
        multiplePoints.put(39, new AstarPoints(242, 387));
        multiplePoints.put(38, new AstarPoints(242, 441));
        multiplePoints.put(37, new AstarPoints(242, 482));
        multiplePoints.put(36, new AstarPoints(242, 548));




/*
        multiplePoints.put(23, new AstarPoints(242, 548));
        multiplePoints.put(22, new AstarPoints(242, 482));
        multiplePoints.put(21, new AstarPoints(242, 441));
        multiplePoints.put(20, new AstarPoints(242, 387));
        multiplePoints.put(19, new AstarPoints(242, 353));
        multiplePoints.put(18, new AstarPoints(242, 297));
        multiplePoints.put(17, new AstarPoints(242, 269));
        multiplePoints.put(16, new AstarPoints(242, 207));
        multiplePoints.put(15, new AstarPoints(242, 174));
        multiplePoints.put(14, new AstarPoints(242, 115));
        multiplePoints.put(13, new AstarPoints(242, 78));
        multiplePoints.put(12, new AstarPoints(242, 12));

        multiplePoints.put(24, new AstarPoints(266, 548));
        multiplePoints.put(25, new AstarPoints(266, 482));
        multiplePoints.put(26, new AstarPoints(266, 441));
        multiplePoints.put(27, new AstarPoints(266, 387));
        multiplePoints.put(28, new AstarPoints(266, 353));
        multiplePoints.put(29, new AstarPoints(266, 297));
        multiplePoints.put(30, new AstarPoints(266, 269));
        multiplePoints.put(31, new AstarPoints(266, 207));
        multiplePoints.put(32, new AstarPoints(266, 174));
        multiplePoints.put(33, new AstarPoints(266, 115));
        multiplePoints.put(34, new AstarPoints(266, 78));
        multiplePoints.put(35, new AstarPoints(266, 12));

        multiplePoints.put(47, new AstarPoints(392, 548));
        multiplePoints.put(46, new AstarPoints(392, 482));
        multiplePoints.put(45, new AstarPoints(392, 441));
        multiplePoints.put(44, new AstarPoints(392, 387));
        multiplePoints.put(43, new AstarPoints(392, 353));
        multiplePoints.put(42, new AstarPoints(392, 297));
        multiplePoints.put(41, new AstarPoints(392, 269));
        multiplePoints.put(40, new AstarPoints(392, 207));
        multiplePoints.put(39, new AstarPoints(392, 174));
        multiplePoints.put(38, new AstarPoints(392, 115));
        multiplePoints.put(37, new AstarPoints(392, 78));
        multiplePoints.put(36, new AstarPoints(392, 12));

        multiplePoints.put(48, new AstarPoints(420, 548));
        multiplePoints.put(49, new AstarPoints(420, 482));
        multiplePoints.put(50, new AstarPoints(420, 441));
        multiplePoints.put(51, new AstarPoints(420, 387));
        multiplePoints.put(52, new AstarPoints(420, 353));
        multiplePoints.put(53, new AstarPoints(420, 297));
        multiplePoints.put(54, new AstarPoints(420, 269));
        multiplePoints.put(55, new AstarPoints(420, 207));
        multiplePoints.put(56, new AstarPoints(420, 174));
        multiplePoints.put(57, new AstarPoints(420, 115));
        multiplePoints.put(58, new AstarPoints(420, 78));
        multiplePoints.put(59, new AstarPoints(420, 12));

        multiplePoints.put(71, new AstarPoints(554, 548));
        multiplePoints.put(70, new AstarPoints(554, 482));
        multiplePoints.put(69, new AstarPoints(554, 441));
        multiplePoints.put(68, new AstarPoints(554, 387));
        multiplePoints.put(67, new AstarPoints(554, 353));
        multiplePoints.put(66, new AstarPoints(554, 297));
        multiplePoints.put(65, new AstarPoints(554, 269));
        multiplePoints.put(64, new AstarPoints(554, 207));
        multiplePoints.put(63, new AstarPoints(554, 174));
        multiplePoints.put(62, new AstarPoints(554, 115));
        multiplePoints.put(61, new AstarPoints(554, 78));
        multiplePoints.put(60, new AstarPoints(554, 12));

        multiplePoints.put(72, new AstarPoints(578, 548));
        multiplePoints.put(73, new AstarPoints(578, 482));
        multiplePoints.put(74, new AstarPoints(578, 441));
        multiplePoints.put(75, new AstarPoints(578, 387));
        multiplePoints.put(76, new AstarPoints(578, 353));
        multiplePoints.put(77, new AstarPoints(578, 297));
        multiplePoints.put(78, new AstarPoints(578, 269));
        multiplePoints.put(79, new AstarPoints(578, 207));
        multiplePoints.put(80, new AstarPoints(578, 174));
        multiplePoints.put(81, new AstarPoints(578, 115));
        multiplePoints.put(82, new AstarPoints(578, 78));
        multiplePoints.put(83, new AstarPoints(578, 12));

        multiplePoints.put(95, new AstarPoints(705, 548));
        multiplePoints.put(94, new AstarPoints(705, 482));
        multiplePoints.put(93, new AstarPoints(705, 441));
        multiplePoints.put(92, new AstarPoints(705, 387));
        multiplePoints.put(91, new AstarPoints(705, 353));
        multiplePoints.put(90, new AstarPoints(705, 297));
        multiplePoints.put(89, new AstarPoints(705, 269));
        multiplePoints.put(88, new AstarPoints(705, 207));
        multiplePoints.put(87, new AstarPoints(705, 174));
        multiplePoints.put(86, new AstarPoints(705, 115));
        multiplePoints.put(85, new AstarPoints(705, 78));
        multiplePoints.put(84, new AstarPoints(705, 12));

        multiplePoints.put(96, new AstarPoints(734, 548));
        multiplePoints.put(97, new AstarPoints(734, 482));
        multiplePoints.put(98, new AstarPoints(734, 441));
        multiplePoints.put(99, new AstarPoints(734, 387));
        multiplePoints.put(100, new AstarPoints(734, 353));
        multiplePoints.put(101, new AstarPoints(734, 297));
        multiplePoints.put(102, new AstarPoints(734, 269));
        multiplePoints.put(103, new AstarPoints(734, 207));
        multiplePoints.put(104, new AstarPoints(734, 174));
        multiplePoints.put(105, new AstarPoints(734, 115));
        multiplePoints.put(106, new AstarPoints(734, 78));
        multiplePoints.put(107, new AstarPoints(734, 12));

        multiplePoints.put(119, new AstarPoints(862, 548));
        multiplePoints.put(118, new AstarPoints(862, 482));
        multiplePoints.put(117, new AstarPoints(862, 441));
        multiplePoints.put(116, new AstarPoints(862, 387));
        multiplePoints.put(115, new AstarPoints(862, 353));
        multiplePoints.put(114, new AstarPoints(862, 297));
        multiplePoints.put(113, new AstarPoints(862, 269));
        multiplePoints.put(112, new AstarPoints(862, 207));
        multiplePoints.put(111, new AstarPoints(862, 174));
        multiplePoints.put(110, new AstarPoints(862, 115));
        multiplePoints.put(109, new AstarPoints(862, 78));
        multiplePoints.put(108, new AstarPoints(862, 12));

        multiplePoints.put(120, new AstarPoints(895, 548));
        multiplePoints.put(121, new AstarPoints(895, 482));
        multiplePoints.put(122, new AstarPoints(895, 441));
        multiplePoints.put(123, new AstarPoints(895, 387));
        multiplePoints.put(124, new AstarPoints(895, 353));
        multiplePoints.put(125, new AstarPoints(895, 297));
        multiplePoints.put(126, new AstarPoints(895, 269));
        multiplePoints.put(127, new AstarPoints(895, 207));
        multiplePoints.put(128, new AstarPoints(895, 174));
        multiplePoints.put(129, new AstarPoints(895, 115));
        multiplePoints.put(130, new AstarPoints(895, 78));
        multiplePoints.put(131, new AstarPoints(895, 12));

        multiplePoints.put(143, new AstarPoints(1024, 548));
        multiplePoints.put(142, new AstarPoints(1024, 482));
        multiplePoints.put(141, new AstarPoints(1024, 441));
        multiplePoints.put(140, new AstarPoints(1024, 387));
        multiplePoints.put(139, new AstarPoints(1024, 353));
        multiplePoints.put(138, new AstarPoints(1024, 297));
        multiplePoints.put(137, new AstarPoints(1024, 269));
        multiplePoints.put(136, new AstarPoints(1024, 207));
        multiplePoints.put(135, new AstarPoints(1024, 174));
        multiplePoints.put(134, new AstarPoints(1024, 115));
        multiplePoints.put(133, new AstarPoints(1024, 78));
        multiplePoints.put(132, new AstarPoints(1024, 12));
*/

        Map<String, List<String>> knowledgeBase = Main.knowledgeBase.getKnowledgeBase();
        System.out.println(knowledgeBase.toString());
        // Declare random case spawn-points
        // X


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
                    if (distance > 55 ) {
                        graphicsContext.drawImage(casesToSpawn[n], casePoints[locOfCases[n]][0],
                                casePoints[locOfCases[n]][1]);

                        // if distance of forklift and case < 30 change state that forklift is busy
                    } else if  (distance < 55 && unlockPack){
                        caseNotToSpawn = true;
                        numberOfCase = n;

                    }
                    if(casesToSpawn[numberOfCase]!= null && unlockPack) {
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
       if(actualPositionW <= 60) {
            caseNotToSpawn = false;
            actualCase = null;
        }

            //leftOrRight = false;

            graphicsContext.drawImage(forklift,actualPositionW, actualPositionH);



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

    private static void getFieldNumber() {
        int it = 0;
        for (int i = astar.pathXY.size()-1; i >=0; i--) {
            for (int j = 0; j < algorithmAvailablePoints.size(); j++) {
                if ((algorithmAvailablePoints.get(j).getX())==(astar.pathXY.get(i).getX()) && (algorithmAvailablePoints.get(j).getY())==(astar.pathXY.get(i).getY())) {
                    fieldNumber[it] = j;
                    it++;
                }

            }
        }
    }


    private void setCase() {

        mainScene.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

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

                    if(iterator == 0) {
                        System.out.print("END");
                        returnMode = false;
                    }
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
