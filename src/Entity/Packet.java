package Entity;

public class Packet {
	private String header;
	private String data;
	private String author;
	private String receiver;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Packet(String header, String data, String author) {
		this.header = header;
		this.data = data;
		this.author = author;
	}

	public Packet(String inp) {
		header = inp.substring(0, inp.indexOf("|"));
		data = inp.substring(inp.indexOf("|") + 1, inp.indexOf("|", inp.indexOf("|") + 1));
		author = inp.substring(inp.indexOf("|", inp.indexOf("|") + 1) + 1, inp.lastIndexOf("|"));
		receiver = inp.substring(inp.lastIndexOf("|") + 1);
	}

	@Override
	public String toString() {
		return header + "|" + data + "|" + author + "|" + receiver;
	}
}
