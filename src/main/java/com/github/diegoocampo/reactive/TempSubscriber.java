package com.github.diegoocampo.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;

public class TempSubscriber implements Subscriber<TempInfo> {

	private Subscription subrscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subrscription = subscription;
		subscription.request(1);

	}

	@Override
	public void onNext(TempInfo item) {
		System.out.println(item);
		subrscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Done!");
	}
}
