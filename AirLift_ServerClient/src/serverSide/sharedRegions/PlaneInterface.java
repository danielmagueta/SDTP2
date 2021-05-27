package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

/**
 *  Interface to the Plane.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Plane and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class PlaneInterface
{
  /**
   *  Reference to the Plane.
   */

   private final Plane plane;

  /**
   *  Instantiation of an interface to the plane.
   *
   *    @param plane reference to the plane
   */

   public PlaneInterface (Plane plane)
   {
      this.plane = plane;
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
      { case MessageType.FLYDEST:  if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                              throw new MessageException ("Invalid pilot state!", inMessage);
                                   break;
        case MessageType.ARRIVAL:    if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                              throw new MessageException ("Invalid pilot state!", inMessage);
                                   break;
        case MessageType.GETNINF: //check nothing
                                   break;
        case MessageType.SETNINF:    if ((inMessage.getIntVal() != 0))
                                      throw new MessageException ("No an aceptable set value!", inMessage);
                                   break;
        case MessageType.FLYBACK:  if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState () > PilotStates.FLYING_BACK))
                                              throw new MessageException ("Invalid pilot state!", inMessage);
                                   break;
        case MessageType.BOARD:    if ((inMessage.getPassengerId () < 0) || (inMessage.getPassengerId () >= SimulPar.N))
                                      throw new MessageException ("Invalid passenger id!", inMessage);
                                      else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState() > PassengerStates.AT_DESTINATION))
                                              throw new MessageException ("Invalid passenger state!", inMessage);
                                   break;
        case MessageType.WAITEND:  if ((inMessage.getPassengerId () < 0) || (inMessage.getPassengerId () >= SimulPar.N))
                                      throw new MessageException ("Invalid passenger id!", inMessage);
                                      else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState() > PassengerStates.AT_DESTINATION))
                                              throw new MessageException ("Invalid passenger state!", inMessage);
                                   break;                                   
        case MessageType.SHUTDOWN:     // check nothing
                                   break;
        default:                   throw new MessageException ("Invalid message type!", inMessage);
      }

     /* processing */

      switch (inMessage.getMsgType ())

      { case MessageType.FLYDEST:  ((PlaneProxy) Thread.currentThread()).setPilotState(inMessage.getPilotState ());
                                   plane.flyToDestinationPoint();
                                   outMessage = new Message (MessageType.FLYDESTDONE,((PlaneProxy)Thread.currentThread()).getPilotState());
                                   break;
        case MessageType.ARRIVAL:  ((PlaneProxy)Thread.currentThread()).setPilotState(inMessage.getPilotState());
                                   plane.announceArrival();
                                   outMessage = new Message (MessageType.ARRIVALDONE,((PlaneProxy)Thread.currentThread()).getPilotState());
                                   break;
        case MessageType.GETNINF:  int INF = plane.getnINF();
                                   outMessage = new Message (MessageType.NINF,INF);
                                   break;
        case MessageType.SETNINF:  int NINF = inMessage.getIntVal();
                                   plane.setnINF(NINF);
                                   outMessage = new Message (MessageType.SETNINFDONE);
                                   break;
        case MessageType.FLYBACK:  ((PlaneProxy) Thread.currentThread()).setPilotState(inMessage.getPilotState());
                                   plane.flyToDeparturePoint();
                                   outMessage = new Message (MessageType.FLYBACKDONE,((PlaneProxy)Thread.currentThread()).getPilotState());
                                   break;
        case MessageType.BOARD:    ((PlaneProxy) Thread.currentThread()).setPassengerId(inMessage.getPassengerId());
                                   ((PlaneProxy) Thread.currentThread()).setPassengerState(inMessage.getPassengerState()); 
                                   plane.boardThePlane();
                                   outMessage = new Message (MessageType.BOARDDONE,((PlaneProxy)Thread.currentThread()).getPassengerId(),
                                           ((PlaneProxy)Thread.currentThread()).getPassengerState());
                                   break;
        case MessageType.WAITEND:  ((PlaneProxy) Thread.currentThread()).setPassengerId(inMessage.getPassengerId());
                                   ((PlaneProxy) Thread.currentThread()).setPassengerState(inMessage.getPassengerState()); 
                                   plane.waitForEndOfFligh();
                                   outMessage = new Message (MessageType.WAITENDDONE,((PlaneProxy)Thread.currentThread()).getPassengerId(),
                                           ((PlaneProxy)Thread.currentThread()).getPassengerState());
                                   break;                                 
        case MessageType.SHUTDOWN: plane.shutdown ();
                                   outMessage = new Message (MessageType.SHUTDOWNDONE);
                                   break;
      }

     return (outMessage);
   }
}
