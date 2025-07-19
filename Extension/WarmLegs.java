import java.util.Random;

public class WarmLegs {
    public static void main(String[] args) {
        Random random = new Random();
        int pointlessSum = 0;

        while (true) {
            // Outer loop to intensify CPU load
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                // Inner loop with random computations for additional inefficiency
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    pointlessSum += random.nextInt(1000) - random.nextInt(1000);
                    
                    // Ensure pointlessSum doesn't overflow by resetting occasionally
                    if (pointlessSum > 1_000_000 || pointlessSum < -1_000_000) {
                        System.out.println(pointlessSum);
                        
                    }
                }
            }
        }
    }
}
