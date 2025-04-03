public class Config {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final long SIMULATION_TICK = 2000L; // Milliseconds
    public static final int REPRODUCTION_CHANCE = 30; // Percentage
    public static final int MAX_ANIMALS_PER_LOCATION = 10;
    public static final int INITIAL_ANIMALS = 200;
    public static final int MAX_PLANTS_PER_LOCATION = 3;
    public static final int MAX_TICKS = 10;
    // Private constructor to prevent instantiation
    private Config() {
        // This class should not be instantiated
    }
}
