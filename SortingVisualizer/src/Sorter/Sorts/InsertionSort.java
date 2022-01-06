package Sorter.Sorts;

import Sorter.*;

public class InsertionSort implements Runnable{
    
    private Integer[] array;
    private VisualizerFrame frame;

    public InsertionSort(Integer[] array, VisualizerFrame frame){
        this.array = array;
        this.frame = frame;
    }
    public void run(){
        for(int i = 1; i < array.length; i++){
            int key = array[i];
            int j = i - 1;
            while(j >= 0 && array[j] > key){
                array[j + 1] = array[j];
                j = j - 1;
            }
            frame.reDrawArray(array, j, key);
            try{
                Thread.sleep(5);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            array[j + 1] = key;
        }
        SortingVisualization.isSorting = false;
    }
}
