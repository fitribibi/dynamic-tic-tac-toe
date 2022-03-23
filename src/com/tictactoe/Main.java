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
