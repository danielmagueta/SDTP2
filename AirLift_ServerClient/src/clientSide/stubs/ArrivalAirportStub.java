package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *  Stub to the arrival airport.
 *
 *    It instantiates a remote reference to the arrival airport.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class ArrivalAirportStub
{
  /**
   *  Name of the platform where is located the arrival airport server.
   */

   private String serverHostName;

  /**
   *  Port number for listening to service requests.
   */

   private int serverPortNumb;

  /**
   *   Instantiation of a stub to the barber shop.
   *
   *     @param serverHostName name of the platform where is located the arrival airport server
   *     @param serverPortNumb port number for listening to service requests
   */

   public ArrivalAirportStub (String serverHostName, int serverPortNumb)
   {
      this.serverHostName = serverHostName;
      this.serverPortNumb = serverPortNumb;
   }

   
      /**
   *  @return number of passengers that have leaved the plane in this flight.
   */
   
    public int getnOut() {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())                                           // waits for a connection to be established
      { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.GETNOUT);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.NOUT))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      return inMessage.getIntVal();
    }
    
    /**
   *  @param nOut Set number of passengers that have leaved the plane in this flight.
   */
    
    public void setnOut(int nOut) {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())                                           // waits for a connection to be established
      { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.SETNOUT,nOut);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.SETNOUTDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
    }
   
   
   
   /**
   *    Get number of passengers that have arrived and deboarded already.
   * 
   *       @return number of passengers that have arrived and deboarded already
   */
    public int getnPassengerArrived() {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())                                           // waits for a connection to be established
      { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.GETPASSARRIVED);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.RECEIVEPASSARRIVED))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      return inMessage.getIntVal();
    }
   
  
   /**
    *  Announcing arrival to the arrival airport.
    *
    *  It is called by a pilot when the plane arrives at the arrival airport and is ready to initiate the deboarding.
    * 
    */
    public void leaveThePlane ()
    {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())                                           // waits for a connection to be established
      { try
        { Thread.currentThread ().sleep ((long) (10));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.LEAVE, ((Passenger) Thread.currentThread ()).getPassengerId (),
                                ((Passenger) Thread.currentThread ()).getPassengerState ());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.LEAVEDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if (inMessage.getPassengerId () != ((Passenger) Thread.currentThread ()).getPassengerId())
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid passenger id!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState () > PassengerStates.AT_DESTINATION))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid passenger state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Passenger) Thread.currentThread ()).setPassengerState (inMessage.getPassengerState ());
    }
   


   /**
   *   Operation server shutdown.
   *
   */

   public void shutdown ()
   {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())
      { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.SHUTDOWN);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if (inMessage.getMsgType() != MessageType.SHUTDOWNDONE)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
   }
}
