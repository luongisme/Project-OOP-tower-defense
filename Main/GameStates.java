package Main;

public enum GameStates {
    PLAYING, MENU, SETTINGS, GAME_OVER;

    public static GameStates gameState = GAME_OVER;

    public static void SetGameState(GameStates state){
        gameState = state;
    }
}
