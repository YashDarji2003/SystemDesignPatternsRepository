
import java.util.*;

public class FlyWeightPattern {

    public static void main(String[] args) {
        final int ASTEROID_COUNT = 100000;
        System.out.println("TESTING WITH FLYWEIGHT PATTERN");

        SpaceGameWithFlyweight game = new SpaceGameWithFlyweight();
        game.spawnAsteroid(ASTEROID_COUNT);

        long totalMemory = game.calculateMemoryUsage();

        System.out.println("========= Memory Usage ========");
        System.out.println("Total Asteroid "+ASTEROID_COUNT);
        System.out.println("Memory per Asteroid "+ AsteroidContext.getMemoryUsage()+" bytes");
        System.out.println("Total memory used "+totalMemory+" bytes");
        System.out.printf("Memory in MB %.2f MB\n", totalMemory / (1024.0 * 1024.0));
    }
}

class AsteroidFlyweight {
    int length;
    int width;
    int weight;
    String color;
    String texture;
    String material;

    public AsteroidFlyweight(int length, int width, int weight, String color, String texture, String material) {
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.color = color;
        this.texture = texture;
        this.material = material;
    }

    void render(int posX, int posY, int velocityX, int velocityY) {
        System.out.println("Rendering" + color + ", " + texture + ", " + material + ", " + " Asterios at (" + posX
                + ", " + posY + ")" + " Size: " + length + "x " + width + "y " + "Velocity" + "( " + velocityX + ", "
                + velocityY + " )");
    }

    public static long getMemoryUsage() {
        // Java equivalent estimates in bytes:
        // int = 4 bytes
        // String (reference) = 4 or 8 bytes (depending on JVM)
        // We'll use 8 for modern 64-bit systems

        int sizeOfInt = Integer.BYTES; // Always 4
        int sizeOfReference = 8; // Approximation for a String reference
        int constantBuffer = 32; // The "32" from your image

        return (sizeOfInt * 3L) +
                (sizeOfReference * 3L) +
                (constantBuffer * 3L);
    }
}

class AsteroidFactory {

    static Map<String, AsteroidFlyweight> flyweight = new HashMap<>();

    static AsteroidFlyweight getAsteroid(int length, int width, int weight, String color,
            String texture,
            String material) {

        String key = String.valueOf(length + "_" + width + "_" + weight + "_" + color + "_" + texture + "_" + material);
        if (!flyweight.containsKey(key)) {
            flyweight.put(key, new AsteroidFlyweight(length, width, weight, color, texture, material));
        }
        return flyweight.get(key);
    }

    static int flyweightCount() {
        return flyweight.size();
    }

    static long getFlyweightMemory() {
        return flyweight.size() * AsteroidFlyweight.getMemoryUsage();
    }

    static void cleanup() {
        flyweight.clear();
    }
}

class AsteroidContext {
    AsteroidFlyweight flyweight;
    int posX, posY;
    int velocityX, velocityY;

    public AsteroidContext(AsteroidFlyweight flyweight, int posX, int posY, int velocityX, int velocityY) {
        this.flyweight = flyweight;
        this.posX = posX;
        this.posY = posY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    void render() {
        flyweight.render(posX, posY, velocityX, velocityY);
    }

    void update() {
        posX += velocityX;
        posY += velocityY;
    }

    static long getMemoryUsage() {
        int sizeOfInt = Integer.BYTES; // Always 4
        System.out.println("Size of Int " + sizeOfInt);
        return (sizeOfInt * 4L);
    }

}

class SpaceGameWithFlyweight {
    List<AsteroidContext> asteroids = new ArrayList<>();

    void spawnAsteroid(int count) {
        System.out.println("==========Spawning " + count + " asteroid ============");
        List<String> colors = List.of("Red", "Blue", "Gray");
        List<String> textures = List.of("Rocky", "Icy", "Metallic");
        List<String> materials = List.of("Iron", "Stone", "Ice");

       // int size[] = new int[] { 25, 35, 45 };
        for (int i = 0; i < count; i++) {
            int type = i % 3;
            AsteroidFlyweight asteroidFlyweight = AsteroidFactory.getAsteroid(
                    type,
                    type,
                    type * 10,
                    colors.get(type),
                    textures.get(type),
                    materials.get(type));

            asteroids.add(new AsteroidContext(asteroidFlyweight,
                    100 + i * 50,
                    200 + i * 50,
                    1,
                    2));
        }
        System.out.println("Created " + asteroids.size() + " asteroid Objects");
        System.out.println("Total flyweight objects " + AsteroidFactory.flyweightCount());
    }

    void renderAll() {
        System.out.println("Rendering First 5 asteroid");
        for (int i = 0; i < Math.min(5, asteroids.size()); i++) {
            asteroids.get(i).render();
        }
    }

    public long calculateMemoryUsage() {
        long contextMemory = asteroids.size() * AsteroidContext.getMemoryUsage();
        long flyweightMemory = AsteroidFactory.getFlyweightMemory();
        return contextMemory + flyweightMemory;
    }

    public int getAsteriodCount() {
        return asteroids.size();
    }

}
