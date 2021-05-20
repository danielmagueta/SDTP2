/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import stubs.*;
import entities.Hostess;
import genclass.GenericIO;

/**
 *
 * @author danimag
 */
public class HostessMain {
    
     public static void main(String args[]) {

	//Stub Shared memory regions
        DepartureAirportStub dAirport = new DepartureAirportStub();
        ArrivalAirportStub aAirport = new ArrivalAirportStub();

        Hostess hostess = new Hostess("Hostess", dAirport, aAirport);

        hostess.start();


         try
            { hostess.join ();
            }
            catch (InterruptedException e) {}
            GenericIO.writelnString ("The hostess has terminated.");
          GenericIO.writelnString ();	
        
    }
}
