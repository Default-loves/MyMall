package com.junyi.mall;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class abc {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    // 真正决定了工作线程数的理论上限
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;
    // 线程池状态，存储在数字的高位
    private static final int RUNNING = -1 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)  { return c & ~COUNT_MASK; }
    private static int workerCountOf(int c)  { return c & COUNT_MASK; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
      log.info(COUNT_BITS +"");
      log.info("COUNT_MASK" + Integer.toBinaryString(COUNT_MASK));
      log.info("-1" + Integer.toBinaryString(-1));
      log.info("RUNNING" + Integer.toBinaryString(RUNNING));
      log.info(RUNNING +"");
      log.info(ctlOf(RUNNING, 0) +"");

    }
}
