package laba1;

import java.lang.reflect.*;

public class Task1 {

    public static String analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return analyzeClass(clazz);
    }

    public static String analyzeClass(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();

        // Package
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            sb.append("Пакет: ").append(pkg.getName()).append("\n");
        }

        // Modifiers
        int modifiers = clazz.getModifiers();
        sb.append("Модифікатори: ").append(Modifier.toString(modifiers)).append("\n");

        // Class name
        sb.append("Назва класу: ").append(clazz.getSimpleName()).append("\n");

        // Superclass
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            sb.append("Суперкласс: ").append(superclass.getSimpleName()).append("\n");
        }

        // Implemented interfaces
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            sb.append("Реалізовані інтерфейси: ");
            for (Class<?> iface : interfaces) {
                sb.append(iface.getSimpleName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
            sb.append("\n");
        }

        // Fields
        Field[] fields = clazz.getDeclaredFields();
        sb.append("Поля:\n");
        for (Field field : fields) {
            sb.append("- ").append(Modifier.toString(field.getModifiers())).append(" ")
                    .append(field.getType().getSimpleName()).append(" ").append(field.getName()).append("\n");
        }

        // Constructors
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        sb.append("Конструктори:\n");
        for (Constructor<?> constructor : constructors) {
            sb.append("- ").append(Modifier.toString(constructor.getModifiers())).append(" ")
                    .append(constructor.getName()).append("(");
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                sb.append(parameter.getType().getSimpleName()).append(", ");
            }
            if (parameters.length > 0) {
                sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
            }
            sb.append(")\n");
        }

        // Methods
        Method[] methods = clazz.getDeclaredMethods();
        sb.append("Методи:\n");
        for (Method method : methods) {
            sb.append("- ").append(Modifier.toString(method.getModifiers())).append(" ")
                    .append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                sb.append(parameter.getType().getSimpleName()).append(", ");
            }
            if (parameters.length > 0) {
                sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
            }
            sb.append(")\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Example usage
        try {
            String className = "java.util.ArrayList";
            String classDescription = analyzeClass(className);
            System.out.println(classDescription);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}