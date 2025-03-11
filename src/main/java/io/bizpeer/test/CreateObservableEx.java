package io.bizpeer.test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

import java.util.concurrent.TimeUnit;

public class CreateObservableEx {

    public static void main(String... args) {

        // Create Observable By Just
        // Just 는 단순하게 Observable을 생성할 수 있다.
        Observable<Integer> observable_just1 = Observable.just(1);
        Observable<Integer> observable_just2 = Observable.just(1,2, 3, 4);

        // Create Observable By Create
        // Create는 일반적으로 Observable을 생성할 수 있는 메서드로 직접 ObservableOnSubscribe 인터페이스를 구현한다.
        Observable<Integer> observable_create = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete(); // onComplete 호출 시 데이터 emit을 중단한다.
                emitter.onNext(4); // consume 되지 않음에 주목.
                emitter.onError(new RuntimeException()); // 에러를 던진다.
            }
        });

        Observable<Integer> observable_create_lambda = Observable.create((emitter) -> { // Lambda 형식
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete(); // onComplete 호출 시 데이터 emit을 중단한다.
            emitter.onNext(4); // consume 되지 않음에 주목.
            emitter.onError(new RuntimeException()); // 에러를 던진다.
        });

        // Create Observable By Range
        // 범위 기반으로 Observable을 생성한다. Integer 타입만 가능하다.
        Observable<Integer> observable_range = Observable.range(1, 10);

        // Create Observable By Defer
        // 구독될 때마다 새로운 Observable을 생성하는 연산자이다. just와는 다르게 선언한 시점의 데이터를 통지하는 것이 아니라 호출 시점에 데이터 생성이 필요할 때 사용한다.
        Observable<String> observable_defer = Observable.defer(() -> {
            return Observable.just("hello");
        });

        // Create Observable By From
        // 다른 객체로 부터 Observable을 생성 (fromArray, fromCallable, fromFuture ...)
        Observable<Integer> observable_array = Observable.fromArray(new Integer[]{1, 2, 3, 4,});

        // Create Observable By Interval
        // 일정 시간 마다 데이터를 방출하는 Observable을 생성한다. (주어진 시간 간격으로 부터 0부터 1씩 증가)
        Observable<Long> observable_interval = Observable.interval(10, TimeUnit.SECONDS);

        // Create Observable By timer
        // Interval과 같지만 일정 시간 이후에 한 개의 데이터를 발행하고 onComplete를 호출 함.
        Observable<Long> observable_timer = Observable.timer(10, TimeUnit.SECONDS);

    }
}
