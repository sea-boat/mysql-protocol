package com.seaboat.mysql.parser;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.parser.util.HexUtil;
import com.seaboat.mysql.protocol.ErrorPacket;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>test auth packet.</p>
 */
public class ErrorPacketTest {
	@Test
	public void produce() {
		ErrorPacket err = new ErrorPacket();
		err.packetId = 1;
		err.errno = 32322;
		err.message = "sorry".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(256);
		err.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		ErrorPacket err2 = new ErrorPacket();
		err2.read(bytes);
		assertTrue(err2.errno == 32322);
		assertTrue(err2.message.length == "sorry".getBytes().length);
	}

}
