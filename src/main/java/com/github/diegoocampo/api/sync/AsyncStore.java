package com.github.diegoocampo.api.sync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncStore extends SyncStore {

	public AsyncStore(String name) {
		super(name);
	}


	public Future<Double> getPriceAsync(String product){
		sleep();
		return CompletableFuture.supplyAsync(() -> {
			System.out.printf("%s - getting price for %s \n", getName(), product);
			return calculatePrice(product);
		});
	}



}
