import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Table extends BufferedScreen {

    public ArrayList<Ball> balls;
    public ArrayList<Hole> holes;
    public Ball playerBall;
    Pen pen = new Pen(this);

    public Table(int width, int height, String title, int count) {
        super(width, height, title);
        balls = new ArrayList<>();
        holes = new ArrayList<>();
        pen.moveTo(100, 100);
        pen.setThickness(3);
        playerBall = new Ball(this, width / 2, height / 2 + height / 4, Color.BLACK, true);
        balls.add(playerBall);
        balls.add(new Ball(this, width / 2, height / 2, Color.BLUE, false));

        holes.add(new Hole(this, 100 + Hole.SIZE, 100 + Hole.SIZE));
        holes.add(new Hole(this, 100 + Hole.SIZE, getHeight() / 2f));
        holes.add(new Hole(this, 100 + Hole.SIZE, getHeight() - 100 - Hole.SIZE));

        holes.add(new Hole(this, getWidth() - 100 - Hole.SIZE, 100 + Hole.SIZE));
        holes.add(new Hole(this, getWidth() - 100 - Hole.SIZE, getHeight() / 2f));
        holes.add(new Hole(this, getWidth() - 100 - Hole.SIZE, getHeight() - 100 - Hole.SIZE));


    }

    public Ball getPlayerBall() {
        return playerBall;
    }

    public void update() {
        pen.drawRectangle(getWidth() - 200, getHeight() - 200, (float) Math.sqrt(getWidth() * getHeight()) / 30f);
        for (Ball ball : balls) {
            ball.move();
        }
        for (Hole hole : holes) {
            hole.update();
        }
        redraw();
    }

    public void resetPlayerBall() {
        playerBall.delete();
        playerBall.pen.moveTo(getWidth() / 2f, getHeight() / 2f + getHeight() / 4f);
        playerBall.vY = 0;
        playerBall.vX = 0;
        playerBall.draw();
    }

    @Override
    public void editKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            playerBall.addVelocityToMouse();
        }
    }

    @Override
    public void editMouseMoved(MouseEvent e) {
        this.cMouseXPos = e.getX();
        this.cMouseYPos = e.getY();
    }
}