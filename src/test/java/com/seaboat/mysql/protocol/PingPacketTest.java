package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test quit packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class PingPacketTest {
	@Test
	public void produce() {
		PingPacket ping = new PingPacket();
		ping.payload = 14;
		ping.packetId = 0;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		ping.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		PingPacket ping2 = new PingPacket();
		ping2.read(bytes);
		assertTrue(result.equals("010000000E"));
	}

}
