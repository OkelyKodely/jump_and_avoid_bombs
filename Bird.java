import java.awt.Image;
import java.awt.event.KeyEvent;

public class Bird {

    public int x;
    public int y;
    public int width;
    public int height;

    // y velocity
    public double yvel;
    public double gravity;

    // delay between key presses
    private int jumpDelay;

    private Image image;
    private Keyboard keyboard;

    public Bird () {
        x = 100;
        y = 388;
        yvel = 0;
        width = 45;
        height = 32;
        gravity = 0.5;
        jumpDelay = 0;

        keyboard = Keyboard.getInstance();
    }

    public void update () {
        yvel += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (y == 388 && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            yvel = -10;
            jumpDelay = 10;
        }
        
        if(keyboard.isDown(KeyEvent.VK_LEFT)) {
            x -= 5;
        } else if(keyboard.isDown(KeyEvent.VK_RIGHT)) {
            x += 5;
        }

        y += (int)yvel;
    }

    public Render getRender() {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("lib/bird.png");     
        }
        r.image = image;

        return r;
    }
}
