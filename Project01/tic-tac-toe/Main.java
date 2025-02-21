import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        System.out.println("Welcome to tic tac toe game, please choose to play with 'x' or 'o' : ");
        String playerSign = data.nextLine();
        String computerSign = "";
        int turns = 0;
        if(playerSign.equalsIgnoreCase("x"))
            computerSign = "o";
        else
            computerSign = "x";

        System.out.println("You want to play 1 round or 3 rounds");
        int choice = data.nextInt();
        if(choice == 3){
            System.out.println("Since you chose 3 if you or the computer got 2 wins game will end");
        }

        int playerWins = 0;
        int computerWins = 0;

        while (true){
            String[][] gameBoard = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
            printBoard(gameBoard);
            int tie = 0;
            while (true){
                System.out.println("Enter a valid position from 1-9 to play");
                int position = data.nextInt();
                play(gameBoard,position,playerSign);
                System.out.println("Your move : ");
                printBoard(gameBoard);
                if (checkWin(gameBoard,playerSign)){
                    System.out.println("You won !!");
                    playerWins++;
                    break;
                }
                turns++;
                if (turns == 9){
                    System.out.println("Results : Tie");
                    tie++;
                    break;
                }
                computerPlay(gameBoard,computerSign);
                System.out.println("Computer move : ");
                printBoard(gameBoard);
                if (checkWin(gameBoard,computerSign)){
                    System.out.println("Computer won !!");
                    computerWins++;
                    break;
                }
                turns++;
            }
            System.out.println("Player wins : " + playerWins);
            System.out.println("Computer wins : " + computerWins);
            System.out.println("Tie : " + tie);
            if(choice == 1){
                break;
            } else if (choice == 3 && playerWins == 2) {
                System.out.println("Final result : You won !!!");
                break;
            } else if (choice == 3 && computerWins == 2) {
                System.out.println("Final result : Unfortunately the computer won");
                break;
            }
        }
    }
    public static void printBoard(String[][] gameBoard){
        System.out.println(gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2]);
        System.out.println("----------");
        System.out.println(gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2]);
        System.out.println("----------");
        System.out.println(gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2]);
    }
    public static String[][] play(String[][] gameBoard, int position, String xOrO){
        Scanner data = new Scanner(System.in);
        boolean condtiotn = true;
        while (condtiotn){
            switch (position){
                case 1:
                    if(gameBoard[0][0].equalsIgnoreCase("x") || gameBoard[0][0].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[0][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 2:
                    if(gameBoard[0][1].equalsIgnoreCase("x") || gameBoard[0][1].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[0][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 3:
                    if(gameBoard[0][2].equalsIgnoreCase("x") || gameBoard[0][2].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[0][2] = xOrO;
                        condtiotn = false;

                        break;                }
                case 4:
                    if(gameBoard[1][0].equalsIgnoreCase("x") || gameBoard[1][0].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[1][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 5:
                    if(gameBoard[1][1].equalsIgnoreCase("x") || gameBoard[1][1].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[1][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 6:
                    if(gameBoard[1][2].equalsIgnoreCase("x") || gameBoard[1][2].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[1][2] = xOrO;
                        condtiotn = false;

                        break;                }
                case 7:
                    if(gameBoard[2][0].equalsIgnoreCase("x") || gameBoard[2][0].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[2][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 8:
                    if(gameBoard[2][1].equalsIgnoreCase("x") || gameBoard[2][1].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[2][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 9:
                    if(gameBoard[2][2].equalsIgnoreCase("x") || gameBoard[2][2].equalsIgnoreCase("o")){
                        System.out.println("Positioned occupied, try again");
                        break;                }
                    else {
                        gameBoard[2][2] = xOrO;
                        condtiotn = false;

                        break;                }
                default:
                    return gameBoard;
            }

            if (condtiotn){
                System.out.println("Enter different position: ");
                position = data.nextInt();
            }
        }

        return gameBoard;
    }
    public static String[][] computerPlay(String[][] gameBoard, String xOrO){
        Random random = new Random();
        boolean condtiotn = true;

        while (condtiotn){
            int position = random.nextInt(1,10);
            switch (position){
                case 1:
                    if(gameBoard[0][0].equalsIgnoreCase("x") || gameBoard[0][0].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[0][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 2:
                    if(gameBoard[0][1].equalsIgnoreCase("x") || gameBoard[0][1].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[0][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 3:
                    if(gameBoard[0][2].equalsIgnoreCase("x") || gameBoard[0][2].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[0][2] = xOrO;
                        condtiotn = false;

                        break;                }
                case 4:
                    if(gameBoard[1][0].equalsIgnoreCase("x") || gameBoard[1][0].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[1][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 5:
                    if(gameBoard[1][1].equalsIgnoreCase("x") || gameBoard[1][1].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[1][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 6:
                    if(gameBoard[1][2].equalsIgnoreCase("x") || gameBoard[1][2].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[1][2] = xOrO;
                        condtiotn = false;

                        break;                }
                case 7:
                    if(gameBoard[2][0].equalsIgnoreCase("x") || gameBoard[2][0].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[2][0] = xOrO;
                        condtiotn = false;

                        break;                }
                case 8:
                    if(gameBoard[2][1].equalsIgnoreCase("x") || gameBoard[2][1].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[2][1] = xOrO;
                        condtiotn = false;

                        break;                }
                case 9:
                    if(gameBoard[2][2].equalsIgnoreCase("x") || gameBoard[2][2].equalsIgnoreCase("o")){
                        break;                }
                    else {
                        gameBoard[2][2] = xOrO;
                        condtiotn = false;

                        break;                }
                default:
                    return gameBoard;
            }
        }
        return gameBoard;
    }
    public static boolean checkWin(String[][] gameBoard, String xOrO) {
        //Check 8 cases for the win status!!
        if(
        //Horizontal check
        gameBoard[0][0].equalsIgnoreCase(xOrO) && gameBoard[0][1].equalsIgnoreCase(xOrO) && gameBoard[0][2].equalsIgnoreCase(xOrO) ||
        gameBoard[1][0].equalsIgnoreCase(xOrO) && gameBoard[1][1].equalsIgnoreCase(xOrO) && gameBoard[1][2].equalsIgnoreCase(xOrO) ||
        gameBoard[2][0].equalsIgnoreCase(xOrO) && gameBoard[2][1].equalsIgnoreCase(xOrO) && gameBoard[2][2].equalsIgnoreCase(xOrO) ||
        //Vertical check
        gameBoard[0][0].equalsIgnoreCase(xOrO) && gameBoard[1][0].equalsIgnoreCase(xOrO) && gameBoard[2][0].equalsIgnoreCase(xOrO) ||
        gameBoard[0][1].equalsIgnoreCase(xOrO) && gameBoard[1][1].equalsIgnoreCase(xOrO) && gameBoard[2][1].equalsIgnoreCase(xOrO) ||
        gameBoard[0][2].equalsIgnoreCase(xOrO) && gameBoard[1][2].equalsIgnoreCase(xOrO) && gameBoard[2][2].equalsIgnoreCase(xOrO) ||
        //Diagonal check
        gameBoard[0][0].equalsIgnoreCase(xOrO) && gameBoard[1][1].equalsIgnoreCase(xOrO) && gameBoard[2][2].equalsIgnoreCase(xOrO) ||
        gameBoard[0][2].equalsIgnoreCase(xOrO) && gameBoard[1][1].equalsIgnoreCase(xOrO) && gameBoard[2][0].equalsIgnoreCase(xOrO)
        )
            return true;
        else
            return false;
    }
}