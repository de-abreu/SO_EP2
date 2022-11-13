package main;

public class RandomSequence {

    // Generate a randomly sorted sequence of n integers from 0 up to n - 1, n > 0
    public static int[] randomSequence(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++)
            array[i] = i;
        shuffle(array, n);

        return array;
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private static void shuffle(int[] array, int size) {
        if (--size < 1)
            return;
        swap(array, (int) (Math.random() * size), size);
        shuffle(array, size);
    }
}
