		// Name:Suraj Madhav Kawthekar	
		// Net id: 1001514614

		import java.io.DataInputStream;
		import java.io.DataOutputStream;
		import java.io.File;
		import java.io.IOException;
		import java.net.Socket;
		import java.nio.file.Files;
		import java.nio.file.Paths;
		import java.nio.file.StandardOpenOption;
		import java.util.Date;
		import java.util.Scanner;
		import java.util.StringTokenizer;

		//References:- “Multi-Threaded Chat Application in Java | Set 1 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-1/. code by Rishabh Mahrsee

		//References:- “Multi-Threaded Chat Application in Java | Set 2 (Client Side Programming).” GeeksforGeeks, 17 June 2017, www.geeksforgeeks.org/multi-threaded-chat-application-set-2/. code by Rishabh Mahrsee

		//ClientHandler class
		class ClientHandler implements Runnable 
		{
			Scanner scan = new Scanner(System.in);
			private String name;
			final DataInputStream dis;
			final DataOutputStream dos;
			Socket sock;
			int i;
			long final_time;
			boolean isloggedin;

			// This is the constructor
			public ClientHandler(Socket sock, String name,
					DataInputStream dis, DataOutputStream dos,int i) {
				this.dis = dis;
				this.dos = dos;
				this.name = name;
				this.sock = sock;
				this.i=i;
				this.final_time = final_time;
				this.isloggedin=true;
			}

			@Override
			public void run() {

				String received;
				long timestamp_fl = 0;
				try
				{
					
					File filef1 = new File("D:\\softwares\\eclipse-jee-kepler-SR2-win32-x86_64\\eclipse\\workspace\\Chatbox\\log.txt");
					filef1.createNewFile();
				
				/* Reference http://www.jmarshall.com/easy/http/*/
				while (true) 
				{
						// The file is received. Displayed in HTTP Format. It is the POST method.-5
						received = dis.readUTF();
						String User_post="POST /path/file.html HTTP/1.1";
						String Client_Name="Client "+i;
						String agent="HTTPTool/1.1 \\n";
						System.out.println(received);
						SocketServerApplication.text1.append("\n"+User_post+"\n"+"Client_Name:"+Client_Name+"\n"+" User-Agent:"+agent+"\n" + received+"\n"); 
						if(received.equals("logout")){
							
							SocketServerApplication.text1.append("Client"+ " " + i + " " + "has succesfully logged out from the Application");
							for(ClientHandler it:SocketServerApplication.clientlist){
								it.dos.writeUTF(this.name+" has logged out");
							}
							this.isloggedin=false;
							this.sock.close();
							break;
						}
						// it encodes the text in http format.This is the GET method.-5
						Date d= new Date();
						String date=d.toString();
						String http="HTTP/1.1 200 OK \r\n";
						String type="text/html\r\n";
						String Server_host="http://localhost:8888";
						  
						// break the string into message and recipient part-2,3
						StringTokenizer st = new StringTokenizer(received, "#");
						
						/*The reference for this timer method is taken from my android project which I had done in
						  Fall 2017. The logic for this code resembles the logic for that code.
						  for the first message the duration will be 00:00. for the next message it will 
						  be the difference between the second message and the first message and so on.*/
						for (ClientHandler it : SocketServerApplication.clientlist) {
							long present_time=System.currentTimeMillis();
				        	if(SocketServerApplication.last_time == 0)
				        	{
				        		SocketServerApplication.last_time = present_time;
				        	}
				        	
				        	timestamp_fl = present_time - SocketServerApplication.last_time;
				  
				        	
				        	if(this.name.equals(it.name))
					        {
				        		SocketServerApplication.last_time = present_time;
				        	}
				        
				        	int seconds = (int) (timestamp_fl/1000);
				        	int min = seconds/60;
				        	seconds = seconds - (min * 60);
				       
				        String interval = String.format("%02d", min);
				        interval = interval + ":";
				        String sec = String.format("%02d", seconds);
				        interval = interval + sec;
		
					        int length=received.length();
							it.dos.writeUTF("\n"+ http+ 
									"Date:"+date+"\n"+ 
									"Content-Type:"+type+ 
									"Content-Length:"+length+this.name+
									" : "+received+"\t"+ interval);
							
						}
						//Reference: http://www.programfaqs.com/faq/how-to-append-text-to-an-existing-file-in-java/ 
						String logfile="Client:" + i + " " + received+"\n";				
						Files.write(Paths.get("D:\\softwares\\eclipse-jee-kepler-SR2-win32-x86_64\\eclipse\\workspace\\Chatbox\\log.txt"), logfile.getBytes(), StandardOpenOption.APPEND);
						
						
				}
				
				}catch (IOException e) {

					e.printStackTrace();
				}

				try
				{
				
					this.dis.close();
					this.dos.close();
				}catch(IOException e){
					e.printStackTrace();
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

