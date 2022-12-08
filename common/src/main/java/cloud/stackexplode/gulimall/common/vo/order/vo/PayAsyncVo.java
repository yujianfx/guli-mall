package cloud.stackexplode.gulimall.common.vo.order.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class PayAsyncVo {

    private String gmtCreate;
    private String charset;
    private String gmtPayment;
    private Date notifyTime;
    private String subject;
    private String sign;
    private String buyerId;
    private String body;
    private String invoiceAmount;
    private String version;
    private String notifyId;
    private String fundBillList;
    private String notifyType;
    private String outTradeNo;
    private String totalAmount;
    private String tradeStatus;
    private String tradeNo;
    private String authAppId;
    private String receiptAmount;
    private String pointAmount;
    private String appId;
    private String buyerPayAmount;
    private String signType;
    private String sellerId;

}
