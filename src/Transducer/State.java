package Transducer;

import java.util.ArrayList;

public class State {

    protected String name;
    protected boolean isFinal;
    protected ArrayList<Transition> transitions = new ArrayList<>();

    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] allTransitions(){
        return transitions.toArray();
    }
    @Override
    public String toString() {
        return String.format("%s, isFinal:%b",name,isFinal);
    }
}
