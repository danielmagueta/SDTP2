package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

/**
 *  Interface to the Departure Airport.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Departure Airport and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class DepartureAirportInterface
{
  /**
   *  Reference to the Departure Airport.
   */

   private final DepartureAirport dAirport;

  /**
   *  Instantiation of an interface to the departure airport.
   *
   *    @param dAirport reference to the departure airport
   */

   public DepartureAirportInterface (DepartureAirport dAirport)
   {
      this.dAirport = dAirport;
   }

  /**
   *  Processing of the incoming messages.
   *
   *  Validation, execution of the corresponding method and generation of the outgoing message.
   *
   *    @param inMessage service request
   *    @return service reply
   *    @throws MessageException if the incoming message is not valid
   */

   public Message processAndReply (Message inMessage) throws MessageException
   {
      Message outMessage = null;                                     // outgoing message

     /* validation of the incoming message */

      switch (inMessage.getMsgType ())
      { case MessageType.PLANEREADY:            if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES)   
                                                || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                                    throw new MessageException ("Invalid pilot state!", inMessage);
                                                break;
        case MessageType.WAITALL:               if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES)   
                                                || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                                    throw new MessageException ("Invalid pilot state!", inMessage);
                                                break;
        case MessageType.PARK:                  if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES)   
                                                || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                                    throw new MessageException ("Invalid pilot state!", inMessage);
                                                break;
        case MessageType.ENDHOSTESS:            //check nothing
                                                break;    
        case MessageType.WAITPILOT:             //check nothing
                                                break; 
        case MessageType.PREPAREBOARDING:      if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT)   
                                                || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
                                                    throw new MessageException ("Invalid hostess state!", inMessage);
                                                break;
        case MessageType.CHECKDOC:              if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT)   
                                                || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
                                                    throw new MessageException ("Invalid hostess state!", inMessage);
                                                break;
        case MessageType.WAITNEXTPASSENGER:     if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT)   
                                                || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
                                                    throw new MessageException ("Invalid hostess state!", inMessage);
                                                else if ((inMessage.getIntVal() < 0) || inMessage.getIntVal() >= SimulPar.N)
                                                    throw new MessageException ("Invalid passenger id!", inMessage);
                                                break;
        case MessageType.READYTOTAKEOFF:        if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT)   
                                                || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
                                                    throw new MessageException ("Invalid hostess state!", inMessage);
                                                break;
        case MessageType.WAITNEXTFLIGHT:        if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT)   
                                                || (inMessage.getHostessState () > HostessStates.READY_TO_FLY))
                                                    throw new MessageException ("Invalid hostess state!", inMessage);
                                                break;
        case MessageType.WAITINQUEUE:           if ((inMessage.getPassengerId () < 0) || (inMessage.getPassengerId () >= SimulPar.N))
                                                    throw new MessageException ("Invalid passenger id!", inMessage);
                                                else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState() > PassengerStates.AT_DESTINATION))
                                                    throw new MessageException ("Invalid passenger state!", inMessage);
                                                break;
        case MessageType.SHOWDOC:               if ((inMessage.getPassengerId () < 0) || (inMessage.getPassengerId () >= SimulPar.N))
                                                    throw new MessageException ("Invalid passenger id!", inMessage);
                                                else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState() > PassengerStates.AT_DESTINATION))
                                                    throw new MessageException ("Invalid passenger state!", inMessage);
                                                break;     
        case MessageType.SHUTDOWN:              // check nothing
                                                break;
        default:                                throw new MessageException ("Invalid message type!", inMessage);
      }

     /* processing */

      switch (inMessage.getMsgType ())

      { case MessageType.PLANEREADY:            ((DepartureAirportProxy) Thread.currentThread()).setPilotState(inMessage.getPilotState ());
                                                dAirport.informPlaneReadyForBoarding();
                                                outMessage = new Message (MessageType.PLANEREADYDONE,((DepartureAirportProxy)Thread.currentThread()).getPilotState());
                                                break;
        case MessageType.WAITALL:               ((DepartureAirportProxy) Thread.currentThread()).setPilotState(inMessage.getPilotState ());
                                                dAirport.waitForAllInBoard();
                                                outMessage = new Message (MessageType.WAITALLDONE,((DepartureAirportProxy)Thread.currentThread()).getPilotState());
                                                break;
        case MessageType.PARK:                  ((DepartureAirportProxy) Thread.currentThread()).setPilotState(inMessage.getPilotState ());
                                                dAirport.parkAtTransferGate();
                                                outMessage = new Message (MessageType.PARKDONE,((DepartureAirportProxy)Thread.currentThread()).getPilotState());
                                                break;
        case MessageType.ENDHOSTESS:            dAirport.endHostess();
                                                outMessage = new Message (MessageType.ENDHOSTESSDONE);
                                                break;    
        case MessageType.WAITPILOT:             dAirport.waitPilot();
                                                outMessage = new Message (MessageType.WAITPILOTDONE);
                                                break;  
        case MessageType.PREPAREBOARDING:       ((DepartureAirportProxy) Thread.currentThread()).setHostessState(inMessage.getHostessState ());
                                                dAirport.prepareForPassBoarding();
                                                outMessage = new Message (MessageType.PREPAREBOARDINGDONE,((DepartureAirportProxy)Thread.currentThread()).getHostessState());
                                                break;
        case MessageType.CHECKDOC:              ((DepartureAirportProxy) Thread.currentThread()).setHostessState(inMessage.getHostessState ());
                                                int passengerID = dAirport.checkDocuments();
                                                outMessage = new Message (MessageType.PASSID,((DepartureAirportProxy)Thread.currentThread()).getHostessState(), passengerID);
                                                break;
        case MessageType.WAITNEXTPASSENGER:     ((DepartureAirportProxy) Thread.currentThread()).setHostessState(inMessage.getHostessState ());
                                                if(dAirport.waitForNextPassenger(inMessage.getIntVal()))
                                                    outMessage = new Message (MessageType.TAKEOFF,((DepartureAirportProxy)Thread.currentThread()).getHostessState());
                                                else
                                                    outMessage = new Message (MessageType.NOTAKEOFF,((DepartureAirportProxy)Thread.currentThread()).getHostessState());
                                                break;
        case MessageType.READYTOTAKEOFF:        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(inMessage.getHostessState ());
                                                dAirport.informPlaneReadyToTakeOff();
                                                outMessage = new Message (MessageType.READYTOTAKEOFFDONE,((DepartureAirportProxy)Thread.currentThread()).getHostessState());
                                                break;
        case MessageType.WAITNEXTFLIGHT:        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(inMessage.getHostessState ());
                                                dAirport.waitForNextFlight();
                                                outMessage = new Message (MessageType.WAITNEXTFLIGHTDONE, ((DepartureAirportProxy)Thread.currentThread()).getHostessState());
                                                break;
        case MessageType.WAITINQUEUE:           ((DepartureAirportProxy) Thread.currentThread()).setPassengerId(inMessage.getPassengerId());
                                                ((DepartureAirportProxy) Thread.currentThread()).setPassengerState(inMessage.getPassengerState()); 
                                                dAirport.waitInQueue();
                                                outMessage = new Message (MessageType.WAITINQUEUEDONE,((DepartureAirportProxy)Thread.currentThread()).getPassengerId(),
                                                ((DepartureAirportProxy)Thread.currentThread()).getPassengerState());
                                                break;  
        case MessageType.SHOWDOC:               ((DepartureAirportProxy) Thread.currentThread()).setPassengerId(inMessage.getPassengerId());
                                                ((DepartureAirportProxy) Thread.currentThread()).setPassengerState(inMessage.getPassengerState()); 
                                                dAirport.showDocuments();
                                                outMessage = new Message (MessageType.SHOWDOCDONE,((DepartureAirportProxy)Thread.currentThread()).getPassengerId(),
                                                ((DepartureAirportProxy)Thread.currentThread()).getPassengerState());
                                                break;    
        case MessageType.SHUTDOWN:              dAirport.shutdown ();
                                                outMessage = new Message (MessageType.SHUTDOWNDONE);
                                                break;


      }
     return (outMessage);
   }
}
