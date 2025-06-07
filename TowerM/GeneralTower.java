package TowerM;

public class GeneralTower {
    private int x, y, id, towerType;

    public GeneralTower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public int getTowerType() {
        return towerType;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }
}
