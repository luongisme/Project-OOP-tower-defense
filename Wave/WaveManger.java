package Wave;

import java.util.ArrayList;
import Enemy.GeneralEnemy;

public class WaveManger {
    private int[] waveEnemyCounts = {5, 10, 15, 20}; // 4 waves with increasing enemy counts
    private int currentWave = 0;
    private int enemiesInCurrentWave = 0;
    private long lastSpawnTime = 0;
    private long spawnInterval = 1500; // ms between spawns
    private ArrayList<GeneralEnemy> enemies = new ArrayList<>();

    public void update() {
        waves();
    }

    private void waves() {
        // Remove dead enemies from the list
        enemies.removeIf(e -> !e.isAlive());

        // Handle enemy waves spawning
        if (enemies.isEmpty() && enemiesInCurrentWave == 0 && currentWave < waveEnemyCounts.length) {
            // Prepare for the next wave
            enemiesInCurrentWave = waveEnemyCounts[currentWave];
            lastSpawnTime = System.currentTimeMillis();
            currentWave++;
        }

        if (enemiesInCurrentWave > 0 && System.currentTimeMillis() - lastSpawnTime > spawnInterval) {
            // Spawn a new enemy randomly
            enemyTypeRandom();
            enemiesInCurrentWave--;
            lastSpawnTime = System.currentTimeMillis();
            System.out.println("Updating game. Current wave: " + currentWave + ", Enemies: " + enemies.size());
        }
    }

    private void enemyTypeRandom() {
       
        int[] allowedTypes = {0, 1, 3};
        int type = allowedTypes[(int) (Math.random() * allowedTypes.length)];
        GeneralEnemy enemy = new GeneralEnemy(0, 64 * 11,type); // Extend to use 'type' if supported
        
        enemies.add(enemy);
    }

    public ArrayList<GeneralEnemy> getEnemies() {
        return enemies;
    }

    public int getCurrentWave() {
        return currentWave;
    }
}
