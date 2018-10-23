import java.awt.Image;
import java.awt.event.KeyEvent;

public class Castle {

    public int x;
    public int y;
    public int width;
    public int height;

    private Image image;
    
    public boolean ate;

    public Castle () {
        x = 0;
        y = 0;
        width = 300;
        height = 260;
    }

    public Render getRender() {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/castle.png");     
        }
        r.image = image;

        return r;
    }
}