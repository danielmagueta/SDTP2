package serverSide.entities;

import serverSide.sharedRegions.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *  Service provider agent for access to the Plane.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class PlaneProxy extends Thread implements PassengerCloning, PilotCloning
{
  /**
   *  Number of instantiated threads.
   */

   private static int nProxy = 0;

  /**
   *  Communication channel.
   */

   private ServerCom sconi;

  /**
   *  Interface to the Departure Airport.
   */

   private PlaneInterface planeInter;

  /**
   *  Passenger identification.
   */

   private int passengerId;

  /**
   *  Passenger state.
   */

   private int passengerState;

  /**
   *  Pilot state.
   */

   private int pilotState;



  /**
   *  Instantiation of a client proxy.
   *
   *     @param sconi communication channel
   *     @param planeInter interface to the plane
   */

   public PlaneProxy (ServerCom sconi, PlaneInterface planeInter)
   {
      super ("PlaneProxy_" + PlaneProxy.getProxyId ());
      this.sconi = sconi;
      this.planeInter = planeInter;
   }

  /**
   *  Generation of the instantiation identifier.
   *
   *     @return instantiation identifier
   */

   private static int getProxyId ()
   {
      Class<?> cl = null;                                            // representation of the PlaneProxy object in JVM
      int proxyId;                                                   // instantiation identifier

      try
      { cl = Class.forName ("serverSide.entities.PlaneProxy");
      }
      catch (ClassNotFoundException e)
      { GenericIO.writelnString ("Data type PlaneProxy was not found!");
        e.printStackTrace ();
        System.exit (1);
      }
      synchronized (cl)
      { proxyId = nProxy;
        nProxy += 1;
      }
      return proxyId;
   }

  /**
   *   Set passenger id.
   *
   *     @param id passenger id
   */

   public void setPassengerId (int id)
   {
      passengerId = id;
   }

  /**
   *   Get passenger id.
   *
   *     @return passenger id
   */

   public int getPassengerId ()
   {
      return passengerId;
   }

  /**
   *   Set passenger state.
   *
   *     @param state new passenger state
   */

   public void setPassengerState (int state)
   {
      passengerState = state;
   }

  /**
   *   Get passenger state.
   *
   *     @return passenger state
   */

   public int getPassengerState ()
   {
      return passengerState;
   }
   
   
  /**
   *   Set pilot state.
   *
   *     @param state new pilot state
   */

   public void setPilotState (int state)
   {
      pilotState = state;
   }

  /**
   *   Get pilot state.
   *
   *     @return pilot state
   */

   public int getPilotState ()
   {
      return pilotState;
   }
   
  

  /**
   *  Life cycle of the service provider agent.
   */

   @Override
   public void run ()
   {
      Message inMessage = null,                                      // service request
              outMessage = null;                                     // service reply

     /* service providing */

      inMessage = (Message) sconi.readObject ();                     // get service request
      try
      { outMessage = planeInter.processAndReply (inMessage);         // process it
      }
      catch (MessageException e)
      { GenericIO.writelnString ("Thread " + getName () + ": " + e.getMessage () + "!");
        GenericIO.writelnString (e.getMessageVal ().toString ());
        System.exit (1);
      }
      sconi.writeObject (outMessage);                                // send service reply
      sconi.close ();                                                // close the communication channel
   }
}