package com.seaboat.mysql.protocol;

import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.seaboat.mysql.protocol.util.HexUtil;

/**
 * 
 * <pre><b>test resultset row packet.</b></pre>
 * @author 
 * <pre>seaboat</pre>
 * <pre><b>email: </b>849586227@qq.com</pre>
 * <pre><b>blog: </b>http://blog.csdn.net/wangyangzhizhou</pre>
 * @version 1.0
 */
public class ResultsetRowPacketTest {
	@Test
	public void produce() {
		ResultsetRowPacket resultsetRow = new ResultsetRowPacket(6);
		resultsetRow.packetId = 0;
		List<byte[]> values = new ArrayList<byte[]>();
		values.add("value1".getBytes());
		values.add("value2".getBytes());
		values.add(null);
		values.add("value4".getBytes());
		values.add("value5".getBytes());
		values.add("value6".getBytes());
		resultsetRow.columnValues = values;
		ByteBuffer buffer = ByteBuffer.allocate(256);
		resultsetRow.write(buffer);
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes, 0, bytes.length);
		String result = HexUtil.Bytes2HexString(bytes);
		System.out.println(result);
		assertTrue(Integer.valueOf(result.substring(0, 2), 16) == result
				.length() / 2 - 4);

		ResultsetRowPacket resultsetRow2 = new ResultsetRowPacket();
		resultsetRow2.read(bytes);
		assertTrue(resultsetRow.columnCount == 6);
		assertTrue(new String(resultsetRow.columnValues.get(0))
				.equals("value1"));
		assertTrue(resultsetRow.columnValues.get(2) == null);
		assertTrue(new String(resultsetRow.columnValues.get(5))
				.equals("value6"));
	}

}
