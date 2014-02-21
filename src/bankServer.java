  import java.rmi.*;
  import java.rmi.registry.LocateRegistry;
  import java.rmi.registry.Registry;
  
  /**
   * Server program for the bank demo
   * @param argv The command line arguments which are ignored.
   */
 public class bankServer  {
   
  public static void main (String[] argv) {
    try {
	   
	  if (argv.length < 1) {
		   System.out.println ("Usage: java bankServer port");
		   return;
	  }

	  int port = Integer.parseInt(argv[0]);

	  if (port <= 0)
	  {
		System.out.println ("Port number illegal!");
		return;
	  }

	  Registry reg = LocateRegistry.createRegistry(port);
	  reg.bind("mybank", new bank(1000));
      // Naming.rebind ("mybank", new bank(1000) );
      System.out.println ("Bank server is ready.");
    } catch (Exception e) {
      System.out.println ("Bank Server failed: " + e);
    }
  }
}
