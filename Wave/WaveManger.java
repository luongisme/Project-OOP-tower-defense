package Wave;

import java.util.ArrayList;

import Enemy.Beatle;
import Enemy.GeneralEnemy;
import Enemy.Orc;
import Enemy.Skeleton;

public class WaveManger {
    private int[] waveEnemyCounts = {5, 10, 15, 20}; // 4 waves with increasing enemy counts
    private int currentWave = 0;
    private int enemiesInCurrentWave = 0;
    private long lastSpawnTime = 0;
    private long spawnInterval = 1500; // ms between spawns
    private ArrayList<GeneralEnemy> enemies = new ArrayList<>();

    public void update() {
        waves();
        enemies.removeIf(e -> !e.isAlive());
    }

    private void waves() {
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
        int[] validTypes = {0, 1, 3};
        int type = validTypes[(int) (Math.random() * validTypes.length)];
        if (type==0){
            enemies.add(new Skeleton(0, 64 * 11, type));
        }
        else if(type==1){
            enemies.add(new Beatle(0, 64 * 11, type));
        }
        else if(type==3){
            enemies.add(new Orc(0,  64*11, type));
        }
        else{
            //do nothing
        }
        
    }

    public ArrayList<GeneralEnemy> getEnemies() {
        return enemies;
    }

    public int getCurrentWave() {
        return currentWave;
    }
}
