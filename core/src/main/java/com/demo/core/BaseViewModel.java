package com.demo.core;

import android.os.Parcelable;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

/**
 * Created by Madan Mohan Yadav on 05/10/2017 AD
 */

public class BaseViewModel<T extends Parcelable> extends ViewModel {

    public static final int NETWORK_CONNECTION_ERROR = 115;

    /**
     * Form fields error
     */

    private ArrayList<ObserverInterface<T>> observerInterfaces = new ArrayList<>();

    public void onResume() {
    }

    public void onPause() {
    }

    public void addObserver(ObserverInterface<T> client) {
        if (!observerInterfaces.contains(client)) {
            observerInterfaces.add(client);
        }
    }

    public void removeObserver(ObserverInterface clientToRemove) {
        if (observerInterfaces.contains(clientToRemove)) {
            observerInterfaces.remove(clientToRemove);
        }
    }

    public void notifyObservers(int eventType, String eventMessage, T output) {
        for (int i = 0; i < observerInterfaces.size(); i++) {
            observerInterfaces.get(i).onObserve(eventType, eventMessage, output);
        }
    }

    /**
     * Failure message to be triggered
     */
    protected void onFail(String error) {
        notifyObservers(NETWORK_CONNECTION_ERROR, error, null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        observerInterfaces.clear();
    }
}

