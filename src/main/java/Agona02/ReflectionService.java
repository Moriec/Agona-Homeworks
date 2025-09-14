package Agona02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
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

    /*
    Метод, который получает на вход наш объект, и возвращает массив String состоящий из имен переменных
     */
    public static String[] getAtributesNames(Object obj) {
        Class<?> clazz = obj.getClass();
        Field [] fields = clazz.getDeclaredFields();
        String[] fieldsNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldsNames[i] = fields[i].getName();
        }
        return fieldsNames;
    }

    /*
    Создать метод, который меняет значение переменной, без разницы public/private,
    на вход он принимает Объект, имя переменной и Object(новое значение переменной),
     */
    public static void setField(Object obj, String fieldName, Object value) {
        try {
            Class<?> clazz = obj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> type = field.getType();
            try {
                field.set(obj, type.cast(value));
            } catch (IllegalAccessException e) {
                field.setAccessible(true);
                field.set(obj, type.cast(value));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Метод, который возвращает список методов класса в формате списка имен методов,
    на вход он принимает объект
    */
    public static String[] getMethodsName(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        String[] methodsNames = new String[methods.length];
        for (int i = 0; i < methods.length; i++) {
            methodsNames[i] = methods[i].getName();
        }
        return methodsNames;
    }
}
