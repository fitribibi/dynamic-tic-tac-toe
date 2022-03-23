package com.tictactoe;

import java.util.Scanner;

enum Player {
    X, O
}

class Board {
    private int n;
    private String[][] board;

    // this two below attributes are used to store the last player position
    // and the attributes are set by method placeThePlayer() only
    private int lastRowIdx;
    private int lastColIdx;

    public Board(int n) {
        this.n = n;
        this.board = new String[n][n];
    }

    public boolean placeThePlayer(Player player, int position) {
        int rowIdx, colIdx;
        int divide = position / n;
        int modulo = position % n;

        if (modulo == 0) {
            rowIdx = divide - 1;
            colIdx = n - 1;
        } else {
            rowIdx = divide;
            colIdx = modulo - 1;
        }

        if (board[rowIdx][colIdx] == null) {
            board[rowIdx][colIdx] = player.name();
            lastRowIdx = rowIdx;
            lastColIdx = colIdx;
            return true;
        } else {
            return false;
        }
    }

    public boolean isPlayerWin(Player player) {
        if (preVerticalWin(player)) {
            return true;
        }

        if (preHorizontalWin(player)) {
            return true;
        }

        return preDiagonalWin(player);
    }

    // this method is used to check if the last player position has potential to win vertically,
    // if the previous and after of last row index on the board has same player's symbol
    // then scan one column of the board with column index = lastColIdx
    private boolean preVerticalWin(Player player) {
        boolean prevRow, afterRow;
        if (lastRowIdx - 1 >= 0) {
            prevRow = board[lastRowIdx - 1][lastColIdx] != null &&
                    board[lastRowIdx - 1][lastColIdx].equals(player.name()) ? true : false;
        } else {
            prevRow = true;
        }

        if (lastRowIdx + 1 < n) {
            afterRow = board[lastRowIdx + 1][lastColIdx] != null &&
                    board[lastRowIdx + 1][lastColIdx].equals(player.name()) ? true : false;
        } else {
            afterRow = true;
        }

        if (prevRow && afterRow) {
            return isVerticallyWin(player);
        }
        return false;
    }

    private boolean isVerticallyWin(Player player) {
        for (int i = 0; i < n; i++) {
            if (board[i][lastColIdx] == null || !board[i][lastColIdx].equals(player.name())) {
                return false;
            }
        }
        return true;
    }

    // this method is used to check if the last player position has potential to win horizontally,
    // if the previous and after of last column index on the board has same player's symbol
    // then scan one row of the board with row index = lastRowIdx
    private boolean preHorizontalWin(Player player) {
        boolean prevCol, afterCol;
        if (lastColIdx - 1 >= 0) {
            prevCol = board[lastRowIdx][lastColIdx - 1] != null &&
                    board[lastRowIdx][lastColIdx - 1].equals(player.name()) ? true : false;
        } else {
            prevCol = true;
        }

        if (lastColIdx + 1 < n) {
            afterCol = board[lastRowIdx][lastColIdx + 1] != null &&
                    board[lastRowIdx][lastColIdx + 1].equals(player.name()) ? true : false;
        } else {
            afterCol = true;
        }

        if (prevCol && afterCol) {
            return isHorizontallyWin(player);
        }
        return false;
    }

    private boolean isHorizontallyWin(Player player) {
        for (int i = 0; i < n; i++) {
            if (board[lastRowIdx][i] == null || !board[lastRowIdx][i].equals(player.name())) {
                return false;
            }
        }
        return true;
    }

