import java.awt.Image;
import java.awt.event.KeyEvent;

public class Ball {

    public int x;
    public int y;
    public int width;
    public int height;

    private Image image;

    public Ball () {
        x = 500;
        y = 393 - 80 + 30 - 3;
        width = 59;
        height = 80;
    }

    public void update () {
        x -= 5;
    }

    public Render getRender() {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/ball.png");     
        }
        r.image = image;

        return r;
    }
}