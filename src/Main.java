
public class Main {

    public static double fun(double x) {
        return x * Math.sqrt(2 - Math.pow(x, 2));
    }


    public static double funpr1(double x) {
        return (-(2 * Math.pow(x, 2) - 2) / (Math.sqrt(2 - Math.pow(x, 2))));

    }

    public static double funpr2(double x) {
        return ((Math.sqrt(2 - Math.pow(x, 2))) * (2 * Math.pow(x, 3) - (6 * x))) / (Math.pow(x, 4) - 4 * Math.pow(x, 2) + 4);
    }


    public static void perebor(double a, double b, double eps) {
        int iterations = 0;
        double n = (b - a) / eps;
        double h = (b - a) / n;
        double result;
        double min = 9999;
        double arg = 0;

        for (double i = a; i <= b; i += h) {
            result = fun(i);
            iterations++;

            if (result < min) {
                arg = i;
                min = result;
            }
            System.out.println("Значение X: " + i + " Значение функции: " + result);
        }

        System.out.println("\nПри X = " + arg + " Минимальное значение функции = " + min);
        System.out.println("Количество итераций: " + iterations);
    }

    public static void dihotomia(double a, double b, double epsilon) {
        System.out.println("Метод Дихотомии");
        double x1;
        double x2;
        double del = epsilon / 2;
        double mid = (b - a) / 2;
        double min = 0;

        int iterations = 0;

        while (Math.abs(mid) > epsilon) {
            iterations++;

            x1 = (b + a - del) / 2;
            x2 = (b + a + del) / 2;

            if (fun(x1) <= fun(x2)) {
                b = x2;
            } else {
                a = x1;
            }

            mid = (b - a) / 2;
            min = (b + a) / 2;
        }

        System.out.println(min + "   " + fun(min));
        System.out.println("Number of iterations: " + iterations);
    }


    public static void Porazrpoisk(double a, double b, double eps) {
        double h = (b - a) / 4;
        double x1 = a;
        double x2 = x1 + h;
        int i = 0;
        while (Math.abs(h) > eps) {
            if (fun(x1) > fun(x2)) {
                x1 = x2;
                if (x1 > a && x1 < b) {
                    x2 += h;
                }
                i++;
            } else {
                x1 = x2;
                h /= -4;
                x2 += h;
                i++;
            }
        }
        System.out.println("Xmin = " + x2 + " F(Xmin) = " + fun(x2) + "\n");
        System.out.println("Итерации = " + i + "\n");
    }

    public static void midpoint(double a, double b, double eps) {
        double first = a;
        double sec = b;
        double x = (first + sec) / 2;

        int iterations = 0;

        while (Math.abs(sec - first) > eps) {
            iterations++;

            if (funpr1(x) > 0) {
                sec = x;
                x = (first + sec) / 2;
            } else {
                first = x;
                x = (first + sec) / 2;
            }
        }

        System.out.println("Xmin = " + x + " F(Xmin) = " + fun(x));
        System.out.println("Number of iterations: " + iterations);
    }


    public static void sechenie(double a, double b, double eps) {
        double tao = (Math.sqrt(5) - 1) / 2;
        double x1 = a + (1 - tao) * (b - a);
        double x2 = a + tao * (b - a);
        double fx1 = fun(x1);
        double fx2 = fun(x2);

        int iterations = 0; // Initialize iteration counter

        while (Math.abs(b - a) > eps) {
            iterations++; // Increment iteration counter

            if (fx1 > fx2) {
                a = x1;
                x1 = x2;
                x2 = a + tao * (b - a);
                fx1 = fx2;
                fx2 = fun(x2);
            } else {
                b = x2;
                x2 = x1;
                x1 = a + (1 - tao) * (b - a);
                fx2 = fx1;
                fx1 = fun(x1);
            }
        }

        double min = (b + a) / 2;
        System.out.println("Xmin = " + min + " F(Xmin) = " + fun(min));
        System.out.println("Number of iterations: " + iterations); // Print number of iterations
    }


    public static void Newton(double a, double b, double epsilon) {
        double x0 = -1.3;
        double x1 = x0 - (funpr1(x0)) / (funpr2(x0));
        int iterations = 1;

        while (funpr1(x1) > epsilon) {
            iterations++;
            x0 = x1;
            x1 = x0 - (funpr1(x0)) / (funpr2(x0));
        }

        System.out.println("Метод Ньютона");
        System.out.println("Xmin = " + x1 + " F(Xmin) = " + fun(x1));
        System.out.println("Number of iterations: " + iterations); // Print number of iterations
    }


    public static void hordmethod(double a, double b, double eps) {
        double first = a;
        double sec = b;
        double x_min = 0;

        int iterations = 0;

        if (funpr1(first) * funpr1(sec) < 0) {
            x_min = first - (funpr1(first)) / (funpr1(sec) - funpr1(first)) * (sec - first);

            while (Math.abs(funpr1(x_min)) > eps) {
                iterations++;

                if (funpr1(x_min) > 0) {
                    sec = x_min;
                    x_min = first - (funpr1(first)) / (funpr1(sec) - funpr1(first)) * (sec - first);
                } else {
                    first = x_min;
                    x_min = first - (funpr1(first)) / (funpr1(sec) - funpr1(first)) * (sec - first);
                }
            }
        } else {
            if ((funpr1(first) > 0 && funpr1(sec) > 0) || (funpr1(first) == 0)) {
                x_min = first;
            }
            if ((funpr1(first) < 0 && funpr1(sec) < 0) || (funpr1(sec) == 0)) {
                x_min = sec;
            }
        }

        System.out.println("Xmin = " + x_min + " F(Xmin) = " + fun(x_min));
        System.out.println("Number of iterations: " + iterations);
    }


    public static void NewtonRaphson(double a, double b, double eps) {
        double x0 = -1.3;
        double xl = x0 - (funpr1(x0)) / (funpr2(x0));
        double t = Math.pow(funpr1(x0), 2) / (Math.pow(funpr1(x0), 2) + funpr1(xl));
        double x1 = x0 - t * (funpr1(x0)) / (funpr2(x0));
        int i = 1;
        while (Math.abs(funpr1(x1)) > eps) {
            x0 = x1;
            xl = x0 - (funpr1(x0)) / (funpr2(x0));
            t = Math.pow(funpr1(x0), 2) / (Math.pow(funpr1(x0), 2) + funpr1(xl));
            x1 = x0 - t * (funpr1(x0)) / (funpr2(x0));
            i++;
        }
        System.out.println("Xmin = " + x1 + " F(Xmin) = " + fun(x1));
        System.out.println("i = " + i);
    }


    public static void main(String[] args) {
        double a = -1.4;
        double b = -0.5;
        double epsilon = 0.01;

        perebor(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        dihotomia(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        Newton(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("Метод золотого сечения");
        sechenie(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("Метод поразрядного поиска");
        Porazrpoisk(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("Метод средней точки");
        midpoint(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("Метод хорд");
        hordmethod(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("Метод Ньютона-Рафсона");
        NewtonRaphson(a, b, epsilon);
        System.out.println("/////////////////////////////////////////////////");

    }
}
