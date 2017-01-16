package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.Capabilities;
import com.seaboat.mysql.protocol.CharsetUtil;
import com.seaboat.mysql.protocol.HandshakePacket;
import com.seaboat.mysql.protocol.Versions;
import com.seaboat.mysql.protocol.util.HexUtil;
import com.seaboat.mysql.protocol.util.RandomUtil;
/**
 * 
 * <pre><b>test handshake packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class HandshakePacketTest {

	@Test
	public void produce() {
		byte[] rand1 = RandomUtil.randomBytes(8);
		byte[] rand2 = RandomUtil.randomBytes(12);
		byte[] seed = new byte[rand1.length + rand2.length];
		System.arraycopy(rand1, 0, seed, 0, rand1.length);
		System.arraycopy(rand2, 0, seed, rand1.length, rand2.length);
		HandshakePacket hs = new HandshakePacket();
		hs.packetId = 0;
		hs.protocolVersion = Versions.PROTOCOL_VERSION;
		hs.serverVersion = Versions.SERVER_VERSION;
		hs.threadId = 1000;
		hs.seed = rand1;
		hs.serverCapabilities = getServerCapabilities();
		hs.serverCharsetIndex = (byte) (CharsetUtil.getIndex("utf8") & 0xff);
		hs.serverStatus = 2;
		hs.restOfScrambleBuff = rand2;

		ByteBuffer buffer = ByteBuffer.allocate(256);
		hs.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		System.out.println(Integer.valueOf(result.substring(0, 2), 16));
		System.out.println(result.length() / 2 - 4);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);
		HandshakePacket hs2 = new HandshakePacket();
		hs2.read(bytes);
		assertTrue(hs2.threadId == 1000);
		System.out.println(hs2.threadId);
		assertTrue(hs2.serverVersion.length == Versions.SERVER_VERSION.length);

	}

	protected int getServerCapabilities() {
		int flag = 0;
		flag |= Capabilities.CLIENT_LONG_PASSWORD;
		flag |= Capabilities.CLIENT_FOUND_ROWS;
		flag |= Capabilities.CLIENT_LONG_FLAG;
		flag |= Capabilities.CLIENT_CONNECT_WITH_DB;
		flag |= Capabilities.CLIENT_ODBC;
		flag |= Capabilities.CLIENT_IGNORE_SPACE;
		flag |= Capabilities.CLIENT_PROTOCOL_41;
		flag |= Capabilities.CLIENT_INTERACTIVE;
		flag |= Capabilities.CLIENT_IGNORE_SIGPIPE;
		flag |= Capabilities.CLIENT_TRANSACTIONS;
		flag |= Capabilities.CLIENT_SECURE_CONNECTION;
		return flag;
	}

}
