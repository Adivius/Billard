import java.awt.*;

public class Ball {
    public final static float SIZE = 10f;
    public final static float FRICTION = 0.995f;
    public final static float SPEED = 0.01f;
    public final Pen pen;
    public final Table table;
    public float vX = 0f, vY = 0f;

    public Ball(Table table, int x, int y, Color color, boolean playerBall) {
        pen = new Pen(table);
        this.table = table;
        pen.setColor(color);
        pen.setThickness(2);
        pen.setFillMode(!playerBall);
        pen.moveTo(x, y);
    }

    public void draw() {
        pen.drawCircle(SIZE);
    }

    public void delete() {
        Color temp = pen.getColor();
        pen.setColorBackground();
        pen.drawCircle(SIZE);
        pen.setColor(temp);
    }

    public void move() {
        testEdge();
        testBalls();
        delete();
        pen.moveTo(pen.getXPos() + vX, pen.getYPos() + vY);
        vX *= FRICTION;
        vY *= FRICTION;
        draw();
    }

    public void addVelocity(float x, float y) {

        vX += x > 20 ? 20 : x;
        vY += y > 20 ? 20 : y;
    }

    public void addVelocityToMouse() {
        float v1 = table.cMouseXPos - pen.getXPos();
        float v2 = table.cMouseYPos - pen.getYPos();

        addVelocity(v1 * SPEED, v2 * SPEED);
    }

    private void testEdge() {
        if (pen.getXPos() >= table.getWidth() - 100 - SIZE || pen.getXPos() <= 100 + SIZE) {
            vX = -vX;
        }
        if (pen.getYPos() >= table.getHeight() - 100 - SIZE || pen.getYPos() <= 100 + SIZE) {
            vY = -vY;
        }
    }


    private void testBalls() {
        for (Ball ball : table.balls) {
            if (ball == this) {
                continue;
            }
            float disX = pen.getXPos() - ball.pen.getXPos();
            float disY = pen.getYPos() - ball.pen.getYPos();
            float distance = (float) Math.sqrt((disX * disX) + (disY * disY));
            if (distance <= SIZE + SIZE) {
                vX = (vX + ball.vX) * FRICTION;
                vY = (vY + ball.vY) * FRICTION;
            }

        }
    }
}



