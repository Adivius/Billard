import java.awt.*;
import java.util.ArrayList;

public class Hole {

    public static float SIZE = 10f;
    public float x, y;
    public Table table;
    Pen pen;

    public Hole(Table table, float x, float y) {
        pen = new Pen(table);
        pen.setColor(Color.DARK_GRAY);
        this.table = table;
        pen.moveTo(x, y);
        pen.setFillMode(true);
        this.x = x;
        this.y = y;
    }

    public void update() {
        testBalls();
        pen.drawCircle(SIZE);
    }

    private void testBalls() {
        ArrayList<Ball> removeList = new ArrayList<>();
        for (Ball ball : table.balls) {
            float disX = x - ball.pen.getXPos();
            float disY = y - ball.pen.getYPos();
            float distance = (float) Math.sqrt((disX * disX) + (disY * disY));
            if (!(distance <= SIZE + ball.size)) {
                continue;
            }

            if (ball == table.getPlayerBall()) {
                table.resetPlayerBall();
            } else {
                removeList.add(ball);
                ball.delete();
            }

        }
        table.balls.removeAll(removeList);
        removeList.clear();
    }
}
