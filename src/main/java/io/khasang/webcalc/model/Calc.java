package io.khasang.webcalc.model;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Калькулятор. Корректно (учитывая приоритет операций) вычисляет результат выражений типа -5 + 2 * 5 / -2.5
 * знак числа можно обозначать как +5 или -5
 * Выдает результат в формате -5 + 2 * 5 / -2.5 = -9.0 и просит нажать Enter для продолжения вычислений
 * Обрабатывает всевозможные сценарии ошибочного ввода: лишние пробелы в начале, в середине и в конце выражения,
 * неизвестные символы, пропущенные знаки операций и пропущенные символы, запятые вместо точек.
 * Вычисляет выражение согласно приоритету знаков арифметических операций.
 *
 * @author Eugeny Karpov
 */
@Component
public class Calc {
    private String scannerString; // вводимая пользователем строка
    private List<Float> numbers; // коллекция чисел
    private List<String> symbols; // коллекция знаков операций

    public Calc() {
    }

    /**
     * основной метод калькулятора
     */
    // todo справить ситуацию с нулем
    public String launchCalc(String expression) {
        initCalc();
        try {
            prepareExpression(expression);
            calculateNumbers();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        return scannerString + " = " + numbers.get(0);
    }

    /**
     * Иницилализация коллекций и флага окончания работы программы
     */
    private void initCalc() {
        numbers = new ArrayList<Float>();
        symbols = new ArrayList<String>();
    }

    /**
     * Метод получения и обработки ползовательского выражения
     */
    private void prepareExpression(String expression) throws RuntimeException {
        boolean isOkInput = false;
        boolean isNextNumber = true;
        /*если вводимая пользователем строка не удовлетворяет требованиям, запросить строку закново*/
        isOkInput = false;
        isNextNumber = true; // флаг ожидания следующего символа - числа или знака операции
        numbers.clear();
        symbols.clear();
//            System.out.printf("\nВведите выражение в формате 3 + 5 * 2 / 2  знак числа можно обозначать как +5 или -5: ");
        scannerString = expression;
        scannerString = scannerString.trim();
        scannerString = scannerString.replaceAll("  +", " ");
        scannerString = scannerString.replace(".", ",");
        Scanner scannerFromString = new Scanner(scannerString);
            /*обработка строки и разбор на коллекции. В случае обнаружения некорректного формата выражения
            * или некорректного символа вывести соответствующее выражение и отправить на новый цикл ввода*/
        while (scannerFromString.hasNext()) {
            if (scannerFromString.hasNextFloat()) {
                if (isNextNumber) {
                    numbers.add(scannerFromString.nextFloat());
                    isNextNumber = false;
                } else {
                    throw new RuntimeException("Введен некорректный формат сообщения");
                }
            } else if (scannerFromString.hasNext("\\+") || scannerFromString.hasNext("\\-") ||
                    scannerFromString.hasNext("\\*") || scannerFromString.hasNext("\\/")) {
                if (!isNextNumber) {
                    symbols.add(scannerFromString.next());
                    isNextNumber = true;
                } else {
                    throw new RuntimeException("Введен некорректный формат сообщения");
                }
            } else {
                throw new RuntimeException("Введен некорректный символ");
            }
            isOkInput = true;
        }
            /*Если пользователь в конце строчки ввел не цифру, а знак операции, то убрать этот знак из коллекции и строки
            * В случае пустой строчки перехват исключения ArrayIndexOutOfBounds и отправка пользователя на новый цикл ввода*/
        if (symbols.size() == numbers.size()) {
            try {
                symbols.remove(symbols.size() - 1);
                scannerString = scannerString.substring(0, scannerString.length() - 2);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException("Введена пустая строчка");
            }
        }
        scannerString = scannerString.replace(",", ".");
        System.out.println(numbers);
        System.out.println(symbols);
    }

    /**
     * Считаем получившееся и подготовленное выражение (разобранное по коллекциям чисел и знаков операций):
     * сначала считаются умножение и деление, потом сложение и вычитание.
     * В случае выполнения операции результат помещается в i (3 + 3 это i и i+1), i + 1 удаляется.
     * Также удаляется выполненный знак операции из коллекции знаков операции
     * i декрементируется, чтобы не пропустить ни один элемент коллекции знаков операций
     * итератор не использован из-за необходимости изменения содержимого коллекций
     */
    private void calculateNumbers() throws RuntimeException {
        
        /*Ищем умножение и деление*/
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).equals("*")) {
                float tempResult = numbers.get(i) * numbers.get(i + 1);
                numbers.set(i, tempResult);
                numbers.remove(i + 1);
                symbols.remove(i);
                i--;
            } else if (symbols.get(i).equals("/")) {
                float tempResult = numbers.get(i) / numbers.get(i + 1);
                if (Float.isInfinite(tempResult) || Float.isNaN(tempResult)) {
                    throw new RuntimeException("На ноль делить нельзя");
                }
                numbers.set(i, tempResult);
                numbers.remove(i + 1);
                symbols.remove(i);
                i--;
            }
        }
        /*ищем сложение и вычитание*/
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).equals("+")) {
                float tempResult = numbers.get(i) + numbers.get(i + 1);
                numbers.set(i, tempResult);
                numbers.remove(i + 1);
                symbols.remove(i);
                i--;
            } else if (symbols.get(i).equals("-")) {
                float tempResult = numbers.get(i) - numbers.get(i + 1);
                numbers.set(i, tempResult);
                numbers.remove(i + 1);
                symbols.remove(i);
                i--;
            }
        }
    }
}
