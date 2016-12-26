package sort;

import android.util.Log;

/**
 * Created by chenming on 16/12/23.
 * 排序算法研究
 */

public class Sort {
    /**
     * 冒泡排序算法:
     * 从头开始遍历, 每一次遍历都将最小值往前交换,n-1次循环，每次循环最小值会上浮
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = arr.length - 1; j > i; j--){
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                }
            }
            printArray(arr);
        }
    }

    /**
     * 选择排序, 冒泡排序优化,n-1次循环,每次找到最小值，与排头兵交换
     * @param arr
     */
    public static void selectSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                swap(arr, minIndex, i);
            }
            printArray(arr);
        }
    }

    /**
     * 插入排序,i = x[1....maxIndex],a[i]表示新进来的牌,遍历已经排好的序列[0...i-1],将大于a[i]的
     * @param arr
     */
    public static void insertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int toInsertValue = arr[i];//新来的"牌"
            int j = i;//从右往左遍历
            while(j > 0 && arr[j-1] > toInsertValue){
                arr[j] = arr[j-1];//
                j--;
            }
            arr[j] = toInsertValue;//合适位置
            printArray(arr);
        }
    }

    /**
     * 快速排序,分治法:每一次划分，指定哨兵值，从两端向中间遍历,将大于它的元素放在右边,小于它的元素放在左边,然后将哨兵值填入中间位置
     * @param arr
     */
    public static void puickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    /**
     * 递归分治法
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        if(left >= right)
            return ;
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos-1);
        quickSort(arr, pivotPos+1, right);
    }


    private static int partition(int[] arr, int left, int right){
        int target = arr[left];
        while (left < right){
            while (left < right && arr[right] >= target){
                right--;
            }
            arr[left] = arr[right];//挖坑，大值左移
            while (left < right && arr[left] <= target){
                left++;
            }
            arr[right] = arr[left];//挖坑，小值右移
        }
        arr[left] = target;//填坑
        printArray(arr);
        return left;
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印
     * @param arr
     */
    private static void printArray(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int item: arr){
            sb.append(item);
            sb.append(" ");
        }
        Log.e("TAG", sb.toString());
    }

    /**
     * @param arr
     */
    private static void shellInsert(int[] arr, int d){
        for(int i = d; i < arr.length; i++){
            int toInsertValue = arr[i];//新来的"牌"
            int j = i;//从右往左遍历
            while(j > 0 && arr[j-d] > toInsertValue){
                arr[j] = arr[j-d];//后移
                j = j - d;
            }
            arr[j] = toInsertValue;//合适位置
        }
        printArray(arr);
    }

    /**
     * 原理:按增量对数组分组，分别进行插入排序.它比直接插入排序算法效率高的原因:
     * 1.当文件初态基本有序时直接插入排序所需的比较和移动次数均较少。
     * 2.在希尔排序开始时增量较大，分组较多，每组的记录数目少，故各组内直接插入较快，后来增量d逐渐缩小，
     * 分组数逐渐减少，而各组的记录数目逐渐增多，但由于已经按di-1作为距离排过序，使文件较接近于有序状态，
     * 所以新的一趟排序过程也较快。
     * @param arr
     */
    public static void shellSort(int[] arr){
        int gap = arr.length/2;
        while (gap >= 1){
            shellInsert(arr, gap);
            gap /= 2;
        }
    }



}
