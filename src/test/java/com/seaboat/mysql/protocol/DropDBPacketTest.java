package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test drop db packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class DropDBPacketTest {
	@Test
	public void produce() {
		byte[] table = { 't', 'e', 's', 't' };
		DropDBPacket dropDB = new DropDBPacket();
		dropDB.packetId = 2;
		dropDB.schema = table;
		dropDB.flag = MysqlPacket.COM_DROP_DB;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		dropDB.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);
		byte[] test = { 5, 0, 0, 2, 6, 116, 101, 115, 116 };
		DropDBPacket dropDB2 = new DropDBPacket();
		dropDB2.read(test);
		System.out.println(new String(dropDB2.schema));
		assertTrue(new String(dropDB2.schema).equals("test"));
	}

}
