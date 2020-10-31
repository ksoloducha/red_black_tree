package aisd.lab3.rbtree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {

    private RedBlackTree<String, String> rbTreeString;
    private RedBlackTree<Integer, Integer> rbTreeInteger;

    @Before
    public void setUp() {
        rbTreeString = new RedBlackTree<>();
        rbTreeInteger = new RedBlackTree<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void put_should_throwIllegalArgumentException_when_keyIsNull() {
        Integer key = null;
        Integer value = 0;

        rbTreeInteger.put(key, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void put_should_throwIllegalArgumentException_when_valueIsNull() {
        Integer key = 1;
        Integer value = null;

        rbTreeInteger.put(key, value);
    }

    @Test
    public void put_should_insertNodesWithStringKeyInExpectedOrder_when_givenCorrectElements() {

        Node<String, String> a = new Node<>("a", "a");
        Node<String, String> l = new Node<>("l", "l");
        Node<String, String> g = new Node<>("g", "g");
        Node<String, String> o = new Node<>("o", "o");
        Node<String, String> r = new Node<>("r", "r");
        Node<String, String> y = new Node<>("y", "y");
        Node<String, String> t = new Node<>("t", "t");
        Node<String, String> m = new Node<>("m", "m");

        a.setColor(Color.BLACK);
        l.setColor(Color.RED);
        g.setColor(Color.BLACK);
        o.setColor(Color.BLACK);
        r.setColor(Color.BLACK);
        y.setColor(Color.BLACK);
        t.setColor(Color.BLACK);
        m.setColor(Color.BLACK);

        o.setLeft(g);
        o.setRight(t);
        g.setLeft(a);
        g.setRight(m);
        m.setLeft(l);
        t.setLeft(r);
        t.setRight(y);

        rbTreeString.put("a", "a");
        rbTreeString.put("l", "l");
        rbTreeString.put("g", "g");
        rbTreeString.put("o", "o");
        rbTreeString.put("r", "r");
        rbTreeString.put("y", "y");
        rbTreeString.put("t", "t");
        rbTreeString.put("m", "m");

        assertEquals(o, rbTreeString.getRoot());
    }

    @Test
    public void put_should_insertNodesWithIntegerKeyInExpectedOrder_when_givenCorrectElements() {

        Node<Integer, Integer> seven = new Node<>(7, 7);
        Node<Integer, Integer> zero = new Node<>(0, 0);
        Node<Integer, Integer> two = new Node<>(2, 2);
        Node<Integer, Integer> nine = new Node<>(9, 9);
        Node<Integer, Integer> one = new Node<>(1, 1);
        Node<Integer, Integer> five = new Node<>(5, 5);

        seven.setColor(Color.BLACK);
        zero.setColor(Color.RED);
        two.setColor(Color.RED);
        nine.setColor(Color.BLACK);
        one.setColor(Color.BLACK);
        five.setColor(Color.BLACK);

        seven.setLeft(two);
        seven.setRight(nine);
        two.setLeft(one);
        two.setRight(five);
        one.setLeft(zero);

        rbTreeInteger.put(7, 7);
        rbTreeInteger.put(0, 0);
        rbTreeInteger.put(2, 2);
        rbTreeInteger.put(9, 9);
        rbTreeInteger.put(1, 1);
        rbTreeInteger.put(5, 5);

        assertEquals(seven, rbTreeInteger.getRoot());
    }

    @Test
    public void timeMeasurement() {

        String outputFile = "..\\AiSD-Lab3-RedBlackTree\\output_data\\time_measurement.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false))) {

            writer.flush();
            int maxNumberOfElements = 1000;
            long[] time = new long[maxNumberOfElements];

            for (int i = 0; i < 10; i++) {

                RedBlackTree<Integer, Integer> myTree = new RedBlackTree<>();
                for (int elementsInTree = 1; elementsInTree <= maxNumberOfElements; elementsInTree++) {

                    long startTime;
                    long stopTime;

                    startTime = System.nanoTime();
                    myTree.put(elementsInTree, elementsInTree);
                    stopTime = System.nanoTime();

                    time[elementsInTree - 1] += stopTime - startTime;
                }
            }

            for (int i = 1; i <= maxNumberOfElements; i++) {
                writer.write(i + " " + (time[i - 1] / 10) + "\n");
            }

        } catch (IOException e) {
        }
    }
}
