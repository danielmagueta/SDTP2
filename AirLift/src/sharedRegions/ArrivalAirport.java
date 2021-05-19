package sharedRegions;

import main.*;
import entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *    Arrival Airport.
 *
 *    It is responsible to keep a continuously updated account of the passengers that arrived.
 *    This class doesn't use semaphores
 *    All public methods are executed in mutual exclusion.

 *    
 */

public class ArrivalAirport {
    

   
  /**
   *   Reference to the general repository.
   */

   private final GeneralRepos repos;
   
   /**
   *  Semaphore to ensure mutual exclusion on the execution of public methods.
   */

   private final Semaphore access;
   
  /**
   *  Number of passengers that have arrived and deboarded at destination.
   */

   private int nPassengerArrived;
   
   /**
   *  Number of passengers that have leaved the plane.
   */
   private int nOut;

   
   /**
   *  Departure Airport instantiation.
   *
   *    @param repos reference to the general repository
   */

   public ArrivalAirport (GeneralRepos repos)
   {

      this.repos = repos;
      access = new Semaphore ();
      access.up ();
      nPassengerArrived = 0;
      nOut = 0;
      
   }
   
   /**
   *  @return number of passengers that have leaved the plane in this flight.
   */
   
    public int getnOut() {
        return nOut;
    }
    
    /**
   *  @param nOut Set number of passengers that have leaved the plane in this flight.
   */
    
    public void setnOut(int nOut) {
        this.nOut = nOut;
    }
   
   
   
   /**
   *    Get number of passengers that have arrived and deboarded already.
   * 
   *       @return number of passengers that have arrived and deboarded alerady
   */
    public int getnPassengerArrived() {
        return nPassengerArrived;
    }
   
  
        /**
    *  Announcing arrival to the arrival airport.
    *
    *  It is called by a pilot when the plane arrives at the arrival airport and is ready to initiate the debaording.
    * 
    */
    public void leaveThePlane ()
    {
        
        access.down();
        repos.subtractInF();
        repos.addPTAL();
        ((Passenger) Thread.currentThread()).setPassengerState(3);
        repos.setPassengerState(((Passenger) Thread.currentThread()).getPassengerId(),3);
        nPassengerArrived ++;
        nOut ++;
        access.up();


    }

}
