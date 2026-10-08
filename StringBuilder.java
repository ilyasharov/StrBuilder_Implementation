import java.util.ArrayDeque;
import java.util.Deque;

// Класс, переопределяющий класс StringBuilder
public class StringBuilder {

    /**
     * Memento — снимок состояния StringBuilder.
     * Делаем его неизменяемым и приватным вложенным классом,
     * чтобы никто извне не мог испортить снимок.
     */
    private static final class Snapshot {
        private final String state;

        private Snapshot(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }

    private String value;
    private final Deque<Snapshot> history = new ArrayDeque<>();

    public StringBuilder() {
        this.value = "";
    }

    public StringBuilder(String initial) {
        this.value = initial == null ? "" : initial;
    }

    // Основные функции

    public StringBuilder append(String str) {
        saveSnapshot();
        value += (str == null ? "null" : str);
        return this;
    }

    public StringBuilder append(char c) {
        saveSnapshot();
        value += c;
        return this;
    }

    public StringBuilder insert(int index, String str) {
        checkIndexForInsert(index);
        saveSnapshot();
        value = value.substring(0, index) + (str == null ? "null" : str) + value.substring(index);
        return this;
    }

    public StringBuilder delete(int start, int end) {
        checkRange(start, end);
        saveSnapshot();
        value = value.substring(0, start) + value.substring(end);
        return this;
    }

    public StringBuilder replace(int start, int end, String str) {
        checkRange(start, end);
        saveSnapshot();
        value = value.substring(0, start) + (str == null ? "null" : str) + value.substring(end);
        return this;
    }

    public StringBuilder reverse() {
        saveSnapshot();
        value = new java.lang.StringBuilder(value).reverse().toString();
        return this;
    }

    /**
     * Функция отменяет последнюю мутирующую операцию.
     * @return true, если откат выполнен; false, если истории нет.
     */
    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }
        Snapshot snapshot = history.pop();
        this.value = snapshot.getState();
        return true;
    }

    public int length() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    @Override
    public String toString() {
        return value;
    }

    // Snapshot-инфраструктура

    private void saveSnapshot() {
        history.push(new Snapshot(value));
    }

    // Валидация

    private void checkRange(int start, int end) {
        if (start < 0 || end > value.length() || start > end) {
            throw new IndexOutOfBoundsException(
                "start=" + start + ", end=" + end + ", length=" + value.length());
        }
    }

    private void checkIndexForInsert(int index) {
        if (index < 0 || index > value.length()) {
            throw new IndexOutOfBoundsException(
                "index=" + index + ", length=" + value.length());
        }
    }
}