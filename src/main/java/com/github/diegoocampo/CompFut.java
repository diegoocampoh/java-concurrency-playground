package com.github.diegoocampo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompFut {

	private ExecutorService executor;

	public static void main(String[] args) throws ExecutionException, InterruptedException {

			CompFut cf = new CompFut();
			cf.combinator();

	}


	private void combinator()  throws ExecutionException, InterruptedException {
		executor = Executors.newFixedThreadPool(10);


		int x = 1337;

		CompletableFuture<Integer> a = new CompletableFuture<>();
		CompletableFuture<Integer> b = new CompletableFuture<>();
		CompletableFuture<Integer> c = a.thenCombine(b, (y, z) -> y + z);

		executor.submit(() -> a.complete(f(x)));
		executor.submit(() -> b.complete(g(x)));

		System.out.println("done submitting a and b");
		System.out.println(c.get());

		executor.shutdown();
	}


	private void first() throws ExecutionException, InterruptedException {
		executor = Executors.newFixedThreadPool(10);

		int x = 1337;

		CompletableFuture<Integer> a = new CompletableFuture<>();
		executor.submit(() -> a.complete(f(x)));


		int b = g(x);

		System.out.println(a.get()+b);

		executor.shutdown();
	}

	private int f(int x){
		System.out.println("calculating f");
		return x*2;
	}

	private int g(int x){
		System.out.println("calculating g");
		return x+2;
	}


}
