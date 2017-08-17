
package Data;

public class Record<T> {
    public int id;
    public T record;
    public Record(int id, T record) {
        this.id = id;
        this.record = record;
    }
}
