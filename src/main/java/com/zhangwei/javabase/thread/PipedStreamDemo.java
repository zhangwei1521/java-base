package com.zhangwei.javabase.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class PipedStreamDemo {

	public static void main(String[] args) {
		//test01();
		test02();
	}

	private static void test01() {
		try {
			PipedInputStream pin = new PipedInputStream();
			PipedOutputStream pout = new PipedOutputStream(pin);
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
						String str = "hello! this is " + Thread.currentThread().getName();
						pout.write(str.getBytes());
                        Thread.sleep(500);
						/*
						 * if (true) { throw new RuntimeException("sorry! something was wrong"); }
						 */
						pout.write("\tgood morning!".getBytes());
						System.out.println("send message success!");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
                        try {
                            pout.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
				}
			}.start();

			System.out.println("before");
			while (true) {
				byte[] buf = new byte[1024];
				int len = pin.read(buf);
				if (len == -1) {
					System.out.println("finish");
					return;
				}
				String msg = new String(buf, 0, len, StandardCharsets.UTF_8);
				System.out.println("收到消息：: " + msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// System.out.println("finish");
		}
	}

    private static void test02() {
        try(PipedInputStream pin = new PipedInputStream();
            PipedOutputStream pout = new PipedOutputStream(pin);) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String str = "hello! this is " + Thread.currentThread().getName();
                        pout.write(str.getBytes());
                        pout.write("\tgood morning!".getBytes());
                        System.out.println("send message success!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            pout.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }.start();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("before");
            byte[] buf = new byte[1024];
            int len = pin.read(buf);
            if (len == -1) {
                System.out.println("finish");
                return;
            }
            String msg = new String(buf, 0, len, StandardCharsets.UTF_8);
            System.out.println("收到消息：: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
