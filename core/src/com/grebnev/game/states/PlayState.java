//package com.grebnev.game.states;

/**
 * Created by Grebnev on 23.04.2017.
 */

//public class PlayState extends State {

//    private GameBoard board;
//    private Texture lightSquareTile;
//    private Texture lightPlayer;
//    private Texture darkPlayer;
//    private Texture bgTable;
//
//    public PlayState(GameStateManager stateManager) {
//        super(stateManager);
//        board = new GameBoard(400);
//        lightSquareTile = new Texture("light_internal.png");
//        lightPlayer = new Texture("white_player.png");
//        darkPlayer = new Texture("black_player.png");
//        bgTable = new Texture("bg_table.jpg");
////        camera.setToOrtho(false, TacTickle.WIDTH / 2, TacTickle.HEIGHT / 2);
//    }
//
//    @Override
//    protected void handleInput() {
//
//    }
//
//    @Override
//    protected void update(float deltaTime) {
//
//    }
//
//    @Override
//    protected void render(SpriteBatch batch) {
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        batch.draw(bgTable, camera.position.x - (camera.viewportWidth / 2), 0);
////        batch.draw(board.getBoard(), );
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 4; j++) {
//                batch.draw(lightSquareTile, (float) (64 * (j + 0.4)), (float) (64 * (i + 0.4)));
//                if (i == 0 && j % 2 == 0 || i == 4 && j % 2 != 0) {
//                    batch.draw(lightPlayer, (float) (64 * (j + 0.4)), (float) (64 * (i + 0.4)));
//                } else {
//                    if (i == 0 && j % 2 != 0 || i == 4 && j % 2 == 0) {
//                        batch.draw(darkPlayer, (float) (64 * (j + 0.4)), (float) (64 * (i + 0.4)));
//                    }
//                }
//            }
//        }
//        batch.end();
//    }
//
//    @Override
//    protected void dispose() {
//
//    }
//}
