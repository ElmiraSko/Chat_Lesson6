package TwoArraysForTesting;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArraysTest {
    @Test
    public void testAfterFour1(){
        int[] arr = {2, 4, 6, 8, 4, 3}; // входные данные, подставляются в проверяемый метод afterFour класса Arrays
        int[] newArr = {3}; // эталонные данные, то что должен вернуть метод afterFour
        assertArrayEquals("Метод не работает", newArr, Arrays.afterFour(arr));
    }
    @Test
    public void testAfterFour2(){
        int[] arr = {2, 4, 6, 3, 1, 8}; // входные данные
        int[] newArr = {6, 3, 1, 8}; // эталонные данные
        assertArrayEquals("Метод не работает", newArr, Arrays.afterFour(arr));
    }
    @Test (expected = RuntimeException.class)
    public void testAfterFour3() throws RuntimeException {
        int[] arr = {2, 7, 6, 3, 1, 9}; // входные данные
        RuntimeException ex = new RuntimeException(); // эталонные данные
        assertSame("Метод не работает", ex, Arrays.afterFour(arr));
    }

//==================================================================================
    @Test
    public void testOnlyOneAndFour(){
        int[] arr = {4, 4, 1, 1, 1, 1}; // входные данные
        assertTrue(Arrays.onlyOneAndFour(arr));  // должны получить true
    }
    @Test
    public void testOnlyOneAndFour2(){
        int[] arr = {4, 4, 1, 1, 5, 1}; // входные данные
        assertFalse(Arrays.onlyOneAndFour(arr));  // должны получить false
    }

    @Test
    public void testOnlyOneAndFour3(){
        int[] arr = {1, 1, 1, 1, 1, 1}; // входные данные
        assertFalse(Arrays.onlyOneAndFour(arr));  // должны получить false
    }

    @Test
    public void testOnlyOneAndFour4(){
        int[] arr = {4, 4, 4, 4}; // входные данные
        assertFalse(Arrays.onlyOneAndFour(arr));  // должны получить false
    }
}