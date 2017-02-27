package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test process kill packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class ProcessKillPacketTest {
	@Test
	public void produce() {
		ProcessKillPacket pk = new ProcessKillPacket();
		pk.connectionId = 10;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		pk.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		ProcessKillPacket pk2 = new ProcessKillPacket();
		pk2.read(bytes);
		assertTrue(result.equals("050000000C0A000000"));
	}

}
