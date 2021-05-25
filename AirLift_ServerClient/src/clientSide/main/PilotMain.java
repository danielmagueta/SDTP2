/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientSide.main;
import clientSide.stubs.*;
import clientSide.entities.Pilot;
import genclass.GenericIO;

/**
 *
 * @author danimag
 */
public class PilotMain {
    
    
    public static void main(String args[]) {

	 //Stub Shared memory regions
        DepartureAirportStub dAirport = new DepartureAirportStub();
        PlaneStub plane = new PlaneStub();
        ArrivalAirportStub aAirport = new ArrivalAirportStub();

        Pilot pilot = new Pilot("Pilot", dAirport, plane, aAirport);
        pilot.setPriority(6);
        GeneralReposStub repos = new GeneralReposStub();

        pilot.start();


         try
            { pilot.join ();
            }
            catch (InterruptedException e) {}
            GenericIO.writelnString ("The pilot has terminated.");
          GenericIO.writelnString ();	
        
    }
   
    
}
