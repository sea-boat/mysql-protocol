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
 * <p>AuthPacket means mysql initial handshake packet .</p>
 */
public class HandshakePacket extends MySQLPacket {
	private static final byte[] FILLER_13 = new byte[] { 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0 };

	public byte protocolVersion;
	public byte[] serverVersion;
	public long threadId;
	public byte[] seed;
	public int serverCapabilities;
	public byte serverCharsetIndex;
	public int serverStatus;
	public byte[] restOfScrambleBuff;

	public void read(byte[] data) {
		MySQLMessage mm = new MySQLMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		protocolVersion = mm.read();
		serverVersion = mm.readBytesWithNull();
		threadId = mm.readUB4();
		seed = mm.readBytesWithNull();
		serverCapabilities = mm.readUB2();
		serverCharsetIndex = mm.read();
		serverStatus = mm.readUB2();
		mm.move(13);
		restOfScrambleBuff = mm.readBytesWithNull();
	}

	@Override
	public int calcPacketSize() {
		int size = 1;
		size += serverVersion.length;// n
		size += 5;// 1+4
		size += seed.length;// 8
		size += 19;// 1+2+1+2+13
		size += restOfScrambleBuff.length;// 12
		size += 1;// 1
		return size;
	}
	
	public void write(ByteBuffer buffer) {
        BufferUtil.writeUB3(buffer, calcPacketSize());
        buffer.put(packetId);
        buffer.put(protocolVersion);
        BufferUtil.writeWithNull(buffer, serverVersion);
        BufferUtil.writeUB4(buffer, threadId);
        BufferUtil.writeWithNull(buffer, seed);
        BufferUtil.writeUB2(buffer, serverCapabilities);
        buffer.put(serverCharsetIndex);
        BufferUtil.writeUB2(buffer, serverStatus);
        buffer.put(FILLER_13);
        BufferUtil.writeWithNull(buffer, restOfScrambleBuff);
    }

	@Override
	protected String getPacketInfo() {
		return "MySQL Handshake Packet";
	}

}