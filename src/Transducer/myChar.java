package Transducer;

public class myChar {
    protected char character;

    public myChar(char character) {
        if((character>='a' && character<='z') || character=='λ') this.character = character;
        else throw new RuntimeException();
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return ""+character+"";
    }
}
