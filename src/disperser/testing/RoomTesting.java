package disperser.testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.*;

import disperser.services.Room;

/**
 * RoomTest:
 * - Test search method of the room!
 * - Test nextRoom method of the room!
 * - Test getters methods!
 */

public class RoomTesting {
    private Room room;

    @Before
    public void setUp() {
        ArrayList<Integer> cardinal = new ArrayList<>();
        cardinal.add(3);
        cardinal.add(4);
        HashSet<String> items = new HashSet<>();
        items.add("Laptop");
        items.add("Mobile");
        room = new Room(100, "Mohammed", cardinal, items);
    }

    @Test
    public void search() throws Exception {
        HashSet<String> itemsToCollect = new HashSet<>();
        HashSet<String> itemsFound = new HashSet<>();
        itemsToCollect.add("Laptop");
        itemsFound.add("Laptop");
        assertEquals(itemsFound, room.search(itemsToCollect));
        itemsToCollect.add("Headphones");
        assertEquals(0, room.search(itemsToCollect).size()); // because it has been search before !
        assertEquals(2, itemsToCollect.size());

    }

    @Test
    public void nextRoom() throws Exception {
        assertEquals(3, room.nextRoom());
        assertEquals(4, room.nextRoom());
    }

    @Test
    public void getId() throws Exception {
        assertEquals(100, room.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Guest1", room.getName());
    }

    @Test
    public void getItems() throws Exception {
        HashSet<String> itemsExpeced = new HashSet<>();
        itemsExpeced.add("Laptop");
        itemsExpeced.add("Mobile");
        assertEquals(itemsExpeced, room.getItems());
    }

    @Test
    public void getCardinal() throws Exception {
        ArrayList<Integer> cardinalsExpected = new ArrayList<>();
        cardinalsExpected.add(3);
        cardinalsExpected.add(4);
        assertEquals(cardinalsExpected, room.getCardinal());
    }

}