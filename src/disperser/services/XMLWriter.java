package disperser.services;


import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * XMLWriter class:
 * helps creating the right output.xml file!
 * it has the following variables:
 * => outputFileName (String) : stores the name of the output file.
 * => xmlDoc ( Document ) : an XML Document object.
 *
 * XMLWriter class contains the following methods:
 * => createRouteTagElement() : it makes a new route tag element and returns it for usage outside the object.
 * => createRoomTagElement() : it makes a new room tag element from Room Object and returns it for usage outside object.
 * => createObjectTagElement() : it makes a new object element from Object name and return it for usage outside object.
 * => saveFileOutput() : it saves the document to the disk and check mate saving error.
 */



public class XMLWriter {

	private String outputFileName;
	private Document xmlDoc;
		
	public XMLWriter(String outputFileName) {
		this.outputFileName = outputFileName;
		try {
		    this.xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (Exception e) {
			System.err.println("There was an error while creating output xml file!");
		    e.printStackTrace();
		}
		
	}
		
	public Element createRouteTagElement() {
	    Element routeElement = this.xmlDoc.createElement("route");
	    this.xmlDoc.appendChild(routeElement);
	    return routeElement;
	}
		
	public Element createRoomTagElement(Room room) {
	    Element roomElement = this.xmlDoc.createElement("room");
	    roomElement.setAttribute("id", Integer.toString(room.getId()));
	    roomElement.setAttribute("name", room.getName());
	    return roomElement;
	}
		
	public Element createObjectTagElement(String name) {
	    Element objectElement = this.xmlDoc.createElement("object");
	    objectElement.setAttribute("name", name);
	    return objectElement;
	}
		
	public void saveFileOutput()
	{
	    if(!this.outputFileName.endsWith(".xml"))
	    {
	        System.err.println("[ "+this.outputFileName+" ] does not have the right file extension!");
	        System.err.println("Output is expected is an xml file! make sure the output is an xml file!");
	        System.exit(-1);
	    }
	    try {
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.transform(new DOMSource(this.xmlDoc), new StreamResult(new File(this.outputFileName)));
	        System.out.println("We have saved the output file to [ " + this.outputFileName + " ]");
	    } catch (TransformerException transformerException) {
	        System.err.println("Error while saving to " + this.outputFileName);
	        transformerException.printStackTrace();
	    }
		
	}
		
		
}