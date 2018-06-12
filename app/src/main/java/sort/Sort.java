package sort;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenming on 16/12/23.
 * 排序算法研究
 */

public class Sort {
    /**
     * 冒泡排序算法:
     * 从头开始遍历, 每一次遍历都将最小值往前交换,n-1次循环，每次循环最小值会上浮
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
            printArray(arr);
        }
    }

    /**
     * 选择排序, 冒泡排序优化,n-1次循环,每次找到最小值，与排头兵交换
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, minIndex, i);
            }
            printArray(arr);
        }
    }

    /**
     * 插入排序,i = x[1....maxIndex],a[i]表示新进来的牌,遍历已经排好的序列[0...i-1],将大于a[i]的
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int toInsertValue = arr[i];//新来的"牌"
            int j = i;//从右往左遍历
            while (j > 0 && arr[j - 1] > toInsertValue) {
                arr[j] = arr[j - 1];//大的牌右移
                j--;
            }
            arr[j] = toInsertValue;//合适位置
            printArray(arr);
        }
    }

    /**
     * 和直接插入不同，插入排序时，二分法查找插入位置
     * @param a
     */
    public static void binaryInsertSort(int [] a){
        for(int i = 0;i<a.length;i++){
            int temp = a[i];//´ý²åÈëµ½Ç°ÃæÓÐÐòÐòÁÐµÄÖµ
            int left  = 0;
            int right = i - 1;
            int mid = 0;
            while(left<=right){
                mid = (left+right)/2;
                if(temp<a[mid]){//往左边查找
                    right = mid -1;
                }else{//往右边查找
                    left  = mid + 1;
                }
            }
            for(int j = i-1;j>=left;j--){
                //left及右边的元素右移
                a[j+1] = a[j];
            }
            if(left!=i){//填坑
                a[left] = temp;
            }
        }
    }

