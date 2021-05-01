package com.frank.multihread.readwritelock;

/**
 * {@link  }
 *
 * @Date 2021/4/30
 * @Author frank
 * @Description:
 */
public interface ReadWriteLock {

    Lock readLock();

    Lock writeLock();

    int getWritingWriters();

    int getWaitingWriters();

    int getReadingReaders();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static  ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }

}
