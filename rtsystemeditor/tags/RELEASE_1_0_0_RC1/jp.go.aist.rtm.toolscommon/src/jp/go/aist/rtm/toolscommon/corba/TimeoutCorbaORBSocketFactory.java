package jp.go.aist.rtm.toolscommon.corba;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.sun.corba.se.impl.transport.DefaultSocketFactoryImpl;
import com.sun.corba.se.spi.transport.ORBSocketFactory;

/**
 * 接続タイムアウトが設定可能なソケットファクトリ
 *
 */
public class TimeoutCorbaORBSocketFactory extends DefaultSocketFactoryImpl implements ORBSocketFactory {

	private static int connectionTimeout;

	@Override
	public Socket createSocket(String type, InetSocketAddress inetSocketAddress)
			throws IOException {
		Socket socket = new Socket();
		socket.connect(inetSocketAddress, connectionTimeout);
//		socket.setSoTimeout(connectionTimeout);	// こいつのせいで不安定なのかも
		// Disable Nagle's algorithm (i.e., always send immediately).
		socket.setTcpNoDelay(true);

		return socket;
	}

	public static void setConnectionTimeout(int defaultTimeout) {
		connectionTimeout = defaultTimeout;
	}

}
