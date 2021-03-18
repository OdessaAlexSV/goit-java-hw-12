package main.java.hw.cycle_barrier;

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
                            h2oCyclicBarrier.oxygen(oxygen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
                case 'H':
                    new Thread(() -> {
                        try {
                            h2oCyclicBarrier.hydrogen(hydrogen);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    break;
            }
        }
    }
}
