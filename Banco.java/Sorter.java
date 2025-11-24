// Sorter.java
// Algoritmos de ordenamiento: Merge Sort y Quick Sort.

import java.util.*;

public class Sorter {

    // Merge Sort por balance
    public static List<Account> mergeSortByBalance(List<Account> list) {
        if (list.size() <= 1) return new ArrayList<>(list);

        int mid = list.size() / 2;

        List<Account> left = mergeSortByBalance(list.subList(0, mid));
        List<Account> right = mergeSortByBalance(list.subList(mid, list.size()));

        return merge(left, right);
    }

    private static List<Account> merge(List<Account> a, List<Account> b) {
        List<Account> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i).getBalance() >= b.get(j).getBalance()) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));

        return result;
    }

    // Quick Sort por nombre
    public static List<Account> quickSortByName(List<Account> list) {
        List<Account> copy = new ArrayList<>(list);
        quickSort(copy, 0, copy.size() - 1);
        return copy;
    }

    private static void quickSort(List<Account> arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Account> arr, int low, int high) {
        String pivot = arr.get(high).getHolderName().toLowerCase();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr.get(j).getHolderName().toLowerCase().compareTo(pivot) <= 0) {
                i++;
                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, i + 1, high);
        return i + 1;
    }
}


