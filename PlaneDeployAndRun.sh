echo "Transfering data to the plane node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws03.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws03.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirPlane.zip sd301@l040101-ws03.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the plane node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws03.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirPlane.zip'
echo "Executing program at the plane node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws03.ua.pt 'cd test/AirLiftTP2/dirPlane ; java serverSide.main.PlaneMain 22302 l040101-ws08.ua.pt 22300'
