package com.example.simplecalendar.model;

import androidx.lifecycle.MutableLiveData;

/*
*LiveData
-RxJava와 같은 Observable 형태로 사용하며 안드로이드의 생명주기에 따라 이벤트를 제어하고 데이터를 관리함
-Observable 패턴을 사용하기 때문에 데이터의 변화를 감지하고 업데이트함
-메모리릭이 없는 사용을 보장함
-항상 최신 데이터를 유지함
-기기 회전이 일어나도 최신 데이터를 처리할 수 있도록 도와줌

*MutableLiveData
-LiveData는 값의 get()만 할 수 있다면, MutableLiveData는 값의 get/set 모두 할 수 있음
-ViewModel과 View의 역할을 분리하기 위해 사용함
 -> ViewModel은 언제나 새로운 값의 변경이 일어나고 다시 읽을 수 있는 형태로 사용하는 것이고
 -> View는 값의 입력이 아닌 읽기만을 허용하는 것임
 */

public class MainModel<T> extends MutableLiveData<T> {

    public MainModel() {

    }

    public MainModel(T value) {
        setValue(value);
    }
}
