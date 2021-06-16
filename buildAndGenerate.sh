echo "Compiling source code."
javac */*.java */*/*.java
echo "Distributing intermediate code to the different execution environments."

echo "  General Repository of Information"
rm -rf dirGeneralRepos
mkdir -p dirGeneralRepos dirGeneralRepos/serverSide dirGeneralRepos/serverSide/main dirGeneralRepos/serverSide/entities dirGeneralRepos/serverSide/sharedRegions \
         dirGeneralRepos/clientSide dirGeneralRepos/clientSide/entities dirGeneralRepos/commInfra dirGeneralRepos/genclass
cp serverSide/main/GeneralReposMain.class dirGeneralRepos/serverSide/main
cp serverSide/entities/GeneralReposProxy.class dirGeneralRepos/serverSide/entities
cp serverSide/sharedRegions/GeneralReposInterface.class serverSide/sharedRegions/GeneralRepos.class dirGeneralRepos/serverSide/sharedRegions
cp clientSide/entities/PilotStates.class clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class \
   clientSide/entities/PassengerCloning.class clientSide/entities/PilotCloning.class clientSide/entities/HostessCloning.class dirGeneralRepos/clientSide/entities
cp commInfra/*.class dirGeneralRepos/commInfra
cp genclass/*.class dirGeneralRepos/genclass

echo "  Departure Airport"
rm -rf dirDepartureAirport
mkdir -p dirDepartureAirport dirDepartureAirport/serverSide dirDepartureAirport/serverSide/main dirDepartureAirport/serverSide/entities dirDepartureAirport/serverSide/sharedRegions \
         dirDepartureAirport/clientSide dirDepartureAirport/clientSide/entities dirDepartureAirport/clientSide/stubs dirDepartureAirport/commInfra dirDepartureAirport/genclass
cp commInfra/SimulPar.class serverSide/main/DepartureAirportMain.class dirDepartureAirport/serverSide/main
cp serverSide/entities/DepartureAirportProxy.class dirDepartureAirport/serverSide/entities
cp serverSide/sharedRegions/DepartureAirportInterface.class serverSide/sharedRegions/DepartureAirport.class dirDepartureAirport/serverSide/sharedRegions
cp clientSide/entities/PilotStates.class clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class \
   clientSide/entities/PassengerCloning.class clientSide/entities/PilotCloning.class clientSide/entities/HostessCloning.class dirDepartureAirport/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirDepartureAirport/clientSide/stubs
cp commInfra/*.class dirDepartureAirport/commInfra
cp genclass/*.class dirDepartureAirport/genclass

echo "  Plane"
rm -rf dirPlane
mkdir -p dirPlane dirPlane/serverSide dirPlane/serverSide/main dirPlane/serverSide/entities dirPlane/serverSide/sharedRegions \
         dirPlane/clientSide dirPlane/clientSide/entities dirPlane/clientSide/stubs dirPlane/commInfra dirPlane/genclass
cp commInfra/SimulPar.class serverSide/main/PlaneMain.class dirPlane/serverSide/main
cp serverSide/entities/PlaneProxy.class dirPlane/serverSide/entities
cp serverSide/sharedRegions/PlaneInterface.class serverSide/sharedRegions/Plane.class dirPlane/serverSide/sharedRegions
cp clientSide/entities/PilotStates.class clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class \
   clientSide/entities/PassengerCloning.class clientSide/entities/PilotCloning.class clientSide/entities/HostessCloning.class dirPlane/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirPlane/clientSide/stubs
cp commInfra/*.class dirPlane/commInfra
cp genclass/*.class dirPlane/genclass

echo "  Arrival Airport"
rm -rf dirArrivalAirport
mkdir -p dirArrivalAirport dirArrivalAirport/serverSide dirArrivalAirport/serverSide/main dirArrivalAirport/serverSide/entities dirArrivalAirport/serverSide/sharedRegions \
         dirArrivalAirport/clientSide dirArrivalAirport/clientSide/entities dirArrivalAirport/clientSide/stubs dirArrivalAirport/commInfra dirArrivalAirport/genclass
cp commInfra/SimulPar.class serverSide/main/ArrivalAirportMain.class dirArrivalAirport/serverSide/main
cp serverSide/entities/ArrivalAirportProxy.class dirArrivalAirport/serverSide/entities
cp serverSide/sharedRegions/ArrivalAirportInterface.class serverSide/sharedRegions/ArrivalAirport.class dirArrivalAirport/serverSide/sharedRegions
cp clientSide/entities/PilotStates.class clientSide/entities/HostessStates.class clientSide/entities/PassengerStates.class \
   clientSide/entities/PassengerCloning.class clientSide/entities/PilotCloning.class clientSide/entities/HostessCloning.class dirArrivalAirport/clientSide/entities
cp clientSide/stubs/GeneralReposStub.class dirArrivalAirport/clientSide/stubs
cp commInfra/*.class dirArrivalAirport/commInfra
cp genclass/*.class dirArrivalAirport/genclass

echo "  Pilot"
rm -rf dirPilot
mkdir -p dirPilot/clientSide dirPilot/clientSide/main dirPilot/clientSide/entities \
         dirPilot/clientSide/stubs dirPilot/commInfra dirPilot/genclass
cp clientSide/main/PilotMain.class dirPilot/clientSide/main
cp clientSide/entities/Pilot.class clientSide/entities/PilotStates.class dirPilot/clientSide/entities
cp clientSide/stubs/*.class dirPilot/clientSide/stubs
cp commInfra/SimulPar.class commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirPilot/commInfra
cp genclass/*.class dirPilot/genclass

echo "  Hostess"
rm -rf dirHostess
mkdir -p dirHostess/clientSide dirHostess/clientSide/main dirHostess/clientSide/entities \
         dirHostess/clientSide/stubs dirHostess/commInfra dirHostess/genclass
cp clientSide/main/HostessMain.class dirHostess/clientSide/main
cp clientSide/entities/Hostess.class clientSide/entities/HostessStates.class dirHostess/clientSide/entities
cp clientSide/stubs/*.class dirHostess/clientSide/stubs
cp commInfra/SimulPar.class commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirHostess/commInfra
cp genclass/*.class dirHostess/genclass

echo "  Passenger"
rm -rf dirPassenger
mkdir -p dirPassenger/clientSide dirPassenger/clientSide/main dirPassenger/clientSide/entities \
         dirPassenger/clientSide/stubs dirPassenger/commInfra dirPassenger/genclass
cp clientSide/main/PassengerMain.class dirPassenger/clientSide/main
cp clientSide/entities/Passenger.class clientSide/entities/PassengerStates.class dirPassenger/clientSide/entities
cp clientSide/stubs/*.class dirPassenger/clientSide/stubs
cp commInfra/SimulPar.class commInfra/Message.class commInfra/MessageType.class commInfra/MessageException.class commInfra/ClientCom.class dirPassenger/commInfra
cp genclass/*.class dirPassenger/genclass

echo "Compressing execution environments."
echo "  General Repository of Information"
rm -f  dirGeneralRepos.zip
zip -rq dirGeneralRepos.zip dirGeneralRepos
echo "  Departure Airport"
rm -f  dirDepartureAirport.zip
zip -rq dirDepartureAirport.zip dirDepartureAirport
echo "  Plane"
rm -f  Plane.zip
zip -rq dirPlane.zip dirPlane
echo "  Arrival Airport"
rm -f  dirArrivalAirport.zip
zip -rq dirArrivalAirport.zip dirArrivalAirport
echo "  Pilot"
rm -f  dirPilot.zip
zip -rq dirPilot.zip dirPilot
echo "  Hostess"
rm -f  dirHostess.zip
zip -rq dirHostess.zip dirHostess
echo "  Passenger"
rm -f  dirPassenger.zip
zip -rq dirPassenger.zip dirPassenger
