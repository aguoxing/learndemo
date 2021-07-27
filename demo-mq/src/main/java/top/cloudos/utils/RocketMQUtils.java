package top.cloudos.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.rocketmq.srvutil.ServerUtil;
import org.apache.rocketmq.tools.command.SubCommand;
import org.apache.rocketmq.tools.command.SubCommandException;
import org.apache.rocketmq.tools.command.topic.DeleteTopicSubCommand;
import org.apache.rocketmq.tools.command.topic.TopicStatusSubCommand;
import org.apache.rocketmq.tools.command.topic.UpdateTopicSubCommand;

/**
 * @author gx
 * @date 2021/7/10 9:06
 **/
public class RocketMQUtils {

    /**
     * 创建指定 topic
     */
    public static boolean createTopic(String namesrv, String cluster, String topic) throws SubCommandException {
        // 构建指令
        String[] subargs = new String[]{
                "-n " + namesrv,
                "-c " + cluster,
                "-t " + topic};

        return exc(new UpdateTopicSubCommand(), subargs);
    }

    /**
     * 删除指定 topic
     */
    public static boolean deleteTopic(String namesrv, String cluster, String topic) throws SubCommandException {
        String[] subargs = new String[]{
                "-n " + namesrv,
                "-c " + cluster,
                "-t " + topic};

        return exc(new DeleteTopicSubCommand(), subargs);
    }

    /**
     * 获取指定 topic状态
     */
    public static boolean topicStatus(String namesrv, String topic) throws SubCommandException {
        String[] subargs = new String[]{
                "-n " + namesrv,
                "-t " + topic};

        return exc(new TopicStatusSubCommand(), subargs);
    }

    /**
     * 执行命令
     */
    public static boolean exc(SubCommand cmd, String[] subargs) throws SubCommandException {
        Options options = ServerUtil.buildCommandlineOptions(new Options());
        final Options topicOptions = cmd.buildCommandlineOptions(options);
        final CommandLine commandLine = ServerUtil
                .parseCmdLine("mqadmin " + cmd.commandName(),
                        subargs, topicOptions, new PosixParser());

        cmd.execute(commandLine, topicOptions, null);
        return true;
    }
}

