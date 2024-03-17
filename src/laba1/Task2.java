package laba1;

import java.lang.reflect.*;
import java.util.Scanner;

public class Task2 {
    public static void inspect(Object obj) {
        // Отримуємо клас об'єкту
        Class<?> cls = obj.getClass();
        System.out.println("Тип об'єкту: " + cls.getName());

        // Виводимо стан об'єкту - список полів з їхніми значеннями
        System.out.println("Стан об'єкту:");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getType().getName() + " " + field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Формуємо список відкритих методів
        System.out.println("Список відкритих методів:");
        Method[] methods = cls.getMethods();
        int count = 1;
        for (Method method : methods) {
            System.out.println(count + "). " + method.toString());
            count++;
        }

        // Вибір методу для виклику
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть порядковий номер методу [1 , " + methods.length + "]: ");
        int methodIndex = scanner.nextInt();

        // Виклик обраного методу без параметрів та виведення результату
        try {
            Method selectedMethod = methods[methodIndex - 1];
            Object result = selectedMethod.invoke(obj);
            System.out.println("Результат виклику методу: " + result);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Створення об'єкту для перевірки
        Check check = new Check(5.0, 6.0);

        // Виклик методу для аналізу об'єкту
        inspect(check);
    }
}

class Check {
    private double x;
    private double y;

    public Check(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double Dist() {
        return Math.sqrt(x * x + y * y);
    }

    public void setRandomData() {
        this.x = Math.random();
        this.y = Math.random();
    }

    public void setData(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Check{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}



