package org.example;

import org.junit.Assert;
import org.junit.Test;

public class BubleSortTest {
    @Test
    public void bublesortTest(){
        int[] array = new int[]{5,2,1,3,4};
        int[] result = BubleSort.bubbleSort(array,array.length);
        int[] expected = new int[]{1,2,3,4,5};
        Assert.assertArrayEquals(expected, result);
    }
}
