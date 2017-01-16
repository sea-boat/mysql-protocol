package com.seaboat.mysql.protocol;

import java.nio.ByteBuffer;

import com.seaboat.mysql.protocol.util.BufferUtil;
/**
 * 
 * <pre><b>mysql init db packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 * @see http://dev.mysql.com/doc/internals/en/com-init-db.html
 */
public class InitDBPacket extends MySQLPacket {
	public byte[] schema;

	public void read(byte[] data) {
		MySQLMessage mm = new MySQLMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		mm.read(); // skip COM_INIT_DB
		this.schema = mm.readBytes();
	}

	public void write(ByteBuffer buffer) {
		BufferUtil.writeUB3(buffer, calcPacketSize());
		buffer.put(packetId);
        buffer.put(COM_INIT_DB);
        buffer.put(schema);
	}

	@Override
	public int calcPacketSize() {
		int i = 1;
		i += schema.length;
		return i;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL Init DB Packet";
	}

}
