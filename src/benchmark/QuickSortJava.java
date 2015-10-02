package benchmark;

/**
 * Created by abdhesh on 7/23/15.
 */
public class QuickSortJava {
    public static void main(String[] args) {

    }

    public static void quickSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int pivot = array[right];
        int p = left;
        int i = left;
        while (i < right) {
            if (array[i] < pivot) {
                if (p != i) {
                    int tmp = array[p];
                    array[p] = array[i];
                    array[i] = tmp;
                }
                p += 1;
            }
            i += 1;
        }
        array[right] = array[p];
        array[p] = pivot;
        quickSort(array, left, p - 1);
        quickSort(array, p + 1, right);
    }
}