    private boolean preDiagonalWin(Player player) {
        // Diagonal A is a top-left to bottom-right diagonal
        // Diagonal B is a top-right to bottom-left diagonal
        boolean prevDiagonalA, afterDiagonalA;
        boolean prevDiagonalB, afterDiagonalB;

        if (lastRowIdx == lastColIdx) {
            if (lastRowIdx - 1 >= 0) {
                prevDiagonalA = board[lastRowIdx - 1][lastColIdx - 1] != null &&
                        board[lastRowIdx - 1][lastColIdx - 1].equals(player.name()) ? true : false;
            } else {
                prevDiagonalA = true;
            }

            if (lastRowIdx + 1 < n) {
                afterDiagonalA = board[lastRowIdx + 1][lastColIdx + 1] != null &&
                        board[lastRowIdx + 1][lastColIdx + 1].equals(player.name()) ? true : false;
            } else {
                afterDiagonalA = true;
            }

            if (prevDiagonalA && afterDiagonalA) {
                return isADiagonallyWin(player);
            }
        }

        if ((lastRowIdx + lastColIdx) == (n - 1)) {
            if (lastRowIdx - 1 >= 0) {
                prevDiagonalB = board[lastRowIdx - 1][lastColIdx + 1] != null &&
                        board[lastRowIdx - 1][lastColIdx + 1].equals(player.name()) ? true : false;
            } else {
                prevDiagonalB = true;
            }

            if (lastRowIdx + 1 < n) {
                afterDiagonalB = board[lastRowIdx + 1][lastColIdx - 1] != null &&
                        board[lastRowIdx + 1][lastColIdx - 1].equals(player.name()) ? true : false;
            } else {
                afterDiagonalB = true;
            }

            if (prevDiagonalB && afterDiagonalB) {
                return isBDiagonallyWin(player);
            }
        }
        return false;
    }

    // Diagonal A is a top-left to bottom-right diagonal
    private boolean isADiagonallyWin(Player player) {
        for (int i = 0; i < n; i++) {
            if (board[i][i] == null || !board[i][i].equals(player.name())) {
                return false;
            }
        }
        return true;
    }

    // Diagonal B is a top-right to bottom-left diagonal
    private boolean isBDiagonallyWin(Player player) {
        int j = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (board[j][i] == null || !board[j][i].equals(player.name())) {
                return false;
            }
            j++;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Tic Tac Toe Board");
        System.out.println("(the number in each cell represent position)");

        int iteration = n * 2 - 1;
        int number = 1;
        int rowIdx = 0;
        int colIdx = 0;
        for (int i = 0; i < iteration; i++) {
            for (int j = 0; j < iteration; j++) {
                if (i % 2 != 0) {
                    if (j % 2 != 0) {
                        System.out.print(".");
                    } else {
                        System.out.print("__");
                    }
                } else {
                    if (j % 2 != 0) {
                        System.out.print("|");
                    } else {
                        if (board[rowIdx][colIdx] != null) {
                            System.out.print(board[rowIdx][colIdx] + " ");
                        } else {
                            if (number < 10) {
                                System.out.print(number + " ");
                            } else {
                                System.out.print(number);
                            }
                        }
                        number++;
                        colIdx++;
                    }
                }
            }
            colIdx = 0;
            if (i % 2 == 0) {
                rowIdx++;
            }
            System.out.println();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        // create a Scanner
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n (1 - 9) for tic tac toe n x n: ");
        int n = scanner.nextInt();

        // the program will stop if user doesn't input number between 1-9
        if (n < 1 || n > 9) {
            System.err.println("wrong number");
            System.exit(0);
        }

        Board board = new Board(n);
        board.printBoard();

        int counter = 0;
        int maxCounter = n * n;
        int pos;

        // the first turn is Player X
        Player player = Player.X;
        while (counter < maxCounter) {
            System.out.printf("input position for player %s: ", player.name());
            pos = scanner.nextInt();
            if (pos >= 1 && pos <= maxCounter) {
                if (board.placeThePlayer(player, pos)) {
                    board.printBoard();
                    if (board.isPlayerWin(player)) {
                        System.out.printf("player %s is win. The game is over!", player.name());
                        break;
                    }
                    counter++;
                } else {
                    System.out.println("wrong number");
                }
            } else {
                System.out.println("wrong number");
            }

            if (player.name().equals(Player.X.name())) {
                player = Player.O;
            } else {
                player = Player.X;
            }
        }
    }
}
