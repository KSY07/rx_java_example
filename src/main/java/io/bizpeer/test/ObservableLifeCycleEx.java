package io.bizpeer.test;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ObservableLifeCycleEx {

    public static void main(String[] args) {
        // 예제 Observable 생성 1부터 20까지의 Integer를 방출한다.
        Observable<Integer> observable = Observable.range(1, 20);

        observable.doOnNext((data) -> { // doOnNext는 각 데이터 마다 방출 시에 로직을 정의 할 수 있다.
            System.out.println(data);
        });

        observable.doOnComplete(() -> { // doOnComplete 는 onComplete가 호출 될 때의 로직을 정의 할 수 있다.
            System.out.println("Data Emit Complete");
        });

        observable.doOnError((err) -> { // doOnError는 Exception을 인자로 받아 에러 처리 시에 로직을 정의 할 수 있다.
           System.out.println("Handle Exception ");
        });

        observable.doOnTerminate(() -> { // doOnTerminate는 Emitting을 끝내는 함수인 onComplete() 또는 onError() 이벤트 발생 직전에 호출하는 로직을 정의한다.

        });

        observable.doOnEach((noti) -> { // doOnEach는 Notification을 인자로 받아 모든 Notification에 대하여 로직을 정의 할 수 있다.
            if(noti.isOnComplete()) {

            } else if(noti.isOnError()) {

            } else if(noti.isOnNext()) {

            }
        });

        observable.doOnSubscribe((subscribe) -> { // doOnSubscribe는 Subscribe 시에 로직을 정의 할 수 있다. 인자는 Subscription

        });


        observable.doOnDispose(() -> { // doOnDispose는 Subscribe를 중단 할 시에 로직을 정의 할 수 있다.

        });

        observable.doOnLifecycle((subscribe) -> { // doOnLifecycle 은 doOnSubscribe와 doOnDispose를 동시에 정의할 수 있다.

                }
                , () -> {

        });

        // Flowable은 배압을 적용한 Observable
        // onBackpressureBuffer 를 통해 배압 전략을 버퍼로 설정 버퍼 Capacity를 조절 가능하고, 버퍼 오버플로우를 처리할 수 있다.
        // 이 외에도 onBackpressureDrop, onBackpressureLatest, onBackpressureError 등으로 배압 전략을 변경 및 설정 할 수 있다.
        Flowable<Integer> integerFlowable = Flowable.range(1, 20).onBackpressureBuffer(10);
        Flowable<Integer> integerFlowableWithOverflowHandle = Flowable.range(1, 20).onBackpressureBuffer(10,
                () -> {
                    System.out.println("Buffer Overflow!!");
                });

        integerFlowable.subscribe((data) -> {});

        // Single
        // Single은 단순 1개의 데이터를 방출하는데, onComplete를 호출 할 필요가 없는 점이 Observable 과의 차이이다.
        // onComplete 에 대한 처리를 생략해도 되기 때문에 결과를 단일값으로 가져오는 네트워크 통신에 유용
        Single<String> single = Single.just("Hello");

        // 이 외에도 Maybe, Completable 등이 존재.
    }
}
