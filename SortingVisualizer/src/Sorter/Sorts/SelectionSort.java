package Sorter.Sorts;

import Sorter.*;

public class SelectionSort implements Runnable{

     private Integer[] array;
     private VisualizerFrame frame;

     public SelectionSort(Integer[] array, VisualizerFrame frame){
         this.array = array;
         this.frame = frame;
     }
     public void run(){
         for(int i = 0; i < array.length - 1; i++){
             int minIndex = i;
             for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
                frame.reDrawArray(array, minIndex, j - 1);
                try{
                    Thread.sleep(SortingVisualization.sleep);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
             }
             int temp = array[minIndex];
             array[minIndex] = array[i];
             array[i] = temp;
         }
         SortingVisualization.isSorting = false;
     }
}
