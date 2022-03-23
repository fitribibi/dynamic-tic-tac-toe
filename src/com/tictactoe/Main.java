package com.tictactoe;

import java.util.Scanner;

enum Player {
    X, O
}

class Board {
    private int n;
    private String[][] board;

    public Board(int n) {
        this.n = n;
        this.board = new String[n][n];
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
    }
}
