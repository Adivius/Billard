import java.awt.*;

public class Ball {

    public final static float FRICTION = 0.995f;
    public final static float SPEED = 0.005f;
    public final Pen pen;
    public final Table table;
    public float vX = 0f, vY = 0f;
    public float size = 10f;
    public float mass = 1f;

    public Ball(Table table, int x, int y, Color color, boolean playerBall) {
        pen = new Pen(table);
        this.table = table;
        pen.setColor(color);
        pen.setThickness(2);
        pen.setFillMode(!playerBall);
        pen.moveTo(x, y);
    }

    public void draw() {
        pen.drawCircle(size);
    }

    public void delete() {
        Color temp = pen.getColor();
        pen.setColorBackground();
        pen.drawCircle(size);
        pen.setColor(temp);
    }

    public void update() {
        testEdge();
        delete();
        pen.moveTo(pen.getXPos() + vX, pen.getYPos() + vY);
        testBalls();
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
        if (pen.getXPos() >= table.getWidth() - 100 - size || pen.getXPos() <= 100 + size) {
            vX = -vX;
        }
        if (pen.getYPos() >= table.getHeight() - 100 - size || pen.getYPos() <= 100 + size) {
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
            if (distance <= this.size + ball.size) {

                float tempBallVX = ball.vX;
                float tempBallVY = ball.vY;
                float tempVX = vX;
                float tempVY = vY;

                vX = 2 * ((mass * tempVX + ball.mass * tempBallVX) / (mass + ball.mass)) - tempVX;
                vY = 2 * ((mass * tempVY + ball.mass * tempBallVY) / (mass + ball.mass)) - tempVY;


                ball.vX = 2 * ((mass * tempVX + ball.mass * tempBallVX) / (mass + ball.mass)) - tempBallVX;
                ball.vY = 2 * ((mass * tempVY + ball.mass * tempBallVY) / (mass + ball.mass)) - tempBallVY;
            }
        }
    }
}



