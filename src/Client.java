import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.plaf.synth.SynthSeparatorUI;

class InputThread extends Thread{
	
	ObjectInputStream inData;
	Model model;
	View view;
	Figure F;
	Controller controller;
	public InputThread(ObjectInputStream inData, Model model, View view, Controller controller){
		this.inData = inData;
		this.view = view;
		this.model = model;
		this.controller = controller;
		start();
		F = null;
	}
	public void run() {
		
		do{
			
			try {
				System.out.println("fungerar");
				F = (Figure) inData.readObject();
				System.out.println(F);
				System.out.println(F.getClass().toString());
				
				try{
					F=(Eraser) F;
					((Eraser) F).erase();
					System.out.println("forsoker eraseea");
				}catch(ClassCastException c){}

				model.addFigure(F);
				view.update(view.g);
				view.paint(view.g);
				model.drawFigures(view.g);
			}catch(IOException ioException){
				ioException.printStackTrace();
				closeInputStream();
				System.exit(0);
				
				
			} catch (ClassNotFoundException classNotFoundException) {
				System.exit(0);
			}
		}while(true);
		
		
		
	}
	public void closeInputStream(){
		try {
			inData.close();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}


public class Client {
	
	ObjectInputStream inData;
	ObjectOutputStream outData;
	Model model;
	View view;
	InputThread inputThread;
	Controller controller;

	
	public Client(InetAddress address, int port) throws IOException{
		
		view = new View();
		model = new Model(view);
		controller = new Controller(view, model, this);
		try {
			Socket connection = new Socket( address, port );
			outData = new ObjectOutputStream(connection.getOutputStream());
			outData.flush();
			inData = new ObjectInputStream(connection.getInputStream());
			new InputThread(inData, model, view, controller);
		} catch (IOException ioException) {
			ioException.printStackTrace();
			System.exit(0);
		}

		}
	
	public void sendObject(Object F) {
		try {
			outData.writeObject((Object) F);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client(InetAddress.getByName("127.0.0.1"),4048);
	}

}
