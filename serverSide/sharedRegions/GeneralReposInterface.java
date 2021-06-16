package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

/**
 *  Interface to the General Repository of Information.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    General Repository and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class GeneralReposInterface
{
  /**
   *  Reference to the general repository.
   */

   private final GeneralRepos repos;

  /**
   *  Instantiation of an interface to the general repository.
   *
   *    @param repos reference to the general repository
   */

   public GeneralReposInterface (GeneralRepos repos)
   {
      this.repos = repos;
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
       Message outMessage = null;                                     // mensagem de resposta

     /* validation of the incoming message */

      switch (inMessage.getMsgType ())
      { case MessageType.SETNFIC:       if (inMessage.getLogFName () == null)
                                        throw new MessageException ("Name of the logging file is not present!", inMessage);
                                        break;
        case MessageType.ADDINQ:        //check nothing
                                        break;
        case MessageType.SUBTRACTINQ:   //check nothing
                                        break;                    
        case MessageType.ADDINF:        //check nothing
                                        break;
        case MessageType.SUBTRACTINF:   //check nothing
                                        break;
        case MessageType.ADDPTAL:       //check nothing
                                        break;
        case MessageType.PILOTSTATE:    if ((inMessage.getPilotState () < PilotStates.AT_TRANSFER_GATES) || (inMessage.getPilotState() > PilotStates.FLYING_BACK))
                                              throw new MessageException ("Invalid pilot state!", inMessage);
                                        break;    
        case MessageType.HOSTESSSTATE:  if ((inMessage.getHostessState () < HostessStates.WAIT_FOR_NEXT_FLIGHT) || (inMessage.getHostessState() > HostessStates.READY_TO_FLY))
                                              throw new MessageException ("Invalid hostess state!", inMessage);
                                        break; 
        case MessageType.PASSENGERSTATE:   if ((inMessage.getPassengerId() < 0) || (inMessage.getPassengerId() >= SimulPar.N))
                                            throw new MessageException ("Invalid passenger id!", inMessage);
                                           else if ((inMessage.getPassengerState () < PassengerStates.GOING_TO_AIRPORT) 
                                                   || (inMessage.getPassengerState () > PassengerStates.AT_DESTINATION))
                                              throw new MessageException ("Invalid passenger state!", inMessage);
                                           break; 
        case MessageType.REPORTBOARDING:    //check nothing
                                            break;
        case MessageType.REPORTCHECK:       if ((inMessage.getIntVal () < 0) || (inMessage.getIntVal () >= SimulPar.N))
                                                throw new MessageException ("Invalid passenger id!", inMessage);
                                            break;
        case MessageType.REPORTDEPARTED:   //check nothing
                                           break;
        case MessageType.REPORTARRIVED:    //check nothing
                                           break;
        case MessageType.REPORTRETURNING:  //check nothing
                                           break;
        case MessageType.SHUTDOWN:         // check nothing
                                           break;
        default:                   throw new MessageException ("Invalid message type!", inMessage);
      }

     /* processing */

      switch (inMessage.getMsgType ())

      { case MessageType.SETNFIC:           repos.initSimul (inMessage.getLogFName ());
                                            outMessage = new Message (MessageType.NFICDONE);
                                            break;
        case MessageType.ADDINQ:            repos.addInQ();
                                            outMessage = new Message (MessageType.ADDINQDONE);
                                            break;
        case MessageType.SUBTRACTINQ:       repos.subtractInQ();
                                            outMessage = new Message (MessageType.SUBTRACTINQDONE);
                                            break;
        case MessageType.ADDINF:            repos.addInF();
                                            outMessage = new Message (MessageType.ADDINFDONE);
                                            break;
        case MessageType.SUBTRACTINF:       repos.subtractInF();
                                            outMessage = new Message (MessageType.SUBTRACTINFDONE);
                                            break;
        case MessageType.ADDPTAL:           repos.addPTAL();
                                            outMessage = new Message (MessageType.ADDPTALDONE);
                                            break;                                   
        case MessageType.PILOTSTATE:        repos.setPilotState(inMessage.getPilotState());
                                            outMessage = new Message (MessageType.PILOTSTATEDONE);
                                            break;                                    
        case MessageType.HOSTESSSTATE:      repos.setHostessState(inMessage.getHostessState());
                                            outMessage = new Message (MessageType.HOSTESSSTATEDONE);
                                            break;                                   
        case MessageType.PASSENGERSTATE:    repos.setPassengerState(inMessage.getPassengerId(), inMessage.getPassengerState());
                                            outMessage = new Message (MessageType.PASSENGERSTATEDONE);
                                            break;  
        case MessageType.REPORTBOARDING:    repos.reportBoarding();
                                            outMessage = new Message (MessageType.REPORTBOARDINGDONE);
                                            break;
        case MessageType.REPORTCHECK:       repos.reportCheck(inMessage.getIntVal());
                                            outMessage = new Message (MessageType.REPORTCHECKDONE);
                                            break;
        case MessageType.REPORTDEPARTED:    repos.reportDeparted();
                                            outMessage = new Message (MessageType.REPORTDEPARTEDDONE);
                                            break;
        case MessageType.REPORTARRIVED:     repos.reportArrived();
                                            outMessage = new Message (MessageType.REPORTARRIVEDDONE);
                                            break;
        case MessageType.REPORTRETURNING:   repos.reportreturning();
                                            outMessage = new Message (MessageType.REPORTRETURNINGDONE);
                                            break;                           
        case MessageType.SHUTDOWN:          repos.shutdown ();
                                            outMessage = new Message (MessageType.SHUTDOWNDONE);
                                            break;                                   
                      
      }

     return (outMessage);
   }
}
