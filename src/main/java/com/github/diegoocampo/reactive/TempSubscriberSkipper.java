package com.github.diegoocampo.reactive;

import java.util.concurrent.Flow;

/**
 * Temp subscriber that discards readings
 */
public class TempSubscriberSkipper extends TempSubscriber{

	private boolean skip = false;

	@Override
	public void onNext(TempInfo item) {
		if (!skip) {
			super.onNext(item);
		}
		System.out.printf("TempSubscriberSkipper\tThread: %s. Skipping data temp %s\n",Thread.currentThread().getName(), item);
		swapSkip();
	}

	private void swapSkip(){
		skip = !skip;
	}

}
