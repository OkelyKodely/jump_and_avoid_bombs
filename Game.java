import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {

    public int score;
    
    public Keyboard keyboard;
    public Bird bird;
    public Background background;
    public Ball[] balls;

    public Boolean paused;
    public Boolean gameover;
    public Boolean started;

    public int pauseDelay;
    public int restartDelay;
    
    public JFrame frame;

    public Game (JFrame frame) {
        keyboard = Keyboard.getInstance();
        background = new Background();
        
        balls = new Ball[100];

        this.frame = frame;
        
        initBalls();
        
        restart();
    }

    public void update () {

        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }

        if (!started)
            return;

        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }

        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
        }

        if (!paused && !gameover) {

            bird.update();
                
            score = 0;
                
            for(int i=0; i<100; i++) {
                balls[i].update();
                
                if(bird.x >= balls[i].x && bird.x <= balls[i].x + 49 && bird.y >= 386) {
                    gameover = true;
                }
                
                if(balls[i].x < 0)
                    score++;
            }
            
            if(gameover) {
                JOptionPane.showMessageDialog(frame, "Game Over");
                restart();
            }

            if(score == 100) {
                gameover = true;
                JOptionPane.showMessageDialog(frame, "You passed all of the bombs!  You win.");
                restart();
            }
            
            if (bird.y + bird.height > App.HEIGHT - 80) {
                
                gameover = false;
                
                bird.y = 388;

                // keep the bird above ground
                bird.y = App.HEIGHT - 80 - bird.height;
            }
        }
    }

    public void restart () {
        bird = new Bird();

        paused = false;
        gameover = false;
        started = true;

        pauseDelay = 0;
        restartDelay = 0;

        initBalls();
    }
    
    private void initBalls() {
        Random random = new Random();
        for(int i=0; i<100; i++) {
            balls[i] = new Ball();
            balls[i].x = 500 + i*100 + random.nextInt(30000);
        }
    }
    
    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(background.getRender());
        renders.add(bird.getRender());
        for(int i=0; i<100; i++)
            renders.add(balls[i].getRender());
        return renders;
    }
}
