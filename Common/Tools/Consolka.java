package lab5.Common.Tools;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Для ввода команд и вывода результата
 */
public class Consolka implements Console {
    private static final String P = "$ Введите команду: ";
    private static Scanner fileScanner = null;
    private static Scanner defScanner = new Scanner(System.in);

    /**
     * Выводит obj.toString() в консоль
     * @param obj Объект для печати
     */
    public void print(Object obj) {

        System.out.print(obj);
    }
    /**
     * Выводит obj.toString() + \n в консоль
     * @param obj Объект для печати
     */
    public void println(Object obj) {
        System.out.println(obj);
    }
    /**
     * Выводит error: obj.toString() в консоль
     * @param obj Объект для печати
     */
    public void printError(Object obj) {
        System.err.println("Error: " + obj);
    }

    public String readln() throws NoSuchElementException, IllegalStateException {
        return (fileScanner!=null?fileScanner:defScanner).nextLine();
    }
    public boolean isCanReadln() throws IllegalStateException {
        return (fileScanner!=null?fileScanner:defScanner).hasNextLine();
    }
    /**
     * Выводит 2 колонки
     * @param elementLeft Левый элемент колонки.
     * @param elementRight Правый элемент колонки.
     */
    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
    }
    /**
     * Выводит prompt текущей консоли
     */
    public void prompt() {
        print(P);
    }
    /**
     * @return prompt текущей консоли
     */
    public String getPrompt() {
        return P;
    }
    public void selectFileScanner(Scanner scanner) {
        this.fileScanner = scanner;
    }
    public void selectConsoleScanner() {
        this.fileScanner = null;
    }
}
