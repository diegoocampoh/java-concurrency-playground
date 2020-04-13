package com.github.diegoocampo;

import com.github.diegoocampo.api.sync.AsyncStore;
import com.github.diegoocampo.api.sync.SyncStore;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static java.util.stream.Collectors.toList;

public class ApiClient {


	List<SyncStore> syncStores = List.of(new SyncStore("sAmazon"),
										 new SyncStore("sEbay"),
										 new SyncStore("sAlibaba"));

	List<AsyncStore> asyncStores = List.of(new AsyncStore("aAmazon"),
										 new AsyncStore("aEbay"),
										 new AsyncStore("aAlibaba"));

	public static void main(String[] args) {
		ApiClient client = new ApiClient();

		client.findPricesASync("ipad");

		long start = System.nanoTime();
		System.out.println("Starting blocking query");
		System.out.println(client.findPricesSync("ipad"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Sync Done in "+ duration + " msecs");

		start = System.nanoTime();
		System.out.println(client.findPricesParallelStream("ipad"));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Parallel stream over Sync Done in "+ duration + " msecs");

	}


	public List<String> findPricesSync(String prod){
		return syncStores.stream()
				.map( store -> String.format("%s price is %.2f", store.getName(), store.getPrice(prod)))
				.collect(toList());
	}

	public List<String> findPricesParallelStream(String prod){
		return syncStores.parallelStream()
				.map( store -> String.format("%s price is %.2f", store.getName(), store.getPrice(prod)))
				.collect(toList());
	}

	public List<Future<Double>> findPricesASync(String prod){
		 return asyncStores.parallelStream()
				.map( store -> store.getPriceAsync(prod))
				.collect(toList());
	}



	public List<String> findPricesAsync(){
		//TODO fill in the gaps
		return null;
	}



}
