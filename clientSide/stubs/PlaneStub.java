package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *  Stub to the departure airport.
 *
 *    It instantiates a remote reference to the plane.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class PlaneStub
{
  /**
   *  Name of the platform where is located the plane server.
   */

   private String serverHostName;

  /**
   *  Port number for listening to service requests.
   */

   private int serverPortNumb;

  /**
   *   Instantiation of a stub to the plane.
   *
   *     @param serverHostName name of the platform where is located the departure airport server
   *     @param serverPortNumb port number for listening to service requests
   */

   public PlaneStub (String serverHostName, int serverPortNumb)
   {
      this.serverHostName = serverHostName;
      this.serverPortNumb = serverPortNumb;
   }
    
    public int getnINF() {
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
      outMessage = new Message (MessageType.GETNINF);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.NINF))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      return inMessage.getIntVal();
    }

    public void setnINF(int nINF) {
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
      outMessage = new Message (MessageType.SETNINF,nINF);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.SETNINFDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();       
    }
    


   /**
    *  Flying to destination airport.
    *
    *  It is called by a pilot when he is flying to the destination airport.
    */
   
    public void flyToDestinationPoint ()
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
      outMessage = new Message (MessageType.FLYDEST, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.FLYDESTDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid pilot state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Pilot) Thread.currentThread ()).setPilotState (inMessage.getPilotState ());     
   }
    
   /**
    *  Flying back to initial airport.
    *
    *  It is called by a pilot when he is flying to the departure airport.
    */
   
    public void flyToDeparturePoint ()
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
      outMessage = new Message (MessageType.FLYBACK, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.FLYBACKDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid pilot state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Pilot) Thread.currentThread ()).setPilotState (inMessage.getPilotState ());       
   
    }
    
    //passenger life cicle
   
   /**
   *  Operation for the passenger board the plane.
   *
   *  It is called by a passenger boarding the plane.
   *
   */
    public void boardThePlane() 
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
      outMessage = new Message (MessageType.BOARD, ((Passenger) Thread.currentThread()).getPassengerId()
              , ((Passenger) Thread.currentThread()).getPassengerState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.BOARDDONE))
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
    *  Announcing arrival to the arrival airport.
    *
    *  It is called by a pilot when the plane arrives at the arrival airport and is ready to initiate the debaording.
    * 
    */ 
    public void announceArrival ()
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
      outMessage = new Message (MessageType.ARRIVAL, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.ARRIVALDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid pilot state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Pilot) Thread.currentThread ()).setPilotState (inMessage.getPilotState ());      
 
    }
    
    
        /**
   *  Operation for the passenger waiting for the end of the flight.
   *
   *  It is called by a passenger awaiting for the arrival of the plane at the arrival airport.
   *
   */
    public void waitForEndOfFligh ()
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
      outMessage = new Message (MessageType.WAITEND, ((Passenger) Thread.currentThread()).getPassengerId()
              , ((Passenger) Thread.currentThread()).getPassengerState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.WAITENDDONE))
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
     
    }
    
    
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
