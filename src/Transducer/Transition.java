package Transducer;

import java.util.ArrayList;

public class Transition {
    protected State fromState,toState;
    protected myChar input,output;

    public Transition(State fromState,  char input, char output, State toState) {
        this.fromState = fromState;
        this.toState = toState;
        this.input = new myChar(input);
        this.output = new myChar(output);
    }



    @Override
    public String toString() {
        return "\t\t\t\t\t "+input.getCharacter()+":"+output.getCharacter()+"\n"+""+fromState+"    ---->    "+toState+"\n";
    }
}
