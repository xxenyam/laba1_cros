package laba1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Evaluatable {
    double evalf(double x);
}

class Function1 implements Evaluatable {
    @Override
    public double evalf(double x) {
        double a = 2.5;
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }
}

class Function2 implements Evaluatable {
    @Override
    public double evalf(double x) {
        return x * x;
    }
}

class Profiler implements InvocationHandler {
    private final Evaluatable target;

    public Profiler(Evaluatable target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();

        String methodName = method.getName();
        String message = "[" + target.getClass().getSimpleName() + "]." + methodName;

        if (args == null || args.length == 0) {
            System.out.println(message + " took " + (endTime - startTime) + " ns");
        } else {
            StringBuilder argString = new StringBuilder("(");
            for (Object arg : args) {
                argString.append(arg).append(", ");
            }
            argString.setLength(argString.length() - 2); // Remove trailing comma and space
            argString.append(")");

            System.out.println(message + argString + " = " + result);
        }

        return result;
    }
}

public class Task5 {
    public static void main(String[] args) {
        Evaluatable function1 = new Function1();
        Evaluatable function2 = new Function2();

        Evaluatable profilerFunction1 = createProfiledInstance(function1);
        Evaluatable profilerFunction2 = createProfiledInstance(function2);

        System.out.println("F1: " + profilerFunction1.evalf(0.0));
        System.out.println("F2: " + profilerFunction2.evalf(0.0));
        System.out.println();

        System.out.println("F1: " + profilerFunction1.evalf(1.0));
        System.out.println("F2: " + profilerFunction2.evalf(1.0));
    }

    public static Evaluatable createProfiledInstance(Evaluatable target) {
        return (Evaluatable) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Profiler(target)
        );
    }
}
