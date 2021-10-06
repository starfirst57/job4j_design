package ru.job4j.it;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 6, 3));
        ListUtils.removeIf(input, x -> x > 4);
        assertThat(input, is(Arrays.asList(0, 1, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 6, 3));
        ListUtils.replaceIf(input, x -> x > 4, 0);
        assertThat(input, is(Arrays.asList(0, 1, 0, 0, 3)));
    }

    @Test
    public void whenDeleteAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 1)));
    }

}