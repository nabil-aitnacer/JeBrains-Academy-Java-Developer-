

import java.util.InputMismatchException;
import java.util.Scanner;

enum GameStatus {
    NOT_FINISHED,
    DRAW,
    X_WIN,
    O_WIN,
    IMPOSSIBLE
}

public class MainGame {
    static int[][] gameWinPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    static int[] gameLoop = new int[9];
    static int[][] coordinates = new int[4][4];
    static int _x, o;
    static Scanner scanner;
    static boolean noWinner = true;
    //Let 1 for X and 2 for O and 0 for _
    static GameStatus gameStatus = GameStatus.NOT_FINISHED;
    static int turn = 0;
    static int c = 0;
    static boolean done = false;

    public static void main(String[] args) {
        // write your code here

        scanner = new Scanner(System.in);


        SetCorrespondIndexInCoordinatesArray();
        PrintFiled(gameLoop);

        while (noWinner) {
            GetCoordinates();
        }


    }

    private static void TransferSymbolToArrayOfNumber(char[] symbols) {
        int index = 0;
        //add symbol to gameloop
        for (char cr : symbols) {
            AddToList(cr, index);
            index++;
        }
    }

    private static void AddToList(char cr, int i) {

        switch (cr) {
            case 'X':
                gameLoop[i] = 1;
                _x += 1;
                break;
            case 'O':
                gameLoop[i] = 2;
                _x += 1;
                break;
            case '_':
                gameLoop[i] = 0;
                break;
        }


    }

    private static void CheckTheWinner() {

        int win = 0;
        int winner = 0;
        int diff = _x - o;
        int sum = _x + o;
        if (diff >= 2 || diff < -1) {
            gameStatus = GameStatus.IMPOSSIBLE;

            return;
        }
        for (int[] pos : gameWinPosition) {
            if (gameLoop[pos[0]] == gameLoop[pos[1]] && gameLoop[pos[2]] == gameLoop[pos[1]]) {

                if (gameLoop[pos[0]] != 0) {

                    if (gameLoop[pos[0]] == 1) {
                        gameStatus = GameStatus.X_WIN;
                        return;
                    } else if (gameLoop[pos[0]] == 2) {
                        gameStatus = GameStatus.O_WIN;
                        return;
                    }
                }
            }
        }
        if (win >= 2) {
            gameStatus = GameStatus.IMPOSSIBLE;

            return;
        }
        if (sum == 9) {

            gameStatus = GameStatus.DRAW;
        }

    }

    private static void PrintStates(GameStatus state) {
        switch (state) {
            case NOT_FINISHED:
                System.out.println("NOT_FINISHED");
                break;
            case DRAW:
                System.out.println("Draw");
                EndGame();
                break;
            case X_WIN:
                System.out.println("X wins");
                EndGame();
                break;
            case O_WIN:
                System.out.println("O wins");
                EndGame();
                break;
            case IMPOSSIBLE:
                System.out.println("IMPOSSIBLE");
                break;

        }
        noWinner = true;
    }

    private static void PrintFiled(int[] fields) {
        System.out.println("---------");
        for (int i = 0; i < fields.length; i += 3) {
            System.out.println("| " + GetSymbol(fields[i]) + " " + GetSymbol(fields[i + 1]) + " " + GetSymbol(fields[i + 2]) + " " + "|");

        }
        System.out.println("---------");
    }

    private static String GetSymbol(int i) {
        switch (i) {
            case 0:
                return "_";

            case 1:
                return "X";

            case 2:
                return "O";

            default:
                return "";
        }
    }

    private static void GetCoordinates() {
        int x, y;

        while (done == false) {
            try {
                System.out.print("Enter the coordinates: ");
                y = scanner.nextInt();
                x = scanner.nextInt();
                if ((x == 1 || x == 2 || x == 3) && (y == 1 || y == 2 || y == 3)) {

                    if (gameLoop[coordinates[x][y]] != 0) {
                        System.out.println("This cell is occupied! Choose another one!");

                    } else {
                        if (turn == 0) {
                            c = 1;
                            turn += 1;
                            _x += 1;

                        } else if (turn == 1) {
                            c = 2;
                            o += 1;
                            turn -= 1;

                        }

                        gameLoop[coordinates[x][y]] = c;
                        PrintFiled(gameLoop);
                        CheckTheWinner();
                        PrintStates(gameStatus);


                    }

                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid integer input");
                scanner.next();
            }


        }


    }

    private static void SetSymbolToCorespandCooridnat() {
        boolean notCorrect = true;
        int x = 0;
        int y = 0;
        do {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                }

            } else {
                System.out.print("You should enter numbers!");
                continue;
            }


            if (gameLoop[coordinates[x][y]] != 0) {
                System.out.println("This cell is occupied! Choose another one!");

            } else {
                gameLoop[coordinates[x][y]] = 1;
                PrintFiled(gameLoop);
                notCorrect = false;
            }


        } while (notCorrect);

    }

    private static void SetCorrespondIndexInCoordinatesArray() {
        int index = 0;
        for (int i = 3; i > 0; i--) {
            for (int j = 1; j < 4; j++) {
                coordinates[i][j] = index;
                index++;
            }
        }
    }

    private static void EndGame() {
        noWinner = false;
        done = true;
        scanner.close();
    }
}
