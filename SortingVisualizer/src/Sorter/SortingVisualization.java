package Sorter;

import java.util.ArrayList;
import java.util.Collections;

import Sorter.Sorts.*;

public class SortingVisualization {
    
    private static Thread sortingThread;

    public static VisualizerFrame frame;
    public static Integer[] array;
    public static boolean isSorting = false;
    public static int sizeOfArray = 200;
    public static int sleep = 5;
    public static int blockWidth;
    public static boolean incremental = false;
    public static void main(String[] args) throws Exception {
        frame = new VisualizerFrame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }
    public static void resetArray(){
        // If we are currently sorting the array, isSorting will be true and we don't want to reset the array while sorting
        if(isSorting) return;
        array = new Integer[sizeOfArray];
        blockWidth = (int)Math.max(Math.floor(500 / sizeOfArray), 1);

        for(int i = 0; i < array.length; i++){
            if(incremental){
                array[i] = i;
            } else {
                array[i] = (int)(sizeOfArray * Math.random());
            }
        }

        if(incremental){ // randomizes the array if incremental values were used
            ArrayList<Integer> shuffleArray = new ArrayList<>();
            for(int i = 0; i < array.length; i++){
                shuffleArray.add(array[i]);
            }
            Collections.shuffle(shuffleArray);
            array = shuffleArray.toArray(array);
        }
        frame.preDrawArray(array);
    }
    public static void startSorting(String algorithm){
        if(sortingThread == null || !(isSorting)){
            resetArray();

            isSorting = true;

            switch(algorithm){
                case "Bubble":
                        sortingThread = new Thread(new BubbleSort(array, frame));
                        break;
                case "Selection":
                        sortingThread = new Thread(new SelectionSort(array, frame));
                        break;
                case "Insertion":
                        sortingThread = new Thread(new InsertionSort(array, frame));
                        break;
                case "Merge":
                        sortingThread = new Thread(new MergeSort());
                        break;
                case "Quick":
                        sortingThread = new Thread(new QuickSort());
                        break;
                default:
                        isSorting = false;
                        return;
            }
            sortingThread.start();
        }
    }
}
