package com.epamjwd.provider.model.pool;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ActiveUserPool {
    private BlockingDeque<String> activeUsers;

    private static class InstanceHolder {
        static final ActiveUserPool instance = new ActiveUserPool();
    }

    private ActiveUserPool() {
        activeUsers = new LinkedBlockingDeque<>();
    }

    public static ActiveUserPool getInstance() {
        return InstanceHolder.instance;
    }

    public void addUser(String email) {
        activeUsers.add(email);
    }

    public boolean removeUser(String email) {
        return activeUsers.remove(email);
    }

    public boolean isActive(String email) {
        return activeUsers.contains(email);
    }
}
