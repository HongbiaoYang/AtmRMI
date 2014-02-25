all:bank bankInterface bankServer bankClient
bank:bank.java
	javac bank.java
bankInterface:bankInterface.java
	javac bankInterface.java
bankServer:bankServer.java
	javac bankServer.java
bankClient:bankClient.java
	javac bankClient.java
clean:
	rm -rf *.class
server:bankServer
	java bankServer 10999
client:bankClient
	java bankClient hydra1.eecs.utk.edu:10999 deposit 1000 12
