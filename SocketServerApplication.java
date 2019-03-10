	    // Name:Suraj Madhav Kawthekar	
		// Net id: 1001514614

		import java.awt.Color;
		import java.io.DataInputStream;
		import java.io.DataOutputStream;
		import java.io.File;
		import java.io.IOException;
		import java.net.ServerSocket;
		import java.net.Socket;
		import java.nio.file.Files;
		import java.nio.file.Paths;
		import java.nio.file.StandardOpenOption;
		import java.text.SimpleDateFormat;
		import java.util.ArrayList;
		import java.util.Calendar;
		import java.util.Date;
		import java.util.Scanner;
		import java.util.StringTokenizer;
		import javax.swing.BorderFactory;
		import javax.swing.JFrame;
		import javax.swing.JLabel;
		import javax.swing.JTextArea;
		import javax.swing.border.Border;
		import java.util.*;
		//References:- “Multi-Threaded Chat Application in Java | Set 1 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-1/. code by Rishabh Mahrsee

		//References:- “Multi-Threaded Chat Application in Java | Set 2 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-2/. code by Rishabh Mahrsee

		// Server class -2,3
		public class SocketServerApplication  extends JFrame
		{
			static ArrayList<ClientHandler> clientlist = new ArrayList<ClientHandler>();

			// counter for clients
			static int i = 0;		
			static JTextArea text1;
			static long last_time = 0;
			
			//This is the main method
			public SocketServerApplication(){									
				
				//Code for defining user interface of the frame
				this.setTitle("SocketServer");																								
				this.setSize(1366, 768);																								
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);																			
				getContentPane().setLayout(null);																						
				
				//Code for User Interface
				JLabel label = new JLabel("===========Main Server===========",JLabel.CENTER);
				label.setBounds(50, 35, 1250, 40);
				
				// This text field will display the clients messages on the server.
				text1 = new JTextArea();			
				text1.setBounds(50, 75, 1250, 575);
				
				/*Reference https://stackoverflow.com/questions/10274750/java-swing-setting-margins-on-textarea-with-line-border*/
				
				// Code to apply border to textarea field
				Border border = BorderFactory.createLineBorder(Color.BLACK);
				
				text1.setBorder(BorderFactory.createCompoundBorder(border, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));		
				text1.setEditable(false);
				text1.setAutoscrolls(true);
				//Code to append Gui component to frame
				add(text1);
				add(label);

				this.setVisible(true);						
				
			}

	//-2,3

			public static void main(String[] args) throws IOException 
			{
				
				
				new SocketServerApplication();
				
				// Server is listening
				
				ServerSocket SocketServer = new ServerSocket(8888);
				Socket sock;

				// This will accept the client requests as they come
				while (true) 
				{
					
					sock = SocketServer.accept();
					
					//It will display the address of the socket
					System.out.println("New client request is received. The Socket address is : " + sock);

					// DataInputStream will receive the messages from other Clients.
					// DataOutputStream will post the messages to other Clients.
					DataInputStream dis = new DataInputStream(sock.getInputStream());
					DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

					text1.append("Generating Client instance for "+ sock.getClass().getName() +" client"+i+"\n");
					
					// It will Create a new handler object for handling this request.
					ClientHandler link = new ClientHandler(sock,"\n client" + i, dis, dos,i);

					// It will initiate a new thread.
					Thread t = new Thread(link);

					System.out.println("Client successfully added to active list of Clients");

					// The Client will be successfully added to active clients list
					clientlist.add(link);

					
					t.start();
					i++;

				}
			}
		}
	/* References
	 * 1) https://stackoverflow.com/questions/15247752/gui-client-server-in-java
	 * 2)Multi-Threaded Chat Application in Java | Set 1 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-1/. code by Rishabh Mahrsee
	 * 3)Multi-Threaded Chat Application in Java | Set 2 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-2/. code by Rishabh Mahrsee
	 * 4)https://stackoverflow.com/questions/10274750/java-swing-setting-margins-on-textarea-with-line-border
	 * 5)Marshall, James. “HTTP Made Really Easy.” HTTP Made Really Easy, 10 Dec. 2012, www.jmarshall.com/easy/http/#http1.1s3.


	 * */
