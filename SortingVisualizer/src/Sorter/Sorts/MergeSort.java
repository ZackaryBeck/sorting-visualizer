package Sorter.Sorts;

import Sorter.*;

public class MergeSort implements Runnable{
    public void run(){
        Integer[] array = SortingVisualization.array;
        Integer[] temp = new Integer[array.length];
        mergeSort(array, temp);
        SortingVisualization.isSorting = false;
    }
    public void mergeSort(Integer[] array, Integer[] temp){
        mergeSort(array, temp, 0, array.length - 1);
    }
    public void mergeSort(Integer[] array, Integer[] temp, int left, int right){
        if(left >= right){
            return;
        }
        int middle = (left + right) / 2;

        mergeSort(array, temp, left, middle);
        mergeSort(array, temp, middle + 1, right);

        merge(array, temp, left, right);
    }
    public void merge(Integer[] array, Integer[] temp, int left, int right){
        int leftEnd = (left + right) / 2;
        int rightStart = leftEnd + 1;
        int size = right - left + 1;

        int leftIndex = left;
        int rightIndex = rightStart;
        int index = left;

        while(leftIndex <= leftEnd && rightIndex <= right){
            if(array[leftIndex] <= array[rightIndex]){
                temp[index] = array[leftIndex];
                leftIndex++;
            } else {
                temp[index] = array[rightIndex];
                rightIndex++;
            }
            index++;
            SortingVisualization.frame.reDrawArray(array, leftEnd, leftIndex, rightStart);
            try{
                Thread.sleep(SortingVisualization.sleep);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        SortingVisualization.frame.reDrawArray(array);
        System.arraycopy(array, leftIndex, temp, index, leftEnd - leftIndex + 1);
        System.arraycopy(array, rightIndex, temp, index, right - rightIndex + 1);
        System.arraycopy(temp, left, array, left, size);
        SortingVisualization.frame.reDrawArray(array);
    }
}
