package commInfra;

import java.io.*;
import genclass.GenericIO;

/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class Message implements Serializable
{
  /**
   *  Serialization key.
   */

   private static final long serialVersionUID = 2021L;

  /**
   *  Message type.
   */

   private int msgType = -1;


  /**
   *  Pilot state.
   */

   private int pilotState = -1;
   
  /**
   *  Hostess state.
   */

   private int hostessState = -1;

  /**
   *  Passenger identification.
   */

   private int passengerId = -1;

  /**
   *  Passenger state.
   */

   private int passengerState = -1;

  /**
   *  Integer value
   */

   private int int_val = -1;
   

  /**
   *  Name of the logging file.
   */

   private String fName = null;



  /**
   *  Message instantiation (form 1).
   *
   *     @param type message type
   */

   public Message (int type)
   {
      msgType = type;
   }

  /**
   *  Message instantiation (form 2).
   *
   *     @param type message type
   *     @param int_val1 first integer
   *     @param int_val2 second integer
   */

   public Message (int type, int int_val1, int int_val2)
   {
      msgType = type;
      if ((msgType == MessageType.PASSID) || (msgType == MessageType.WAITNEXTPASSENGER))
         { hostessState = int_val1;
           int_val = int_val2;
         }
      else  
         { passengerId= int_val1;
           passengerState = int_val2;
         }
                
   }

  /**
   *  Message instantiation (form 3).
   *
   *     @param type message type
   *     @param int_val hostess or pilot state / integer value to pass
   */

   public Message (int type, int int_val)
   {
      msgType = type;
      if ((msgType == MessageType.NOUT) || (msgType == MessageType.RECEIVEPASSARRIVED) || (msgType == MessageType.SETNOUT)
              || (msgType == MessageType.NINF) || (msgType == MessageType.SETNINF))
         { this.int_val = int_val;
         }
      else if ((msgType == MessageType.PLANEREADY) || (msgType == MessageType.PLANEREADYDONE) || (msgType == MessageType.WAITALL)
              || (msgType == MessageType.WAITALLDONE) || (msgType == MessageType.PARK) || (msgType == MessageType.PARKDONE) 
              || (msgType == MessageType.PILOTSTATE))
         { pilotState = int_val;
         }
      else  
         { 
           hostessState = int_val;
         }
   }


  /**
   *  Message instantiation (form 4).
   *
   *     @param type message type
   *     @param name name of the logging file
   */

   public Message (int type, String name)
   {
      msgType = type;
      fName= name;
   }

  /**
   *  Getting message type.
   *
   *     @return message type
   */

   public int getMsgType ()
   {
      return (msgType);
   }

  /**
   *  Getting passenger identification.
   *
   *     @return passenger identification
   */

   public int getPassengerId ()
   {
      return (passengerId);
   }

  /**
   *  Getting passenger state.
   *
   *     @return passenger state
   */

   public int getPassengerState ()
   {
      return (passengerState);
   }


  /**
   *  Getting pilot state.
   *
   *     @return pilot state
   */

   public int getPilotState ()
   {
      return (pilotState);
   }
   
  /**
   *  Getting hostess state.
   *
   *     @return hostess state
   */

   public int getHostessState ()
   {
      return (hostessState);
   }


  /**
   *  Getting integer value.
   *
   *     @return integer value
   */

   public int getIntVal ()
   {
      return (int_val);
   }
   
  /**
   *  Getting name of logging file.
   *
   *     @return name of the logging file
   */

   public String getLogFName ()
   {
      return (fName);
   }


  /**
   *  Printing the values of the internal fields.
   *
   *  It is used for debugging purposes.
   *
   *     @return string containing, in separate lines, the pair field name - field value
   */

   @Override
   public String toString ()
   {
      return ("Message type = " + msgType +
              "\nPilot State = " + pilotState +
              "\nHostess State = " + hostessState +
              "\nPassenger Id = " + passengerId +
              "\nPassenger State = " + passengerState +
              "\nInteger Value = " + int_val +
              "\nName of logging file = " + fName);
      
   }
}
