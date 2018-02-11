package application;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



public class Assignment5Server extends Application {
	TextArea messages = new TextArea();
	String outputMessage;
	
public void printToTextArea(String text) {
		
		messages.appendText(text);
	}
	public void serverChat(String text) {
		
		messages.appendText("Server: "+text+ "\n");
	}
	
	@Override
	public void start(Stage primaryStage) {
	
		primaryStage.setTitle("ChatServer");
	
		
		messages.setEditable(false);
		messages.setPrefHeight(550);
		
		Label  serverLabel = new Label("Server");
		Label clientLabel = new Label("Client");
		
		TextField textField = new TextField();
		textField.setOnAction(e->{
			
			
			
			outputMessage = textField.getText();
			serverChat(outputMessage);
			textField.setText("");
		});
	
		
		VBox root = new VBox( 5 ,clientLabel, messages,serverLabel, textField);
		 root.setPrefSize(600, 600);
		
		
		
		 
		 Scene scene = new Scene(root);
		 
		 
		
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 
		 
		 
		 
			try {
				ServerSocket serverSocket = new ServerSocket(6000);
				
				printToTextArea("Waiting to connect...\n");
				
				Socket socket = serverSocket.accept();
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.flush();
				
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
				printToTextArea("Streams Connected!\n");
				
				try {
					outputMessage = (String) input.readObject();
					serverChat(outputMessage);
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				
				
				
				
				serverSocket.close();
				
			} catch (IOException e) {
				
			System.out.println("IO exception");
			}
			
		 
	 
		 
		 
	}
	public static void main( String[] args)	{
		
		launch(args);
		
	}
	
	
}