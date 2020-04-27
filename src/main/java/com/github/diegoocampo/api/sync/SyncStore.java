package com.github.diegoocampo.api.sync;

import java.util.Random;

public class SyncStore {

	private final String name;

	public String getName() {
		return name;
	}

	public SyncStore(String name) {
		this.name = name;
	}

	public double getPrice(String product){
		sleep();
		return calculatePrice(product);
	}


	protected double calculatePrice(String prod){
		return getName().hashCode() * 2.1 * prod.hashCode();
	}

	protected void sleep(){
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
