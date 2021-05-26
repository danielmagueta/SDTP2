package commInfra;

/**
 *   Type of the exchanged messages.
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
  /**
   *  Initialization of the logging file name and the number of iterations (service request).
   */

   public static final int SETNFIC = 1;

  /**
   *  Logging file was initialized (reply).
   */

   public static final int NFICDONE = 2;

  /**
   *  Pilot informs plane is ready (service request).
   */

   public static final int PLANEREADY = 3;

  /**
   *  Plane is ready (reply).
   */

   public static final int PLANEREADYDONE = 4;

  /**
   *  Pilot waits for passengers (service request).
   */

   public static final int WAITALL = 5;

  /**
   *  All passengers aboard (reply).
   */

   public static final int WAITALLDONE = 6;

  /**
   *  Pilot parks the plane (service request).
   */

   public static final int PARK = 7;

  /**
   *  Plane is parked (reply).
   */

   public static final int PARKDONE = 8;

  /**
   *  Pilot signal to end hostess thread (service request).
   */

   public static final int ENDHOSTESS = 9;

  /**
   *  Hostess thread ends (reply).
   */

   public static final int ENDHOSTESSDONE = 10;

  /**
   *  Hostess wait pilot (service request).
   */

   public static final int WAITPILOT = 11;

  /**
   *  Pilot arrives (reply)
   */

   public static final int WAITPILOTDONE = 12;

  /**
   *  Hostess prepares boarding (service request).
   */

   public static final int PREPAREBOARDING = 13;

  /**
   *  Boarding is prepared (reply).
   */

   public static final int PREPAREBOARDINGDONE = 14;

  /**
   *  Hostess check documents (service request).
   */

   public static final int CHECKDOC = 15;

  /**
   *  Documents are checked and passenger id returned (reply).
   */

   public static final int PASSID = 16;

  /**
   *  Hostess wait next passenger (service request).
   */

   public static final int WAITNEXTPASSENGER = 17;

  /**
   *  No more passengers to wait right now (reply)
   */

   public static final int TAKEOFF = 18;

  /**
   *  Still more passengers to be boarded (reply).
   */

   public static final int NOTAKEOFF = 19;
   
  /**
   *  Hostess inform plane is ready to take off (service request).
   */

   public static final int READYTOTAKEOFF = 20;
  
   /**
   *  Plane is ready to take off (reply).
   */

   public static final int READYTOTAKEOFFDONE = 21;
  
   /**
   *  Hostess waits next flight (service request).
   */

   public static final int WAITNEXTFLIGHT = 22;
  
   /**
   *  Hostess will wait for next flight (reply).
   */

   public static final int WAITFORNEXTFLIGHTDONE = 23;
  
   /**
   *  Hostess/Pilot asks for whom many passengers arrived in total (service request).
   */

   public static final int GETPASSARRIVED = 24;
  
   /**
   *  Retrieves numbers of passengers that have arrived in total.
   */

   public static final int RECEIVEPASSARRIVED = 25;
  
   /**
   *  Pilot asks how many passengers get out of the plane (service request).
   */

   public static final int GETNOUT = 26;
  
   /**
   *  Retrieves number of passenger that did get out (reply).
   */

   public static final int NOUT = 27;
  
   /**
   *  Pilot set passengers out to 0 (service request).
   */

   public static final int SETNOUT = 28;
   
   /**
   *  Passengers out set to 0 (reply).
   */

   public static final int SETNOUTDONE = 29;
   
   /**
   *  Pilot flies to destination (service request).
   */

   public static final int FLYDEST = 30;
   
   /**
   *  Pilot flies to destination (reply).
   */

   public static final int FLYDESTDONE = 31;
   
   /**
   *  Pilot arrived (service request).
   */

   public static final int ARRIVAL = 32;
   
   /**
   *  Pilot arrival confirmed (reply).
   */

   public static final int ARRIVALDONE = 33;
   
   /**
   *  Pilot asks how many passengers in flight (service request).
   */

   public static final int GETNINF = 34;
   
   /**
   *  Retrieves number of passengers in flight (reply).
   */

   public static final int NINF = 35;
   
   /**
   *  Pilot set ninf to 0 (service request).
   */

   public static final int SETNINF = 36;
   
   /**
   *  NINF set to 0 (reply).
   */

   public static final int SETNINFDONE = 37;
   
   /**
   *  Pilot is flying back (service request).
   */

   public static final int FLYBACK = 38;
   
   /**
   *  Pilot flying back confirmed (reply).
   */

   public static final int FLYBACKDONE = 39;
   
   /**
   *  Passenger goes to queue (service request).
   */

   public static final int WAITINQUEUE = 40;
   
   /**
   *  Passenger is waiting in queue (reply).
   */

   public static final int WAITINQUEUEDONE = 41;
   
   /**
   *  Passenger show documents (service request).
   */

   public static final int SHOWDOC = 42;
   
   /**
   *  Passenger documents shown (reply).
   */

   public static final int SHOWDOCDONE = 43;
   
   /**
   *  Passenger asks to board the plane (service request).
   */

   public static final int BOARD = 44;
   
   /**
   *  Passenger boards the plane (reply).
   */

   public static final int BOARDDONE = 45;
   
   /**
   *  Passenger waits for the end of the flight (service request).
   */

   public static final int WAITEND = 46;
   
   /**
   *  Passenger waiting for end of flight (reply).
   */

   public static final int WAITENDDONE = 47;
   
   /**
   *  Passenger tries to leave the plane (service request).
   */

   public static final int LEAVE = 48;
   
   /**
   *  Passenger leave the plane (reply).
   */

   public static final int LEAVEDONE = 49;
  
  /**
   *  Shutdown of server (service request).
   */

   public static final int SHUTDOWN = 50;
   
  /**
   *  Server is shutdown (reply).
   */

   public static final int SHUTDOWNDONE = 51;
   
   
   
}
