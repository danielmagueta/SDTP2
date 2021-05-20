/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

/**
 *
 * @author danimag
 */
public class DepartureAirportStub {
    
          /**
   *  @return number of passengers in queue .
   */
    
    public int getnINQ() {
        return 1;
    }


    
    

//pilot life cicle
   
  /**
   *  Operation for the pilot to unblock the waiting hostess.
   *
   *  It is called by a pilot to unlock the hostess thread, so it can terminate.
   *
   */
    
    public void endHostess ()
    {
      
    }
    
  /**
   *  Operation inform that the plane is ready for boarding.
   *
   *  It is called by a pilot when the plane is ready for the next boarding.
   *
   */
    
    public void informPlaneReadyForBoarding ()
    {
       

    }
    
  /**
   *  Operation for the pilot to wait for conclusion of boarding.
   *
   *  It is called by a pilot when the hostess informs the plane is ready to take off.
   *
   */
    
    public void waitForAllInBoard ()
    {
      
    }
    

  /**
   *  Operation for the pilot to park at the transfer gate.
   *
   *  It is called by a pilot when he parks the plane at the transfer gate, .
   *
   */
    public void parkAtTransferGate (){
         
    }
    
    //hostess life cicle
    
   /**
   *  Operation for the hostess to prepare for pass boarding.
   *
   *  It is called by a hostess when she prepares to wait for the passengers.
   *
   */
    public void prepareForPassBoarding ()
    {
        
    }
    
    
    /**
   *  Operation for the hostess to check documents.
   *
   *  It is called by a hostess when she is checking the passenger documents.
   * @return id of the passenger
   *
   */
    public int checkDocuments ()
    {
        return 1;
    }


    

    
    
    /**
   *  Operation for the hostess to be locked in the first iteration.
   *
   *  It is called by a hostess in the first iteration so shw waits for the plane to be ready for boarding.
   * 

   */
    public void waitPilot ()
    {
        

    }
    
    /**
   *  Operation for the hostess to wait for a new passenger.
   *
   *  It is called by a hostess when she waits for a new passenger.
   * 
   * @param passengerID id of passenger
   *
   * @return true if the plane is ready to fly, false otherwise
   */
    public boolean waitForNextPassenger (int passengerID)
    {
        return false;
        
        
    }
    
    /**
   *  Operation for the hostess to inform the plane is ready to flight.
   *
   *  It is called by a hostess after the plane gets ready to leave.
   *
   */
    public void informPlaneReadyToTakeOff ()
    {
     
    }
    
    /**
   *  Operation for the hostess to wait for the next flight.
   *
   *  It is called by a hostess while she is waiting for the next flight.
   *
   */
    public void waitForNextFlight ()
    {
     
    }
    
     //passenger life cicle
    
    
   /**
   *  Operation for the passenger to go to the waiting queue.
   *
   *  It is called by a passenger after going to the airport.
   *
   */
    public void waitInQueue ()
    {
        
    }
    
    /**
   *  Operation for the passenger to show his/her documents to the hostess.
   *
   *  It is called by a passenger showing his/her documents to teh hostess.
   *
   */
    public void showDocuments ()
    {
        
        
    }

}
