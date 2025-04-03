import simulation.Simulation;

public class Main {

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.start();

        while (true) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                System.err.println("Sleep interrupted: " + e.getMessage()); // Handle the exception
                Thread.currentThread().interrupt();  // Restore interrupted state
                break; // Exit the loop
            }
        }
    }
}
