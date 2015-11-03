package Salesbot;

import java.util.Random;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    Salesbot[] salesmen;
    int numSalesmen = 3;

    public static void main(String[] args) {
        launch(args);
    }

    Random random;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Travelling Salesman");
        Group root = new Group();
        Canvas canvas = new Canvas(canvas_w, canvas_h);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawSim(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawSim(GraphicsContext gc) {
        List<String> params = this.getParameters().getRaw();

        int seed = 1337;
        if (params.size() >= 1)
            seed = Integer.parseInt(params.get(0));
        random = new Random(seed);

        // Set up the neighborhood
        Neighborhood n = new Neighborhood();

        for (int i = 0; i < n.getNumHouses(); i ++) {
            int xpos = random.nextInt(grid_w) - (grid_w / 2);
            int ypos = random.nextInt(grid_h) - (grid_h / 2);
            House h = new House(xpos, ypos);
            n.setHouseAt(i, h);
        }

        // Draw the grid
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
   
        for (int c = 1; c <= grid_w / 2; c ++) {
            gc.strokeLine(gridToCanvasX(c), 0, gridToCanvasX(c), canvas_h);
            gc.strokeLine(gridToCanvasX(-c), 0, gridToCanvasX(-c), canvas_h);
        }
        gc.strokeLine(gridToCanvasX(0), 0.0, gridToCanvasX(0), canvas_h);
        
        for (int r = 1; r <= grid_h / 2; r ++) {
            gc.strokeLine(0.0, gridToCanvasY(r), canvas_w, gridToCanvasY(r));
            gc.strokeLine(0.0, gridToCanvasY(-r), canvas_w, gridToCanvasY(-r));
        }
        gc.strokeLine(0.0, gridToCanvasY(0), canvas_w, gridToCanvasY(0));

        // Determine the salesman to use
        Color[] colors = { Color.BLUE, Color.ORANGE, Color.PURPLE };
        Salesbot[] salesmen = { new SalesbotSimple(n),
                                new SalesbotVert(n),
                                new SalesbotGreedy(n) };

        // Parse from the launch args
        int sID = 0;
        if (params.size() >= 2)
            sID = Integer.parseInt(params.get(1)) % numSalesmen;

        // Draw either all, or one
        if (sID < 0) {  
            for (int i = 0; i < numSalesmen; i ++) {
                drawPath(gc, salesmen[i], colors[i]);
                System.out.println();
            }
        } else {
            drawPath(gc, salesmen[sID], colors[sID]);
            System.out.println();
        }
    }

    private void drawPath(GraphicsContext gc, Salesbot s, Color pathCol) {
        House[] path = s.findPath();
        System.out.println("Using: " + s);

        // Draw the start position
        drawCircle(gc, s.getPosition(), grid_size / 4, Color.RED);

        // Draw the path
        gc.setStroke(pathCol);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineWidth(3);
    
        int i;
        int hrad = grid_size / 8;
        drawLine(gc, s.getPosition(), path[0].getPosition());
        for (i = 1; i < s.getHood().getNumHouses(); i ++) {
            // Control house radius
            if (i % 2 == 0)
                hrad += 2;

            // Draw the house first
            drawHouse(gc, path[i - 1], hrad, pathCol);

            // Then draw a line from that house to the house after it
            drawLine(gc, path[i - 1].getPosition(), path[i].getPosition());
        }
        // Also don't forget the last house
        drawHouse(gc, path[i - 1], hrad, pathCol);

        // Determine length of the path
        gc.setLineWidth(1);
        gc.setStroke(Color.GRAY);
        System.out.println("Total Distance: " + calcTotalDistance(s.getPosition(), path, s.getHood().getNumHouses()));
    }

    private void drawHouse(GraphicsContext gc, House h, int rad, Color col) {
        Vector2 pos = h.getPosition();
        drawCircle(gc, pos, rad, col);
    }

    private void drawCircle(GraphicsContext gc, Vector2 pos, int rad, Color col) {
        Paint p = gc.getStroke();
        gc.setStroke(col);
        gc.strokeOval(gridToCanvasX(pos.x) - rad, gridToCanvasY(pos.y) - rad, rad * 2, rad * 2);
        gc.setStroke(p);
    }

    private void drawLine(GraphicsContext gc, Vector2 posA, Vector2 posB) {
        gc.strokeLine(gridToCanvasX(posA.x), gridToCanvasY(posA.y), gridToCanvasX(posB.x), gridToCanvasY(posB.y));
    }

    private double gridToCanvasX(int coord) {
        return ( xoff + (coord * grid_size) );
    }

    private double gridToCanvasY(int coord) {
        return ( yoff + (coord * grid_size) );
    }

    private double calcTotalDistance(Vector2 start, House[] path, int len) {
        double total = Math.sqrt(Vector2.distanceSq(start, path[0].getPosition()));
        for (int i = 1; i < len; i ++) {
            total += Math.sqrt(Vector2.distanceSq(path[i - 1].getPosition(), path[i].getPosition()));
        }
        return total;
    }
}
