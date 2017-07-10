package disperser.testing;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;

import disperser.controller.Disperser;
import disperser.services.Room;

/**
 * DisperserTesting tests the following methods of Disperser:
 * - getRooms() : make sure that we have the right HashMap<Integer,Room>.
 * - getFirstRoomId() : make sure that we have the correct starting id from the config file.
 * - getItemToBeCollected() : make sure that we have a HashSet of all items inside config file.
 **/

public class DisperserTesting {
    private Disperser disperser;
    private HashMap<Integer, Room> rooms;

    @Before
    public void setUp() {
        disperser = new Disperser("test_files/map.xml", "test_files/config.txt");
        rooms = new HashMap<>();
        ArrayList<Integer> cardinal = new ArrayList<>();
        HashSet<String> objects = new HashSet<>();
        cardinal.add(2);
        rooms.put(1, new Room(1, "Stairway", cardinal, objects));
        objects = new HashSet<>();
        objects.add("Scarf");
        cardinal = new ArrayList<>();
        cardinal.add(1);
        rooms.put(2, new Room(2, "Hallway 1", cardinal, objects));
    }

    @Test
    public void getRooms() throws Exception {
        Room actualRoom;
        for (Map.Entry<Integer, Room> roomSet : rooms.entrySet()) {
            actualRoom = disperser.getRooms().get(roomSet.getKey());
            assertNotNull(actualRoom);
            assertEquals(roomSet.getValue().getId(), actualRoom.getId());
            assertEquals(roomSet.getValue().getName(), actualRoom.getName());
            assertEquals(roomSet.getValue().getCardinal(), actualRoom.getCardinal());
            assertEquals(actualRoom.getItems(), actualRoom.getItems());
        }
    }

    @Test
    public void getStartingRoomId() throws Exception {
        assertEquals(1, disperser.getFirstRoomId());
    }

    @Test
    public void getItemsToCollect() throws Exception {
        HashSet<String> objectsToCollect = new HashSet<>();
        objectsToCollect.add("Scarf");
        objectsToCollect.add("Soap");
        assertEquals(objectsToCollect, disperser.getItemToBeCollected());
    }

}