package pdm.pkg.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pdm.pkg.project1.SingleTouchEventView;
public class GameActivity extends Activity{
	
	//Variabili******************************************************************************
	ServerSocket providerSocket;
	Socket requestSocket = null;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message,secret;
	
	   private File sd = Environment.getExternalStorageDirectory();
       private File f = new File(sd, "nome_file.txt");
       private int i=0;
       private Activity activity = null;
       private ServerSocket server;
       private Socket connessione;
       private BufferedReader dalClient;
       private PrintStream alClient;
       private String name;
       private BufferedReader dalServer;
       private PrintStream alServer;
       
	//Funzioni*******************************************************************************
       public void conversazione() {
           String messaggio = "";
           BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
           try {
                   while(!messaggio.equals("/logout")) {
                           messaggio = dalServer.readLine();
                           System.out.println(messaggio);
                           if(!messaggio.equals("/logout")) {
                                   messaggio = t.readLine();
                                   alServer.println(name+" scrive: "+messaggio);
                           }
                   } // while
                   connessione.close();
           }
           catch(IOException e) {
                   System.out.println("Conversazione interrotta");
           }
   } // conversazione()
       
       
       
       public GameActivity(String name) {
           this.name = name;
           BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
           try {
                    
                   System.out.println("Inserire indirizzo server: ");
                   //String indirizzo = t.readLine();
                   connessione = new Socket(getIntent().getExtras().getString("IPPLAYER2"), 9999);
                   dalServer = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                   alServer = new PrintStream(connessione.getOutputStream());
           }
       
           catch(IOException e) {
               System.out.println(e);
       }
}
       
       void sendMessage(String msg)
	
		{
			try{
				out.writeObject(msg);
				out.flush();
				Log.d("MESSAGE SEND",msg);
				
			}
			catch(IOException ioException){
				Log.d("ERRORE","sendMessage");
				ioException.printStackTrace();
			}
		}
		
	
		
		
		public void runServer(){
		/*	try{
			
		    connection = providerSocket.accept();
			providerSocket.setSoTimeout(10000);
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			message = (String)in.readObject();
			
			}catch(IOException e){
				Log.d("ERRORE connessione","SERVER");
				closeSC();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				Log.d("ERRORE message","SERVER");
				closeSC();
				e.printStackTrace();
			}*/
			
			byte[] buf = new byte[17];
            String st = null;
                               
        try {
                InetAddress serverAddr = InetAddress.getByName(getIntent().getExtras().getString("IPPLAYER1"));
                st = serverAddr.toString();
                server = new ServerSocket(9999,5,serverAddr);
                write(server.getInetAddress().toString());
                        //System.out.println("Server attivo");
                        connessione = server.accept();
                        dalClient = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                        alClient = new PrintStream(connessione.getOutputStream());
                        message = "";
                        BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
                        alClient.println("Simple Hack Chat - Sei connesso al server! Digita /logout");
        while ((!message.equals("/logout")) ) {
        try {
                message = dalClient.readLine();
                        write(message);
                        if(!message.equals("/logout")) {
                                message = t.readLine();
                                alClient.println(name+" scrive: "+message);
                        }

          } catch (IOException e) {
            i++;    
            write("IO!" + i);  
          }
        }
        } catch (Exception e) {
                 write(e.toString() + "-" + st);};
      }
     			

        public void write (String Data){
        try{
            TextView tv = new TextView(this.activity);
            tv.setText(tv.getText()+ " - "+Data);
            setContentView(tv);
                               }
            catch (Exception e) {      
            e.printStackTrace();
            }
        }
		
		public void closeSC(){
			try{
			in.close();
			out.close();
			providerSocket.close();
			}catch(IOException e){
				Log.d("ERRORE chiusura", "SERVER/CLIENT");
			}		
		}
		
    //OnCreate******************************************************************************
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        //************************ SERVER *************************************************
        if(getIntent().getExtras().getBoolean("SERVER")==true){
        	//Stringa casuale
        	secret="pippo";
        	//activity per disegnare
        	setContentView(new SingleTouchEventView(this, null));
        	try{
        		//providerSocket = new ServerSocket(8080);
        		while(true){
        		runServer();
					//caso di vittoria
					if(message==secret){
						Toast.makeText(getApplicationContext(), "INDOVINATO" , Toast.LENGTH_LONG).show();
						sendMessage("INDOVINATO");
						Log.d("INDOVINATO",secret);
						closeSC();
						Intent intent = new Intent(GameActivity.this,Project1Activity.class);
						startActivity(intent);
					}else{
						//qualsiasi altro caso
						Toast.makeText(getApplicationContext(), "SBAGLIATO" , Toast.LENGTH_LONG).show();
						Log.d("SBAGLIATO",message);
						sendMessage("SBAGLIATO");
					}
							
				}
        	}finally{
    			closeSC();
				Intent intent = new Intent(GameActivity.this,Project1Activity.class);
				startActivity(intent);
    		}
        	
        }
        
        //******************************* CLIENT ******************************************
        if(getIntent().getExtras().getBoolean("CLIENT")==true){
        	 setContentView(R.layout.game_activity);
        	 final EditText edt = (EditText) findViewById(R.id.editText1);
             //ImageView im = (ImageView) findViewById(R.id.imageView1);
             Button btn = (Button) findViewById(R.id.button1);
             GameActivity c = new GameActivity("nome");
             c.conversazione();
             
             try {/*
				requestSocket = new Socket(getIntent().getExtras().getString("IPPLAYER2"), 8080);
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
				*/
            	
				btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						message = edt.getText().toString();
						sendMessage(message);
					}
				});
				
				try{
					message = (String)in.readObject();
					if(message=="INDOVINATO"){
						Toast.makeText(getApplicationContext(), "INDOVINATO" , Toast.LENGTH_LONG).show();
						in.close();
						out.close();
						providerSocket.close();
						Intent intent = new Intent(GameActivity.this,Project1Activity.class);
						startActivity(intent);
					}else{
					
						Toast.makeText(getApplicationContext(), "SBAGLIATO" , Toast.LENGTH_LONG).show();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					Log.d("ERRORE message","CLIENT");
					e.printStackTrace();
				}
				finally{
					in.close();
					out.close();
				    providerSocket.close();
				    Intent intent = new Intent(GameActivity.this,Project1Activity.class);
					startActivity(intent);
				}
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				Log.d("ERRORE socket","CLIENT");
				closeSC();
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("ERRORE socket","CLIENT");
				closeSC();
				e.printStackTrace();
			}
        }
      
	}
	
}
