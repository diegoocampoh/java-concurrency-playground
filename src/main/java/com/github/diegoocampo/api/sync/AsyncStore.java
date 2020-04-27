package com.github.diegoocampo.api.sync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncStore extends SyncStore {

	public AsyncStore(String name) {
		super(name);
	}


	public Future<Double> getPriceAsync(String product) {
		sleep();
		return CompletableFuture.supplyAsync(() -> {
			System.out.printf("%s - getting price for %s \n", getName(), product);
			return calculatePrice(product);
		});
	}


	public CompletableFuture<Double> getPriceAsyncTimeout(String product) {
		return CompletableFuture.supplyAsync(() -> {
			sleep(1100);
			System.out.printf("%s - getting price for %s \n", getName(), product);
			return calculatePrice(product);
		});
	}


}
