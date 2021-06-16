xterm  -T "General Repository" -hold -e "./GeneralReposDeployAndRun.sh" &
xterm  -T "Departure Airport" -hold -e "./DepartureAirportDeployAndRun.sh" &
xterm  -T "Plane"  -hold -e "./PlaneDeployAndRun.sh" &
xterm  -T "Arrival Airport" -hold -e "./ArrivalAirportDeployAndRun.sh" &
sleep 1
xterm  -T "Pilot" -hold -e "./PilotDeployAndRun.sh" &
xterm  -T "Hostess" -hold -e "./HostessDeployAndRun.sh" &
xterm  -T "Passenger" -hold -e "./PassengerDeployAndRun.sh" &
