import java.rmi.*;
/**
 * Remote Interface for the bank
 */
public interface bankInterface extends Remote {
  /**
   * Remotely invocable method, deposit amt from acnt
   * @return void
   * @exception RemoteException if the remote invocation fails.
   */
  public CODE deposit(int acnt, int amt) throws RemoteException;

  /**
   * Remotely invocable method, withdraw amt from acnt
   * @return void
   * @exception RemoteException if the remote invocation fails.
   */
  public CODE withdraw(int acnt, int amt) throws RemoteException;

   /**
   * Remotely invocable method, transfer amt from acnt1 to acnt2
   * @return void
   * @exception RemoteException if the remote invocation fails.
   */
  public CODE transfer(int acnt1, int acnt2, int amt) throws RemoteException;

   /**
   * Remotely invocable method, inquiry balance of acnt
   * @return int
   * @exception RemoteException if the remote invocation fails.
   */
  public int inquiry(int acnt) throws RemoteException;

}
