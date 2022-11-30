package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP_Client {
    private Socket client;
    private InputStream is;
    private OutputStream os;
    private BufferedReader inData;
    private BufferedWriter outData;
    public static final int BufferSize = 32 * 1024;

    public boolean ConnectToServer(String ip, int port) {
        try {
            this.client = new Socket(ip, port);
            is = client.getInputStream();
            os = client.getOutputStream();
            inData = new BufferedReader(new InputStreamReader(is));
            outData = new BufferedWriter(new OutputStreamWriter(os));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public byte[] readBytes() {
        var buffer = new byte[BufferSize];
        try {
            int realLength = is.read(buffer);
            if (realLength != -1) {
                var realBuffer = new byte[realLength];
                System.arraycopy(buffer, 0, realBuffer, 0, realLength);
                return realBuffer;
            }
        } catch (IOException e) {
        }
        return null;
    }

    public byte[] readAllBytes() {
        try {
            return is.readAllBytes();
        } catch (IOException e) {
        }
        return null;
    }

    public void sendBytes(byte[] data) {
        try {
            os.write(data);
        } catch (IOException e) {
        }
    }
	 void disconnect() {
		// TODO Auto-generated method stub
		
	}

    public String readString() {
        try {
            return inData.readLine();
        } catch (IOException e) {
        }
        return null;
    }

    public void sendString(String inp) {
        try {
            outData.write(inp + "\n");
            outData.flush();
        } catch (IOException e) {
        }
    }

    public void DisconnectToServer() {
        try {
            inData.close();
            outData.close();
            client.close();
        } catch (IOException e) {
        }
    }
}
