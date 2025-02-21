import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe! Choose 'X' or 'O': ");
        String playerSign = data.next().toUpperCase();
        String computerSign;
        
        if (playerSign.equals("X"))
            computerSign = "O";
        else
            computerSign = "X";

        System.out.println("How many rounds do you want to play?");
        int rounds = data.nextInt();
        int playerWins = 0, computerWins = 0, ties = 0;

        for (int i = 0; i < rounds; i++) {
            String[][] board = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
            int turns = 0;

            System.out.println("\nRound " + (i + 1) + " begins!");
            printBoard(board);

            while (true) {
                // Player Move
                int position = getPlayerMove(data, board);
                makeMove(board, position, playerSign);
                printBoard(board);

                // Check Win Condition
                if (checkWin(board, playerSign)) {
                    System.out.println("You won this round!");
                    playerWins++;
                    break;
                }
                turns++;

                // Check if tie
                if (turns == 9) {
                    System.out.println("It's a tie!");
                    ties++;
                    break;
                }

                // Computer Move
                int compPosition = getComputerMove(board);
                makeMove(board, compPosition, computerSign);
                System.out.println("\nComputer's move:");
                printBoard(board);

                // Check Win Condition
                if (checkWin(board, computerSign)) {
                    System.out.println("Computer won this round!");
                    computerWins++;
                    break;
                }
                turns++;
            }

            System.out.println("\nCurrent Scores:");
            System.out.println("Player: " + playerWins + " | Computer: " + computerWins + " | Ties: " + ties);
        }

        // Final Results
        System.out.println("\nFinal Results:");
        if (playerWins > computerWins) {
            System.out.println("Congratulations! You won the game!");
        } else if (computerWins > playerWins) {
            System.out.println("The computer won. Better luck next time!");
        } else {
            System.out.println("It's a tie!");
        }

        data.close();
    }

    public static void printBoard(String[][] board) {
        System.out.println("\n     |     |     ");
        System.out.println("  " + board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2] + "  ");
        System.out.println("     |     |     \n");
    }

    public static int getPlayerMove(Scanner data, String[][] board) {
        int position;
        while (true) {
            System.out.print("Enter a position (1-9): ");
            if (!data.hasNextInt()) {
                data.next(); // Clear invalid input
                System.out.println("Invalid input. Please enter a number between 1-9.");
                continue;
            }
            position = data.nextInt();

            if (position >= 1 && position <= 9 && isPositionAvailable(board, position)) {
                return position;
            }
            System.out.println("Invalid or occupied position, try again.");
        }
    }

    public static int getComputerMove(String[][] board) {
        Random random = new Random();
        int position;
        do {
            position = random.nextInt(9) + 1;
        } while (!isPositionAvailable(board, position));
        return position;
    }

    public static boolean isPositionAvailable(String[][] board, int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return !board[row][col].equalsIgnoreCase("X") && !board[row][col].equalsIgnoreCase("O");
    }

    public static void makeMove(String[][] board, int position, String sign) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        board[row][col] = sign;
    }

    public static boolean checkWin(String[][] board, String sign) {
        return (board[0][0].equals(sign) && board[0][1].equals(sign) && board[0][2].equals(sign)) ||
                (board[1][0].equals(sign) && board[1][1].equals(sign) && board[1][2].equals(sign)) ||
                (board[2][0].equals(sign) && board[2][1].equals(sign) && board[2][2].equals(sign)) ||
                (board[0][0].equals(sign) && board[1][0].equals(sign) && board[2][0].equals(sign)) ||
                (board[0][1].equals(sign) && board[1][1].equals(sign) && board[2][1].equals(sign)) ||
                (board[0][2].equals(sign) && board[1][2].equals(sign) && board[2][2].equals(sign)) ||
                (board[0][0].equals(sign) && board[1][1].equals(sign) && board[2][2].equals(sign)) ||
                (board[0][2].equals(sign) && board[1][1].equals(sign) && board[2][0].equals(sign));
    }
}