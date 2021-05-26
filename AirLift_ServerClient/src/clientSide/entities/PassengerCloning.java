package clientSide.entities;

/**
 *    Passenger cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 */

public interface PassengerCloning
{
  /**
   *   Set passenger id.
   *
   *     @param id passenger id
   */

   public void setPassengerId (int id);

  /**
   *   Get passenger id.
   *
   *     @return passenger id
   */

   public int getCustomerId ();

  /**
   *   Set passenger state.
   *
   *     @param state new passenger state
   */

   public void setPassengerState (int state);

  /**
   *   Get passenger state.
   *
   *     @return passenger state
   */

   public int getPassengerState ();
}
