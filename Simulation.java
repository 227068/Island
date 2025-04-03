
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Simulation {

    private static final Random random = new Random();

    private final Island island = new Island();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    private int currentTick = 0;

    public void start() {
        executor.scheduleAtFixedRate(this::tick, 0, Config.SIMULATION_TICK, TimeUnit.MILLISECONDS);
    }

    private void tick() {
        System.out.println("=== Такт симуляции " + currentTick + " ===");  // Simulation tick
        currentTick++;

        if (currentTick >= Config.MAX_TICKS) {
            System.out.println("Достигнуто максимальное количество тактов. Симуляция остановлена."); // Max ticks reached. Simulation stopped.
            executor.shutdown();
            return;
        }

        if (island.countAnimals() == 0) {
            System.out.println("Все животные вымерли. Симуляция остановлена."); // All animals died. Simulation stopped.
            executor.shutdown();
            return;
        }

        for (int y = 0; y < Config.HEIGHT; y++) {
            for (int x = 0; x < Config.WIDTH; x++) {
                Location location = island.grid[y][x];

                if (location.getPlants().size() < Config.MAX_PLANTS_PER_LOCATION && random.nextBoolean()) {
                    location.getPlants().add(new Plant());
                }

                // Copy lists to avoid ConcurrentModificationException

                List<Animal> animalsToProcess = new java.util.ArrayList<>(location.getAnimals());
                // Process the animals
                for (Animal animal : animalsToProcess) {
                    //Double check
                    if (!location.getAnimals().contains(animal)) continue;

                    animal.eat(location);
                    animal.reproduce(location);
                    animal.starve();

                    if (animal.energy <= 0) {
                        location.getAnimals().remove(animal);
                        System.out.println(animal.getName() + " умер от голода."); // died of hunger
                    }
                }


                List<Animal> animalsToMove = new java.util.ArrayList<>(location.getAnimals());
                for (Animal animal : animalsToMove) {
                    if (!location.getAnimals().contains(animal)) continue;
                    int newX = animal.move(island, x, y).getKey(); // Assuming Pair has getX()
                    int newY = animal.move(island, x, y).getValue(); // and getY() methods

                    if (newX != x || newY != y) {
                        island.grid[newY][newX].getAnimals().add(animal); // Add to the new location
                        location.getAnimals().remove(animal);           // Remove from the old location
                    }
                }
            }
        }
    }
}
