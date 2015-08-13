package gamefuel;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Created by mark the great on 6/13/14.
 */
public class Primary extends StateBasedGame {

    public static final int MENU = 0;
    public static final int SHOP = 1;
    public static final int GAMEPLAY = 2;

    public Primary() {
        super("Slinker");
        SharedGameState sharedState = new SharedGameState();
        this.addState(new Menu(MENU, sharedState));
        this.addState(new GamePlay(GAMEPLAY, sharedState));
    }

    public static void main(String[] args) {
        try {
            AppGameContainer gameContainer = new AppGameContainer(new Primary(), 640, 480, false);
            gameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(MENU).init(gameContainer, this);
        this.getState(GAMEPLAY).init(gameContainer, this);
        this.enterState(MENU);
    }
}
