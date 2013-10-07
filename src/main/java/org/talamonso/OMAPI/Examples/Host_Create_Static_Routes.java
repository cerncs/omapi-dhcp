package org.talamonso.OMAPI.Examples;

import java.util.Random;

import org.talamonso.OMAPI.Connection;
import org.talamonso.OMAPI.Message;
import org.talamonso.OMAPI.Exceptions.OmapiConnectionException;
import org.talamonso.OMAPI.Exceptions.OmapiException;
import org.talamonso.OMAPI.Objects.Host;

/**
 * Creat a new Host Object withe some presets.
 * 
 * @author <a href="mailto:serafin.sedano@abiquo.com">Serafin Sedano</a> 
 */
public class Host_Create_Static_Routes {

	/**
	 * Creates an host object. Sets the hostname and some other attributes. You can set more then one ip address!
	 * 
	 * @param args
	 * @throws OmapiConnectionException 
	 */
	public static void main(final String[] args) throws Exception {
		Connection c = new Connection("10.60.10.16", 7911);
		
		Host h = new Host(c);
		h.setName("albert_" + new Random().nextInt(100000000));
		try {
			h.setIPAddress("1.2.3.4");
			h.setHardwareAddress("00:00:00:00:00:17");
			h.setHardwareType(1);
			h.setStatements("supersede classless-static-routes = 18:c0:a8:02:0a:3c:0a:01; supersede ms-classless-static-routes = 18:c0:a8:02:0a:3c:0a:01;");
			Host remote = h.send(Message.CREATE);
			System.out.println(remote);
		} catch (OmapiException e) {
			System.err.println(e.getMessage());
		}
		finally
        {
            h.delete();
        }
		c.close();
	}
}
