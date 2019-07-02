# JPassport, 3DPassport user manager By M.Shams v1.0
By this tool, you can easily remove unused or unwanted users from 3DPassport.

Examples:
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -l
    java -jar JPassport.jar -s 192.168.1.1:3dpassdb -u 3dadmin:mypassword -l
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d id:13
    java -jar JPassport.jar -s 3dexpServer:3dpassdb -u 3dadmin:mypassword -d mail:%unused@domain%


Usage:[-h|--help] (-s|--server) <server> (-u|--user) <user> [-l|--list] [(-d|--del) <del>]
  [-h|--help]
        Get help information.

  (-s|--server) <server>
        Set server IP and database name.
        Ex: -s localhost:3dsdb

  (-u|--user) <user>
        Set username and password of 3dPassport database.
        Ex: -u x3d:myPassword

  [-l|--list]
        Get list of users.

  [(-d|--del) <del>]
        Delete specific user.
        Ex: -d id:101
        Ex: -d mail:myname@domain.com
        Ex: -d mail:%john@dom%

