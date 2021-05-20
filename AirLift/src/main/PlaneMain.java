/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketTimeoutException;
import commInfra.*;
import sharedRegions.Plane;
import stubs.*;
import proxies.*;
/**
 *
 * @author danimag
 */
public class PlaneMain {
    
    /**
	* Plane main's thread
 	* @throws SocketTimeoutException if the socket timeouts
 	* @throws NoSuchMethodException if the method doesn't exist
	* @throws NoSuchFieldException if the field doesn't exist
	* @throws InstantiationException instantiation exception
	* @throws IllegalAccessException illegal access exception
	* @throws InvocationTargetException invocation target exception
	*/
	public static void main(String args[]) throws SocketTimeoutException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

		ServerCom serverCom, serverConn;
		GeneralReposStub repos = new GeneralReposStub();
		Plane plane = new Plane(repos);
		PlaneProxy proxy = new PlaneProxy();

		serverCom = new ServerCom(SimulPar.GeneralReposPort, 1000);
		serverCom.start();

		while(proxy.getSimStatus()) {
			try {
				serverConn = serverCom.accept();
				serviceProvider = new ServiceProvider(proxy, serverConn);
				serviceProvider.start();
			} catch (SocketTimeoutException e) {}
		}
		System.out.printf("%s: Plane ended\n");
	}
    
}
