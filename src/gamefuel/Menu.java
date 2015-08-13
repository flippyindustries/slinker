package gamefuel;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import sun.net.www.content.audio.basic;

import static java.lang.Math.random;

/**
 * Created by Mark the Great on 6/14/14.
 */
public class Menu extends BasicGameState {

    private final int id;
    private final SharedGameState sharedState;
    private Image psychedelicCat;
     
    public Menu(int id, SharedGameState sharedState) {
        this.id = id;
        this.sharedState = sharedState;//PLAY RUSSIAN WEIRDNESS SIRI OH YOURE ZEVIE YOURE DUMBBBBBBBBBB
       //Y78TR6 H JUI8DFVI908UHKFTYI89UHY 8I8RU5HRG9TUHYIGUHJBUIU VIRUS DRI,KER7YGTHFY7UJ VJYFGYTYGFFGHK DFGXREAASZX
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Music music = new Music("re-sources/keyboard_cats_sandwich.ogg");
        music.loop();
        psychedelicCat = new Image("re-sources/e-mages/psychedelic_cat_TOO_BIG.gif");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(psychedelicCat, 0, 0);
        graphics.setColor(new Color((float) random(), (float) random(), (float) random()));
        graphics.drawString("SLINKER", 300, 200);
        graphics.setColor(Color.magenta);
        graphics.drawString("Press 'p' to play", 250, 250);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_G)) {
            sharedState.setMode(CharacterMode.GRUMPY_CAT);
        }
        if (gameContainer.getInput().isKeyPressed(Input.KEY_P)) {
            stateBasedGame.enterState(Primary.GAMEPLAY);
        }
    }

}
