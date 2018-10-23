import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable {

    private Background bg;
    private Game game;
    
    private JFrame frame;

    public GamePanel (JFrame frame) {
        this.frame = frame;
        
        game = new Game(frame);

        new Thread(this).start();
    }

    public void update () {
        game.update();
        this.frame.setTitle("life: " + game.score);
        repaint();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);

        try {
            for (Render r : game.getRenders()) {
                g.drawImage(r.image, r.x, r.y, null);
            }
        } catch(Exception e) {e.printStackTrace();}
    }
    
    public void run () {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
