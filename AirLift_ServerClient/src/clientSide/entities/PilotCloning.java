package clientSide.entities;

/**
 *    Pilot cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 */

public interface PilotCloning
{

  /**
   *   Set pilot state.
   *
   *     @param state new pilot state
   */

   public void setPilotState (int state);

  /**
   *   Get pilot state.
   *
   *     @return pilot state
   */

   public int getPilotState ();
}
