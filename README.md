JavaChatSystem
==============

A Java Chat System

Protocol
--------

Commands :

+ *connect* _id_ : permettra se connecter au serveur sous l’identiﬁant id
+ *send* _msg_ : enverra `a tous les autres participants le message msg
+ *bye* : eﬀectuera la d´econnexion du serveur
+ *who* : aﬃche la liste des identiﬁants des clients connect´es au serveur

Architecture :

+ First step :
++ Server
+++ The server creates a stub and a skeleton
+++ The server object registers itself to the rmiregistry (Naming.rebind)
+++ The registry records the stub
+++ The registry is set up to send data to the server
++ Client
+++ The client objet calls the rmiregistry to localize the object server (Naming.lookup)
+++ The registry delivers a copy of the stub
+++ The stub object is installed and its reference is returned to the client
+++ The client calls the server object with a call to the stub object 
+ Client
++ 
+ Server
++ 