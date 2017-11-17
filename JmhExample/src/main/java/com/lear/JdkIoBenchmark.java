package com.lear;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JdkIoBenchmark {
    public static final int LIST_LENGTH = 24 * 1024 * 1024;

    List<Integer> arrayList = new ArrayList<>(LIST_LENGTH);
    List<Integer> linkedList = new LinkedList<>();

    @Setup
    public void setup() {
        Random rand = new Random();
        for (int i = 0; i < LIST_LENGTH; i++) {
            arrayList.add(rand.nextInt());
        }
        //initializeLinkedListEffectively();
		//initializeLinkedListCommonly();
		initializeLinkedListBadly();
    }
	
	private void initializeLinkedListEffectively() {
		for (int i = 0; i < LIST_LENGTH; i++) {
            linkedList.add(arrayList.get(i));
        }
	}
	
	private void initializeLinkedListCommonly() {
		Random rand = new Random();
		for (int i = 0; i < LIST_LENGTH; i++) {
            linkedList.add(rand.nextInt());
        }
	}
	
	private void initializeLinkedListBadly() {
		for (int i = 0; i < LIST_LENGTH; i++) {
            int pos = Math.abs(rand.nextInt()) % (LIST_LENGTH);
            linkedList.add(arrayList.get(pos));
        }
	}

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OperationsPerInvocation(LIST_LENGTH)
    @Warmup(iterations = 1)
    @Measurement(iterations = 5)
    @Fork(1)
    public int arrayListIterationTest() {
        int sum = 0;
        for (int i : arrayList) {
            sum += i;
        }
        return  sum;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OperationsPerInvocation(LIST_LENGTH)
    @Warmup(iterations = 1)
    @Measurement(iterations = 5)
    @Fork(1)
    public int linkedListIterationTest() {
        int sum = 0;
        for (int i : linkedList) {
            sum += i;
        }
        return  sum;
    }
}
