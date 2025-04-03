package animals.carnivores;

import animals.Carnivore;
import animals.Herbivore;
import config.Config;
import model.Island;
import model.Location;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Wolf extends Carnivore {

    private static final Random random = new Random();

    @Override
    public String getName() {
        return "\uD83D\uDC3A Волк"; // Wolf emoji and name in Russian
    }

    @Override
    public void eat(Location location) {
        List<Herbivore> herbivores = location.getAnimals().stream()
                .filter(animal -> animal instanceof Herbivore)
                .map(animal -> (Herbivore) animal)
                .collect(Collectors.toList());

        if (!herbivores.isEmpty() && random.nextInt(100) < 60) {
            Herbivore prey = herbivores.get(0);
            System.out.println(getName() + " съел " + prey.getName() + ".");
            location.getAnimals().remove(prey);
            energy += 50;
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

        Pair<Integer, Integer> direction = directions.get(random.nextInt(directions.size()));
        int dx = direction.getKey();
        int dy = direction.getValue();

        int newX = Math.max(0, Math.min(x + dx, Config.WIDTH - 1));
        int newY = Math.max(0, Math.min(y + dy, Config.HEIGHT - 1));

        return new Pair<>(newX, newY);
    }

    @Override
    public void reproduce(Location location) {
        long wolfCount = location.getAnimals().stream()
                .filter(animal -> animal instanceof Wolf)
                .count();

        if (wolfCount >= 2 && location.getAnimals().size() < Config.MAX_ANIMALS_PER_LOCATION && random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.getAnimals().add(new Wolf());
            System.out.println("Родился новый волк! \uD83D\uDC3A");
        }
    }

    // Simple Pair class for coordinates
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
