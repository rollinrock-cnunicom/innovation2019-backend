package io.renren.modules.station.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class EvaluateRequest {

    private Double longitude;
    private Double latitude;

    /* 网络类型：1-3G 2-4G */
    private Integer network;
    /* 设备来源：1-利旧 2-新购 */
    private Integer equipmentSource;
    /* 主设备类型：1-L900 2-L1800 3-UL2100 4-UBR */
    private Integer equipmentType;
    /* 方向 1234 */
    private Integer direction;
    /* 铁塔租用：1-自建 2-友商共享 3-铁塔组用 4-第三方租用 */
    private Integer towerRent;
//    private String newTransferEquipment: true,
    /* 传输距离（米） */
    private Integer transDistance;
    /* 第一年收益 */
    private BigDecimal firstYearIncome;
    /* 第二年收益 */
    private BigDecimal secondYearIncome;
    /* 第三年收益 */
    private BigDecimal thirdYearIncome;
    /* 天线高度（米） */
    private Integer antennaHeight;
    /* 机房配置 1-租赁机房 2-一体化机柜 3-普通地面塔自建机房 4-普通楼面塔自建彩钢板*/
    private Integer computerLabConfig;
    /* 铁塔种类 1-普通地面塔 2-景观塔 3-简易塔 4-普通楼面塔 5-楼面抱杆 */
    private Integer towerType;
    /* 当前铁塔共享客户总数 0-5 */
    private Integer sharedTowerCustomerTotal;
    /* 当前机房及配套存量新增共享客户数 0-5 */
    private Integer sharedLabCustomerTotal;
    /* 租户类型: 1-锚定租户 2-其他租户 */
    private Integer renterType;
    /* 订单提交前天线数量（个）: 0-15 */
    private Integer prevAntennaCount;
    /* 订单提交前系统数量（个）: 0-5 */
    private Integer prevSystemCount;
}
