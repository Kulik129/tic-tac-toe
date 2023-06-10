package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final int WIN_COUNT = 3;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();

    private static int fieldSizeY; // Размерность игрового поля
    private static int fieldSizeX; // Размерность игрового поля

    /**
     * Инициализация игрового поля
     */
    public static void initialize() {
        // Размер игрового поля
        fieldSizeY = 5;
        fieldSizeX = 5;

        field = new char[fieldSizeY][fieldSizeX];
        // Проход по всем элементам массива
        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++) {
                // Инициализация всех элементов массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {
        // Header
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();
        // left
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            // Centre
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        // Footer
        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода X и Y (от 1 до " + fieldSizeY + ") через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeY && y >= 0 && y < fieldSizeX;
    }

    /**
     * Ход компьютера
     */
    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeY);
            y = random.nextInt(fieldSizeX);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка победы*
     * @param c
     * @return
     */

    static boolean checkWin(char c){

        for (int i = 0; i < fieldSizeX; i++){ // проверка по горизонтали
            if (field[i][0] == c && field[i][1] == c && field[i][2] == c && field[i][3] == c && field[i][4] == c) return true;
        }
        for (int i = 0; i < fieldSizeY; i++){ // проверка по вертикали
            if (field[0][i] == c && field[1][i] == c && field[2][i] == c && field[3][i] == c && field[4][i] == c) return true;
        }
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c && field[3][3] == c && field[4][4] == c) return true; // проверка диагонали
        if (field[0][4] == c && field[3][1] == c && field[2][2] == c && field[1][3] == c && field[0][4] == c) return true; // проверка диагонали

        return false;
    }


    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++)
                if (isCellEmpty(x, y)) return false;
        }
        return true;
    }

    /**
     * Метод проверки состояния игры
     *
     * @param c
     * @param str
     * @return
     */
    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!"))
                    break;
            }
            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
    }
}