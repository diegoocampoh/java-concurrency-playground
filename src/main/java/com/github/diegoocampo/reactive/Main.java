package com.github.diegoocampo.reactive;

import java.util.concurrent.Flow;

public class Main {

	public static void main(String[] args) {
		getTemperaturesPublisher("Sydney").subscribe(new TempSubscriber());
	}

	private static Flow.Publisher<TempInfo> getTemperaturesPublisher(String town){
		return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
	}

}
