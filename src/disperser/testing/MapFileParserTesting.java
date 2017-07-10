package disperser.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import disperser.services.Room;
import file.parser.MapFileParser;

/**
 * MapParserTest test the following methods:
 * => getRooms() : make sure that we parse map.xml file correctly!
 */

public class MapFileParserTesting {
    private MapFileParser mapFileParser;
    private HashMap<Integer, Room> rooms;

    @Before
    public void setUp() {
        mapFileParser = new MapFileParser("map.xml");
        mapFileParser.parse();
        rooms = new HashMap<>();
        ArrayList<Integer> cardinal = new ArrayList<>();
        HashSet<String> objects = new HashSet<>();
        cardinal.add(2);
        rooms.put(1, new Room(1, "Stairway", cardinal, objects));
        objects = new HashSet<>();
        objects.add("Scarf");
        objects.add("Soap");
        cardinal = new ArrayList<>();
        cardinal.add(1);
        rooms.put(2, new Room(2, "Hallway 1", cardinal, objects));
    }

    @Test
    public void getRooms() throws Exception {
        Room actualRoom;
        for (Map.Entry<Integer, Room> roomSet : rooms.entrySet()) {
            actualRoom = mapFileParser.getRooms().get(roomSet.getKey());
            assertNotNull(actualRoom);
            assertEquals(roomSet.getValue().getId(), actualRoom.getId());
            assertEquals(roomSet.getValue().getName(), actualRoom.getName());
            assertEquals(roomSet.getValue().getCardinal(), actualRoom.getCardinal());
            assertEquals(roomSet.getValue().getItems(), actualRoom.getItems());
        }
    }

}