package animals.carnivores

import animals.Carnivore
import animals.Herbivore
import config.Config
import model.Island
import model.Location
import kotlin.random.Random

class Fox : Carnivore() {
        override val name = "\uD83E\uDD8A Лиса"
        override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 70) {
        println("$name съел ${prey.name}.")
        location.animals.remove(prey)
        energy += 30
        }
        }
        override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
        }
        override fun reproduce(location: Location) {
        if (location.animals.count { it is Fox } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
        location.animals.add(Fox())
        println("Родилась новая лиса! \uD83E\uDD8A")
        }
        }
        }
