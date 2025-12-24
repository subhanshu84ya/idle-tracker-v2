package com.example.idletracker.nativeapi;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface IdleTrackerLibrary extends Library {

    IdleTrackerLibrary INSTANCE =
            Native.load("IdleTracker", IdleTrackerLibrary.class);

    boolean isSystemIdle(int seconds);
}
