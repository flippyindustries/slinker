package gamefuel;

public class SharedGameState {

    private CharacterMode mode = CharacterMode.FERRET;

    public CharacterMode getMode() {
        return mode;
    }

    public void setMode(CharacterMode mode) {
        this.mode = mode;
    }
}
