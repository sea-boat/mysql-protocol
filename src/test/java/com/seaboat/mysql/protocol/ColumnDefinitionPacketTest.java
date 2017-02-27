package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test column definition packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class ColumnDefinitionPacketTest {
	@Test
	public void produce() {

		ColumnDefinitionPacket columnDefinition = new ColumnDefinitionPacket();
		columnDefinition.packetId = 0;
		columnDefinition.schema = "testdb".getBytes();
		columnDefinition.table = "table1".getBytes();
		columnDefinition.orgTable = "orgTable1".getBytes();
		columnDefinition.name = "column1".getBytes();
		columnDefinition.orgName = "orgName1".getBytes();
		columnDefinition.charsetSet = CharsetUtil.getIndex("utf8");
		columnDefinition.length = 10;
		columnDefinition.type = 3;
		columnDefinition.flags = 1;
		columnDefinition.decimals = 3;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		columnDefinition.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		ColumnDefinitionPacket columnDefinition2 = new ColumnDefinitionPacket();
		columnDefinition2.read(bytes);
		assertTrue(new String(columnDefinition2.schema).equals("testdb"));
		assertTrue(new String(columnDefinition2.table).equals("table1"));
		assertTrue(new String(columnDefinition2.orgTable).equals("orgTable1"));
		assertTrue(new String(columnDefinition2.name).equals("column1"));
		assertTrue(new String(columnDefinition2.orgName).equals("orgName1"));
	}

}
