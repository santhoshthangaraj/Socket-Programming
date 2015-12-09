# Socket-Programming

Implement an HTTP client and server that run a simplified version of HTTP/1.1. Specifically, implement two HTTP commands: GET and PUT. This project can be completed in C++, Java, or Python. Other languages may be allowed, but only with advance permission from the instructor and TA.
HTTP Client
Client should take the following command line arguments (in order): server name, port on which to contact the server, HTTP command (GET or PUT), and the path of the requested
object on the server. In other words, assuming that your executable is named “myclient”, it should be able to run the program from the command line as follows:
myclient hostname port command filename

In response to a GET command, the client must:
1. connect to the server via a TCP connection
2. submit a valid HTTP/1.1 GET request to the server
3. read the server’s response and display it
In response to a PUT command, the client must:
1. Connect to the server via a TCP connection
2. submit a valid HTTP/1.1 PUT request to the server
3. send the file to the server
4. wait for the server’s reply
5. read the server’s response and display it

HTTP Server
Server should take a command line argument that specifies the port number that the server will use to listen for incoming connection requests. In other words, assuming that your
executable is named “myserver”, it should be able to run  server from the command line as follows: 
myserver port
server must:
1. Create a socket with the specified port number
2. Listen for incoming connections from clients
3. When a client connection is accepted, read the HTTP request
4. Construct a valid HTTP response:
a. When the server receives a GET request, it should either construct a “200
OK” message followed by the requested object or a “404 Not Found”
message.
b. When the server receives a PUT request, it should save the file locally.
c. If the received file is successfully saved, the server should construct a “200 OK
File Created” response.
5. Send the HTTP response over the TCP connection with the client
6. Close the client connection
7. Continue to “loop” to listen for incoming connection

Project 1
Multithreaded server will have an infinite loop to listen for connections. To shut down server, it has to interrupt it with a termination signal. Upon receiving the
termination signal, your server must shut down gracefully, closing all sockets before exiting. Design the program that best matches programming styles.
Advice on how to tackle this project:
• Implement your client first.
• Test your client’s GET command with an external HTTP server.
o Before you test your client with your server, test it with a web server that you know works. In other words, use your client to a get a file from some known
external HTTP server. For example, from the command line: myclient
www.amazon.com 80 GET index.html
• Now implement the server.
• Test the server with a browser (e.g., Firefox) as a HTTP client.
o For example, your server is running at host pc1.cs.uncc.edu on port number 12000, and there is a file index.html in the current directory. In the URL box
of the web browser, type: pc1.cs.uncc.edu:12000/index.html The browser should fetch the file and display it.
• Now use your client to get a file from your HTTP server. For example, if your server
is running on pc1.cs.uncc.edu port 12000, then enter at the command line: myclient
pc1.cs.uncc.edu 12000 GET index.html
• Now use your client to put a file on your HTTP server.

Project 1
Part 2: UDP vs. TCP - A Performance Evaluation 
Objective: To become familiar with TCP and UDP performance characteristics in practice by
using socket programming.
Procedure:
1. Using TCP port number 8777, write a sender program and a receiver program. Each program runs on a different computer. First, the sender opens a connection to the receiver (in case of
TCP). Next, the sender starts the testing process by recording the current time just before sending a test message, as shown in figure 1 below, with sequence number 1. Initially, the
padding should be a 10 character string. Upon receiving this message, the receiver copies it into an array, then sends it back to the sender, which can now measure the End-to-End (ETE)
delay by subtracting from current time the time recorded earlier and dividing the result by two. Next, the sender sends another test message, this time with sequence number 2, and
again measures the ETE. Then another message with sequence number 3, and so on and so forth. Have the sender repeat this process 1000 times, recording the overall average and the
maximum ETE.
Sequence number
(integer)
Padding
(string, all character are ‘C’)
Figure 1: Format of the test message.
2. Repeat 1, 5 times, and record the average and maximum ETE for each of the 5 times.
3. Repeat 2 with a 200-character string.
4. Repeat 2 with a 1000-character string.
5. Repeat 2, 3, and 4, but this time with UDP instead of TCP.
Questions
1) Are the average ETE values, obtained from step 2, different for each of the 5 times that step 1
is repeated? Why?
2) Compare the average and maximum ETE for steps 2, 3, and 4 for TCP only. Explain the
differences.
3) Compare the average and maximum ETE between TCP and UDP, for each of steps 2, 3, and
4. Explain the differences between TCP and UDP. What conclusion can you draw in terms of
the performance of TCP and UDP?
4) If the tests were run over the Internet, where there is a chance of packet loss, how would the
ETE values be different between UDP and TCP?
