public class RandomSequence {

    // Generate a randomly sorted sequence of n numbers from 1 to n
    public static int[] randomSequence(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++)
            array[i] = i + 1;
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
