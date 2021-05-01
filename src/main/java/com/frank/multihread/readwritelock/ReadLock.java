package com.frank.multihread.readwritelock;

/**
 * {@link  }
 *
 * @Date 2021/4/30
 * @Author frank
 * @Description:
 */
public class ReadLock implements Lock{

    private final ReadWriteLockImpl  readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()){
            // 若此时有线程在进行写操作，或者有些线程在等待并且偏向锁的表示为 true,就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.getPreferWriter()
                    && readWriteLock.getWaitingWriters() > 0)){
                readWriteLock.getMUTEX().wait();
            }
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()){
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
