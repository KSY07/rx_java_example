package io.bizpeer.test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SubscriptionEx {

    public static void main(String[] args) {
        Observable<Integer> intObservable = Observable.range(1, 20);

        // 가장 기본적인 구독 호출 Disposable을 리턴
        Disposable disposable = intObservable.subscribe();

        // 구독 취소
        disposable.dispose();

        // subscribe 의 여러 메서드 시그니쳐
        // 단순 Consumer 정의
        intObservable.subscribe((data) -> {
            System.out.println(data);
        });

        intObservable.subscribe((data) -> {}, (err) -> {}); // onNext와 onError를 함께 정의
        intObservable.subscribe((data) -> {}, (err) -> {}, ()->{} ); // onNext와  onError, onComplete를 정의


        // Observer를 직접 생성하여 구독하기
        // 각각 onSubscribe, onNext, onError, onComplete를 정의한다.
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        intObservable.subscribe(observer); // observer를 통해서 구독
    }
}
