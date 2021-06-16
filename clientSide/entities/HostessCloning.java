package clientSide.entities;

/**
 *    Hostess cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 */

public interface HostessCloning
{

  /**
   *   Set hostess state.
   *
   *     @param state new hostess state
   */

   public void setHostessState (int state);

  /**
   *   Get hostess state.
   *
   *     @return hostess state
   */

   public int getHostessState ();
}
