package by.epam.refiealt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Joins sorted lists into common sorted list
 *
 * @author Ala Hryntsevich
 */
public class ListUtils {

    private ListUtils(){}
    /**
     * checks if list has only Integer.MAX_VALUE
     *
     * @param temp List <Integer> input list that is checked of Integer.MAX_VALUE elements having
     * @return boolean value of only Integer.MAX_VALUE elements having
     */

    private static boolean hasAllMAXIntegerValues(List<Integer> temp) {
        return temp.stream()
                .allMatch(val -> val == Integer.MAX_VALUE);
    }

    /**
     * gets an index of minimum element
     *
     * @param list List<Integer> input list in that is looking for an index of minimum element
     * @return int value of an index of minimum element
     */

    private static int getMinIndex(List<Integer> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .min(Comparator.comparingInt(list::get)).
                        orElse(list.get(0));
    }

    /**
     * merge lot of lists in common list
     *
     * @param lists List<List<Integer>> input lists that are joined
     * @return List<Integer> value of result list
     */
    public static List<Integer> join(List<List<Integer>> lists) {

        if (lists == null){
            throw new IllegalArgumentException("Lists can not be null");
        }

        List<Integer> temp = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();

        lists = lists.stream()
                .filter(Objects::nonNull)
                .filter(list -> !list.isEmpty())
                .collect(Collectors.toList());

        lists.forEach(list -> {
            temp.add(list.get(0));
            indexes.add(1);
        });

        while (!hasAllMAXIntegerValues(temp)) {

            int indexOfListWithMinElement = getMinIndex(temp);
            int indexOfMinElement = indexes.get(indexOfListWithMinElement);

            resultList.add(temp.get(indexOfListWithMinElement));

            if (indexOfMinElement == lists.get(indexOfListWithMinElement).size()) {
                temp.set(indexOfListWithMinElement, Integer.MAX_VALUE);
            } else {
                indexes.set(indexOfListWithMinElement, indexOfMinElement + 1);
                temp.set(indexOfListWithMinElement, lists.get(indexOfListWithMinElement).get(indexOfMinElement));
            }
        }
        return resultList;
    }

    /**
     * merge lot of lists in common list. It's a small version of join, but it doesn't use
     * a fact that elements in source arrays are already sorted.
     *
     * @param lists List<List<Integer>> input lists that are joined
     * @return List<Integer> value of result list
     */
    public static List<Integer> join2(List<List<Integer>> lists){
        return lists.stream()
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.toList());
    }
}
