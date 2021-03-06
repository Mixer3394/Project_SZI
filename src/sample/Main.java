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

    static Image currentCase;
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

    static int locOfCases[] = new int[20];

    static List<Map<String, Object>> casesPlace = new ArrayList<Map<String, Object>>();

    static int movingTicks = 3;

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
        areasData.put("blue", blueAreaCoordinates);

        //TODO complete it

        List<int[]> greenAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 3;
        point[0] = 12;
        greenAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 11;
        greenAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 10;
        greenAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 9;
        greenAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 8;
        greenAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 3;
        point[0] = 7;
        greenAreaCoordinates.add(point);
        areasData.put("green", greenAreaCoordinates);


        List<int[]> blackAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 8;
        point[0] = 12;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 11;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 10;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 9;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 8;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 7;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 6;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 5;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 4;
        blackAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 8;
        point[0] = 3;
        blackAreaCoordinates.add(point);

        areasData.put("black", blackAreaCoordinates);

        List<int[]> brownAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 11;
        point[0] = 12;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 11;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 10;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 9;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 8;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 7;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 6;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 5;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 4;
        brownAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 11;
        point[0] = 3;
        brownAreaCoordinates.add(point);
        areasData.put("brown", brownAreaCoordinates);

        List<int[]> yellowAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 12;
        point[0] = 3;
        yellowAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 4;
        yellowAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 5;
        yellowAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 6;
        yellowAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 7;
        yellowAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 8;
        yellowAreaCoordinates.add(point);
        areasData.put("yellow", yellowAreaCoordinates);

        List<int[]> redAreaCoordinates = new ArrayList<>();
        point = new int[2];
        point[1] = 12;
        point[0] = 12;
        redAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 11;
        redAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 10;
        redAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 9;
        redAreaCoordinates.add(point);
        point = new int[2];
        point[1] = 12;
        point[0] = 8;
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

        graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
        if (currentCase != null) {
            graphicsContext.drawImage(currentCase, actualPositionW, actualPositionH);
        }

        casesPlace.forEach(caseInfo -> {
            double caseW = (double)caseInfo.get("caseW");
            double caseH = (double)caseInfo.get("caseH");
            Image image = (Image)caseInfo.get("caseImage");

            graphicsContext.drawImage(image, caseW, caseH);
        });

    }

    private static double parseToRealPoint(double point) {
        return point*36;
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

//            getFieldNumber();
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
        System.out.println("Case base: " + Start.casesBase.getCasesBase());
        mainPool.execute(() -> {

            int[] destinationXY = findPlace();
            getFieldNumber();

            Astar.test(16, 16, 0, 0, destinationXY[0], destinationXY[1], astarBlockedPoints);

            while (iterator < astar.pathXY.size() - 1) {
                handleGoingWithPackage();
            }
            returnMode = true;
            unlockPack = true;


            HashMap<String, Object> currentCaseInfo = new HashMap<>();
            currentCaseInfo.put("caseW", actualPositionW);
            currentCaseInfo.put("caseH", actualPositionH);
            currentCaseInfo.put("caseImage", currentCase);

            casesPlace.add(currentCaseInfo);

            //So case is not rendered on forklift when it's going back
            currentCase = null;

            while (iterator > 0 && returnMode) {
                handleReturning();
            }
            System.out.print("END\n");
            Start.getResults();

            returnMode = false;
        });
    }

    private List<String> parseProperties(List<String> currentProperties) {
        List<String> result = new ArrayList<>();
        List<String> colors = new ArrayList<>(Arrays.asList("gray", "brown", "white", "red", "black", "blue", "yellow"));
        String color = currentProperties.stream().filter(colors::contains).findFirst().orElse("0");
        List<String> materials = new ArrayList<>(Arrays.asList("metal", "wooden", "transparent", "labelled", "paper"));
        String material = currentProperties.stream().filter(materials::contains).findFirst().orElse("0");
        List<String> weights = new ArrayList<>(Arrays.asList("heavy", "light", "middleweight"));
        String weight = currentProperties.stream().filter(weights::contains).findFirst().orElse("0");
        List<String> states = new ArrayList<>(Arrays.asList("solid", "liquid"));
        String state = currentProperties.stream().filter(states::contains).findFirst().orElse("0");
        result.addAll(Arrays.asList(color, material, weight, state));
        return result;
    }

    private int[] findPlace() {
        String caseName = getRandomCase();
        return learningStrategy.findDestinationPlace(knowledgeBase, caseName, areasData);
    }


    private String getRandomCase() {
        List<String> casesNames = new ArrayList<>(Arrays.asList("car parts", "wood", "paper", "explosives", "chemicals", "water", "oil", "glass"));
        int random = new Random().nextInt(casesNames.size());

        String randomCase = casesNames.get(random);
        System.out.println(randomCase);
        currentCase = new Image("images/cases/" + randomCase + ".png");

        return randomCase;
    }

    private int[] findPlace(List<String> properties) {
        return learningStrategy.findDestinationPlace(properties, areasData);
    }

    private void handleGoingWithPackage() {
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
