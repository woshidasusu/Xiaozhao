package coder.dasu.xiaozhao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sxq on 2016/9/19.
 * 从小到大排序
 */
public class SortTest {


    /**
     * 直接插入排序,第一个默认有序，之后取一个插一个,之前的往后移。
     * O(n^2)
     */
    public int[] insertDirectSort(int[] a){
        for (int i=1;i<a.length;i++){
            int num = a[i];
            int j=i-1;
            for (;j>=0 && num < a[j];j--){
                a[j+1] = a[j];
            }
            a[j+1] = num;
        }
        return a;
    }

    /**
     * 选择排序
     * O(n^2)
     */
    public int[] selectSort(int[] a){
        for (int i=0;i<a.length - 1;i++){
            int min = i;
            for (int j=i+1;j<a.length;j++){
                if (a[min] > a[j]){
                    min = j;
                }
            }
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }
        return a;
    }

    /**
     * 冒泡排序
     * O(n^2)
     */
    public int[] bubbleSort(int[] a){
        for (int i=1;i<a.length;i++){
            for (int j=0;j<a.length-i;j++){
                if (a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
        return a;
    }

    /**
     * 快速排序
     * O(nlogn)
     */
    public void quickSort(int[] a, int start, int end){
        int i, j;
        i = start;
        j = end;

        while (i < j) {//查找基准点下标
            while (i < j && a[i] <= a[j])
                // 以数组start下标的数据为key，右侧扫描
                j--;
            if (i < j) { // 右侧扫描，找出第一个比key小的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            while (i < j && a[i] < a[j])
                // 左侧扫描（此时a[j]中存储着key值）
                i++;
            if (i < j) { // 找出第一个比key大的，交换位置
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        if (i - start > 1) { // 递归调用，把key前面的完成排序
            quickSort(a, 0, i - 1);
        }
        if (end - j > 1) {
            quickSort(a, j + 1, end); // 递归调用，把key后面的完成排序
        }
    }

    @Before
    public void init(){
        testData = new ArrayList<>();
        expectedData = new ArrayList<>();
        testData.add(new int[]{5,1,8,7,4,3,2,9,6,0});
        expectedData.add(new int[]{0,1,2,3,4,5,6,7,8,9});
        testData.add(new int[]{49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51});
        expectedData.add(new int[]{4,5,12,13,15,17,18,23,25,27,34,34,35,38,49,49,51,53,54,56,62,64,65,76,78,97,98,99});
    }

    private List<int[]> testData;
    private List<int[] > expectedData;

    @Test
    public void test(){
        for (int i=0;i<testData.size();i++){
            //插入排序
//            Assert.assertArrayEquals(expectedData.get(i), insertDirectSort(testData.get(i)));
//            Assert.assertArrayEquals(expectedData.get(i), selectSort(testData.get(i)));
//            Assert.assertArrayEquals(expectedData.get(i), bubbleSort(testData.get(i)));
            quickSort(testData.get(i),0,testData.get(i).length-1);
            Assert.assertArrayEquals(expectedData.get(i), testData.get(i));
        }
    }

}
