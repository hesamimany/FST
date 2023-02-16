package Transducer;

import java.util.ArrayList;

public class Automata {
    static final String aZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    ArrayList<State> states = new ArrayList<>();
    ArrayList<Transition> transitions = new ArrayList<>();
    State startState;

    public static void main(String[] args) {
        /*Automata automata = new Automata();

        State q0 = new State("q0", false);
        State q1 = new State("q1", false);
        State q2 = new State("q2", true);

        automata.add_state(q0);
        automata.add_state(q1);
        automata.add_state(q2);

        automata.add_set_transition("q0", "abcdefghijklmnopqrstuvwxyz".toCharArray(), "q0");
        automata.add_set_transition("q0", "abcdefghijklmnopqrtuvwxyz".toCharArray(), "q2");
        automata.add_transition("q0", 's', 's', "q1");
        automata.add_transition("q1", 'λ', 'λ', "q2");
        automata.add_transition("q1", 's', 'λ', "q2");
        automata.parse_input("bss");*/


        Automata f = new Automata();
        State q0 = new State("q0",false);
        State qf = new State("qf",true);

        f.add_state(q0);
        f.add_state(qf);

        f.add_transition("q0",'a','λ',"q0");
        f.add_transition("q0",'b','b',"q0");
        f.add_transition("q0",'a','λ',"qf");
        f.add_transition("q0",'λ','λ',"qf");

        f.parse_input("baab");
        f.parse_input("bb");
        System.out.println();
        f.parse_input("aaba");
    }

    public int findStateIndex(String name) {
        int counter = 0;
        for (State state : states) {
            if (state.name.equals(name)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public State findState(String name) {
        for (State state : states) {
            if (state.name.equals(name)) {
                return state;
            }
        }
        return null;
    }

    public Object[] findTransition(State inState, char c) {
        ArrayList<Transition> list = new ArrayList<>();

        for (Transition transition : transitions) {
            if (transition.fromState.name.equals(inState.name) && (transition.input.character == c || transition.input.getCharacter() == 'λ')) {
                list.add(transition);
            }
        }
        return list.toArray(new Transition[list.size()]);
    }

    public void add_state(String state_name, boolean is_Final) {
        states.add(new State(state_name, is_Final));
    }

    public void add_state(State add) {
        states.add(add);
        if (startState == null)
            startState = states.get(0);
    }

    public void add_transition(String in_state_name, char input, char output, String out_state_name) {
        State inState;
        State toState;
        int temp1 = findStateIndex(in_state_name);
        if (temp1 != -1) inState = states.get(temp1);
        else throw new RuntimeException();

        int temp2 = findStateIndex(out_state_name);
        if (temp2 != -1) toState = states.get(temp2);
        else throw new RuntimeException();

        transitions.add(new Transition(inState, input, output, toState));
        inState.transitions.add(new Transition(inState, input, output, toState));
    }

    public void add_set_transition(String in_state_name, char[] chars, String out_state_name) {
        for (char c : chars) {
            add_transition(in_state_name, c, c, out_state_name);
        }
    }

    public void parse_input(String input) {
        if (!input.matches("^[a-z]+$")) {
            System.out.println("FAIL");
            return;
        }
        String tapeOut = "";
        int tapeInIndex = 0, tapeOutIndex = 0;
        recursiveFST(input, tapeOut, tapeInIndex, tapeOutIndex, startState);
    }

    public void recursiveFST(String tapeIn, String tapeOut, int tapeInIndex, int tapeOutIndex, State currentState) {

        if (tapeInIndex == tapeIn.length()) {
            if (currentState.isFinal) {
                System.out.println(tapeOut);
                return;
            }
            for (Transition transition : currentState.transitions) {
                if (transition.input.character == 'λ' && transition.output.character == 'λ') {
                    recursiveFST(tapeIn, tapeOut, tapeInIndex, tapeOutIndex, transition.toState);
                } else if (transition.input.character == 'λ') {
                    recursiveFST(tapeIn, tapeOut + transition.output.getCharacter(), tapeInIndex, tapeOutIndex + 1, transition.toState);
                }
            }
            return;
        }

        Transition[] currentTransitions = (Transition[]) findTransition(currentState, tapeIn.charAt(tapeInIndex));
        for (Transition currentTransition : currentTransitions) {
            if (currentTransition.input.character != 'λ' && currentTransition.output.character != 'λ') {
                recursiveFST(tapeIn, tapeOut + (currentTransition.output.getCharacter()), tapeInIndex + 1, tapeOutIndex + 1, currentTransition.toState);
            }
            if (currentTransition.input.character == 'λ' && currentTransition.output.character == 'λ') {
                recursiveFST(tapeIn, tapeOut, tapeInIndex, tapeOutIndex, currentTransition.toState);
            } else if (currentTransition.input.character == 'λ') {
                recursiveFST(tapeIn, tapeOut + (currentTransition.output.getCharacter()), tapeInIndex, tapeOutIndex + 1, currentTransition.toState);
            } else if (currentTransition.output.character == 'λ') {
                recursiveFST(tapeIn, tapeOut, tapeInIndex + 1, tapeOutIndex, currentTransition.toState);
            }

        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (State s : states) {
            sb.append(s);
            sb.append("\n");
        }
        for (Transition t : transitions) {
            sb.append(t);
            sb.append("\n");
        }
        return sb.toString();
    }
}



