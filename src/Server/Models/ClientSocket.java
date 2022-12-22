package Server.Models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket {
	private Socket client;
	private BufferedReader in;
	private BufferedWriter out;
	private InputStream is;
	private OutputStream os;

	private String id;

	public ClientSocket(Socket client) {
		this.client = client;
		this.id = null;

		try {
			is = client.getInputStream();
			os = client.getOutputStream();
			in = new BufferedReader(new InputStreamReader(is));
			out = new BufferedWriter(new OutputStreamWriter(os));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readString() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void sendString(String inp) {
		try {
			out.write(inp + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] readAllBytes() {
		try {
			return is.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] readBytes() {
		var buffer = new byte[32 * 1024];
		try {
			int retSize = is.read(buffer);
			if (retSize != -1) {
				var realBuffer = new byte[retSize];
				System.arraycopy(buffer, 0, realBuffer, 0, retSize);
				return realBuffer;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendBytes(byte[] data) {
		try {
			os.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (client.isConnected()) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean isConnected() {
		return client.isConnected();
	}

	public int getPort() {
		return client.getPort();
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}
}
