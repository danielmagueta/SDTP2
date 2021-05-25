package commInfra;

/**
 *    Definition of the simulation parameters.
 */

public final class SimulPar
{
  /**
   *   Number of total passengers.
   */

   public static final int N = 21;

  /**
   *   Number of the minimum passengers allowed in a single flight.
   */

   public static final int MIN = 5;

 /**
   *   Number of the maximum passengers allowed in a single flight.
   */

   public static final int MAX = 10;

  /**
   *   It can not be instantiated.
   */
   
   /**
    * Departure Airport host name
    */
	public static final String DepartureAirportHostName = "127.0.0.1";

	/**
    * Departure Airport port
    */
	public static final int DepartureAirportPort = 30000;
   
   
        /**
    * Arrival Airport host name
    */
	public static final String ArrivalAirportHostName = "127.0.0.1";

	/**
    * Arrival Airport port
    */
	public static final int ArrivalAirportPort = 30001;
   
        /**
    * Plane hostname
    */
	public static final String PlaneHostName = "127.0.0.1";

	/**
    * Plane port
    */
	public static final int PlaneAirportPort = 30002;
   
        /**
    * General Repository host name
    */
	public static final String GeneralReposHostName = "127.0.0.1";

	/**
    * Departure Airport port
    */
	public static final int GeneralReposPort = 30003;
   
   
   private SimulPar ()
   { }
}
