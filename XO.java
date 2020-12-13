import java.util.Scanner;

public class HelloWorld {

    static String[] matrix = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    static boolean player1 = true;
    static String symbol;
    static int movements = 0;
    static boolean win = false;

    public static void main(String[] args) {
        draw();
        readInput();
    }

    private static void readInput() {
        Scanner getInput = new Scanner(System.in);
        String posStr = getInput.nextLine();
        int posInt;
        try {
            posInt = Integer.parseInt(posStr);
            updateMatrix(posInt);
            draw();
            readInput();
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            readInput();
        }
    }

    private static void updateMatrix(int index) {
        if (index < 1 || index > 9) {
            System.out.println("Out of range");
            return;
        }
        if (player1)
            symbol = "X";
        else
            symbol = "O";
        if (matrix[index - 1].equals("X") || matrix[index - 1].equals("O")) {
            System.out.println("Position taken");
            return;
        }
        matrix[index - 1] = symbol;
        player1 = !player1;
        movements++;
        if (movements > 4)
            checkWin();
        if (movements == 9){
            draw();
            System.out.println("No Winner");
            System.exit(1);
        }

    }

    public static void draw() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0)
                System.out.println();
            System.out.print(matrix[i] + " ");
        }
        System.out.println();
        if (player1 && !win)
            System.out.print("Player 1. ");
        else
            System.out.print("Player 2. ");
    }

    private static void checkWin() {
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:
                    if ((matrix[i].equals(symbol) && matrix[i + 1].equals(symbol) && matrix[i + 2].equals(symbol)) ||
                            (matrix[i].equals(symbol) && matrix[i + 4].equals(symbol) && matrix[i + 8].equals(symbol)) ||
                            (matrix[i].equals(symbol) && matrix[i + 3].equals(symbol) && matrix[i + 6].equals(symbol)))
                        winMessage();
                    break;
                case 1:
                    if (matrix[i].equals(symbol) && matrix[i + 3].equals(symbol) && matrix[i + 6].equals(symbol))
                        winMessage();
                    break;
                case 2:
                    if (matrix[i].equals(symbol) && matrix[i + 3].equals(symbol) && matrix[i + 6].equals(symbol))
                        winMessage();
                    break;
                case 3:
                    if (matrix[i].equals(symbol) && matrix[i + 1].equals(symbol) && matrix[i + 2].equals(symbol))
                        winMessage();
                    break;
                case 6:
                    if ((matrix[i].equals(symbol) && matrix[i + 1].equals(symbol) && matrix[i + 2].equals(symbol)) ||
                            (matrix[i].equals(symbol) && matrix[i - 2].equals(symbol) && matrix[i - 4].equals(symbol)))
                        winMessage();
                    break;
                default:
                    break;
            }
        }
    }

    private static void winMessage() {
        win = true;
        draw();
        System.out.println();
        System.out.println();
        if (!player1)
            System.out.print("Player 1  ");
        else
            System.out.print("Player 2  ");
        System.out.println("WINS, congratulation!");
        System.exit(1);
    }
}
