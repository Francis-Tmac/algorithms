package com.frank.zookeeper;

import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * {@link  }
 *
 * @Date 2021/3/28
 * @Author frank.yang
 * @Description:
 */
public class ZooKeeperProSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        String path = "/username";
        // 连接zk 并且注册一个默认的监听器
        zk = new ZooKeeper("127.0.0.1:2181", 5000, new ZooKeeperProSync());
        // 等待zk 连接成功的通知
        connectedSemaphore.await();
        System.out.println(new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
            if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeChildrenChanged) {
                try {
                    System.out.println(
                            "配置已修改：新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {

                }
            }
        }
    }
}
