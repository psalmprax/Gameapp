package disperser.testing;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import file.parser.ConfigFileParser;

/**
 * ConfigFileParserTesting test the following methods:
 * => getFirstRoomId() : check that we get the right value from config file.
 * => getItemsToCollect() : check that we get all items from config file.
 */

public class ConfigFileParserTesting {

    private ConfigFileParser configFileParser;

    @Before
    public void setUp() {
        configFileParser = new ConfigFileParser("config.txt");
        configFileParser.parse();
    }

    @Test
    public void getFirstRoomId() throws Exception {
        assertEquals(1, configFileParser.getFirstRoomId());
    }

    @Test
    public void getItemsToCollect() throws Exception {
        HashSet<String> itemsToCollect = new HashSet<>();
        itemsToCollect.add("Scarf");
        itemsToCollect.add("Soap");
        assertEquals(itemsToCollect, configFileParser.getItemsToCollect());
    }

}
