package tqs.lab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tqs.lab1.TqsStack;

public class TqsStackTest {
    // Chose String for testing, for no particular reason other than T cannot be used
    TqsStack<String> myTqsStack;

    @BeforeEach
    void setup() {
        myTqsStack = new TqsStack<String>();
    }

    @DisplayName("A stack is empty on construction")
    @Test
    void a_Test() {

        // assert statements
        Assertions.assertTrue(myTqsStack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction")
    @Test
    void b_Test() {

        // assert statements
        Assertions.assertEquals(0, myTqsStack.size());
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void c_Test() {

        // act
        myTqsStack.push("a");
        myTqsStack.push("b");
        myTqsStack.push("c");

        // assert statements
        Assertions.assertFalse(myTqsStack.isEmpty());
        Assertions.assertEquals(3, myTqsStack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x")
    @Test
    void d_Test() {

        // act
        myTqsStack.push("a");
        myTqsStack.push("b");
        myTqsStack.push("c");

        // assert statements
        Assertions.assertEquals("c", myTqsStack.pop());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x but the size stays the same")
    @Test
    void e_Test() {

        // act
        myTqsStack.push("a");
        myTqsStack.push("b");
        myTqsStack.push("c");

        // assert statements
        Assertions.assertEquals("c", myTqsStack.peek());
        Assertions.assertEquals(3, myTqsStack.size());
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void f_Test() {

        // act
        myTqsStack.push("a");
        myTqsStack.push("b");
        myTqsStack.push("c");

        myTqsStack.pop();
        myTqsStack.pop();
        myTqsStack.pop();

        // assert statements
        Assertions.assertTrue(myTqsStack.isEmpty());
        Assertions.assertEquals(0, myTqsStack.size());
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void g_Test() {

        // assert statements
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> {
            myTqsStack.pop();
        });
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void h_Test() {

        // assert statements
        Assertions.assertThrows(java.util.NoSuchElementException.class, () -> {
            myTqsStack.peek();
        });
    }

    @DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void i_Test() {

        // arrange
        myTqsStack = new TqsStack<String>(3);

        // act
        myTqsStack.push("a");
        myTqsStack.push("b");
        myTqsStack.push("c");

        // assert statements
        Assertions.assertThrows(IllegalStateException.class, () -> {
            myTqsStack.push("d");
        });
    }
}
