package file.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import disperser.services.Room;



/**
 * MapFileParser class creates a new object of MapFileParser
 * it has the following variables:
 * => mapXMLFile (String) : it stores the name of the map file ( E.g Map.xml )
 * => rooms ( Map<Integer,Room> ) : it stores items of every room in the map file.
 *
 * MapFileParser has the following methods:
 * => parse() : which parse map.xml and get back a HashMap of all rooms inside the file.
 *
 * MapFileParser hash all getters and a setter of only the mapXMLFile.
 *
 * There are 3 private methods of MapFileParser to help parser do its job )
 * => getRoomFromXMLFile(NamedNodeMap attributes, NodeList objectNodes)
 * => getCardinalityFromXMLFILE(NamedNodeMap attributes)
 * => getItemsFromXMLFile(NodeList itemNodes)  
 * *** View comments inside the class for details ***
 */
public class MapFileParser implements Parser {
    private String mapXMLFile;
    private Map<Integer, Room> rooms;
    
    public MapFileParser(String XMLFile){
    	if(mapXMLFile != ""){
    		mapper(XMLFile);
    	}
    }
    protected void mapper(String mapXMLFile) {
        this.mapXMLFile = mapXMLFile;
        this.rooms = new HashMap<>();
    }

    public void parse() {
        try {
            Document map = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(this.mapXMLFile));
            NodeList roomsNodes = map.getElementsByTagName("room");
            for (int i = 0, n = roomsNodes.getLength(); i < n; i++) {
                Room room = this.getRoomFromXMLFile(roomsNodes.item(i).getAttributes(), roomsNodes.item(i).getChildNodes());
                this.rooms.put(room.getId(), room);
            }
        } catch (Exception e) {
            System.err.println("There was an error while parsing [" + this.mapXMLFile + "] !");
            e.printStackTrace();
        }
    }

    public void setMapXMLFile(String mapXMLFile) {
        this.mapXMLFile = mapXMLFile;
    }

    public String getMapXMLFile() {
        return this.mapXMLFile;
    }

    public Map<Integer, Room> getRooms() {
        return this.rooms;
    }

    /*
        getRoomFromXMLFile method:
            - takes attributes and objectNodes of a single room node as an input
            - make a new room from the values of attributes and objects
     */
    private Room getRoomFromXMLFile(NamedNodeMap attributes, NodeList objectNodes) {
        int roomID = Integer.parseInt(attributes.getNamedItem("id").getNodeValue());
        String roomName = attributes.getNamedItem("name").getNodeValue();
        ArrayList<Integer> roomCardinal = this.getCardinalityFromXMLFILE(attributes);
        HashSet<String> roomObjects = this.getItemsFromXMLFile(objectNodes);

        return new Room(roomID, roomName, roomCardinal, roomObjects);
    }

    /*
        getCardinalFromXMLFILE method:
            - takes attributes of a room and check the cardinal of this room
            - return an array list which contains the ids of rooms that you can go through.
     */
    private ArrayList<Integer> getCardinalityFromXMLFILE(NamedNodeMap attributes) {
        ArrayList<Integer> cardinal = new ArrayList<>();
        String directions[] = {"north", "east", "south", "west"}; // change the order the output will change!

        for (int i = 0; i < 4; i++) {
            try {
                cardinal.add(Integer.parseInt(attributes.getNamedItem(directions[i]).getNodeValue()));
            } catch (NullPointerException er) {
                // we will do nothing if the direction isn't an attribute of the node
            }
        }

        return cardinal;
    }

    /*
        getItemsFromXMLFile method
            - takes itemNodes of a room as an input
            - check for name attribute for every object
            - put the object name inside a HashSet
            - return HashSet
     */
    private HashSet<String> getItemsFromXMLFile(NodeList itemNodes) {
        HashSet<String> objects = new HashSet<>();
        for (int i = 0, n = itemNodes.getLength(); i < n; i++) {
            if (itemNodes.item(i).getNodeType() == Node.ELEMENT_NODE)
                objects.add(itemNodes.item(i).getAttributes().getNamedItem("name").getNodeValue());
        }
        return objects;
    }
}
