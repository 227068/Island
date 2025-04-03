import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {
    private Location[][] locations;
    private int width;
    private int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y);
            }
        }
    }

    public void startSimulation() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(this::growPlants, 0, 10, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(this::updateAnimals, 0, 10, TimeUnit.SECONDS);

        // Для вывода статистики
        scheduledExecutorService.scheduleWithFixedDelay(this::printStatistics, 0, 10, TimeUnit.SECONDS);
    }

    private void growPlants() {
        // Реализовать логику роста растений
    }

    private void updateAnimals() {
        // Логика обновления состояния животных
        for (Location[] row : locations) {
            for (Location loc : row) {
                loc.updateAnimals();
            }
        }
    }

    private void printStatistics() {
        // Печать статистики
    }
}
