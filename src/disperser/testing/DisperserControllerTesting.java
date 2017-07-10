package disperser.testing;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import disperser.controller.DisperserController;

/**
 * DisperserControllerTesting tests the execute method and make sure that we will get the intended results !
 */
public class DisperserControllerTesting {

    @SuppressWarnings("resource")
	@Test
    public void execute() throws Exception {
    	DisperserController.execute("map.xml", "config.txt", "output.xml");
        Scanner actualFile = new Scanner(new File("output_Actual.xml"));
        Scanner expectedFile = new Scanner(new File("output_Expected.xml"));
        while (actualFile.hasNextLine())
            assertEquals(actualFile.nextLine(), expectedFile.nextLine());
    }

}