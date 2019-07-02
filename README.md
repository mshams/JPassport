# JPassport, 3DPassport user manager
3DPassport user manager in 3DExperience platform.
By this tool, you can easily remove unused or unwanted users from 3DPassport.

Examples:
-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -l
-	java -jar JPassport.jar -s 192.168.1.1:3dpassdb -u 3dadmin:mypassword -l
-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d id:13
-	java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d mail:%unused@domain%
