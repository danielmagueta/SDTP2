/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import stubs.*;
import commInfra.*;
import entities.Passenger;
import genclass.GenericIO;
/**
 *
 * @author danimag
 */
public class PassengerMain {
 
     public static void main(String args[]) {

	//Stub Shared memory regions
        DepartureAirportStub dAirport = new DepartureAirportStub();
        PlaneStub plane = new PlaneStub();
        ArrivalAirportStub aAirport = new ArrivalAirportStub();
        
        Passenger [] passenger = new Passenger [SimulPar.N];   // array of passenger threads
        
        for (int i = 0; i < SimulPar.N; i++)
            passenger[i] = new Passenger ("Passenger_" + (i+1), i, dAirport, plane, aAirport);
        
        for (int i = 0; i < SimulPar.N; i++)
        { try
          { passenger[i].join ();
          }
          catch (InterruptedException e) {}
          GenericIO.writelnString ("The passenger " + (i+1) + " has terminated.");
        }
        GenericIO.writelnString ();
 
    }
}
