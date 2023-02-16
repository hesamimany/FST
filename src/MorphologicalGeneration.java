import Transducer.Automata;
import Transducer.State;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorphologicalGeneration {
    public static void main(String[] args) throws FileNotFoundException {
        Automata FST = new Automata();

        State q0 = new State("q0", false);
        State q1 = new State("q1", false);
        State q2 = new State("q2", false);
        State q3 = new State("q3", false);
        State q4 = new State("q4", false);
        State q5 = new State("q5", false);
        State q6 = new State("q6", false);
        State q7 = new State("q7", false);
        State q8 = new State("q8", false);
        State q9 = new State("q9", false);
        State q10 = new State("q10", false);
        State q11 = new State("q11", false);
        State q12 = new State("q12", false);
        State q13 = new State("q13", false);
        State q14 = new State("q14", true);
        State q15 = new State("q15", false);

        FST.add_state(q0);
        FST.add_state(q1);
        FST.add_state(q2);
        FST.add_state(q3);
        FST.add_state(q4);
        FST.add_state(q5);
        FST.add_state(q6);
        FST.add_state(q7);
        FST.add_state(q8);
        FST.add_state(q9);
        FST.add_state(q10);
        FST.add_state(q11);
        FST.add_state(q12);
        FST.add_state(q13);
        FST.add_state(q14);
        FST.add_state(q15);

        FST.add_set_transition("q0", "abcdefghijklmnopqrstuvwxyz".toCharArray(), "q0");

        FST.add_set_transition("q0", "abcdgijklmnpqrtuvw".toCharArray(), "q1");
        FST.add_transition("q1", 'λ', 's', "q14");

        FST.add_set_transition("q0", "sxzo".toCharArray(), "q2");
        FST.add_set_transition("q0", "sc".toCharArray(), "q4");
        FST.add_transition("q4", 'h', 'h', "q2");
        FST.add_transition("q0", 'f', 'v', "q2");
        FST.add_transition("q2", 'λ', 'e', "q3");
        FST.add_transition("q3", 'λ', 's', "q14");

        FST.add_set_transition("q0", "bcdfghjklmnpqrstvwxyz".toCharArray(), "q5");
        FST.add_transition("q5", 'y', 'i', "q6");
        FST.add_transition("q6", 'λ', 'e', "q7");
        FST.add_transition("q7", 'λ', 's', "q14");

        FST.add_set_transition("q0", "uaeio".toCharArray(), "q8");
        FST.add_transition("q8", 'y', 'y', "q9");
        FST.add_transition("q9", 'λ', 's', "q14");

        FST.add_transition("q0", 'f', 'v', "q10");
        FST.add_set_transition("q0", "abcdeghijklmnopqrstuvwxyz".toCharArray(), "q10");
        FST.add_transition("q10", 'e', 'e', "q11");
        FST.add_transition("q11", 'λ', 's', "q14");

        FST.add_set_transition("q0", "abdefghijklmnopqrtuvwxyz".toCharArray(), "q13");
        FST.add_transition("q13", 'h', 'h', "q15");
        FST.add_transition("q15", 'λ', 's', "q14");

        File in = new File("src/test.txt");
        Scanner sc = new Scanner(in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            for (String word : words) {
                System.out.print(word + "-->");
                FST.parse_input(word);
                System.out.println();
            }
        }
    }
}
