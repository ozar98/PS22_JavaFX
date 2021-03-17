package PS22_10;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.security.SecureRandom;

public class AnimatedArtController {
    @FXML
    Pane pane;
    private SecureRandom random = new SecureRandom();
    private int n;
    private int[] dx;
    private int[] dy;
    private int[] dz;
    public void initialize() {
        n = random.nextInt(50) + 10;
        dx = new int[n];
        dy = new int[n];
        dz= new int[n];
        for (int i = 0; i < n; i++) {
            Sphere box = new Sphere();
            box.setRadius(random.nextInt(500) + 201);
            box.setTranslateX(random.nextInt(300) + 201);
            box.setTranslateY(random.nextInt(100));
            //box.setCullFace(CullFace.BACK);


            pane.getChildren().add(box);
            dx[i] = 1 + random.nextInt(5);
            dy[i] = 1 + random.nextInt(5);
            dz[i] = 1 + random.nextInt(5);

        }
        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> moveBox())
        );
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }

    private void moveBox() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Sphere b = (Sphere) pane.getChildren().get(i);
            b.setRadius(b.getRadius() + dx[i]);
            b.setTranslateX(b.getTranslateX() + dy[i]);
            b.setTranslateY(b.getTranslateY()+dz[i]);
            if (b.getRadius()  > pane.getWidth() || b.getRadius()  < 0) dx[i] = -dx[i];
            if (b.getRadius()  > pane.getHeight() || b.getRadius()  < 0) dy[i] = -dy[i];
            if (b.getRadius()  > pane.getHeight() || b.getRadius()  < 0) dz[i] = -dz[i];
        }
    }
    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(101) / 100);
    }
}
