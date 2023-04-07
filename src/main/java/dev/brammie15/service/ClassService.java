package dev.brammie15.service;

import dev.brammie15.objects.Class;

public interface ClassService {
    void addClass(Class c);

    Class getClass(String id);

    void updateClass(Class c);

    void deleteClass(Class c);

}
