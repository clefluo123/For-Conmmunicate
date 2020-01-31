package ClientPac;
import java.io.*;
import java.net.*;

		
public class SocketClient {
	protected String IP;
	protected int Port;
	protected BufferedReader ClientReader = null;
	protected PrintWriter ClientWriter = null;
	protected Socket ClientSocket = null;
	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		client.SetUpConnection("127.0.0.1",3000);
		client.getFile("C:\\Users\\DELL\\Desktop\\Sample.txt");
		client.ShutDownConnection();
	}
	//建立套接字客户机连接
	public void SetUpConnection(String IP,int Port) {
		this.IP = IP;
		this.Port = Port;
		
		try {
			ClientSocket = new Socket(IP, Port);
		}
		catch (UnknownHostException e) {
			System.err.println(e);
		}
		catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
	public void getFile(String FileTrack) {
		try {
			//通过套接字上的输入流读取从主机获取的文件
			ClientReader = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
			//通过套接字上的输出流向主机发送指令（文件路径字符串）
			ClientWriter = new PrintWriter(ClientSocket.getOutputStream());
			ClientWriter.println(FileTrack);
			ClientWriter.flush();
			String line = null;
			StringBuffer filebuffer = new StringBuffer();
			//逐行读取文件并显示
			while((line=ClientReader.readLine())!=null) {
				filebuffer.append(line);
			}
			System.out.println(filebuffer.toString());
		} 
		catch (IOException e) {
			System.err.println(e);
		} 
	}
	public void ShutDownConnection() {  //关闭客户机
		try {
			ClientSocket.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
