package Projectile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.nio.Buffer;

import Enemy.GeneralEnemy;
import HelperMethod.LoadSave;
import Scene.Playing;
import TowerM.Tower;
import static HelperMethod.Constant.Towers.*;
import static HelperMethod.Constant.Projectile.*;



public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage[] projectileImages;
    private int projectileID = 0;

    public ProjectileManager(Playing playing){
        this.playing = playing;
    }
    /* 
    private void importProjectileImages() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        projectileImages = new BufferedImage[3];
        projectileImages[0] = atlas.getSubimage(64*6, 64*2, 64, 64); // Example image for projectile type 0
        projectileImages[1] = atlas.getSubimage(64*6, 64*3, 64, 64); // Example image for projectile type 1
        projectileImages[2] = atlas.getSubimage(64*6, 64*4, 64, 64); // Example image for projectile type 2
    }
        */
    public void update(){
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                p.move();
                if(isProjectileHittingEnemy(p)) {
                    p.setActive(false);
                    //playing.getEnemyManaging().damageEnemy(p.getTargetEnemy(), p.getDamage());
                }else{
                    //do nothing
                }
            }
            p.move();
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

        float xSpeed = xPercent * HelperMethod.Constant.Projectile.GetSpeed(type);
        float ySpeed = HelperMethod.Constant.Projectile.GetSpeed(type) - xSpeed;

        if(t.getX() < e.getX()) {
            xSpeed *= -1; // Adjust speed direction based on tower position
        }
        if(t.getY() < e.getY()) {
            ySpeed *= -1; // Adjust speed direction based on tower position
        }

        projectiles.add(new Projectile(t.getX() + 32, t.getY() + 32, xSpeed, ySpeed, t.getDamage(), projectileID++, type));
    }
    private int getProjectileType(Tower t) {
        switch (t.getTowerType()) {
            case TOWER1:
                return LASER;
        }
        return 0;
    }



}

    
