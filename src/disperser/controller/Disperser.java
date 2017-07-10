package disperser.controller;

/**
 * Disperser class takes two files ( map.xml , config.txt ) and disperse them using the suitable File parsers.
 * => getRooms() : return HashMap<Integer,Room> of rooms generated from mapFileParser.
 * HashMap[ Integer => roomID , Room => room Items ]
 * => getFirstRoomId() : returns the starting room id generated from config file.
 * => getItemsToBeCollect() : return a HashSet of items we will collect generated from config file.
 */


import java.util.HashSet;
import java.util.Map;

import disperser.services.Room;

import file.parser.ConfigFileParser;
import file.parser.MapFileParser;

public class Disperser {
	private MapFileParser mapFileParser;
    private ConfigFileParser configFileParser;
	public Disperser(String mapFile, String configFile) {
		 this.mapFileParser = new MapFileParser(mapFile);
	     this.configFileParser = new ConfigFileParser(configFile);
	     this.mapFileParser.parse();
	     this.configFileParser.parse();
	}

	public Map<Integer, Room> getRooms() {
	     return this.mapFileParser.getRooms();
	}

	public int getFirstRoomId() {
	    return this.configFileParser.getFirstRoomId();
	}

	public HashSet<String> getItemToBeCollected() {
	    return this.configFileParser.getItemsToCollect();
	}
}
