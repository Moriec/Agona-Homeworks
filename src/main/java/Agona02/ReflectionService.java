package Agona02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Scanner;


public class ReflectionService {
    /*
    Метод принимает путь к классу и возвращает инстанс этого обьекта
     */
    public static Object getInstance(String classLocation) throws ClassNotFoundException {
        Class <?> clazz = Class.forName(classLocation);
        Constructor<?>[] constructors = clazz.getConstructors();
        if (constructors.length == 0) {
            return null;
        }
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Доступные конструкторы:");
            for (int i = 0; i < constructors.length; i++) {
                System.out.print(i + ": (");
                Parameter[] params = constructors[i].getParameters();
                for (int j = 0; j < params.length; j++) {
                    System.out.print(params[j].getType().getSimpleName());
                    if (j < params.length - 1) System.out.print(", ");
                }
                System.out.println(")");
            }

            System.out.print("Выберите номер конструктора: ");
            int constructorIndex = Integer.parseInt(scanner.nextLine());

            Constructor<?> constructor = constructors[constructorIndex];
            Parameter[] params = constructor.getParameters();
            Object[] args = new Object[params.length];

            for (int i = 0; i < params.length; i++) {
                System.out.printf("Введите параметр %d типа %s: ", i + 1, params[i].getType().getSimpleName());
                String input = scanner.nextLine();
                args[i] = parseValue(input, params[i].getType());
            }

            return constructor.newInstance(args);

        } catch (Exception e) {
            System.out.println("Ошибка при создании экземпляра: " + e);
            return null;
        }

    }

    // Метод для парсинга строки в нужный тип
    private static Object parseValue(String value, Class<?> type) {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == float.class || type == Float.class) {
            return Float.parseFloat(value);
        }
        throw new IllegalArgumentException("Тип не поддерживается: " + type.getName());
    }
}
