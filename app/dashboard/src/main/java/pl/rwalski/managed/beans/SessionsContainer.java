package pl.rwalski.managed.beans;

import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@Singleton
public class SessionsContainer {

    private Set<String> registeredUsers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void add(String user) {
        registeredUsers.add(user);
    }

    public void delete(String user) {
        registeredUsers.remove(user);
    }

    public boolean contains(String user) {
        return registeredUsers.contains(user);
    }

    public int size() {
        return registeredUsers.size();
    }
}
