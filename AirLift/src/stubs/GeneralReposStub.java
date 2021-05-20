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
public class GeneralReposStub {
    
    /**
   *   @return number of passengers in queue.
   *
   */
   
    public int getInQ() {
       return 1;
    }
    
   /**
   *   
   *   @return number of passengers in flight.
   */
    public int getInF() {
       return 1;
    }
    
   /**
   *   @return numbr of passengers that have arrived.
   *
   */
    public int getPTAL() {
      return 1;
    }
   
 
   
  /**
   *   Add one to the numbers of passengers in queue.
   *
   */

   public void addInQ ()
   {
     
   }
   
    /**
   *   Subtract one to the numbers of passengers in queue.
   *
   */

   public void subtractInQ ()
   {
   
   }
   
  /**
   *   Add one to the numbers of passengers in flight.
   *
   */

   public void addInF ()
   {

   }
   
   /**
   *   Subtract one to the numbers of passengers in flight.
   *
   */

   public void subtractInF ()
   {

   }
   
  /**
   *   Add one to the numbers of passengers that arrived at destination.
   *
   */
   public void addPTAL ()
   {
      
   }
   

  /**
   *   Set pilot state.
   *
   *     @param pilot pilot
   *     @param state pilot state
   */

   public void setPilotState (int state)
   {
     
   }
   
  /**
   *   Set hostess state.
   *
   *     @param hostess hostess
   *     @param state hostess state
   */

   public void setHostessState (int state)
   {
      
   }

  /**
   *   Set h state.
   *
   *     @param id passenger id
   *     @param state passenger state
   */

   public void setPassengerState (int id, int state)
   {
    
   }


  /**
   *  Write the header to the logging file.
   *
   *  The passengers are going to the airport, the hostess is waiting for next flight and the pilot is at the transfer gate.
   *  Internal operation.
   */

   private void reportInitialStatus ()
   {
    
   }

  /**
   *  Write a state line at the end of the logging file.
   *
   *  The current state of the barbers and the customers is organized in a line to be printed.
   *  Internal operation.
   */

   private void reportStatus ()
   {
     
   }
   
   
  /**
   *  Report that the flight boarding started.
   *  Internal operation.
   */

   public void reportBoarding ()
   {

   }
   
  /**
   *  Report that the passenger checked his/her documents.
   *  Internal operation.
   *     @param id passenger id
   */

   public void reportCheck (int id)
   {
 
   }
   
     /**
   *  Report that the flight has departed.
   *  Internal operation.
   */

   public void reportDeparted ()
   {
      
   }
   
  /**
   *  Report that the flight has arrived at arrival airport.
   *  Internal operation.
   */

   public void reportArrived ()
   {
    
   }
   
     /**
   *  Report that the flight is returning to the initial airport.
   *  Internal operation.
   */

   public void reportreturning ()
   {

   }
}
