import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: cm
 * @since: Created in 2023/6/15 13:13
 * @description:
 */
@Data
public class DateEntity {

    private String vin;
    //服务器接收时间
    private Date serverReceiveTime;
    //数据采集时间
    private Date dataCollectionTime;
    //里程(km)
    private Double mileage;

    private Integer soc;
}
