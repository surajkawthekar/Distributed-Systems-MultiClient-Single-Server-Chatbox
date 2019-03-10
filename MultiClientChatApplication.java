	
					// Name:Suraj Madhav Kawthekar	
					// Net id: 1001514614

		import java.awt.event.ActionEvent;
		import java.awt.Color;
		import javax.swing.BorderFactory;
		import java.net.Socket;
		import javax.swing.JButton;
		import javax.swing.JTextArea;
		import java.io.DataOutputStream;
		import java.net.UnknownHostException;
		import java.util.Scanner;
		import java.net.InetAddress;
		import javax.swing.border.Border;
		import java.io.DataInputStream;
		import javax.swing.JLabel;
		import javax.swing.JFrame;
		import java.awt.event.ActionListener;
		import java.io.IOException;

		//References:- “Multi-Threaded Chat Application in Java | Set 1 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-1/. code by Rishabh Mahrsee

		//References:- “Multi-Threaded Chat Application in Java | Set 2 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-2/. code by Rishabh Mahrsee

		public class MultiClientChatApplication extends JFrame implements ActionListener
		{
			final static int ServerPort = 8888;
			//Declarations for variables
			static Socket sock;
			static JTextArea WriteMessage;
			static JTextArea DisplayMessage;
			
			// DataInputStream will receive the messages from other Clients.
			// DataOutputStream will post the messages to other Clients.
			DataInputStream ClientIncoming;
			DataOutputStream ClientOutgoing;
			
			JButton button1;
			
			//This is the Main method
			public MultiClientChatApplication(){												
				
				//Code for User Interface
				this.setTitle("Client");																								
				this.setSize(1366, 768);																								
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);																			
				getContentPane().setLayout(null);							

				//Textbox to type your message
				JLabel label2 = new JLabel("===========Type your text Below===========",JLabel.CENTER);
				label2.setBounds(50, 505, 1250, 20);		
				
				WriteMessage = new JTextArea();																						
				WriteMessage.setBounds(50, 525, 1250, 110);
				add(WriteMessage);
				
				//Textbox to Display the incoming and outgoing messages
				
				JLabel label1 = new JLabel("===========Client Chat Application===========",JLabel.CENTER);
				label1.setBounds(50, 15, 1250, 40);
				
				DisplayMessage = new JTextArea();																						
				DisplayMessage.setBounds(50, 50, 1250, 450);
				DisplayMessage.setEditable(false);
				add(DisplayMessage);
				
				//Button to send the message
				button1 = new JButton("Send");		
				button1.setBounds(625, 650, 130, 25);
				button1.addActionListener(this);
				
		/*Reference https://stackoverflow.com/questions/10274750/java-swing-setting-margins-on-textarea-with-line-border*/
				
				//Code to add border to the textboxes
				Border bordertop = BorderFactory.createLineBorder(Color.BLACK);		
				DisplayMessage.setBorder(BorderFactory.createCompoundBorder(bordertop, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
				
				Border borderbottom = BorderFactory.createLineBorder(Color.BLACK);		
				WriteMessage.setBorder(BorderFactory.createCompoundBorder(borderbottom, 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
				
				//Code to append Gui component to frame and make it visible
				add(button1);
				add(label1);
				add(label2);
				this.setVisible(true);			

			}

			@Override
			// When the user will click the send message button Send message function will be called else it will 
			//give an exception.-2,3
			public void actionPerformed(ActionEvent ae) {																				
				if (ae.getSource().equals(button1)) {																					
					try {
						SendMessage();				
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			//This function will read the message from the clients and write the message on server-2,3
			public void SendMessage()  throws UnknownHostException, IOException {
				DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
				String word = WriteMessage.getText().trim();																					   // get the word to search entered by user
				dos.writeUTF(word);	
			        WriteMessage.setText("");
			}
		     //code to read and append the message- 2,3
			public static void main(String args[]) throws UnknownHostException, IOException 
			{
				new MultiClientChatApplication();

				InetAddress ip = InetAddress.getByName("localhost");
				sock = new Socket(ip, ServerPort);
				while(true) {
					DataInputStream dis = new DataInputStream(sock.getInputStream());
					String msg = dis.readUTF();
					DisplayMessage.append(msg);
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
