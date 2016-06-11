package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
import sample.Genetic.Start;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Main extends Application {
    static ExecutorService mainPool = Executors.newFixedThreadPool(1);
    static ExecutorService pool = Executors.newFixedThreadPool(1);
    static int WIDTH = 1800;
    static int HEIGHT = 800;
    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static HashSet<String> currentlyActiveKeys = new HashSet<String>();
    static Map<Integer, AstarPoints> algorithmAvailablePoints = new HashMap<Integer, AstarPoints>();

    static int[] tempArray = new int[20];

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

    // Oil
    static Image oilSlick;

    // Randoms for cases-spawns
    static Random caseSpawn = new Random();
    static Random caseNumber = new Random();
    static Image casesToSpawn[] = new Image[20];

    // Arrays for oil spawns
    static int oilArray[] = new int[10];
    static int oilsToDraw[] = new int[10];
    static int oilsCoordinates[][] = new int[10][2];

    // Random for algorithm points
    static Random randPoints = new Random();

    // actual case on forklift
    static Image actualCase;
    static int locOfCases[] = new int[20];

    static int movingTicks = 3;

    // state if forklift is busy
    static Boolean caseNotToSpawn = false;

    // number of case that is on forklift
    static int numberOfCase;

    static double actualPositionH = 0;
    static double actualPositionW = 110;
    static double conveyorPos = 0.0;

    static LearningStrategy learningStrategy;

    // True if right, false if left
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
            {11, 14}, {12, 14}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}, {15, 15}
    };

    static int[][] pointsForOil = {
            {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11}, {0, 12},
            {0, 13}, {0, 14}, {0, 15}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, {1, 9}, {1, 10}, {1, 11}, {1, 12},
            {1, 13}, {1, 14}, {1, 15}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9}, {2, 10}, {2, 11}, {2, 12},
            {2, 13}, {2, 14}, {2, 15}, {14, 0}, {14, 1}, {14, 2}, {14, 3}, {14, 4}, {14, 5}, {14, 6}, {14, 7}, {14, 8}, {14, 9}, {14, 10}, {14, 11}, {14, 12},
            {14, 13}, {14, 14}, {14, 15}, {15, 0}, {15, 1}, {15, 2}, {15, 3}, {15, 4}, {15, 5}, {15, 6}, {15, 7}, {15, 8}, {15, 9}, {15, 10}, {15, 11}, {15, 12},
            {15, 13}, {15, 14}, {15, 15},


    };
    public int iterator = 0;

    private static Map<String, List<int[]>> areasData = new HashMap<>();

    private static void prepareAreasData() {
        int[] point = new int[2];
        List<int[]> blueAreaCoordinates = new ArrayList<>();
        point[1] = 0;
        point[0] = 3;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 4;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 5;
        blueAreaCoordinates.add(point);
        point[1] = 0;
        point[0] = 6;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point = new int[2];
        point[1] = 0;
        point[0] = 7;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 8;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 9;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 10;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 11;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 12;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 13;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 14;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 0;
        point[0] = 15;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 3;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 4;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 5;
        blueAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 6;
        blueAreaCoordinates.add(point);
        areasData.put("blue", blueAreaCoordinates);

        //TODO complete it

        List<int[]> greenAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 3;
        point[0] = 7;
        greenAreaCoordinates.add(point);
        areasData.put("green", greenAreaCoordinates);

        List<int[]> blackAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 4;
        point[0] = 17;
        blackAreaCoordinates.add(point);
        areasData.put("black", blackAreaCoordinates);

        List<int[]> brownAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 8;
        point[0] = 17;
        brownAreaCoordinates.add(point);
        areasData.put("brown", brownAreaCoordinates);

        List<int[]> yellowAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 12;
        point[0] = 3;
        yellowAreaCoordinates.add(point);
        areasData.put("yellow", yellowAreaCoordinates);

        List<int[]> redAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 12;
        point[0] = 17;
        redAreaCoordinates.add(point);
        areasData.put("red", redAreaCoordinates);
        System.out.println("areas data ready");
    }

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
        oilSlick = new Image("images/oil.png");

    }

    private static void tickAndRender() {
        // clear canvas
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        graphicsContext.drawImage(background, 0, 0);


        // ZAMIANA NA WSPÓŁRZĘDNE
        for (int i = 0; i < oilArray.length; i++) {
            graphicsContext.drawImage(oilSlick, oilsCoordinates[i][0] + 5, oilsCoordinates[i][1] + 25);
        }


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


        graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);

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

    // Get coordinates of oil slicks

    private static void getOilSlickNumber() {

        for (int i = 0; i < oilArray.length; i++) {
            for (int j = 0; j < algorithmAvailablePoints.size(); j++) {
                if ((algorithmAvailablePoints.get(j).getX() == astarBlockedPoints[80 + i][0]) &&
                        algorithmAvailablePoints.get(j).getY() == astarBlockedPoints[80 + i][1]) {

                    oilsToDraw[i] = j;
                }
            }
        }
    }

    private static void convertOilNumberToCoordinates() {
        for (int i = 0; i < oilsToDraw.length; i++) {
//            System.out.print("X: " + multiplePoints.get(oilsToDraw[i]).getX() + " Y: " + multiplePoints.get(oilsToDraw[i]).getY() + "\n");
            oilsCoordinates[i][0] = multiplePoints.get(oilsToDraw[i]).getX();
            oilsCoordinates[i][1] = multiplePoints.get(oilsToDraw[i]).getY();
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
        learningStrategy = new CandidateEleminationLearningStrategy();
        Start geneticAlgotithm = new Start();

        // scenario for oil
        oilArray[0] = 0;
        astarBlockedPoints[81] = pointsForOil[0];
        oilArray[1] = 15;
        astarBlockedPoints[82] = pointsForOil[15];
        oilArray[2] = 30;
        astarBlockedPoints[83] = pointsForOil[30];
        oilArray[3] = 32;
        astarBlockedPoints[84] = pointsForOil[32];
        oilArray[4] = 34;
        astarBlockedPoints[85] = pointsForOil[34];
        oilArray[5] = 36;
        astarBlockedPoints[86] = pointsForOil[36];
        oilArray[6] = 37;
        astarBlockedPoints[87] = pointsForOil[37];
        oilArray[7] = 17;
        astarBlockedPoints[88] = pointsForOil[17];
        astarBlockedPoints[89] = pointsForOil[21];
        oilArray[9] = 7;
        astarBlockedPoints[80] = pointsForOil[7];


        //random
//        astar.test(16, 16, 0, 0, casesCoordinates[randCasePoint][0], casesCoordinates[randCasePoint][1], astarBlockedPoints);

        //scenario 1
        //  astar.test(16,16,0,0,15,15,astarBlockedPoints);

        //scenrio 2
//        astar.test(16,16,0,0,12,8,astarBlockedPoints);

        //scenario 3
//        astar.test(16,16,0,0,4,15,astarBlockedPoints);

        //scenario 4 not possible
        //  astar.test(16,16,0,0,3,2,astarBlockedPoints);


        prepareKnowledgeBase();
        prepareAreasData();

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

        prepareMultiplePoints();

        getOilSlickNumber();
        convertOilNumberToCoordinates();

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
//            astar.test(16,16,0,0,15,15,astarBlockedPoints);

            /**
             * Main.java "game" loop
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

    private void prepareMultiplePoints() {
        AstarPoints.prepareMultiplePoints(multiplePoints);
    }

    private void prepareKnowledgeBase() {
        knowledgeBase = new KnowledgeBase();
        knowledgeBase.addData("car parts", "gray");
        knowledgeBase.addData("car parts", "metal");
        knowledgeBase.addData("car parts", "heavy");
        knowledgeBase.addData("car parts", "solid");
        knowledgeBase.addData("wood", "brown");
        knowledgeBase.addData("wood", "wooden");
        knowledgeBase.addData("wood", "heavy");
        knowledgeBase.addData("wood", "solid");
        knowledgeBase.addData("paper", "white");
        knowledgeBase.addData("paper", "paper");
        knowledgeBase.addData("paper", "light");
        knowledgeBase.addData("paper", "solid");
        knowledgeBase.addData("explosives", "red");
        knowledgeBase.addData("explosives", "labelled");
        knowledgeBase.addData("explosives", "middleweight");
        knowledgeBase.addData("explosives", "solid");
        knowledgeBase.addData("chemicals", "black");
        knowledgeBase.addData("chemicals", "labelled");
        knowledgeBase.addData("chemicals", "middleweight");
        knowledgeBase.addData("chemicals", "liquid");
        knowledgeBase.addData("water", "blue");
        knowledgeBase.addData("water", "metal");
        knowledgeBase.addData("water", "heavy");
        knowledgeBase.addData("water", "liquid");
        knowledgeBase.addData("oil", "yellow");
        knowledgeBase.addData("oil", "metal");
        knowledgeBase.addData("oil", "middleweight");
        knowledgeBase.addData("oil", "liquid");
        knowledgeBase.addData("glass", "blue");
        knowledgeBase.addData("glass", "transparent");
        knowledgeBase.addData("glass", "light");
        knowledgeBase.addData("glass", "solid");
    }

    private void setCase() {
        mainScene.addEventHandler(MouseEvent.MOUSE_RELEASED,
                mouseEvent -> mouseClicked());

    }

    private void mouseClicked() {
        System.out.print(Start.casesBase.getCasesBase());
        mainPool.execute(() -> {
            int[] destinationXY = findPlace();
            astar.test(16, 16, 0, 0, destinationXY[0], destinationXY[1], astarBlockedPoints);
            getFieldNumber();

            while (iterator < astar.pathXY.size() - 1) {
                handleGoingForPackage();
            }
            returnMode = true;
            unlockPack = true;

            while (iterator > 0 && returnMode) {
                handleReturning();
            }
            if (iterator == 0) {
                System.out.print("END\n");
                Start.getResults();

                returnMode = false;
            }
        });
    }

    private void getRandomCase() {
        int random = new Random().nextInt(7);
//        if (random == 0)
//
    }


    private int[] findPlace() {
//        int[] result = new int[2];
//        result[0] = 15;
//        result[1] = 15;
//        return result;
//        String caseName = "glass";
        String caseName = "water";
//        String caseName = "explosives";
//        String caseName = "oil";
//        String caseName = "wood";
        return learningStrategy.findDestinationPlace(knowledgeBase, caseName, areasData);
    }

    private void handleGoingForPackage() {
        iterator++;
        move();
    }

    private void move() {
        double xIterator = calculateXIterator();
        double yIterator = calculateYIterator();
        Runnable runnable = prepareRunableForMovingSlowly(xIterator, yIterator);
        pool.execute(runnable);

        waitUntilRunThreadFinishes(100 * movingTicks);
    }

    private void waitUntilRunThreadFinishes(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double calculateXIterator() {
        return (multiplePoints.get(fieldNumber[iterator]).getX() - actualPositionW) / movingTicks;
    }

    private double calculateYIterator() {
        return (multiplePoints.get(fieldNumber[iterator]).getY() - actualPositionH) / movingTicks;
    }

    private Runnable prepareRunableForMovingSlowly(double xIterator, double yIterator) {
        return () -> {
            for (int i = 0; i < movingTicks; i++) {
                actualPositionW += xIterator;
                actualPositionH += yIterator;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void handleReturning() {
        if (iterator >= 0 && returnMode) {
            iterator--;
            move();
        }
    }

    public static boolean contains(int[] arr, int targetValue) {
        for (int s : arr) {
            if (s == targetValue) return true;
        }
        return false;
    }
}
