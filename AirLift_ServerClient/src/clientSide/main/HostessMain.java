package clientSide.main;

import clientSide.entities.*;
import clientSide.stubs.*;
import serverSide.main.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *    Client side of the AirLift (hostess).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class HostessMain
{
  /**
   *  Main method.
   *
   *    @param args runtime arguments
   *        args[0] - name of the platform where is located the departure airport server
   *        args[1] - port number for listening to service requests
   *        args[2] - name of the platform where is located the arrival airport server
   *        args[3] - port number for listening to service requests
   *        args[4] - name of the platform where is located the general repository server
   *        args[5] - port number for listening to service requests
   */

   public static void main (String [] args)
   {
      String DepartureAirportServerHostName;                             // name of the platform where is located the departure airport server
      int DepartureAirportServerPortNumb = -1;                           // port number for listening to service requests
      String ArrivalAirportServerHostName;                               // name of the platform where is located the arrival airport server
      int ArrivalAirportServerPortNumb = -1;                             // port number for listening to service requests
      String genReposServerHostName;                                     // name of the platform where is located the general repository server
      int genReposServerPortNumb = -1;                                   // port number for listening to service requests
      Hostess hostess;                                                   // hostess thread
      DepartureAirportStub dAirportStub;                                 // remote reference to the departure airport
      ArrivalAirportStub aAirportStub;                                   // remote reference to the arrival airport
      GeneralReposStub genReposStub;                                     // remote reference to the general repository


     /* getting problem runtime parameters */

      if (args.length != 6)
         { GenericIO.writelnString ("Wrong number of parameters!");
           System.exit (1);
         }
      
      DepartureAirportServerHostName = args[0];
      try
      { DepartureAirportServerPortNumb = Integer.parseInt (args[1]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[1] is not a number!");
        System.exit (1);
      }
      if ((DepartureAirportServerPortNumb < 4000) || (DepartureAirportServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[1] is not a valid port number!");
           System.exit (1);
         }
      
      ArrivalAirportServerHostName = args[2];
      try
      { ArrivalAirportServerPortNumb = Integer.parseInt (args[3]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[3] is not a number!");
        System.exit (1);
      }
      if ((ArrivalAirportServerPortNumb < 4000) || (ArrivalAirportServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[3] is not a valid port number!");
           System.exit (1);
         }
      
      genReposServerHostName = args[4];
      try
      { genReposServerPortNumb = Integer.parseInt (args[5]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[5] is not a number!");
        System.exit (1);
      }
      if ((genReposServerPortNumb < 4000) || (genReposServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[5] is not a valid port number!");
           System.exit (1);
         }
      
      

     /* problem initialization */
      
      dAirportStub = new DepartureAirportStub(DepartureAirportServerHostName, DepartureAirportServerPortNumb);
      aAirportStub = new ArrivalAirportStub(ArrivalAirportServerHostName, ArrivalAirportServerPortNumb);
      genReposStub = new GeneralReposStub (genReposServerHostName, genReposServerPortNumb);
      hostess = new Hostess("Hostess", dAirportStub, aAirportStub);

     /* start of the simulation */
     
      hostess.start();

     /* waiting for the end of the simulation */

      GenericIO.writelnString ();
      try
      { hostess.join ();
      }
      catch (InterruptedException e) {}
      GenericIO.writelnString ("The hostess has terminated.");
      
      GenericIO.writelnString();
      dAirportStub.shutdown();
      aAirportStub.shutdown();
      genReposStub.shutdown();
   }
}
