package animals.herbivores;

import animals.Herbivore;
import config.Config;
import model.Island;
import model.Location;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Rabbit extends Herbivore {

    private static final Random random = new Random();

    @Override
    public String getName() {
        return "\uD83D\uDC07 Кролик"; // Rabbit emoji and name in Russian
    }

    @Override
    public void eat(Location location) {
        if (!location.getPlants().isEmpty()) {
            location.getPlants().remove(0); // Remove the first plant
            energy += 20;
            System.out.println(getName() + " съел растение.\uD83C\uDF3F"); // Ate a plant
        }
    }

    @Override
    public Pair<Integer, Integer> move(Island island, int x, int y) {
        // Possible directions (up, down, left, right)
        List<Pair<Integer, Integer>> directions = List.of(
                new Pair<>(-1, 0),
                new Pair<>(1, 0),
                new Pair<>(0, -1),
                new Pair<>(0, 1)
        );

        // Choose a random direction
        Pair<Integer, Integer> direction = directions.get(random.nextInt(directions.size()));
        int dx = direction.getKey();
        int dy = direction.getValue();

        // Calculate new coordinates, ensuring they stay within the island bounds
        int newX = Math.max(0, Math.min(x + dx, Config.WIDTH - 1));
        int newY = Math.max(0, Math.min(y + dy, Config.HEIGHT - 1));

        return new Pair<>(newX, newY);
    }

    @Override
    public void reproduce(Location location) {
        // Count the number of rabbits in the location
        long rabbitCount = location.getAnimals().stream()
                .filter(animal -> animal instanceof Rabbit)
                .count();

        // Check reproduction conditions
        if (rabbitCount >= 2 && location.getAnimals().size() < Config.MAX_ANIMALS_PER_LOCATION && random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.getAnimals().add(new Rabbit());
            System.out.println("Родился новый кролик! \uD83D\uDC07"); // New rabbit is born!
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
