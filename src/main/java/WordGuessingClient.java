import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class WordGuessingClient extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	int port = 5555;
	GameObject gameData;
	
	private Consumer<Serializable> callback;
	private Consumer<Serializable> addGuess;
	private Consumer<Serializable> addCategory;
	private Consumer<Serializable> addAttempt;
	private Consumer<Serializable> deleteCategoryOption;
	private Consumer<Serializable> restartGame;
	
	WordGuessingClient(Consumer<Serializable> call, Consumer<Serializable> guess, Consumer<Serializable> category, Consumer<Serializable> attempt, Consumer<Serializable> delete, Consumer<Serializable> restart, int port){
		this.port = port;
		callback = call;
		addGuess = guess;
		addCategory = category;
		addAttempt = attempt;
		deleteCategoryOption = delete;
		restartGame = restart;
		gameData = new GameObject();
	}
	
	public void run() {
		
		try {
		socketClient= new Socket("127.0.0.1", port);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
	    System.out.println("Connected to word guessing server!");
		}
		catch(Exception e) {
			System.out.println("Could not establish connection to the server.");
		}
		
		while(true) {
			 
			try {
				Object receivedObject = in.readUnshared();
				if (receivedObject instanceof GameObject) {
					gameData.updateState((GameObject) receivedObject);
					gameData.print();
					
					if (gameData.roundOver.equals("won")) {
						restartGame.accept("won");
					} else if (gameData.roundOver.equals("lost")) {
						restartGame.accept("lost");
					}
					
					callback.accept(gameData.getCurrString());
					addGuess.accept(gameData.getCurrGuesses());
					if (gameData.getNewcategory()) {
						String m = "Failed Attempts: " + gameData.getAttempts();
						addAttempt.accept(m);
						String n = "Categories Completed - ";
						for (int i = 0; i < gameData.getCategoriesCompleted().size(); i++) {
							n = n.concat(gameData.categoriesCompleted.get(i) + " - ");
							deleteCategoryOption.accept(gameData.categoriesCompleted.get(i));
						}
						addCategory.accept(n);
					}

				} else {
					System.out.println("Invalid input from the server");
				}
			} catch(Exception e) {}
		}
	
    }
	
	public void send(GameObject wg) {
		try {
//			out.reset();
			out.writeUnshared(wg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
