package com.github.diegoocampo.reactive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TempSubscription implements Subscription {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private final Subscriber<? super TempInfo> subscriber;
	private final String town;

	public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
		this.subscriber = subscriber;
		this.town = town;
	}

	@Override
	public void request(long n) {
		executor.submit(() -> {
			System.out.printf("--- TempSubscription %s \tThread: %s. Requesting temp\n",this.hashCode(), Thread.currentThread().getName());
			for (long i = 0L; i < n; i++) {
				try {
					//calculates the temp.
					Thread.sleep(1000);
					//finished calculating temp
					System.out.printf("<--- TempSubscription\tThread: %s. Sending data temp\n",Thread.currentThread().getName());
					subscriber.onNext(TempInfo.fetch(town));
				} catch (Exception e) {
					subscriber.onError(e);
					break;
				}
			}
			System.out.printf("<--- TempSubscription\tThread: %s. Finished sending data\n",Thread.currentThread().getName());
			cancel();
		});
	}

	@Override
	public void cancel() {
		executor.shutdown();
		subscriber.onComplete();
	}
}
