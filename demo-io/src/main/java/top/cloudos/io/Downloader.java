package top.cloudos.io;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author GX
 * @email 334412097@qq.com
 * @date 2020/12/18 10:05
 * @description
 **/
public class Downloader {
    /**
     * 默认线程数量
     */
    private static final int DEFAULT_THREAD_COUNT = 4;
    /**
     * 取消状态，如果有一个子线程出现异常，则取消整个下载任务
     */
    private AtomicBoolean canceled;
    /**
     * 下载的文件对象
     */
    private DownloadFile file;
    private String storageLocation;
    /**
     * 线程数量
     */
    private final int threadCount;
    /**
     * 文件大小
     */
    private long fileSize;
    private final String url;
    /**
     * 开始时间
     */
    private long beginTime;
    private Logger logger;

    public Downloader(String url) {
        this(url, DEFAULT_THREAD_COUNT);
    }

    public Downloader(String url, int threadCount) {
        this.url = url;
        this.threadCount = threadCount;
        this.canceled = new AtomicBoolean(false);
        this.storageLocation = url.substring(url.lastIndexOf('/') + 1);
        this.logger = new Logger(storageLocation + ".log", url, threadCount);
    }

    public void start() {
        boolean reStart = Files.exists(Paths.get(storageLocation + ".log"));
        if (reStart) {
            logger = new Logger(storageLocation + ".log");
            System.out.printf("* 继续上次下载进度[已下载：%.2fMB]：%s\n", logger.getWroteSize() / 1014.0 / 1024, url);
        } else {
            System.out.println("* 开始下载：" + url);
        }
        if (-1 == (this.fileSize = getFileSize())) {
            return;
        }
        System.out.printf("* 文件大小：%.2fMB\n", fileSize / 1024.0 / 1024);

        this.beginTime = System.currentTimeMillis();
        try {
            this.file = new DownloadFile(storageLocation, fileSize, logger);
            if (reStart) {
                file.setWroteSize(logger.getWroteSize());
            }
            // 分配线程下载
            dispatcher(reStart);
            // 循环打印进度
            printDownloadProgress();
        } catch (IOException e) {
            System.err.println("x 创建文件失败[" + e.getMessage() + "]");
        }
    }

    /**
     * 分配器，决定每个线程下载哪个区间的数据
     */
    private void dispatcher(boolean reStart) {
        // 每个线程要下载的数据量
        long blockSize = fileSize / threadCount;
        long lowerBound = 0, upperBound = 0;
        long[][] bounds = null;
        int threadID = 0;
        if (reStart) {
            bounds = logger.getBounds();
        }
        for (int i = 0; i < threadCount; i++) {
            if (reStart) {
                threadID = (int) (bounds[i][0]);
                lowerBound = bounds[i][1];
                upperBound = bounds[i][2];
            } else {
                threadID = i;
                lowerBound = i * blockSize;
                // fileSize-1 !!!!! fu.ck，找了一下午的错
                upperBound = (i == threadCount - 1) ? fileSize - 1 : lowerBound + blockSize;
            }
            new DownloadTask(url, lowerBound, upperBound, file, canceled, threadID).start();
        }
    }

    /**
     * 循环打印进度，直到下载完毕，或任务被取消
     */
    private void printDownloadProgress() {
        long downloadedSize = file.getWroteSize();
        int i = 0;
        // 三秒前的下载量
        long lastSize = 0;
        while (!canceled.get() && downloadedSize < fileSize) {
            // 每3秒打印一次
            if (i++ % 4 == 3) {
                System.out.printf("下载进度：%.2f%%, 已下载：%.2fMB，当前速度：%.2fMB/s\n",
                        downloadedSize / (double) fileSize * 100,
                        downloadedSize / 1024.0 / 1024,
                        (downloadedSize - lastSize) / 1024.0 / 1024 / 3);
                lastSize = downloadedSize;
                i = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
            }
            downloadedSize = file.getWroteSize();
        }
        file.close();
        if (canceled.get()) {
            try {
                Files.delete(Paths.get(storageLocation));
            } catch (IOException ignore) {
            }
            System.err.println("x 下载失败，任务已取消");
        } else {
            System.out.println("* 下载成功，本次用时" + (System.currentTimeMillis() - beginTime) / 1000 + "秒");
        }
    }

    /**
     * @return 要下载的文件的尺寸
     */
    private long getFileSize() {
        if (fileSize != 0) {
            return fileSize;
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("HEAD");
            conn.connect();
            System.out.println("* 连接服务器成功");
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL错误");
        } catch (IOException e) {
            System.err.println("x 连接服务器失败[" + e.getMessage() + "]");
            return -1;
        }
        return conn.getContentLengthLong();
    }

    public static void main(String[] args) throws IOException {
        String url = "http://qdall01.baidupcs.com/file/dd8d775b1t108e73fd392208d2556151?bkt=en-038bee77e919b76a6c79812f408062d98c8ab6950115d073d440a4832d67a8097cf8e11a99e054d8&fid=743503436-250528-754154547030311&time=1608282324&sign=FDTAXUGERLQlBHSKfWaqi-DCb740ccc5511e5e8fedcff06b081203-Js5OWjbN4SZmu9JEpFkCRd8mcCs%3D&to=34&size=1828644607&sta_dx=1828644607&sta_cs=9261&sta_ft=zip&sta_ct=6&sta_mt=5&fm2=MH%2CXian%2CAnywhere%2C%2Cguangdong%2Cct&ctime=1582203732&mtime=1600526882&resv0=-1&resv1=0&resv2=rlim&resv3=5&resv4=1828644607&vuk=743503436&iv=-2&htype=&randtype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-08ec34d04c4975be7ec3f24431a5cb79556f20e47d3a7874244cc2f5b1eb5c8f94a5f3b6115854e0&sl=81395790&expires=8h&rt=pr&r=510923606&vbdid=2073157003&fin=Sky_Mi6_20.2.20Dev_Pie%28B11F8FE555%29.zip&rtype=1&dp-logid=3181618744837129063&dp-callid=0.1&tsl=5&csl=39&fsl=-1&csign=vysUJ0gFL%2FZonR85YrT9SjvAQXY%3D&so=1&ut=8&uter=0&serv=0&uc=3200815480&ti=643d9844218956627489adcd7319bfb80c18fa769c07f7d2&hflag=30&adg=c_ebf83252f9cffc41ed6699d1ab6971bd&reqlabel=250528_f_c6c367f07580851a265e978cea6a1412_-1_c83c0fd29def88a6234825ed365691e2&by=themis";
        new Downloader(url).start();
    }
}
