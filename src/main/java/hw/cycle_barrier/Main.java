package main.java.hw.cycle_barrier;

/**
 * Есть два вида потоков, oxygen и hydrogen.
 * Ваша задача сгруппировать потоки и составить из них молекулы воды.
 * Должен быть барьер, где каждый поток ждет, пока не будет составлена молекула воды.
 * Для потоков должны быть методы releaseHydrogen и releaseOxygen, которые позволяют им "пройти барьер" и вывести в консоль Н или О.
 * Потоки обязательно проходят барьер только группами из 3 (2 гидрогена и 1 оксиген).
 * Так как после барьера идет вывод на консоль, убедитесь, что группа из 3 элементов была выведена до того,
 * как начнется вывод следующей группы (корректный вывод: НОН, ОНН, ННО, но не НООН - значит один атом оксигена попал из другой группы).
 * Другими словами:
 * если поток оксигена "подходит к барьеру", но нет потоков гидрогена, он ждет 2 потока гидрогена;
 * если поток гидрогена "подходит к барьеру" и нет других потоков, он ждет поток оксигена и еще один поток гидрогена.
 */

public class Main {
    public static void main(String[] args) {
        DataPreparation dataInput = new DataPreparation(21);
        H2OCyclicBarrier h2oCyclicBarrier = new H2OCyclicBarrier();

        String finalStr = dataInput.finalStr().toString();

        System.out.println("Input  : " + finalStr);

        System.out.print("Output : ");
        Runnable hydrogen = () -> System.out.print("H");
        Runnable oxygen = () -> System.out.print("O");

        for (int i = 0; i < finalStr.length(); i++) {
            char element = finalStr.charAt(i);
            switch (element) {
                case 'O':
                    new Thread(() -> {
                        try {
                            h2oCyclicBarrier.releaseOxygen(oxygen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
                case 'H':
                    new Thread(() -> {
                        try {
                            h2oCyclicBarrier.releaseHydrogen(hydrogen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
            }
        }
    }
}
