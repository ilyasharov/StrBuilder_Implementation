import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println("Начальное состояние: '" + sb + "'");

        sb.append("Hello");
        System.out.println("append(\"Hello\"):    '" + sb + "'");

        sb.append(", World");
        System.out.println("append(\", World\"):   '" + sb + "'");

        sb.insert(5, " dear");
        System.out.println("insert(5, \" dear\"): '" + sb + "'");

        sb.replace(0, 5, "Hi");
        System.out.println("replace(0,5,\"Hi\"):  '" + sb + "'");

        sb.delete(2, 7);
        System.out.println("delete(2, 7):        '" + sb + "'");

        System.out.println("\n--- undo по шагам ---");
        while (sb.undo()) {
            System.out.println("undo -> '" + sb + "'");
        }

        System.out.println("\nЕщё undo (история пуста): " + sb.undo());
        System.out.println("Итог: '" + sb + "'");
    }
}