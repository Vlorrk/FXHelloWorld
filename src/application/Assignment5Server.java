package application;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
@SuppressWarnings("unused")

public class Assignment5Server extends Application {
	TextArea messages = new TextArea();
	String outputMessage;
	
public void printToTextArea(String text) {
		
		messages.appendText(text);
	}
	public void serverChat(String text) {
		
		messages.appendText("Server: "+outputMessage + "\n");
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
				
				printToTextArea("Connected to " + socket.getPort());
				DataOutputStream outputToClient = new DataOutputStream (socket.getOutputStream());
				outputToClient.flush();
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				
				String msg = inputFromClient.readUTF();
				
				outputToClient.writeUTF(msg);
				messages.appendText(msg);
				
				
				
				
				serverSocket.close();
				
			} catch (IOException e) {
				
			System.out.println("IO exception");
			}
			
		 
	 
		 
		 
	}
	public static void main( String[] args)	{
		
		launch(args);
		
	}
	
	
}