    /**
     * 快速排序,分治法:每一次划分，指定哨兵值，从两端向中间遍历,将大于它的元素放在右边,小于它的元素放在左边,然后将哨兵值填入中间位置
     *
     * @param arr
     */
    public static void puickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归分治法
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {//递归结束
            return;
        }
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos - 1);
        quickSort(arr, pivotPos + 1, right);
    }


    /**
     * 分治法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        int target = arr[left];//哨兵值
        while (left < right) {
            while (left < right && arr[right] >= target) {
                right--;
            }
            arr[left] = arr[right];//挖坑，小值左移
            while (left < right && arr[left] <= target) {
                left++;
            }
            arr[right] = arr[left];//挖坑，大值右移
        }
        arr[left] = target;//填坑
        printArray(arr);
        return left;
    }

    /**
     * 交换
     *
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
     *
     * @param arr
     */
    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int item : arr) {
            sb.append(item);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    /**
     * @param arr
     */
    private static void shellInsert(int[] arr, int d) {
        for (int i = d; i < arr.length; i++) {
            int toInsertValue = arr[i];//新来的"牌"
            int j = i;//从右往左遍历
            while (j > 0 && arr[j - d] > toInsertValue) {
                arr[j] = arr[j - d];//后移
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
     * 在大量实验的基础上推出当n在某个范围内时，时间复杂度可以达到O(n^1.3)。
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        while (gap >= 1) {
            shellInsert(arr, gap);
            gap /= 2;
        }
    }

    /**
     * 归并排序,原理:分治思想，划分两个子序列，然后将两个排好的子序列合并.空间复杂度O(n), 时间复杂度O(nlgn)
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归分治
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (arr == null || left >= right) {//递归结束
            return;
        }
        int mid = (left + right) / 2;//数组一分为二
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        mergeArr(arr, left, mid, right);
    }

    /**
     * 左右都排好序的前提下，合并arr
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void mergeArr(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int index = 0;

        while (i <= mid && j <= right) {//俩数组索引均未越界
            if (arr[i] <= arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }
        //数组剩余部分
        while (i <= mid) {
            tmp[index++] = arr[i++];
        }

        while (j <= right) {
            tmp[index++] = arr[j++];
        }

        //数组copy
        for (int p = 0; p < tmp.length; p++) {
            arr[left + p] = tmp[p];
        }

        printArray(arr);

    }

    /**
     * 计数排序:找到数组最大值max, 创建引入数组tmp[max+1],
     * 然后遍历待排序数组arr[i],做tmp[arr[i]]++操作,这样
     * tmp每个位置就记录了arr的元素分布，最后从低到高，将元素还原，实现排序
     * 空间复杂度O(n),时间复杂度O(n)!
     *
     * @param arr
     */
    public static void countSort(int[] arr) {
        int max = max(arr);
        int[] countArr = new int[max + 1];
        Arrays.fill(countArr, 0);

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;//相应的索引值+1
        }
        printArray(countArr);
        int scanIndex = 0;
        //还原数组
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                arr[scanIndex++] = i;
            }
        }

        printArray(arr);

    }

    private static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int ele : arr) {
            if (ele > max)
                max = ele;
        }

        return max;

    }

    /**
     * 将数组划分M个桶，每个桶最多N个元素,两者关系 M = max/N+1,然后将每个元素根据索引映射函数确定属于哪个桶
     * 桶划分好之后，分别进行快速排序,空间复杂度M+N,时间复杂度:O(n+M*(N*logN)),最佳可以达到O(n)，
     * 弊端:数组的最大值对桶的个数有决定影响,当max和均值差距比较大时，会造成空间的急剧浪费
     *
     * @param arr
     */
    public static void bucketSort(int[] arr) {
        int bucketNum = max(arr) / 10 + 1;//每个桶10个元素
        List<List<Integer>> buckets = new LinkedList<>();

        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = arr[i] / 10;
            buckets.get(bucketIndex).add(arr[i]);
        }

        //每个桶排序
        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> integers = buckets.get(i);
            if (!integers.isEmpty()) {
                Collections.sort(integers);
            }
        }

        //还原排序数组
        int k = 0;
        for (int i = 0; i < buckets.size(); i++) {
            for (Integer item : buckets.get(i)) {
                arr[k++] = item;
            }
        }

        printArray(arr);
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        buildMaxHeap(arr);//先构造最大堆，此时[0]为最大值
        for (int i = arr.length - 1; i >= 1; i--) {
            swap(arr, 0, i);//0位置是最大值，放到最后的位置
            maxHeap(arr, i, 0);//继续构造大堆
        }
    }

    private static void buildMaxHeap(int[] arr) {
        int half = (arr.length - 1) / 2;
        for (int i = half; i >= 0; i--) {//从下往上构造最大堆,一直到根节点
            maxHeap(arr, arr.length, i);
        }
    }

    /**
     * 构建最大堆
     *
     * @param arr 数组
     * @param len 范围
     * @param i   根节点索引
     */
    private static void maxHeap(int[] arr, int len, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        int tmp = arr[i];
        if (left < len) {
            if (tmp < arr[left]) {
                largest = left;
            }
        }
        if (right < len) {
            if (arr[largest] < arr[right]) {
                largest = right;
            }
        }
        if (i != largest) {//堆发生变化，则将最大值上升到根节点，根节点下沉到叶子节点，

            swap(arr, i, largest);//一棵树的最大值放到堆顶
            maxHeap(arr, len, largest);// 此时根节点下沉到largest节点，此节点发生变化，因此将次子树构造最大堆
        }

    }

    /**
     * 基数排序(计数排序的拓展)，桶结构为二维数组,适用于大于0的整数组
     * 第一次排序按个位数的索引放到桶中
     * 第二次排序按照十位数索引放到桶中
     * 第三次排序按照百威数索引放到桶中
     * ...
     * 每一次遍历就将同量级的数字排序。
     * 步骤:
     * 1>获取最大数的位数m,它决定了遍历次数
     * 2>每一次遍历，取各个元素对应位数x，然后放到桶中
     * 3>每一次遍历完成，按顺序搜集数据放到arr中
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //找到最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //计算最大值的位数
        int count = 0;
        int tmp = max;
        while (tmp > 0) {
            tmp = tmp / 10;
            count++;
        }

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        //初始化二维数组,大小为10
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> link = new ArrayList<>();
            buckets.add(link);
        }

        for (int i = 0; i < count; i++) {//按位数从低到高遍历
            //数据入桶
            for (int j = 0; j < arr.length; j++) {
                int x = (int) (arr[j] % Math.pow(10, i + 1) / Math.pow(10, i));//个位-十位-百位
                ArrayList<Integer> link = buckets.get(x);
                link.add(arr[j]);
            }
            //遍历桶数据，放到arr里
            int index = 0;
            for (int m = 0; m < buckets.size(); m++) {
                ArrayList<Integer> link = buckets.get(m);
                //取出桶中元素，放到arr里
                while (link.size() > 0){
                    int item = link.remove(0);
                    arr[index++] = item;
                }
            }
        }

    }
}