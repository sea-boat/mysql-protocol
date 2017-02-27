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
public class EOFPacketTest {
	@Test
	public void produce() {
		EOFPacket eof = new EOFPacket();
		eof.packetId = 1;
		eof.warningCount = 0;
		eof.status = 2;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		eof.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		EOFPacket eof2 = new EOFPacket();
		eof2.read(bytes);
		assertTrue(result.equals("05000001FE00000200"));
	}

}
