import disperser.controller.DisperserController;

/*
 * Gameapp is the starting point of the application
 */

public class Gameapp {
	public static void main(String args[])
    {
        try
        {
            DisperserController.execute(args[0],args[1],args[2]);
        }
        catch (NullPointerException exception)
        {
            System.err.println("Either argument Order is wrong or the value of the argument is empty");
            System.out.println("The order should be: java Gameapp map.xml config.txt output.xml");
            //System.out.println(args[0] + args[1] + args[2] );
        }

    }
}
