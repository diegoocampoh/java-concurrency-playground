package com.github.diegoocampo.reactive.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		Observable<String> strings = Observable.just("Hello", "World");
		strings.subscribe(System.out::println);


		Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);
		timer.subscribe(new Consumer<Long>() {
			@Override
			public void accept(Long aLong) throws Throwable {
				System.out.println(aLong);
			}
		});

	}
}
