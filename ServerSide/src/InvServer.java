	import java.net.*;
	import java.io.*;
	import java.util.*;

	public class InvServer extends Thread {
		
		private ServerSocket s;
		
		public InvServer(int port) throws IOException {
		 
			s = new ServerSocket(port);
			s.setSoTimeout(1000000);
		
		}
		
		public void run() {
			
			System.out.println("Welcome to the Inventory Server");
			Random rand= new Random(111);
		
			while(true) {
				try {
			System.out.println("Waiting for the Client:" + s.getLocalPort());
			Socket serv = s.accept();
			System.out.println("Just connected to" + serv.getRemoteSocketAddress());
			DataInputStream in = new DataInputStream(serv.getInputStream());
			
			String itemcode = in.readUTF();
			if (itemcode.equals("X")) {
				System.out.println("Server is terminating upon request");
				System.out.println("Goodbye");
				serv.close();
				s.close();
				break;
			}
			else {
					DataOutputStream out = new DataOutputStream(serv.getOutputStream());
				
					
					int rNumb = rand.nextInt(51);
			        System.out.println("Inventory for"  + itemcode + "is" + rNumb);
					out.writeUTF(String.valueOf(rNumb));
				}
		
				} catch (IOException e) {
					System.out.println("Connection error: " + e.getMessage());
				break;
				}
				catch (Exception ex) {
					break;
				}
			}
	
			}
		
		
			public static void main(String[] args) {
				try {
					InvServer is = new InvServer(6067);
					Thread t = is;
					t.start();
				}
				catch (Exception e) {}
				
			}
		
	
	}
		
