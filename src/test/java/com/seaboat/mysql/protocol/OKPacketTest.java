package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.OKPacket;
import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * @author seaboat
 * @date 2016-09-25
 * @version 1.0
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * <p>test ok packet.</p>
 */
public class OKPacketTest {
	@Test
	public void produce() {
		OKPacket ok = new OKPacket();
		ok.packetId = 2;
		ok.affectedRows = 0;
		ok.insertId = 0;
		ok.serverStatus = 2;
		ok.warningCount = 0;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		ok.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		OKPacket ok2 = new OKPacket();
		ok2.read(bytes);
		//auth ok
		assertTrue(result.equals("0700000200000002000000"));
	}

}
