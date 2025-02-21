import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        String playerSign = getPlayerSign(data);
        String computerSign;

        if (playerSign.equals("X")) {
            computerSign = "O";
        } else {
            computerSign = "X";
        }

        int rounds = getValidInteger(data, "How many rounds do you want to play?");
        int playerWins = 0, computerWins = 0, ties = 0;

        while (true) {
            try {
                for (int i = 0; i < rounds; i++) {
                    String[][] board = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
                    int turns = 0;

                    System.out.println("\nRound " + (i + 1) + " begins!");
                    printBoard(board);

                    while (true) {
                        int position = getValidMove(data, board);
                        makeMove(board, position, playerSign);
                        printBoard(board);

                        if (checkWin(board, playerSign)) {
                            System.out.println("You won this round!");
                            playerWins++;
                            break;
                        }
                        turns++;
                        if (turns == 9) {
                            System.out.println("It's a tie!");
                            ties++;
                            break;
                        }

                        int compPosition = getComputerMove(board);
                        makeMove(board, compPosition, computerSign);
                        System.out.println("\nComputer's move:");
                        printBoard(board);

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

                System.out.println("\nFinal Results:");
                if (playerWins > computerWins) {
                    System.out.println("Congratulations! You won the game!");
                } else if (computerWins > playerWins) {
                    System.out.println("The computer won. Better luck next time!");
                } else {
                    System.out.println("It's a tie!");
                }

                break;
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                data.nextLine();
            }
        }
    }

    public static String getPlayerSign(Scanner data) {
        while (true) {
            try {
                System.out.print("Welcome to Tic-Tac-Toe! Choose 'X' or 'O': ");
                String sign = data.next().toUpperCase();
                if (sign.equals("X") || sign.equals("O")) {
                    return sign;
                }
                throw new IllegalArgumentException("Invalid input! Please enter 'X' or 'O'.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                data.nextLine();
            }
        }
    }

    public static int getValidInteger(Scanner data, String message) {
        while (true) {
            try {
                System.out.print(message + " ");
                return data.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                data.nextLine();
            }
        }
    }

    public static int getValidMove(Scanner data, String[][] board) {
        while (true) {
            try {
                System.out.print("Enter a position (1-9): ");
                int position = data.nextInt();
                if (position < 1 || position > 9) {
                    throw new IllegalArgumentException("Invalid input! Please enter a number between 1-9.");
                }
                if (!isPositionAvailable(board, position)) {
                    throw new IllegalArgumentException("Position occupied! Choose a different position.");
                }
                return position;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                data.nextLine();
            }
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
