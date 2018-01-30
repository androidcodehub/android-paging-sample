package com.androidcodehub.androidpagingsample;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppsExecutor {

    private final static Executor networkIO = Executors.newFixedThreadPool(3);

    private final static Executor mainThread = new MainThreadExecutor();

    private AppsExecutor() {

    }

    public static Executor networkIO() {
        return networkIO;
    }

    public static Executor mainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }


}
