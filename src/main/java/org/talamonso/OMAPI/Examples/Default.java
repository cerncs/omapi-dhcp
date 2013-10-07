package org.talamonso.OMAPI.Examples;

import java.util.Arrays;
import java.util.Random;

import org.talamonso.OMAPI.Connection;
import org.talamonso.OMAPI.Exceptions.OmapiException;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Bytes;

/**
 * This object holds the connection informations used by the examples.
 * 
 * @author Talamonso
 */
public class Default {

	/**
	 * This is just a Default methode...
	 * 
	 * @return a connection object
	 */
	public static Connection getC() {
		Connection c = null;
		try {
			c = new Connection("192.168.2.150", 9991);
			c.setAuth("omapi_key", "2YdVRKaJ4x41lDqHfA8rl8pHx95C4PmBgPcf5hIJ8j417HFN0AxUBEo6/3FoYyWjPyvXXCd+H6fPygtZd/iKxQ==");
		} catch (OmapiException e) {
			System.err.println(e.getMessage());
		}
		return c;
	}
	
	/** Generates a <b>random</b> MAC. */
	public static String randomMac() {
	    byte[] bytes = new byte[6];
	    new Random().nextBytes(bytes);
	    String mac = Joiner.on(':').join(Iterables.transform(Bytes.asList(bytes), new Function<Byte, String>()
        {
            @Override
            public String apply(Byte input)
            {
                return String.format("%02x", input&0xff);
            }
        }));
	    return mac;
	}

}
