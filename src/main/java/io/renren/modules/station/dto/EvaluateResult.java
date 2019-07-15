package io.renren.modules.station.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class EvaluateResult implements Serializable {
    private BigDecimal equipmentCost;
    private BigDecimal installCost;
    private BigDecimal transferCost;
    private BigDecimal towerCostYearly;
    private BigDecimal energyCostYearly;
    private BigDecimal maintenanceCostYearly;
    private Double arpuRank;
    private Double userAroundRank;
    private Integer complaintsAround;
    private Double competitorLevel;
    private BigDecimal firstYearRevenue;
    private BigDecimal secondYearRevenue;
    private BigDecimal thirdYearRevenue;
    private Integer investmentRecoveryYears;

    private List<SceneData> scenes;
    private List<StationData> stations;


}
