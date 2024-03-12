package no.uib.inf101.tetris.model;

/**
 * The different states the game can be in at any given moment.
 * It is used to control game flow, manage game logi and
 * update the users interface based on this.
 */
public enum GameState {
    WELCOME_SCREEN,
    ACTIVE_GAME,
    GAME_OVER

}
