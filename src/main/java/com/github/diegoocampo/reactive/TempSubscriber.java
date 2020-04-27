package com.github.diegoocampo.reactive;

import java.util.concurrent.Flow.*;

public class TempSubscriber implements Subscriber<TempInfo> {

	private Subscription subrscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subrscription = subscription;
		System.out.printf("---> TempSubscriber\tThread: %s. Requesting 10 items\n",Thread.currentThread().getName());
		subscription.request(10);
	}

	@Override
	public void onNext(TempInfo item) {
		System.out.printf("TempSubscriber\tThread: %s. onNext item. %s\n",Thread.currentThread().getName(),item);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.printf("TempSubscriber\tThread: %s. Done!.\n",Thread.currentThread().getName());
	}
}
