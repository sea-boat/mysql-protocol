# mysql-protocol
a lib for mysql protocol operation

# how to use
i want an OK packet, just simple as below ,bytes is what you need.
```
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
```