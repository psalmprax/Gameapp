package file.parser;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * ConfigFileParser class create an object of ConfigParser
 * it contains the following variables:
 * => configFile (String) : store the name of the config file ( E.G: config.txt )
 * => firstRoomId (int) : store the id of the starting room after parsing config file.
 * => itemsToCollect ( HashSet<String> ) : store the items that will be collected. ( from config file )
 *
 * ConfigFileParser methods:
 * => parse() : it will parse config file and set the values of firstRoomId and itemsToCollect variables.
 *
 * ConfigParser has all the getters methods and a setter method of only the configFileName.
 */

public class ConfigFileParser {

	private String configFileName;
    private int firstRoomId;
    private HashSet<String> itemsToCollect;
    
    public ConfigFileParser(String FileName){
    	if(FileName != ""){
    		configFile(FileName);
    	}
    }

    protected void configFile(String configFileName) {
        this.configFileName = configFileName;
        this.firstRoomId = -1;
        this.itemsToCollect = new HashSet<>();
    }

    @SuppressWarnings("resource")
	public void parse() {
        try {
            Scanner fileScanner = new Scanner(new File(this.configFileName));
            this.firstRoomId = Integer.parseInt(fileScanner.nextLine());
            while ((fileScanner.hasNextLine()))
                this.itemsToCollect.add(fileScanner.nextLine());
        } catch (FileNotFoundException exception) {
            System.err.print(this.configFileName + " can't be found!\n");
            exception.printStackTrace();
        } catch (Exception otherExceptions) {
            System.err.print("Please make sure that the file is a valid config file!");
            otherExceptions.printStackTrace();
        }
    }

    public String getConfigFileName() {
        return this.configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public int getFirstRoomId() {
        return this.firstRoomId;
    }

    public HashSet<String> getItemsToCollect() {
        return this.itemsToCollect;
    }
}
