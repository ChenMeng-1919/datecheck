import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: cm
 * @since: Created in 2023/6/15 13:12
 * @description:
 */
@Slf4j
public class DateCheck {
    public static void main(String[] args) {
        //String csvFileName = args[0];
        String csvFileName = "123.csv";
        File jarFile = new File(DateCheck.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String jarAbsolutePath = jarFile.getAbsolutePath();
        DataListener dataListener = new DataListener();
        int lastIndex = jarAbsolutePath.lastIndexOf("\\");
        String substring = jarAbsolutePath.substring(0, lastIndex);
        String pathName = substring + "\\" + csvFileName;
        log.info(pathName);
        EasyExcel.read(pathName, DateEntity.class, dataListener).sheet().doRead();
        List<DateEntity> cachedDataList = dataListener.cachedDataList;
        // 创建一个 HashSet 来存储不同的日期
        Set<String> uniqueDates = new HashSet<>();
        // 遍历对象集合，提取日期部分并添加到 HashSet 中
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (DateEntity cachedData : cachedDataList) {
            Date serverReceiveTime = cachedData.getServerReceiveTime();
            String dateStr = dateFormat.format(serverReceiveTime);
            uniqueDates.add(dateStr);
        }
        // 创建一个名为 result.txt 的文件，并将 uniqueDates 中的每个元素写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            for (String date : uniqueDates) {
                writer.write(date);
                writer.newLine();
            }
            log.info("写入完成");
        } catch (IOException e) {
            log.info("写入文件时发生错误：" + e.getMessage());
        }

    }
}
