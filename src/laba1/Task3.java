package laba1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


// Клас з методом, який будемо викликати
class TestClass {
    public double testMethod(double a) {
        return Math.exp(-Math.abs(a) * 1) * Math.sin(1);
    }

    public double testMethod(double a, int b) {
        return Math.exp(-Math.abs(a) * b) * Math.sin(b);
    }
}

// Головний клас з методом для виклику іншого методу за його ім'ям та параметрами
public class Task3 {
    public static void main(String[] args) {
        TestClass obj = new TestClass(); // Створення об'єкту класу TestClass

        // Виклик методу з виведенням результату або викиданням винятку
        try {
            callMethod(obj, "testMethod", Arrays.asList(1.0)); // Виклик методу з одним параметром типу double
            callMethod(obj, "testMethod", Arrays.asList(1.0, 1)); // Виклик методу з двома параметрами типу double та int
        } catch (FunctionNotFoundException e) {
            System.out.println("Функція не знайдена: " + e.getMessage());
        }
    }

    // Метод для виклику методу за його ім'ям та параметрами
    private static void callMethod(Object obj, String methodName, List<Object> params) throws FunctionNotFoundException {
        Class<?>[] paramTypes = new Class<?>[params.size()];
        Object[] paramValues = params.toArray();

        // Визначення типів параметрів
        for (int i = 0; i < params.size(); i++) {
            paramTypes[i] = params.get(i).getClass();
            // Виправлення на правильні типи для примітивних типів даних
            if (paramTypes[i] == Double.class) {
                paramTypes[i] = double.class;
            } else if (paramTypes[i] == Integer.class) {
                paramTypes[i] = int.class;
            }
        }

        try {
            // Знаходження методу за ім'ям та типами параметрів
            Method method = obj.getClass().getMethod(methodName, paramTypes);

            // Виклик методу та виведення результату
            System.out.println("Типи: " + Arrays.toString(paramTypes) + ", значення: " + Arrays.toString(paramValues));
            System.out.println("Результат виклику: " + method.invoke(obj, paramValues));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Обробка винятків
            throw new FunctionNotFoundException("Метод " + methodName + " не знайдено або недоступний.");
        }
    }
}






