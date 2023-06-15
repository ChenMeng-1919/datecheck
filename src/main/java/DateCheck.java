import com.alibaba.excel.EasyExcel;

import java.io.File;
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
public class DateCheck {
    public static void main(String[] args) {
        String csvFile = "C:\\cm\\workspace\\my-sources\\datecheck\\src\\main\\resources\\123.csv";
        DataListener dataListener = new DataListener();
        EasyExcel.read(csvFile, DateEntity.class, dataListener).sheet().doRead();
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
        // 打印出不同的日期
        for (String date : uniqueDates) {
            System.out.println(date);
        }
    }
}
