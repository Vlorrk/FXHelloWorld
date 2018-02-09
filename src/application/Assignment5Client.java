package application;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
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

public class Assignment5Client extends Application {
	TextArea messages = new TextArea();
	String outputMessage;
	
	
	public void printTextField(String text) {
		
		messages.appendText("Client: "+outputMessage + "\n");
	}
	
	@Override
	public void start(Stage primaryStage) {
	
		primaryStage.setTitle("ChatClient");
	
		
		messages.setEditable(false);
		messages.setPrefHeight(550);
		
		Label  serverLabel = new Label("Server");
		Label clientLabel = new Label("Client");
		
		TextField textField = new TextField();
		
		textField.setOnAction(e->{
			
			
			outputMessage = textField.getText();
			printTextField(outputMessage);
			textField.setText("");
		});
	
		
		VBox root = new VBox( 5 ,serverLabel, messages,clientLabel, textField);
		 root.setPrefSize(600, 600);
		
		
		
		 
		 Scene scene = new Scene(root);
		 
		 
		
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 
		 try {
				Socket socket = new Socket("127.0.0.1",6000);

				DataOutputStream toServer = new DataOutputStream (socket.getOutputStream());
				DataInputStream fromServer = new DataInputStream(socket.getInputStream());
				
				//toServer.writeUTF(outputMessage);
				toServer.flush();
				
				outputMessage = fromServer.readUTF();
				messages.appendText(outputMessage +"\n");
				
				socket.close();
		 }
			  catch (UnknownHostException e) {
				
				  System.out.println("Unknown host exception");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO exception");
			}
			
			
			
			
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
