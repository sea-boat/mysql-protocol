package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test create db packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class CreateDBPacketTest {
	@Test
	public void produce() {
		byte[] table = { 't', 'e', 's', 't' };
		CreateDBPacket createDB = new CreateDBPacket();
		createDB.packetId = 2;
		createDB.schema = table;
		createDB.flag = MySQLPacket.COM_CREATE_DB;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		createDB.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);
		byte[] test = { 5, 0, 0, 2, 5, 116, 101, 115, 116 };
		CreateDBPacket createDB2 = new CreateDBPacket();
		createDB2.read(test);
		System.out.println(new String(createDB2.schema));
		assertTrue(new String(createDB2.schema).equals("test"));
	}

}
