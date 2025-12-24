package com.example.idletracker.nativecode;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface IdleNative extends Library {

    // Load DLL (IdleTracker.dll)
    IdleNative INSTANCE = Native.load("IdleTracker", IdleNative.class);

    // C++ method signature
    boolean isSystemIdle(int seconds);
}
