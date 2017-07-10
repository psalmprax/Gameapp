package disperser.testing;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import disperser.services.Room;
import disperser.services.XMLWriter;

/**
 * XMLWriterTest tests the following methods:
 * - saveFileOutput() : To make sure that we save the file correctly.
 */
public class XMLWriterTest {
    private XMLWriter xmlWriter;

    @Before
    public void setUp() {
        xmlWriter = new XMLWriter("RightOutput.xml");
        Element route = xmlWriter.createRouteTagElement();
        Element guest1Room = xmlWriter.createRoomTagElement(new Room(1, "Guest1", new ArrayList<Integer>(),
                new HashSet<String>()));
        Element anotherRoom = xmlWriter.createRoomTagElement(new Room(2, "Another", new ArrayList<Integer>(),
                new HashSet<String>()));
        Element guest1RoomItem = xmlWriter.createObjectTagElement("Laptop");
        guest1Room.appendChild(guest1RoomItem);
        route.appendChild(guest1Room);
        route.appendChild(anotherRoom);
        xmlWriter.saveFileOutput();
    }

    @SuppressWarnings("resource")
	@Test
    public void saveFileOutput() throws Exception {
        Scanner actualFile = new Scanner(new File("RightOutput.xml"));
        Scanner expectedFile = new Scanner(new File("OutputExpected.xml"));
        while (actualFile.hasNextLine())
            assertEquals(actualFile.nextLine(), expectedFile.nextLine());
    }

}