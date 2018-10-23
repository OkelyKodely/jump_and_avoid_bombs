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
    
    private Background background;
    private Background background2;

    private Ball[] balls;
    private Star[] stars;
    
    private Castle castle;

    public Bird (Background background, Background background2, Ball[] balls, Star[] stars, Castle castle) {
        this.background = background;
        this.background2 = background2;
        this.balls = balls;
        this.stars = stars;
        this.castle = castle;
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
        
        if(keyboard.isDown(KeyEvent.VK_LEFT) && x > 170) {
            x -= 5;
        } else if(keyboard.isDown(KeyEvent.VK_RIGHT) && x < 250) {
            x += 5;
        } else if(keyboard.isDown(KeyEvent.VK_LEFT) && x <= 170) {
            if(background.x == 4)
                background2.x = background.x - 500;
            if(background2.x == -4) {
                background.x = 0;
                background2.x = 500;
            }
            background.moveRight();
            background2.moveRight();
            background.moveRight();
            background2.moveRight();
            castle.x += 10;
            for(int i=0; i<100; i++)
                balls[i].x += 10;
            for(int i=0; i<100; i++)
                stars[i].x += 10;
        } else if(keyboard.isDown(KeyEvent.VK_RIGHT) && x >= 250) {
            if(background.x == -4)
                background2.x = background.x + 500;
            if(background2.x == 4) {
                background.x = 0;
                background2.x = 500;
            }
            background.moveLeft();
            background2.moveLeft();
            background.moveLeft();
            background2.moveLeft();
            castle.x -= 10;
            for(int i=0; i<100; i++)
                balls[i].x -= 10;
            for(int i=0; i<100; i++)
                stars[i].x -= 10;
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
