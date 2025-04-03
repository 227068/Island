package
import model.Island;
import model.Location;

public abstract class Animal {

    public abstract String getName();  // getter for the name
    public int energy = 100; // Mutable energy

    public abstract void eat(Location location);
    public abstract Pair<Integer, Integer> move(Island island, int x, int y);
    public abstract void reproduce(Location location);

    public void starve() {
        energy -= 8;
        if (energy <= 0) {
            System.out.println(getName() + " умер от голода."); // "died of hunger."
        }
    }

    // Simple Pair class for coordinates (could use javafx.util.Pair or create your own)
    public static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}