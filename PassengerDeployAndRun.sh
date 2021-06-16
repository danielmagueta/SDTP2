echo "Transfering data to the passenger node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws07.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws07.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirPassenger.zip sd301@l040101-ws07.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the passenger node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws07.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirPassenger.zip'
echo "Executing program at the passenger node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws07.ua.pt 'cd test/AirLiftTP2/dirPassenger ; java clientSide.main.PassengerMain l040101-ws02.ua.pt 22301 l040101-ws03.ua.pt 22302 l040101-ws04.ua.pt 22303 l040101-ws08.ua.pt 22300'
