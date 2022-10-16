package cloud.stackexplode.gulimall.common.to.mq;

import lombok.Data;

@Data
public class StockLockedTo {
    private Long id;
    private StockDetailTo detailTo;
}
