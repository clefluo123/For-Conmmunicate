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
	//�����׽��ֿͻ�������
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
			//ͨ���׽����ϵ���������ȡ��������ȡ���ļ�
			ClientReader = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
			//ͨ���׽����ϵ����������������ָ��ļ�·���ַ�����
			ClientWriter = new PrintWriter(ClientSocket.getOutputStream());
			ClientWriter.println(FileTrack);
			ClientWriter.flush();
			String line = null;
			StringBuffer filebuffer = new StringBuffer();
			//���ж�ȡ�ļ�����ʾ
			while((line=ClientReader.readLine())!=null) {
				filebuffer.append(line);
			}
			System.out.println(filebuffer.toString());
		} 
		catch (IOException e) {
			System.err.println(e);
		} 
	}
	public void ShutDownConnection() {  //�رտͻ���
		try {
			ClientSocket.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
