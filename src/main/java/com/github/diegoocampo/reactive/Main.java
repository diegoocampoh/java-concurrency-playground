package com.github.diegoocampo.reactive;

import java.util.concurrent.Flow;

public class Main {

	public static void main(String[] args) {
		System.out.printf("Thread: %s. Starting system. \n",Thread.currentThread().getName());
		Flow.Publisher<TempInfo> sydneyTempPublisher = getTemperaturesPublisher("Sydney");
		sydneyTempPublisher.subscribe(new TempSubscriber());
		sydneyTempPublisher.subscribe(new TempSubscriberSkipper());
		System.out.printf("Thread: %s. finishing system. \n",Thread.currentThread().getName());
	}

	private static Flow.Publisher<TempInfo> getTemperaturesPublisher(String town){
		return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
	}

}
