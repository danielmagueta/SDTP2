package clientSide.main;

import clientSide.entities.*;
import clientSide.stubs.*;
import serverSide.main.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *    Client side of the AirLift (pilot).
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
   *        args[2] - name of the platform where is located the plane server
   *        args[3] - port number for listening to service requests
   *        args[4] - name of the platform where is located the arrival airport server
   *        args[5] - port number for listening to service requests
   *        args[6] - name of the platform where is located the general repository server
   *        args[7] - port number for listening to service requests
   *        args[8] - name of the logging file
   */

   public static void main (String [] args)
   {
      String DepartureAirportServerHostName;                             // name of the platform where is located the departure airport server
      int DepartureAirportServerPortNumb = -1;                           // port number for listening to service requests
      String PlaneAirportServerHostName;                                 // name of the platform where is located the plane server
      int PlaneAirportServerPortNumb = -1;                               // port number for listening to service requests
      String ArrivalAirportServerHostName;                               // name of the platform where is located the arrival airport server
      int ArrivalAirportServerPortNumb = -1;                             // port number for listening to service requests
      String genReposServerHostName;                                     // name of the platform where is located the general repository server
      int genReposServerPortNumb = -1;                                   // port number for listening to service requests
      String fileName;                                                   // name of the logging file
      Pilot pilot;                                                       // pilot thread
      DepartureAirportStub dAirportStub;                                 // remote reference to the departure airport
      PlaneStub planeStub;                                               // remote reference to the plane          
      DepartureAirportStub aAirportStub;                                 // remote reference to the arrival airport
      GeneralReposStub genReposStub;                                     // remote reference to the general repository


     /* getting problem runtime parameters */

      if (args.length != 9)
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
      
      PlaneServerHostName = args[2];
      try
      { PlaneServerPortNumb = Integer.parseInt (args[3]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[3] is not a number!");
        System.exit (1);
      }
      if ((PlaneServerPortNumb < 4000) || (PlaneServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[3] is not a valid port number!");
           System.exit (1);
         }
      
      ArrivalAirportServerHostName = args[4];
      try
      { ArrivalAirportServerPortNumb = Integer.parseInt (args[5]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[5] is not a number!");
        System.exit (1);
      }
      if ((ArrivalAirportServerPortNumb < 4000) || (ArrivalAirportServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[5] is not a valid port number!");
           System.exit (1);
         }
      
      genReposServerHostName = args[6];
      try
      { genReposServerPortNumb = Integer.parseInt (args[7]);
      }
      catch (NumberFormatException e)
      { GenericIO.writelnString ("args[5] is not a number!");
        System.exit (1);
      }
      if ((genReposServerPortNumb < 4000) || (genReposServerPortNumb >= 65536))
         { GenericIO.writelnString ("args[7] is not a valid port number!");
           System.exit (1);
         }
      
      fileName = args[8];
      

     /* problem initialization */
      
      dAirportStub = new DepartureAirportStub(DepartureAirportServerHostName, DepartureAirportServerPortNumb);
      planeStub = new PlaneStub(PlaneServerHostName, PlaneServerPortName);
      aAirportStub = new ArrivalAirportStub(ArrivalAirportServerHostName, ArrivalAirportServerPortNumb);
      genReposStub = new GeneralReposStub (genReposServerHostName, genReposServerPortNumb);
      genReposStub.initSimul (fileName);
      hostess = new Hostess("Pilot", dAirportStub, planeStub, aAirportStub);

     /* start of the simulation */
     
      pilot.start();

     /* waiting for the end of the simulation */

      GenericIO.writelnString ();
      try
      { pilot.join ();
      }
        catch (InterruptedException e) {}
        GenericIO.writelnString ("The pilot has terminated.");
      }
      GenericIO.writelnString ();
      dAirportStub.shutdown();
      planeStub.shutdown();
      aAirportStub.shutdown ();
      genReposStub.shutdown ();
   }
}
