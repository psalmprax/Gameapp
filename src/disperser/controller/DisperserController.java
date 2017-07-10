package disperser.controller;



import org.w3c.dom.Element;

import disperser.services.Room;
import disperser.services.XMLWriter;




import java.util.HashSet;

/**
 * DisperserController class:
 * this class is responsible for running the application and searching for items using data
 * provided inside the config file.
 * -------------------------------------------------------------------------------------------------
 * it contains the following methods:
 * => execute(String mapFile,String configFile, String outputFile) :
 * creates a new Disperser and an XMLWriter
 * Uses the private method to search for items.
 * -------------------------------------------------------------------------------------------------
 * there are three private methods:
 * => search() : search function searches for items and write to XMLWriter. 
 * => getFirstRoomId() : checks if the startingRoomId is valid.
 * => getNextRoom() : checks if the nextRoom if a valid room.
 */
public class DisperserController {
    public static void execute(String mapFile, String configFile, String outputFile) {
        Disperser disperser = new Disperser(mapFile, configFile);
        XMLWriter output = new XMLWriter(outputFile);
        search(disperser, output);

    }

    private static void search(Disperser disperser, XMLWriter output) {
        Element routeItem, roomItem, objectItem;
        routeItem = output.createRouteTagElement();
        
        Room currentRoom = disperser.getRooms().get(getFirstRoomId(disperser));
        HashSet<String> objectsFound;

        while (disperser.getItemToBeCollected().size() != 0) {
            objectsFound = currentRoom.search(disperser.getItemToBeCollected());
            disperser.getItemToBeCollected().removeAll(objectsFound);
            roomItem = output.createRoomTagElement(currentRoom);

            if (objectsFound.size() > 0) {
                for (String object : objectsFound) {
                    objectItem = output.createObjectTagElement(object);
                    roomItem.appendChild(objectItem);
                }
            }
            routeItem.appendChild(roomItem);
            currentRoom = getNextRoom(disperser, currentRoom);
        }
        output.saveFileOutput();
    }

    private static int getFirstRoomId(Disperser disperser) {
        if (disperser.getRooms().containsKey(disperser.getFirstRoomId()))
            return disperser.getFirstRoomId();
        System.err.println("First Room Id value is wrong!!! Please check your config file!");
        System.exit(0);

        return -1;
    }

    private static Room getNextRoom(Disperser parserEngineer, Room currentRoom) {
        int nextRoom = currentRoom.nextRoom();
        if (parserEngineer.getRooms().containsKey(nextRoom))
            return parserEngineer.getRooms().get(nextRoom);

        System.err.println("Room id value is out of range!!! Please check your Map File!");
        System.exit(-1);

        return null;
    }

}
