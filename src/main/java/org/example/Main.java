package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int WIN_COUNT = 3;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();

    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля

    /**
     * Инициализация игрового поля
     * */
    public static void initialize() {
        // Размер игрового поля
        fieldSizeX = 3;
        fieldSizeY = 3;

        field = new char[fieldSizeX][fieldSizeY];
        // Проход по всем элементам массива
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // Инициализация всех элементов массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }
    /**
     * Отрисовка игрового поля
     */
    private static void printField(){
        // Header
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();
        // left
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(i + 1 + "|");
            // Centre
            for (int j = 0; j < fieldSizeY; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        // Footer
        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        initialize();
        printField();
    }
}