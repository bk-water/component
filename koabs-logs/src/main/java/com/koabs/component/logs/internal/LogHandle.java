package com.koabs.component.logs.internal;

import com.koabs.component.logs.pojo.LogInfo;
import com.koabs.component.logs.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin1 on 7/11/16.
 * 处理Log 信息并发送保存
 */
public class LogHandle {

    private static Logger logger = LoggerFactory.getLogger(LogHandle.class);

    public static void handle(LogInfo logInfo) {
//        LogHandleTask logHandleTask = new LogHandle.LogHandleTask(logInfo);
//        logHandleTask.run();
        try {
            logger.info("日志:" + JSONUtils.toJson(logInfo));
        } catch (Exception e) {

        }
    }

    private static class LogHandleTask implements Runnable {
        private static Logger logger = LoggerFactory.getLogger(LogHandleTask.class);
        private LogInfo logInfo;

        public LogHandleTask(LogInfo logInfo) {
            this.logInfo = logInfo;
        }

        public void run() {
            long startHandleTime = System.currentTimeMillis();

            try {
                sendLogInfo(logInfo);
                if(logger.isDebugEnabled() && this.logInfo != null) {
                    long e = System.currentTimeMillis() - startHandleTime;
                    logger.debug("It takes {} milliseconds to handle CalStatisticTarget={}", Long.valueOf(e), this.logInfo);
                }
            } catch (Exception err) {
                logger.error("Error occurs for calStatisticTargetHandleTask task.", err);
                if(logger.isDebugEnabled() && this.logInfo != null) {
                    long handleCostTime = System.currentTimeMillis() - startHandleTime;
                    logger.debug("It takes {} milliseconds to handle CalStatisticTarget={}", Long.valueOf(handleCostTime), this.logInfo);
                }
            } finally {
                if(logger.isDebugEnabled() && this.logInfo != null) {
                    long handleCostTime1 = System.currentTimeMillis() - startHandleTime;
                    logger.debug("It takes {} milliseconds to handle CalStatisticTarget={}", Long.valueOf(handleCostTime1), this.logInfo);
                }

            }

        }

        private void sendLogInfo(LogInfo logInfo) {
            long startSendTime = System.currentTimeMillis();

            try {
                logger.info("日志:"+ JSONUtils.toJson(logInfo));
               // System.out.printf("日志:"+ JSONUtils.toJson(logInfo));
//                SendForMysql sendForMysql =  (SendForMysql) SpringContextUtil.getBean("logForMysql");
//                sendForMysql.send(logInfo);
            } finally {
                if(logger.isDebugEnabled()) {
                    long sendCostTime = System.currentTimeMillis() - startSendTime;
                    logger.debug("It takes " + sendCostTime + " milliseconds to send calLogInfo=" + JSONUtils.toJson(logInfo));
                }

            }

        }
    }

}
