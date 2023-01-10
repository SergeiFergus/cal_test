import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in); // сканер для считывания с консоли
        String eq = in.nextLine(); // считывание строки с консоли
        String[] eqs = eq.split(" "); // разбиение строки по пробелу. В итоге получаем массив со строками

        if (eqs.length != 3) {
            throw new Exception(); // если длина массива не равна 3 (т.к. у нас 2 числа и 1 действие - 3 элемента), то выкидываем исключение
        }

        int num1, num2; // задаём переменные для чисел
        // блок try-catch - пытаемся поймать ошибку
        try {
            num1 = Integer.parseInt(eqs[0]); // пытаемся парсить числа из строк
            num2 = Integer.parseInt(eqs[2]);
            if (num1 < 0 || num1 > 10 || num2 < 0 || num2 > 10) { // условие для чисел в отрезке от 1 до 10 вкл.
                throw new Exception();
            }

            int result = count(num1, num2, eqs[1]); // считаем результат нашего действия
            System.out.println(result); // выводим на консоль
        } catch (Exception e) { // если поймали ошибку
            try {
                num1 = parseNum(eqs[0]); // То пытаемся теперь спарсить арабские цифры из римских
                num2 = parseNum(eqs[2]);
                int result = count(num1, num2, eqs[1]); // считаем результат нашего действия
                if (result < 1) {
                    throw new Exception(); // если результат не положительный, выводим ошибку
                } else {
                    System.out.println(parseRome(result)); // если всё прошло хорошо, парсим результат в римские цифры и выводим в консоль
                }
            } catch (Exception ex) {
                throw ex; // если в ходе работы программы мы поймали ошибку, то выводим
            }
        }

        in.close(); // закрываем сканер
    }

    /*
    Функция для подсчёта результата действия
    Принимает аргументы в виде двух чисел и строки с действием
    Если действие не найдено или что-то пошло не так, то выкидывается ошибка
     */
    static int count(int num1, int num2, String action) throws Exception {
        int result;
        switch (action) {
            case ("+"):
                result = num1 + num2;
                break;
            case ("-"):
                result = num1 - num2;
                break;
            case ("*"):
                result = num1 * num2;
                break;
            case ("/"):
                result = num1 / num2; // Дробная часть откидывается
                break;
            default:
                throw new Exception();
        }
        return result;
    }

    /*
    Функция для парсинга числа из римской строки
    Может спарсить только строки, соотв. числам римского алфавита от 1 до 10, иначе выкидывает ошибку
    */
    static int parseNum(String num) throws Exception {
        int result;
        switch (num) {
            case ("I"):
                result = 1;
                break;
            case ("II"):
                result = 2;
                break;
            case ("III"):
                result = 3;
                break;
            case ("IV"):
                result = 4;
                break;
            case ("V"):
                result = 5;
                break;
            case ("VI"):
                result = 6;
                break;
            case ("VII"):
                result = 7;
                break;
            case ("IIX"):
                result = 8;
                break;
            case ("IX"):
                result = 9;
                break;
            case ("X"):
                result = 10;
                break;
            default:
                throw new Exception();
        }
        return result;
    }

    /*
    Функция для парсинга римского числа из арабского
    У нас есть два массива одинаковой длины - с числами и строками, которые соответствуют данным числам
    Затем мы в цикле проходим по всем числам из массива с числами
    На каждую итерацию у нас есть дополнительный цикл, в котором есть условие - переданное в функцию число больше или равно текущему числу из массива
    Если условие истинно, то мы выполняем цикл, в котором из введённого числа вычитаем текущее, а затем в строку результата добавляем соответствующую строку римского алфавита

    Пример:
    У нас есть число 6. Ищем В массиве число, которое подходит под условие. Первое такое число - это 5
    Получается нам надо из 6 вычесть 5
    6 - 5 = 1
    В строку с результатом добавляем соотетствующую римскую строку, т.е. V
    И наше текущее число теперь равно 1
    Следующее число подходящее под это условие - 1
    Далее опять вычитаем и добавляем строку
    1 - 1 = 0
    V + I = VI
    Массив исчерпан
     */
    static String parseRome(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
}