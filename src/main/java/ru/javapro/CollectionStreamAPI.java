package ru.javapro;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionStreamAPI {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);

        Employee emp1 = new Employee("Pete", 21, "Инженер");
        Employee emp2 = new Employee("Bob", 26, "Инженер");
        Employee emp3 = new Employee("Pete_admin", 22, "Администратор");
        Employee emp4 = new Employee("George", 31, "Инженер");
        Employee emp5 = new Employee("Michael", 32, "Инженер");
        List<Employee> employees = Arrays.asList(emp1, emp2, emp3,emp4, emp5);

        List<String> listWords = Arrays.asList("Это", "не", "самое", "длинное", "слово");

        String[] stringArray = {"Это обычный массив пять слов", "Это массив состоит из слов", "Короткая строка в массиве строк", "Пример длинной строки в массиве"};

        String str = "это пример строки для задания это";

        // Удаление дубликатов из списка
        List<Integer> uniqueList = list.stream().distinct().collect(Collectors.toList());
        System.out.println("Уникальный список: " + uniqueList);

        // Нахождение 3-го наибольшего числа в списке
        int thirdLargest = list.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().get();
        System.out.println("3-е наибольшее число: " + thirdLargest);

        // Нахождение 3-го наибольшего уникального числа в списке
        int thirdUniqueLargest = list.stream().distinct().sorted(Comparator.reverseOrder()).skip(2).limit(1).findFirst().get();
        System.out.println("3-е наибольшее уникальное число: " + thirdUniqueLargest);

        // Получить список имен 3 самых старших сотрудников с должностью "Инженер", в порядке убывания возраста
            System.out.println( employees.stream()
                    .filter(emp -> emp.getTitle().equals("Инженер"))
                    .sorted(Comparator.comparing(Employee::getAge).reversed())
                    .limit(3)
                    .map(Employee::getName)
                    .collect(Collectors.toList()));

        // Посчитать средний возраст сотрудников с должностью "Инженер"
            System.out.println( employees.stream()
                    .filter(emp -> emp.getTitle().equals("Инженер"))
                    .mapToDouble(Employee::getAge)
                    .average()
                    .orElse(0.0));

        // Найти самое длинное слово в списке
        System.out.println(listWords.stream()
                    .max(Comparator.comparing(String::length))
                    .orElse(""));

        // Построить хеш-мапу слов и их количества в строке
        System.out.println(Arrays.stream(str.split(" ")).collect(Collectors.toMap(word -> word, word -> 1, Integer::sum)));

        // Отпечатать строки из списка, отсортированные по увеличению длины слов и алфавиту
            listWords.stream()
                    .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
                    .forEach(System.out::println);

        // Найти самое длинное слово из массива строк
        System.out.println(Arrays.stream(stringArray)
                    .flatMap(wordString -> Arrays.stream(wordString.split(" ")))
                    .max(Comparator.comparing(String::length))
                    .orElse(""));
        }


}