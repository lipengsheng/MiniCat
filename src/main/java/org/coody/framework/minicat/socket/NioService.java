package org.coody.framework.minicat.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.coody.framework.minicat.builder.NioHttpBuilder;
import org.coody.framework.minicat.builder.iface.HttpBuilder;
import org.coody.framework.minicat.socket.iface.MiniCatService;
import org.coody.web.notes.Notes;

public class NioService implements MiniCatService {

	private Selector selector;

	public void openPort(Integer port, Integer timeOut) throws IOException {
		selector = Selector.open(); // 打开选择器
		ServerSocketChannel server = ServerSocketChannel.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		server.socket().setSoTimeout(timeOut);
	}

	public void doService() throws IOException {
		while (true) {
			selector.select();
			Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
			while (iter.hasNext()) {
				try {
					Notes.input.addAndGet(1);
					SelectionKey key = iter.next();
					iter.remove();
					process(key);
				} finally {
					Notes.output.addAndGet(1);
				}

			}
		}
	}

	private void process(SelectionKey key) {
		try {
			if (key.isAcceptable()) { // 接收请求
				acceptable(key);
				return;
			}
			if (key.isReadable()) { // 读信息
				readable(key);
				return;
			}
			if (key.isWritable()) { // 写事件
				writable(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				key.channel().close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void acceptable(SelectionKey key) throws IOException {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel channel = server.accept();
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_READ);

	}

	private void readable(SelectionKey key) throws ClosedChannelException {
		SocketChannel channel = (SocketChannel) key.channel();
		HttpBuilder builder = new NioHttpBuilder(channel);
		builder.builder();
		SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
		sKey.attach(builder);
	}

	private void writable(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		HttpBuilder builder = (HttpBuilder) key.attachment();
		try {
			builder.flushAndClose();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			channel.close();
		}
	}

}
