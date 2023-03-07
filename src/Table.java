import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Table extends EventScreen {

    public ArrayList<Ball> balls;
    public ArrayList<Hole> holes;
    public Ball playerBall;
    public boolean finished = false;
    Pen pen = new Pen(this);

    public Table(int width, int height, String title) {
        super(width, height, title);
        balls = new ArrayList<>();
        holes = new ArrayList<>();
        pen.moveTo(100, 100);
        pen.setThickness(3);
        playerBall = new Ball(this, width / 2f, height / 2f + height / 4f, Color.BLACK, true);
        playerBall.mass = 1.2f;
        balls.add(playerBall);

        int radius = 12;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                float pX = width / 2f + 10 + j * radius * 2.5f - i * radius * 1.25f;
                float pY = height / 2f - i * radius * 2.5f;
                balls.add(new Ball(this, pX, pY, Color.BLACK, false));
            }
        }

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
            ball.update();
        }
        for (Hole hole : holes) {
            hole.update();
        }
        redraw();

        if (balls.size() <= 1) {
            finished = true;
        }
    }

    public void exit() {
        pen.moveTo(getWidth() / 2f, getHeight() / 2f);
        pen.write("Finished");
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                playerBall.addVelocityToMouse();
                break;
            case KeyEvent.VK_1:
                playerBall.mass = 1f;
                break;
            case KeyEvent.VK_2:
                playerBall.mass = 2f;
                break;
            case KeyEvent.VK_3:
                playerBall.mass = 0.5f;
                break;
        }
    }

    @Override
    public void editMouseMoved(MouseEvent e) {
        this.cMouseXPos = e.getX();
        this.cMouseYPos = e.getY();
    }

    @Override
    public void editMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            resetPlayerBall();
        }
    }
}