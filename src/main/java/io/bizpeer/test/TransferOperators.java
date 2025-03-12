package io.bizpeer.test;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TransferOperators {
    public static void main(String[] args) {
        // Example Observable
        Observable<Integer> observable = Observable.range(1, 20);
        // Buffer Operators
        // 5개씩 담아서 List를 반환하는 Observable로 Transfer
        Observable<List<Integer>> bufferedObservable = observable.buffer(5);
        // skip argument를 주면, 7개중 5개만 모아 리턴하고 남은 2개는 Skip 한다.
        Observable<List<Integer>> bufferedObservable2 = observable.buffer(5, 7);
        // buffer Supplier를 넘겨 주면 List가 아닌 다른 자료형으로 받을 수 있다.
        Observable<Set<Integer>> bufferedObservable3 = observable.buffer(5,  HashSet::new);
        Observable<Set<Integer>> bufferedObservable4 = observable.buffer(5, 7, HashSet::new);
        // 시간 단위로 buffered도 가능 하다.
        // timespan은 buffered 할 시간을 의미 하며, timeskip은 buffered를 스킵할 시간을 정의한다.
        // 이 외에도 Scheduler를 직접 정의하여 입력 가능하다.
        Observable<List<Integer>> bufferedObservable5 = observable.buffer(10, 1, TimeUnit.SECONDS);



        // Map Functions
        // 기본 map 함수는 모든 방출되는 데이터를 변환 한다.
        observable.map((data) -> {
            return data * 2;
        });

        // flatMap 계열은 병렬 스레드로 처리하여 데이터의 순서를 보장하지 않는다. 또한 Observable을 리턴한다. (새로운 데이터 방출 요소를 만든다, 입력 하나를 여러 개의 Observable로 전환 가능 하다.)
        observable.flatMap((data) -> {
            return Observable.just(data * 2);
        });

        // concatMap 계열은 flatMap과 용도는 같으나 순처적 처리를 통해 순서를 보장한다.
        observable.concatMap((data) -> {
            return Observable.just(data * 2);
        });

        // switchMap 계열은 새로운 데이터가 emit 되면 기존 값을 무시하고 새로운 Observable로 교체한다.
        observable.switchMap((data) -> {
           return Observable.just(data * 2);
        }).delay(100, TimeUnit.MILLISECONDS);
    }
}
