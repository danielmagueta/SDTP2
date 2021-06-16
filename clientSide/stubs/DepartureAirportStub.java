package clientSide.stubs;

import serverSide.main.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *  Stub to the departure airport.
 *
 *    It instantiates a remote reference to the departure airport.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */


public class DepartureAirportStub
{
  /**
   *  Name of the platform where is located the departure airport server.
   */

   private String serverHostName;

  /**
   *  Port number for listening to service requests.
   */

   private int serverPortNumb;

  /**
   *   Instantiation of a stub to the barber shop.
   *
   *     @param serverHostName name of the platform where is located the departure airport server
   *     @param serverPortNumb port number for listening to service requests
   */

   public DepartureAirportStub (String serverHostName, int serverPortNumb)
   {
      this.serverHostName = serverHostName;
      this.serverPortNumb = serverPortNumb;
   }
     

//pilot life cicle
   
  /**
   *  Operation for the pilot to unblock the waiting hostess.
   *
   *  It is called by a pilot to unlock the hostess thread, so it can terminate.
   *
   */
    
    public void endHostess ()
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
      outMessage = new Message (MessageType.ENDHOSTESS);

      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.ENDHOSTESSDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();    
    }
    
  /**
   *  Operation inform that the plane is ready for boarding.
   *
   *  It is called by a pilot when the plane is ready for the next boarding.
   *
   */
    
    public void informPlaneReadyForBoarding ()
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
      outMessage = new Message (MessageType.PLANEREADY, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.PLANEREADYDONE))
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
   *  Operation for the pilot to wait for conclusion of boarding.
   *
   *  It is called by a pilot when the hostess informs the plane is ready to take off.
   *
   */
    
    public void waitForAllInBoard ()
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
      outMessage = new Message (MessageType.WAITALL, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.WAITALLDONE))
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
   *  Operation for the pilot to park at the transfer gate.
   *
   *  It is called by a pilot when he parks the plane at the transfer gate, .
   *
   */
    public void parkAtTransferGate ()
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
      outMessage = new Message (MessageType.PARK, ((Pilot) Thread.currentThread ()).getPilotState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.PARKDONE))
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
    
    //hostess life cicle
    
   /**
   *  Operation for the hostess to prepare for pass boarding.
   *
   *  It is called by a hostess when she prepares to wait for the passengers.
   *
   */
    public void prepareForPassBoarding ()
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
      outMessage = new Message (MessageType.PREPAREBOARDING, ((Hostess) Thread.currentThread ()).getHostessState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.PREPAREBOARDINGDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid hostess state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Hostess) Thread.currentThread ()).setHostessState (inMessage.getHostessState ());           
    }
    
    
    /**
   *  Operation for the hostess to check documents.
   *
   *  It is called by a hostess when she is checking the passenger documents.
   * @return id of the passenger
   *
   */
    public int checkDocuments ()
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
      outMessage = new Message (MessageType.CHECKDOC, ((Hostess) Thread.currentThread ()).getHostessState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.PASSID))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid hostess state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Hostess) Thread.currentThread ()).setHostessState (inMessage.getHostessState ()); 
      return inMessage.getIntVal();
    }


    

    
    
    /**
   *  Operation for the hostess to be locked in the first iteration.
   *
   *  It is called by a hostess in the first iteration so shw waits for the plane to be ready for boarding.
   * 

   */
    public void waitPilot ()
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
      outMessage = new Message (MessageType.WAITPILOT);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.WAITPILOTDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();         
    }
    
    /**
   *  Operation for the hostess to wait for a new passenger.
   *
   *  It is called by a hostess when she waits for a new passenger.
   * 
   * @param passengerID id of passenger
   *
   * @return true if the plane is ready to fly, false otherwise
   */
    public boolean waitForNextPassenger (int passengerID)
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
      outMessage = new Message (MessageType.WAITNEXTPASSENGER, ((Hostess) Thread.currentThread ()).getHostessState(), passengerID);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.TAKEOFF) && (inMessage.getMsgType () != MessageType.NOTAKEOFF))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid hostess state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Hostess) Thread.currentThread ()).setHostessState (inMessage.getHostessState ()); 
       return (inMessage.getMsgType() == MessageType.TAKEOFF);
        
        
    }
    
    /**
   *  Operation for the hostess to inform the plane is ready to flight.
   *
   *  It is called by a hostess after the plane gets ready to leave.
   *
   */
    public void informPlaneReadyToTakeOff ()
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
      outMessage = new Message (MessageType.READYTOTAKEOFF, ((Hostess) Thread.currentThread ()).getHostessState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.READYTOTAKEOFFDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid hostess state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Hostess) Thread.currentThread ()).setHostessState (inMessage.getHostessState ());   
    }
    
    /**
   *  Operation for the hostess to wait for the next flight.
   *
   *  It is called by a hostess while she is waiting for the next flight.
   *
   */
    public void waitForNextFlight ()
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
      outMessage = new Message (MessageType.WAITNEXTFLIGHT, ((Hostess) Thread.currentThread ()).getHostessState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.WAITNEXTFLIGHTDONE))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid hostess state!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
      ((Hostess) Thread.currentThread ()).setHostessState (inMessage.getHostessState ());        
    }
    
     //passenger life cicle
    
    
   /**
   *  Operation for the passenger to go to the waiting queue.
   *
   *  It is called by a passenger after going to the airport.
   *
   */
    public void waitInQueue ()
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
      outMessage = new Message (MessageType.WAITINQUEUE, ((Passenger) Thread.currentThread()).getPassengerId()
              , ((Passenger) Thread.currentThread()).getPassengerState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.WAITINQUEUEDONE))
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
   *  Operation for the passenger to show his/her documents to the hostess.
   *
   *  It is called by a passenger showing his/her documents to teh hostess.
   *
   */
    public void showDocuments ()
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
      outMessage = new Message (MessageType.SHOWDOC, ((Passenger) Thread.currentThread()).getPassengerId()
              , ((Passenger) Thread.currentThread()).getPassengerState());
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if ((inMessage.getMsgType () != MessageType.SHOWDOCDONE))
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
