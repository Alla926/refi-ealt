package by.epam.refiealt;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ListUtilsTest {
    @Test
    public void join(){
        List<List<Integer>> list = List.of(List.of(1, 3, 5), List.of(1, 7, 8, 11), List.of(2, 4, 8), new ArrayList<>());
        list = new ArrayList<>(list);
        list.add(null);

        List<Integer> expectedList = List.of(1, 1, 2, 3, 4, 5, 7, 8, 8, 11);
        List<Integer> resultList = ListUtils.join(list);
        Assert.assertArrayEquals(resultList.toArray(), expectedList.toArray());
    }

    @Test
    public void speedTest(){
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 500; j++) {
                list.add(j);
            }
            lists.add(list);
        }
        long start = System.currentTimeMillis();
        ListUtils.join(lists);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

}
