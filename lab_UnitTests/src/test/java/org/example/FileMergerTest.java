package org.example;

import org.junit.Assert;
import org.junit.Test;

public class FileMergerTest {
    @Test
    public void mergeTest(){
        String result = FileMerger.merge();
        String expected = "helloworld";
        Assert.assertEquals(expected, result);
    }
}
