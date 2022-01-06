package Sorter.Sorts;

import Sorter.*;

public class BubbleSort implements Runnable{

    private Integer[] array;
    private VisualizerFrame frame;

    public BubbleSort(Integer[] array, VisualizerFrame frame){
        this.array = array;
        this.frame = frame;
    }
    public void run(){
        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                frame.reDrawArray(array, j, j + 1);
                try{
                    Thread.sleep(SortingVisualization.SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        SortingVisualization.isSorting = false;
    }
}
