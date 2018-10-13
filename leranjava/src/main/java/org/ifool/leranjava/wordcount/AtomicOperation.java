package org.ifool.leranjava.wordcount;

public class AtomicOperation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		final long x = 5;
		final long y = 1234567890123456789L;
		final AAA a = new AAA();
		a.val = 0;
		Thread[] threads=new Thread[1000];
		for(int i = 0; i < 1000; i++) {
			final int index = i;
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					if(index % 2 == 0) {
						while(true) {
							a.val = x;
						}
					} else {
						while(true) {
							a.val = y;
						}
					}
				}
			});
			threads[i].start();
		}
		while(true) {
			System.out.println(a.val);
			Thread.sleep(500);
		}
	}

}
class AAA {
	public long val;
}