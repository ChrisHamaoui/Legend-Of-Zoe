/**
 * Generic class to represent a pair of elements
 */
public final class Paire<K, V> {

    private K key;
    private V value;

    public Paire(K key, V value) {

        this.key = key;
        this.value = value;
    }

    // GETTERS & SETTERS
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}