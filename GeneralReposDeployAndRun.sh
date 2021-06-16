echo "Transfering data to the general repository node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws01.ua.pt 'mkdir -p test/AirLiftTP2'
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws01.ua.pt 'rm -rf test/AirLiftTP2/*'
sshpass -f password scp -o StrictHostKeyChecking=no dirGeneralRepos.zip sd301@l040101-ws01.ua.pt:test/AirLiftTP2
echo "Decompressing data sent to the general repository node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws01.ua.pt 'cd test/AirLiftTP2 ; unzip -uq dirGeneralRepos.zip'
echo "Executing program at the general repository node."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws01.ua.pt 'cd test/AirLiftTP2/dirGeneralRepos ; java serverSide.main.GeneralReposMain 22300'
echo "Server shutdown."
sshpass -f password ssh -o StrictHostKeyChecking=no sd301@l040101-ws01.ua.pt 'cd test/AirLiftTP2/dirGeneralRepos ; less log'
