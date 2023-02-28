public class Main {

    public Table table;

    public Main() {
        table = new Table(600, 850, "Billard", 1);
        table.sleep(100);
        while (true) {
            table.update();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

}