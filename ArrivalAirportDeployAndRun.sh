echo "Transfering data to the arrival airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws04.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws04.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirArrivalAirport.zip sd301@l040101-ws04.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the arrival airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws04.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirArrivalAirport.zip'
echo "Executing program at the arrival airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws04.ua.pt 'cd test/AirLiftTP2/dirArrivalAirport ; java serverSide.main.ArrivalAirportMain 22303 l040101-ws01.ua.pt 22300'