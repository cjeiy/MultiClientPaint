
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;


public class Server {
	
	ServerSocket socket;
	
	ObjectOutputStream dataOutStream;
	private Hashtable<Socket, ObjectOutputStream> outputs;
	
	public Server(int port) throws IOException{
		outputs = new Hashtable<Socket, ObjectOutputStream>();
		listenTo(port);
	}
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt("4048");
		new Server(port);
	}
	private void listenTo(int port) throws IOException{
		
		//skapa förutsättning för anslutning
		socket = new ServerSocket(port);
		//Terminalbesked
		System.out.println("The server is listening on" + socket);
		
		while(true){
			Socket connection = socket.accept();
			dataOutStream = new ObjectOutputStream(connection.getOutputStream());
			//dataOutStream.flush();
			outputs.put(connection, dataOutStream);
			new ServThread(this, connection);
		
		}
	}
		
		Enumeration<ObjectOutputStream> getOutStreams(){
			return outputs.elements();
			
		}
		
		void sendToClients(Figure F){

			synchronized(outputs){
			for(Enumeration<ObjectOutputStream> enu = getOutStreams(); enu.hasMoreElements(); )
					{
					ObjectOutputStream dataOut = (ObjectOutputStream)enu.nextElement();
					try {
						dataOut.writeObject(F);
					} catch (IOException ioException) {
						System.out.println("Skickar inte figur till klienter!!");
					}
				}}
			
	}
		
		void removeConnection(Socket socket){
			
			
			synchronized(outputs){
				System.out.println("Removing" + socket);
				outputs.remove(socket);
				try {
					socket.close();
					
				} catch (IOException ioException) {
					ioException.printStackTrace();
					System.out.println("Kunde inte stanga anslutning till" + socket);
				}
			
		}
		}
}
