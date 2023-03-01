import java.util.ArrayList;

public class Hole {

    public static float SIZE = 12f;
    public float x, y;
    public Table table;
    Pen pen;

    public Hole(Table table, float x, float y) {
        pen = new Pen(table);
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
        ArrayList<Ball> removeBalls = new ArrayList<>();
        for (Ball ball : table.balls) {
            float disX = x - ball.pen.getXPos();
            float disY = y - ball.pen.getYPos();
            float distance = (float) Math.sqrt((disX * disX) + (disY * disY));
            if (!(distance <= SIZE + ball.size)) {
                return;
            }
            if (ball == table.getPlayerBall()) {
                table.resetPlayerBall();
            } else {
                ball.delete();
                removeBalls.add(ball);
            }

        }
        table.balls.removeAll(removeBalls);
        removeBalls.clear();
    }

}
