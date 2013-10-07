package org.talamonso.OMAPI.Examples;

import java.util.Random;

import org.talamonso.OMAPI.Connection;
import org.talamonso.OMAPI.Message;
import org.talamonso.OMAPI.Exceptions.OmapiConnectionException;
import org.talamonso.OMAPI.Exceptions.OmapiException;
import org.talamonso.OMAPI.Objects.Host;

/**
 * Creat a new Host Object with and IPv6 and some presets.
 * 
 * @author <a href="mailto:serafin.sedano@abiquo.com">Serafin Sedano</a>
 */
public class Host_Ipv4_Mapped_Ipv6_Compact_Create
{

    /**
     * Creates an host object. Sets the hostname and some other attributes. You can set more then
     * one IPv6 address!
     * 
     * @param args
     * @throws OmapiConnectionException
     */
    public static void main(final String[] args) throws Exception
    {
        String name = "ipv6_" + new Random().nextInt(1000000);
        try (Connection c = new Connection("127.0.0.1", 7911))
        {
            Host h = new Host(c);
            h.setName(name);
            h.setIPAddress("::ffff:10.60.1.254");
            h.setHardwareAddress(Default.randomMac());
            h.setHardwareType(1);
            Host remote = h.send(Message.CREATE);
            System.out.println(remote);
        }
        catch (OmapiException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            cleanup(name);
        }
    }

    private static void cleanup(String name) throws OmapiConnectionException, OmapiException
    {
        
        try(Connection c1 = new Connection("127.0.0.1", 7911);)
        {
            Host del = new Host(c1);
            del.setName(name);
            del.send(Message.OPEN);
            del.delete();
        }
    }
}
