package transportXML;

import java.nio.ByteBuffer;

public class TestByteBufferPut {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(100);
		//buffer.flip();
		//buffer.limit(50);
		buffer.put("okfdgsdfgsfdgsfdgsfgfgfdgsfgfg".getBytes());
		byte[] array = new byte[1024];
		buffer.flip();
		buffer.get(array, 0, buffer.remaining());
		String tempString = new String(array);
		System.out.println(tempString);
		System.out.println(buffer.position());
		System.out.println(buffer.remaining());
		System.out.println(buffer.limit());
		//xmlStringBuffer.append(tempString);
	}
}
