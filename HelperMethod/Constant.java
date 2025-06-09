package HelperMethod;

public class Constant {

	public static class Projectile{
		public static final int LASER = 0;
		
		public static float GetSpeed(int projectileType){
			switch (projectileType) {
			case LASER:
				return 0.5f;
			}
			return 0f;

		}
	}

    public static class Towers{
        public static final int TOWER1 = 0;
		public static int GetDefaultDamage(int towerType) {
			switch(towerType){
				case TOWER1:
					return 30;
			}
			return 0;
    	}
		public static float GetDefaultRange(int towerType) {
			switch(towerType){
				case TOWER1:
					return 100;
			}
			return 0;
		}
		public static float GetDefaultCooldown(int towerType) {
			switch(towerType){
				case TOWER1:
					return 30;
			}
			return 0;
		} 
}
    public static class Direction{
    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int UP=2;
    public static final int DOWN=3;
    }

    public static class Tiles{
        public static final int WATER_TILE=0;
        public static final int GRASS_TILE=1;
        public static final int CAMP_TILE=2;
        public static final int TREE_TILE=3;
        public static final int TOWER_TILE=4;
        public static final int ROAD_TILE=5;
        public static final int GRASS_TOWER_TILE=6;
    }

    public static class Enemies{
		public static final int ORC = 0;
		public static final int BAT = 1;
		public static final int KNIGHT = 2;
		public static final int WOLF = 3;

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case ORC:
				return 5;
			case BAT:
				return 5;
			case KNIGHT:
				return 25;
			case WOLF:
				return 10;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case ORC:
				return 0.5f;
			case BAT:
				return 0.7f;
			case KNIGHT:
				return 0.45f;
			case WOLF:
				return 0.85f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case ORC:
				return 85;
			case BAT:
				return 100;
			case KNIGHT:
				return 400;
			case WOLF:
				return 125;
			}
			return 0;
		}
}
}


