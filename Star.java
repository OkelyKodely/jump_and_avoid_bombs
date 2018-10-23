import java.awt.Image;
import java.awt.event.KeyEvent;

public class Star {

    public int x;
    public int y;
    public int width;
    public int height;

    private Image image;
    
    public boolean ate;

    public Star () {
        x = 500;
        y = 393 - 80 + 30 - 3 + 40;
        width = 40;
        height = 40;
    }

    public void update () {
        //x -= 5;
    }

    public Render getRender() {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/star.png");     
        }
        r.image = image;

        return r;
    }
}