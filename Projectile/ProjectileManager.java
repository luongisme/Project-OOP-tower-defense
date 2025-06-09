package Projectile;

import Enemy.GeneralEnemy;
import static HelperMethod.Constant.Projectile.*;
import static HelperMethod.Constant.Towers.*;
import HelperMethod.LoadSave;
import Scene.Playing;
import TowerM.Tower;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImages;
    private int projectileID = 0;

    public ProjectileManager(Playing playing){
        this.playing = playing;
        importProjectileImages();
    }
    
    private void importProjectileImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImages = new BufferedImage[1];
        projectileImages[0] = atlas.getSubimage(64*6, 0, 64, 64); // Example image for projectile type 0
    }
        
    public void update(){
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                if(isProjectileHittingEnemy(p)) {
                    p.setActive(false);
                } else {
                    p.move();
                }
            }
        }
    }
    private boolean isProjectileHittingEnemy(Projectile p) {
        for(GeneralEnemy e : playing.getEnemyManaging().getEnemies()) {
            if(e.getHitBox().contains(p.getPosition())) {
                e.hurt(p.getDamage());
                return true; // Hit detected
            }
        }
        return false; // No hit detected
    }
    public void draw(Graphics g){
        for(Projectile p : projectiles) {
                if(p.isActive()){
                    g.drawImage(projectileImages[p.getProjectileType()],(int) p.getPosition().x, (int) p.getPosition().y, null);
                }
            }
        }
    public void newProjectile(Tower t, GeneralEnemy e) {
        int type = getProjectileType(t);

        int xDistance = (int) Math.abs(t.getX() - e.getX());
        int yDistance = (int) Math.abs(t.getY() - e.getY());
        int towerDistance = xDistance + yDistance;

        float xPercent = (float) xDistance / towerDistance;
       // float yPercent = 1.0f - xPercent; // Ensure yPercent is the complement of xPercent

        float xSpeed = xPercent * HelperMethod.Constant.Projectile.GetSpeed(getProjectileType(t)); //type
        float ySpeed = HelperMethod.Constant.Projectile.GetSpeed(getProjectileType(t)) - xSpeed; //type

        if(t.getX() > e.getX()) {
            xSpeed *= -1; // Adjust speed direction based on tower position
        }
        if(t.getY() > e.getY()) {
            ySpeed *= -1; // Adjust speed direction based on tower position
        }

        projectiles.add(new Projectile(t.getX() , t.getY() , xSpeed, ySpeed, t.getDamage(), projectileID++, getProjectileType(t))); //type
    }
    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case TOWER1:
                return CANNON;
        }
        return 0;
    }

    

}


