import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;

/**
 * Remote Class for the bank demo
 */
public class bank extends UnicastRemoteObject implements bankInterface {

  private int acnt;
  private int amt;

  private final String dataPath = "src//bankdata.txt";
  private Hashtable<Integer, Integer> bankItems;



  public bank(int account) throws RemoteException {
    acnt = account;
	amt = 0;

	bankItems = loadData(dataPath);

	// PrintHash(bankItems);

  }
  /**
   * Implementation of the remotely invocable method.
   * @return void
   * @exception RemoteException if the remote invocation fails.
   */
   public CODE deposit(int acnt, int amt) throws RemoteException {

	Integer fund = bankItems.get(acnt);
	if (fund != null) {
		fund = new Integer(fund.intValue() + amt);
		bankItems.put(new Integer(acnt), fund);

		System.out.printf("%d= %d\n", acnt, fund);

        // save the change back to the database
        saveData(bankItems, dataPath);
		return CODE.OK;
	}
	
	return CODE.UNKNOWN_ACCOUNT;
   }

   public CODE withdraw(int acnt, int amt) throws RemoteException {

       Integer fund = bankItems.get(acnt);
       if (fund != null) {
           if (fund.intValue() < amt) {
               return CODE.NO_SUFFICIENT_FUND;
           }

           fund = new Integer(fund.intValue() - amt);
           bankItems.put(new Integer(acnt), fund);

           System.out.printf("%d= %d\n", acnt, fund);

           // save the change back to the database
           saveData(bankItems, dataPath);
           return CODE.OK;
       }

	   return CODE.UNKNOWN_ACCOUNT;
   }


   public CODE transfer(int acnt1, int acnt2, int amt) throws RemoteException {

       Integer fund1 = bankItems.get(acnt1);
       Integer fund2 = bankItems.get(acnt2);
       if (fund1 != null && fund2 != null ) {
           if (fund1.intValue() < amt) {
               return CODE.NO_SUFFICIENT_FUND;
           }

           System.out.printf("Account(%d):%d\t", acnt1, fund1);
           System.out.printf("Account(%d):%d\n", acnt2, fund2);

           fund1 = new Integer(fund1.intValue() - amt);
           fund2 = new Integer(fund2.intValue() + amt);
           bankItems.put(new Integer(acnt1), fund1);
           bankItems.put(new Integer(acnt2), fund2);

           System.out.printf("Account(%d):%d\t", acnt1, fund1);
           System.out.printf("Account(%d):%d\n", acnt2, fund2);

           // save the change back to the database
           saveData(bankItems, dataPath);
           return CODE.OK;
       }

	   return CODE.OK;
   }

   public int inquiry(int acnt) throws RemoteException {

       Integer fund = bankItems.get(acnt);
       if (fund != null) {
           return fund.intValue();
       }

       // no account found
       return -1;
   }

   private Hashtable<Integer, Integer> loadData(String path)
   {
	  Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();


	   try{
	     FileReader read = new FileReader(path);
	     BufferedReader br = new BufferedReader(read);
	     String row;
		 String[] item;
		 int HEADER = 1;  // skip the header line
		 
		 while((row = br.readLine())!=null){
			
		  if (HEADER-- > 0) {
			  continue;
		  }


		  item = row.split("\t");
		  ht.put(Integer.parseInt(item[0]), Integer.parseInt(item[1]));

	     // System.out.printf("row=%s item1=%s item2=%s\n", row, item[0], item[1]);
	    }
	   } catch(FileNotFoundException  e)
	   {
		   System.out.println("Error:"+e.toString());
	   } catch(IOException  e)
	   {
		   System.out.println("Error:"+e.toString());
	   }
		
	   return ht;
   }

    private void saveData(Hashtable<Integer, Integer> table, String path)  {

        FileWriter fw = null;
        StringBuilder data;

        data = new StringBuilder("Account\tBalance\n");

        try {
            fw = new FileWriter(path);

            Integer key;
            Enumeration em = table.keys();

            while(em.hasMoreElements()){
                key = (Integer) em.nextElement();

                data.append(key.toString() + "\t" + table.get(key) + "\n");
                // System.out.print(key);
                // System.out.println("-"+table.get(key));
             }

            fw.write(data.toString(),0,data.length());
            fw.flush();
            fw.close();

        }catch (IOException e) {
            System.out.println("Err=" + e.toString());
        } catch(NoSuchElementException e)
        {
            System.out.println("Err="+e.toString());
        }
    }
	
	private void PrintHash(Hashtable<Integer, Integer> hashtable)
    {
		try
		{
			Integer key;
			Enumeration e=hashtable.keys();
			while(e.hasMoreElements()){
				key = (Integer) e.nextElement();
				System.out.print(key);
				System.out.println("-"+hashtable.get(key));
			}
		} catch(NoSuchElementException e)
		{
			System.out.println("Err="+e.toString());
		}
		
	}
}
