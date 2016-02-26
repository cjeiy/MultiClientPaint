import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;






public class ServThread extends Thread{


	private Server server;
	private Socket socket;

	
	public ServThread(Server server, Socket socket){
		
		this.server = server;
		this.socket = socket;
		
		start();
	}
	
	public void run(){
		
		try {
			ObjectInputStream dataInStream = new ObjectInputStream(socket.getInputStream());
			Figure F = null;
		 
		
		while(true){
			
			F = (Figure) dataInStream.readObject();
			
			
			//logga
			System.out.println("sending a figure");
			
			server.sendToClients(F);
		}
			
			} catch (ClassNotFoundException classNotFoundException) {
				
			} catch (EOFException eofException) {
				
			} catch(IOException ioException){
				ioException.printStackTrace();
			} finally{
				//anslutningen stängs av någon anledning, servern tar hand om det
				server.removeConnection(socket);
			}
			
			
			
		}
		
		
	}
	

