package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;


public class Main extends Application {

    static int WIDTH = 1800;
    static int HEIGHT = 800;
    static Scene mainScene;
    static GraphicsContext graphicsContext;
    static HashSet<String> currentlyActiveKeys = new HashSet<String>();
    static  Image forklift;
    static  Image forklift2;
    static  Image background;
    static  Image conveyor;
    static Image cover;
    static int actualPositionH = 500;
    static int actualPositionW = 100;
    static int conveyorPos = 0;
    @Override
    public void start(Stage mainStage) throws Exception {

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
    }

    private static void prepareActionHandlers()
    {
        mainScene.setOnKeyPressed(event -> currentlyActiveKeys.add(event.getCode().toString()));
        mainScene.setOnKeyReleased(event -> currentlyActiveKeys.remove(event.getCode().toString()));
    }

    private static void loadGraphics()
    {
        forklift = new Image("forklift.png");
        forklift2 = new Image("forklift2.png");
        background = new Image("background.png");
        conveyor = new Image("conveyor.png");
        cover = new Image("cover.png");
    }

    private static void tickAndRender()
    {
        // clear canvas
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        graphicsContext.drawImage(background, 0, 0);


        if (currentlyActiveKeys.contains("LEFT"))
        {
            actualPositionW = actualPositionW - 2;
            graphicsContext.drawImage(forklift2, actualPositionW, actualPositionH);
        }
        else if (currentlyActiveKeys.contains("RIGHT"))
        {
            actualPositionW = actualPositionW + 2;
            graphicsContext.drawImage(forklift, actualPositionW , actualPositionH);
        }
        else if (currentlyActiveKeys.contains("DOWN")) {
            actualPositionH = actualPositionH + 2;
            graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
        }
        else if (currentlyActiveKeys.contains("UP")) {
            actualPositionH = actualPositionH - 2;
            graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
       }
        else {
            graphicsContext.drawImage(forklift, actualPositionW, actualPositionH);
        }
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
        conveyorPos = conveyorPos + 1;
        graphicsContext.drawImage(cover, 5, 618 );
        if(conveyorPos >= 600) {
            conveyorPos = 0;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}
