import java.util.Scanner;

public class Baguenaudier {
    //11111 -> 01111 -> 01011 -> 11011 -> 10011 -> 00011 -> 00010
    // true -> on; false -> off;
    // state[i] means the state of the (i+1)th ring
    // state[0] is the state of the first ring
    private boolean[] state;

    Baguenaudier(int n) {
        state = new boolean[n];
        for (int i = 0; i<state.length; i++) {
            state[i] = true;
        }
    }

    public void solveBaguenaudier() {
        printState();
        solve(state.length);
    }

    private void flip(int i) {
        state[i] = !state[i];
        printState();
    }

    private void solve(int n) {
        if (n == 1) {
            flip(0);    //Solving n means flipping the n-1 state.
        } else if (n == 2) {
            flip(1);
            solve(1);    //Base case: need to flip
        } else {
            solve(n-2);
            flip(n-1);
            unsolve(n-2);
            solve(n-1);
        }
    }

    private void unsolve(int n) {
        if (n == 1) {
            flip(0);
        } else if (n == 2) {
            unsolve(1);
            flip(1);
        } else {
            unsolve(n-1);
            solve(n-2);
            flip(n-1);
            unsolve(n-2);
        }
    }

    public void printState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state.length; i++) {
            sb.append(state[i] ? 1 : 0);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rings: ");
        int i = sc.nextInt();
        Baguenaudier puzzle = new Baguenaudier(i);
        System.out.println("Enter to begin solving the puzzle!");
        sc.nextLine();
        puzzle.solveBaguenaudier();
    }
}
