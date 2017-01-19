package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test query packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class QueryPacketTest {
	@Test
	public void produce() {
		QueryPacket query = new QueryPacket();
		query.flag = 3;
		query.message = "select * from dual".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(256);
		query.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		QueryPacket query2 = new QueryPacket();
		query2.read(bytes);
		assertTrue(new String(query2.message).equals("select * from dual"));
	}

}
