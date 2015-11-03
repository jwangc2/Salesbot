package Salesbot;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class SaleSim extends Application {

    int grid_size = 32;
    int grid_w = 12;
    int grid_h = 10;

    double canvas_w = (grid_w + 2) * grid_size;
    double canvas_h = (grid_h + 2) * grid_size;

    double xoff = canvas_w / 2;
    double yoff = canvas_h / 2;

    public static void main(String[] args) {
        launch(args);
    }

    Random random = new Random(1337);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Travelling Salesman");
        Group root = new Group();
        Canvas canvas = new Canvas(canvas_w, canvas_h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawPath(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawPath(GraphicsContext gc) {
        // Set up the neighborhood
        Neighborhood n = new Neighborhood();

        for (int i = 0; i < n.getNumHouses(); i ++) {
            int xpos = random.nextInt(grid_w) - (grid_w / 2);
            int ypos = random.nextInt(grid_h) - (grid_h / 2);
            House h = new House(xpos, ypos);
            n.setHouseAt(i, h);
        }

        SalesbotSimple s = new SalesbotSimple(n);
        House[] path = s.findPath();

        // Draw the grid
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
   
        for (int c = 1; c < grid_w / 2; c ++) {
            gc.strokeLine(gridToCanvasX(c), 0, gridToCanvasX(c), canvas_h);
            gc.strokeLine(gridToCanvasX(-c), 0, gridToCanvasX(-c), canvas_h);
        }
        gc.strokeLine(gridToCanvasX(0), 0.0, gridToCanvasX(0), canvas_h);
        
        for (int r = 1; r < grid_h / 2; r ++) {
            gc.strokeLine(0.0, gridToCanvasY(r), canvas_w, gridToCanvasY(r));
            gc.strokeLine(0.0, gridToCanvasY(-r), canvas_w, gridToCanvasY(-r));
        }
        gc.strokeLine(0.0, gridToCanvasY(0), canvas_w, gridToCanvasY(0));

        // Draw the start position
        drawCircle(gc, s.getPosition(), grid_size / 4, Color.RED);

        // Draw the path
        gc.setStroke(Color.BLUE);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineWidth(3);
    
        int i;
        int hrad = grid_size / 8;
        for (i = 1; i < s.getHood().getNumHouses(); i ++) {
            // Control house radius
            if (i % 2 == 0)
                hrad += 2;

            // Draw the house first
            drawHouse(gc, path[i - 1], hrad);

            // Then draw a line from that house to the house after it
            Vector2 posA = path[i - 1].getPosition();
            Vector2 posB = path[i].getPosition();
            gc.strokeLine(gridToCanvasX(posA.x), gridToCanvasY(posA.y), gridToCanvasX(posB.x), gridToCanvasY(posB.y));
        }
        // Also don't forget the last house
        drawHouse(gc, path[i - 1], hrad);
    }

    private void drawHouse(GraphicsContext gc, House h, int rad) {
        Vector2 pos = h.getPosition();
        drawCircle(gc, pos, rad, Color.GREEN);
    }

    private void drawCircle(GraphicsContext gc, Vector2 pos, int rad, Color col) {
        gc.setFill(col);
        gc.fillOval(gridToCanvasX(pos.x) - rad, gridToCanvasY(pos.y) - rad, rad * 2, rad * 2);
    }

    private double gridToCanvasX(int coord) {
        return ( xoff + (coord * grid_size) );
    }

    private double gridToCanvasY(int coord) {
        return ( yoff + (coord * grid_size) );
    }
}
