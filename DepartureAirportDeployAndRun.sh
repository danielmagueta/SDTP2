echo "Transfering data to the departure airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws02.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws02.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirDepartureAirport.zip sd301@l040101-ws02.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the departure airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws02.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirDepartureAirport.zip'
echo "Executing program at the departure airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws02.ua.pt 'cd test/AirLiftTP2/dirDepartureAirport ; java serverSide.main.DepartureAirportMain 22301 l040101-ws08.ua.pt 22300'
