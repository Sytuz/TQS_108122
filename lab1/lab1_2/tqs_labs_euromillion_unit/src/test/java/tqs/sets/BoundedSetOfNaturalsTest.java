/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(10);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    //@Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());
        
        assertThrows(IllegalArgumentException.class, () -> {
            setA.add(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            setD.add(10); setD.add(10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            setD.add(-1);
        });
    }

    @Test
    public void testSize() {
        assertEquals(0, setA.size(), "size: elements count not as expected.");
        assertEquals(6, setB.size(), "size: elements count not as expected.");
    }

    //@Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems1 = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems1));

        int[] elems2 = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems2));

        int[] elems3 = new int[]{10, 20, 30, 40, 50, 60, 60};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems3));

        setD.add(10);
        int elems4[] = new int[]{10, 20, 30, 40, 50, 60};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setD.add(elems4));
    }

    @Test
    public void testIntersects() {
        assertTrue(setB.intersects(setC), "intersects: sets should intersect.");
        assertFalse(setA.intersects(setB), "intersects: sets should not intersect.");
    }

    @Test
    public void testFromArray() {
        int[] elems = new int[]{10, 20, 30, 40, 50, 60};
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(elems);
        assertEquals(6, set.size(), "fromArray: elements count not as expected.");
    }

    @Test
    public void testEquals() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        assertTrue(setB.equals(set), "equals: sets should be equal.");
        assertFalse(setA.equals(setB), "equals: sets should not be equal.");

        assertTrue(setB.equals(setB), "equals: sets should be equal.");
        assertFalse(setB.equals(null), "equals: sets should not be equal.");
        assertFalse(setB.equals(new Object()), "equals: sets should not be equal.");
    }

    @Test
    public void testHashCode() {
        BoundedSetOfNaturals set = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        assertEquals(setB.hashCode(), set.hashCode(), "hashCode: hash codes should be equal.");
        assertNotEquals(setA.hashCode(), setB.hashCode(), "hashCode: hash codes should not be equal.");
    }
}
