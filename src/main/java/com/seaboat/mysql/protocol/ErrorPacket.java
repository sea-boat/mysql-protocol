package com.seaboat.mysql.protocol;

import java.nio.ByteBuffer;

import com.seaboat.mysql.protocol.util.BufferUtil;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>mysql error packet.</p>
 */
public class ErrorPacket extends MySQLPacket {
	public static final byte header = (byte) 0xff;
	private static final byte SQLSTATE_MARKER = (byte) '#';
	private static final byte[] DEFAULT_SQLSTATE = "HY000".getBytes();

	public int errno;
	public byte mark = SQLSTATE_MARKER;
	public byte[] sqlState = DEFAULT_SQLSTATE;
	public byte[] message;

	public void read(byte[] data) {
		MySQLMessage mm = new MySQLMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		mm.read();
		errno = mm.readUB2();
		if (mm.hasRemaining() && (mm.read(mm.position()) == SQLSTATE_MARKER)) {
			mm.read();
			sqlState = mm.readBytes(5);
		}
		message = mm.readBytes();
	}

	public void write(ByteBuffer buffer) {
		int size = calcPacketSize();
		BufferUtil.writeUB3(buffer, size);
		buffer.put(packetId);
		buffer.put(header);
		BufferUtil.writeUB2(buffer, errno);
		buffer.put(mark);
		buffer.put(sqlState);
		buffer.put(message);
	}

	@Override
	public int calcPacketSize() {
		int size = 9;// 1 + 2 + 1 + 5
		if (message != null) {
			size += message.length;
		}
		return size;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Error Packet";
	}

}
