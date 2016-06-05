package homework.hw05

import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.util.*

/**
 * Tests for hw 05.
 * Author: Kirill Smirenko, group 271
 */
class Hw05Test {
    // to test whether MergeSort works at all
    @Test fun testSingleThread() {
        testMergeSort(100, 1)
        testMergeSort(100, 1)
        testMergeSort(100, 1)
    }

    @Test fun testTwoThreads() {
        testMergeSort(1000, 2)
        testMergeSort(1000, 2)
        testMergeSort(1000, 2)
    }

    @Test fun testLongManyThreads() {
        testMergeSort(1000000, 7)
        testMergeSort(1000000, 7)
    }

    private fun testMergeSort(arrSize : Int, threadsNumber : Int) {
        val rnd = Random()
        val arr = Array(arrSize, { i -> rnd.nextInt() })
        mergeSort(arr, threadsNumber)
        assertArrayEquals(arr.sortedArray(), arr)
    }
}