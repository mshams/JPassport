# JPassport, 3DPassport user manager
3DPassport user manager in 3DExperience platform.
By this tool, you can easily remove unused or unwanted users from 3DPassport.

Examples:
- Get list of all users:\
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -l\
    java -jar JPassport.jar -s 192.168.1.1:3dpassdb -u 3dadmin:mypassword -l
    
- Delete user by id:\
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d id:13
    
- Delete users by mail wildcard:\
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d mail:%unused@domain%
