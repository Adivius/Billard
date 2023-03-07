public class Main {

    public Table table;

    public Main() {
        table = new Table(600, 800, "Billard");
        while (!table.finished) {
            table.update();
        }
        table.exit();
        table.sleep(3000);
        System.exit(0);

    }

    public static void main(String[] args) {
        Main main = new Main();
    }

}