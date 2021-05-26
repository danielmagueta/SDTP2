package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;
import static java.lang.Thread.sleep;

/**
 *    Plane.
 *
 *    It is responsible to keep a continuously updated account of the passengers inside the plane.
 *    and is implemented using semaphores for synchronization.
 *    All public methods are executed in mutual exclusion.
 *    There are one internal synchronization points: an array of blocking points, one per each passenger, 
 *    where they wait for the end of the flight
 */

public class Plane {
 
  /**
   *  Reference to passenger threads.
   */

   private final Passenger [] passenger;
   
    /**
   *  Reference to number of passengers in flight.
   */

   private int nINF;
   
  /**
   *   Reference to the general repository.
   */

   private final GeneralReposStub repos;
   
   /**
   *  Semaphore to ensure mutual exclusion on the execution of public methods.
   */

   private final Semaphore access;



    /**
   *   Passenger waiting in flight waiting to leave.
   */

   private MemFIFO<Integer> passengerINF;
   
        /**
   *  Blocking semaphore for the passengers while they are in flight.
   */

   private final Semaphore [] in_flight;
   
   /**
   *  Plane instantiation.
   *
   *    @param repos reference to the general repository
   */

   public Plane (GeneralReposStub repos)
   {
      nINF = 0;
       passenger = new Passenger [SimulPar.N];
      for (int i = 0; i < SimulPar.N; i++)
        passenger[i] = null;
      this.repos = repos;
      access = new Semaphore ();
      access.up ();
      in_flight = new Semaphore [SimulPar.N];
      for (int i = 0; i < SimulPar.N; i++)
        in_flight[i] = new Semaphore ();
      try
      { passengerINF = new MemFIFO<> (new Integer [SimulPar.N]);
      }
      catch (MemException e)
      { GenericIO.writelnString ("Instantiation of in flight FIFO failed: " + e.getMessage ());
        passengerINF = null;
        System.exit (1);
      }
   }

    public int getnINF() {
        return nINF;
    }

    public void setnINF(int nINF) {
        this.nINF = nINF;
    }
    

   
     /**
   *    Passengers in flight
   * 
   *       @return FIFo of ids of passengers in the flight
   */
    public MemFIFO<Integer> getPassengerINF() {
        return passengerINF;
    }
    
   /**
    *  Flying to destination airport.
    *
    *  It is called by a pilot when he is flying to the destination airport.
    */
   
    public void flyToDestinationPoint ()
   {
      access.down();
      ((Pilot) Thread.currentThread()).setPilotState(3);
      repos.setPilotState ((Pilot) Thread.currentThread(),3);
      access.up();
      try
      { sleep ((long) (1 + 100 * Math.random ()));
      }
      catch (InterruptedException e) {}
   }
    
   /**
    *  Flying back to initial airport.
    *
    *  It is called by a pilot when he is flying to the departure airport.
    */
   
    public void flyToDeparturePoint ()
   {
      
      access.down();
      ((Pilot) Thread.currentThread()).setPilotState(5);
      while(nINF != 0){}
      repos.reportreturning ();
      repos.setPilotState ((Pilot) Thread.currentThread(),5);
      access.up();
      try
      { sleep ((long) (1 + 100 * Math.random ()));
      }
      catch (InterruptedException e) {}
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
        access.down();
        repos.addInF();
        int passengerID = ((Passenger) Thread.currentThread()).getPassengerId();     
        try
        { passengerINF.write (passengerID);                    
        }
        catch (MemException e)
        { GenericIO.writelnString ("Insertion of passenger in flight failed: " + e.getMessage ());
          access.up ();                
          System.exit (1);
        }
        ((Passenger) Thread.currentThread()).setPassengerState(2);
        repos.setPassengerState(passengerID,2);
        nINF ++;
        access.up();
    }
    
    
    /**
    *  Announcing arrival to the arrival airport.
    *
    *  It is called by a pilot when the plane arrives at the arrival airport and is ready to initiate the debaording.
    * 
    */ 
    public void announceArrival ()
    {
        access.down();
        int passengerID = -1;
        repos.reportArrived ();
        for(int i = 0; i<nINF; i++)
        {
            try
            { passengerID = passengerINF.read ();
              if ((passengerID < 0) || (passengerID >= SimulPar.N))
                 throw new MemException ("illegal passenger id!");
            }
            catch (MemException e)
            { GenericIO.writelnString ("Retrieval of passenger id from flight failed: " + e.getMessage ());
              access.up ();                                                // exit critical region
              System.exit (1);
            }

            ((Pilot) Thread.currentThread()).setPilotState(4);
            repos.setPilotState((Pilot) Thread.currentThread(),4);
            access.up();
            in_flight[passengerID].up();
        }
 
    }
    
    
        /**
   *  Operation for the passenger waiting for the end of the flight.
   *
   *  It is called by a passenger awaiting for the arrival of the plane at the arrival airport.
   *
   */
    public void waitForEndOfFligh ()
    {
    
        access.down();
        int passengerID = ((Passenger) Thread.currentThread()).getPassengerId();
        access.up();
        in_flight[passengerID].down();
    }

    
    


}
