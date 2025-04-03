import java.util.ArrayList;
import java.util.List;

public class Location {
    private int x;
    private int y;
    private List<Animal> animals;
    private List<Plant> plants;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public void updateAnimals() {
        // Обновление состояния животных в локации
        for (Animal animal : animals) {
            animal.act();
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }
}
