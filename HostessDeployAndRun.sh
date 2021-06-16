echo "Transfering data to the hostess airport node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws06.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws06.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirHostess.zip sd301@l040101-ws06.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the hostess node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws06.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirHostess.zip'
echo "Executing program at the hostess node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws06.ua.pt 'cd test/AirLiftTP2/dirHostess ; java clientSide.main.HostessMain l040101-ws02.ua.pt 22301 l040101-ws04.ua.pt 22303 l040101-ws01.ua.pt 22300'