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
		public static final int SKELETON = 0;
		public static final int BEATLE = 1;
		//enemy 2 accidentally is the tower
		public static final int ORC = 3;
		

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 10;
			case BEATLE:
				return 15;
			case ORC:
				return 25;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 1f;
			case BEATLE:
				return 0.7f;
			case ORC:
				return 0.5f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case SKELETON:
				return 85;
			case BEATLE:
				return 200;
			case ORC:
				return 400;
			}
			return 0;
		}
}
}


