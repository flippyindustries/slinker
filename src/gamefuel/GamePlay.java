package gamefuel;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static java.lang.Math.random;

public class GamePlay extends BasicGameState {

    private final int id;
    private final SharedGameState sharedState;
    private int ferretX = -1000;
    private int ferretY = 370;
    private Animation ferrimation;

    private int killballX = 320;
    private int killballY = 400;
    private Image killballImage;
    private Direction killballDirection = Direction.LEFT;
    private Image grumpyCatImage;
    private Sound burnyDrinkBoom;
    private Image brickImage;

    private long numberOfUpdates = 0;


    public GamePlay(int id, SharedGameState sharedState) {
        this.id = id;
        this.sharedState = sharedState;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        ferrimation = new Animation();
        ferrimation.setAutoUpdate(true);
        ferrimation.addFrame(getFerretImage("ferret2_1.gif"), 100);
        ferrimation.addFrame(getFerretImage("ferret2_2.gif"), 100);
        ferrimation.addFrame(getFerretImage("ferret2_3.gif"), 100);
        ferrimation.addFrame(getFerretImage("ferret2_4.gif"), 100);
        burnyDrinkBoom = new Sound("/Users/etavela/game_development/mark/Slinker/re-sources/sounds/molotov.wav");
        killballImage = new Image("/Users/etavela/game_development/mark/Slinker/re-sources/killball1.png");
        grumpyCatImage = new Image("/Users/etavela/game_development/mark/Slinker/re-sources/cheat_codes/grumpy_cat.jpg");
        brickImage = new Image("re-sources/e-mages/tylers/blue_brick.png");
    }

    private Image getFerretImage(String name) throws SlickException {
        Image image = new Image("re-sources/bob/" + name).getScaledCopy(2);
//        image.setImageColor(Color.blue.r, Color.blue.g, Color.blue.b);
        return image;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(Color.black);
        drawBricks(graphics);
        if (sharedState.getMode().equals(CharacterMode.FERRET)) {
            ferrimation.draw(ferretX, ferretY);
        } else {
            grumpyCatImage.draw(ferretX, ferretY);
        }
        killballImage.draw(killballX, killballY);
    }

    private void drawBricks(Graphics graphics) {
        int brickWidth = brickImage.getWidth();
        int brickHeight = brickImage.getHeight();
        for (int x = 0; x <= 640 - brickWidth; x = x + brickWidth - 1) {
            graphics.drawImage(brickImage, x, 480 - brickHeight);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        numberOfUpdates++;
        Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT) && ferretX > 20) {
            ferretX = ferretX -  1;
        }
        if (input.isKeyDown(Input.KEY_RIGHT) && ferretX < 600) {
            ferretX = ferretX +  1;
        }
        if (input.isKeyPressed(Input.KEY_Q)) {
            gameContainer.exit();
        }
        if (shouldMoveKillball()) {
            moveKillball();
        }
    }

    private boolean shouldMoveKillball() {
        return numberOfUpdates % 4 == 0;
    }

    private void moveKillball() {
        moveBackAndForth();
    }

    private void moveBackAndForth() {
        if (killballDirection == Direction.LEFT) {
            if (killballX <= 0) {
                killballDirection = Direction.RIGHT;
            } else {
                killballX--;
            }
        }
        if (killballDirection == Direction.RIGHT) {
            if (killballX >= 640) {
                killballDirection = Direction.LEFT;
            } else {
                killballX++;
            }
        }
    }

    private void moveKillballTowardFerret() {
        if (killballX < ferretX) {
            killballX++;
        } else {
            killballX--;
        }
    }

}
