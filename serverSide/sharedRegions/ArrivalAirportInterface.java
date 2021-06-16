package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

/**
 *  Interface to the Arrival Airport.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Arrival Airport and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class ArrivalAirportInterface
{
  /**
   *  Reference to the Arrival Airport.
   */

   private final ArrivalAirport aAirport;

  /**
   *  Instantiation of an interface to the arrival airport.
   *
   *    @param aAirport reference to the arrival airport
   */

   public ArrivalAirportInterface (ArrivalAirport aAirport)
   {
      this.aAirport = aAirport;
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
      { case MessageType.GETPASSARRIVED:    //check nothing
                                            break;
        case MessageType.GETNOUT:           //check nothing
                                            break;
        case MessageType.SETNOUT:           if ((inMessage.getIntVal() != 0))
                                                throw new MessageException ("No an aceptable set value!", inMessage);
                                            break;
        case MessageType.LEAVE:             if ((inMessage.getPassengerId () < 0) || (inMessage.getPassengerId () >= SimulPar.N))
                                                throw new MessageException ("Invalid passenger id!", inMessage);
                                            else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) || (inMessage.getPassengerState() > PassengerStates.AT_DESTINATION))
                                                throw new MessageException ("Invalid passenger state!", inMessage);
                                            break;                                
        case MessageType.SHUTDOWN:          // check nothing
                                            break;
        default:                            throw new MessageException ("Invalid message type!", inMessage);
      }

     /* processing */

      switch (inMessage.getMsgType ())

      { case MessageType.GETPASSARRIVED:    int nPassArrived = aAirport.getnPassengerArrived();
                                            outMessage = new Message (MessageType.RECEIVEPASSARRIVED,nPassArrived);
                                            break;
        case MessageType.GETNOUT:           int nOut = aAirport.getnOut();
                                            outMessage = new Message (MessageType.NOUT,nOut);
                                            break;
        case MessageType.SETNOUT:           aAirport.setnOut(inMessage.getIntVal());
                                            outMessage = new Message (MessageType.SETNOUTDONE);
                                            break;
        case MessageType.LEAVE:             ((ArrivalAirportProxy) Thread.currentThread()).setPassengerId(inMessage.getPassengerId());
                                            ((ArrivalAirportProxy) Thread.currentThread()).setPassengerState(inMessage.getPassengerState()); 
                                            aAirport.leaveThePlane();
                                            outMessage = new Message (MessageType.LEAVEDONE,((ArrivalAirportProxy)Thread.currentThread()).getPassengerId(),
                                            ((ArrivalAirportProxy)Thread.currentThread()).getPassengerState());
                                            break;                 
        case MessageType.SHUTDOWN:          aAirport.shutdown ();
                                            outMessage = new Message (MessageType.SHUTDOWNDONE);
                                            break;
      }
     return (outMessage);
   }
}
