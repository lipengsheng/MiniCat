package org.coody.framework.minicat.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.coody.framework.minicat.builder.BioHttpBuilder;
import org.coody.framework.minicat.builder.iface.HttpBuilder;
import org.coody.framework.minicat.socket.iface.MiniCatService;
import org.coody.framework.minicat.threadpool.MiniCatThreadPool;
import org.coody.web.notes.Notes;

public class BioService implements MiniCatService {

	private ServerSocket server;

	public void openPort(Integer port, Integer timeOut) throws IOException {
		server = new ServerSocket(port);
		server.setSoTimeout(timeOut);
	}

	public void doService() throws IOException {
		while (true) {
			try {
				Notes.input.addAndGet(1);
				Socket socket = server.accept();
				doSocket(socket);
			} finally {
				Notes.output.addAndGet(1);
			}
		}
	}

	private void doSocket(final Socket socket) {
		MiniCatThreadPool.HTTP_POOL.execute(new Runnable() {
			public void run() {
				try {
					HttpBuilder builder = new BioHttpBuilder(socket);
					builder.builder();
					builder.flushAndClose();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}