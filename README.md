# JPassport, 3DPassport user manager
3DPassport user manager in 3DExperience platform.
By this tool, you can easily remove unused or unwanted users from 3DPassport.

Examples:
    3dexpServer: Hostname or IP address of 3DPassport server
    3dpassdb: Database name of 3DPassport
    dbadmin: Database admin username
    dbpass: Database password

-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u dbadmin:dbpass -l
-	java -jar JPassport.jar -s 192.168.1.1:3dpassdb -u dbadmin:dbpass -l
-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u dbadmin:dbpass -d id:13
-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u dbadmin:dbpass -d mail:%unused@domain%
