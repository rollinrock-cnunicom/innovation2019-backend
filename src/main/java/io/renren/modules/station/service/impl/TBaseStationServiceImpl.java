package io.renren.modules.station.service.impl;

import io.renren.modules.station.dto.EvaluateRequest;
import io.renren.modules.station.dto.EvaluateResult;
import io.renren.modules.station.dto.StationDTO;
import io.renren.modules.station.entity.TBaseStation;
import io.renren.modules.station.mapper.AllyStationMapper;
import io.renren.modules.station.mapper.ArpuMapper;
import io.renren.modules.station.mapper.ComplaintMapper;
import io.renren.modules.station.mapper.TBaseStationMapper;
import io.renren.modules.station.service.ITBaseStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.utils.LocUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@Service
public class TBaseStationServiceImpl extends ServiceImpl<TBaseStationMapper, TBaseStation> implements ITBaseStationService {

    @Resource
    private TBaseStationMapper stationMapper;
    @Resource
    private ArpuMapper arpuMapper;
    @Resource
    private ComplaintMapper complaintMapper;
    @Resource
    private AllyStationMapper allyStationMapper;

    @Override
    public List<StationDTO> search(Double longitude, Double latitude, Double radius) {

        return stationMapper.searchAround(Objects.requireNonNull(longitude),
                Objects.requireNonNull(latitude), Objects.requireNonNull(radius)).stream().map(station -> StationDTO.fromEntity(station))
                    .collect(Collectors.toList());
    }

    @Override
    public EvaluateResult evaluate(EvaluateRequest evaluate) {
        Objects.requireNonNull(evaluate);
        EvaluateResult result = new EvaluateResult();
        result.setEquipmentCost(BigDecimal.valueOf(this.calcEquipmentCost(evaluate)));
        result.setInstallCost(BigDecimal.valueOf(this.calcInstallCost(evaluate)));
        result.setTransferCost(BigDecimal.valueOf(this.calcTransferCost(evaluate)));

        result.setTowerCostYearly(BigDecimal.valueOf(this.calcTowerCostYearly(evaluate)));
        result.setEnergyCostYearly(BigDecimal.valueOf(this.calcEnergyCostYearly(evaluate)));
        result.setMaintenanceCostYearly(BigDecimal.valueOf(this.calcMaintenanceCostYearly(evaluate)));

        result.setArpuRank(this.calcArpuRank(evaluate));
        result.setUserAroundRank(this.calcUserAroundRank(evaluate));
        result.setComplaintsAround(this.calcComplaintsAround(evaluate));
        result.setCompetitorLevel(this.calcCompetitorLevel(evaluate));

        result.setFirstYearRevenue(evaluate.getFirstYearIncome().add(BigDecimal.valueOf(this.calcStationRevenueAround(evaluate) * (1 + 0.2))));/* 0.2需要换成规则表3.2.4.9所表示的内容，第二年第三年同理 */
        result.setSecondYearRevenue(evaluate.getSecondYearIncome().add(BigDecimal.valueOf(this.calcStationRevenueAround(evaluate) * (1 + 0.3))));
        result.setThirdYearRevenue(evaluate.getThirdYearIncome().add(BigDecimal.valueOf(this.calcStationRevenueAround(evaluate) * (1 + 0.4))));
        result.setInvestmentRecoveryYears(2);/* 先默认2年 */
        return null;
    }
    /* 计算周边站点过去12月总收入 */
    private double calcStationRevenueAround(EvaluateRequest evaluate) {
        return 0;
    }
    /* 友商排名 */
    private double calcCompetitorLevel(EvaluateRequest evaluate) {
        return allyStationMapper.rank(allyStationMapper.predictRpc(evaluate.getLongitude(), evaluate.getLatitude(), 6));
    }
    /* 计算 周边投诉量 */
    private int calcComplaintsAround(EvaluateRequest evaluate) {
        return complaintMapper.countAround(evaluate.getLongitude(), evaluate.getLatitude(), 1000);
    }
    //计算 途径用户数全网排名
    private double calcUserAroundRank(EvaluateRequest evaluate) {
        return arpuMapper.rankRelatedUser(arpuMapper.predictUser(evaluate.getLongitude(), evaluate.getLatitude(), 18));
    }
    //计算 周边基站常驻用户ARPU值全网排名
    private double calcArpuRank(EvaluateRequest evaluate) {
        return arpuMapper.rank(arpuMapper.predictArpu(evaluate.getLongitude(), evaluate.getLatitude(), 18));
    }
    // 计算塔租每年成本
    private double calcTowerCostYearly(EvaluateRequest evaluate) {
        /* 算法极其复杂，先按36000默认，最后方豪平再想办法 */
        return 36000;
    }
    // 计算能源每年成本
    private double calcEnergyCostYearly(EvaluateRequest evaluate) {
        switch(evaluate.getDirection()) {
            case 1:/* L900 */
                return 446.87052 * 12;
            case 2:/* L1800 */
                return 445.2912 * 12;
            case 3:/* UL2100 */
                return 337.05 * 12;
            case 4:/* nbr */
                return 482.2704 * 12;
            default:
                return 0;
        }

    }
    // 计算维护费用
    private double calcMaintenanceCostYearly(EvaluateRequest evaluateRequest) {
        return 1121.28 * evaluateRequest.getDirection();
    }

    // 计算传输成本
    private double calcTransferCost(EvaluateRequest evaluate) {
        return 4000 + 1500 * 5500;
    }
    // 计算 安装成本 施工成本
    private double calcInstallCost(EvaluateRequest evaluate) {
        switch(evaluate.getDirection()) {
            case 1:
                return 3000 * 0.41 * 1.11;
            case 2:
                return 3850 * 0.41 * 1.11;
            case 3:
                return 4890 * 0.41 * 1.11;
            case 4:
                return 4890 * 0.41 * 1.11;
            default:
                return 0;
        }
    }
    // 计算 设备成本
    private double calcEquipmentCost(EvaluateRequest evaluate) {
        // 利旧不需要设备成本
        if (evaluate.getEquipmentSource() == 1) return 0;
        //是否在兰溪浦江区域内
        boolean lanxiOrPujiang = LocUtils.locatedInLanxiOrPujiang(evaluate.getLongitude(), evaluate.getLatitude());

        switch(evaluate.getEquipmentType()) {
            case 1: //L900
                if (evaluate.getDirection() == 4) {
                    return lanxiOrPujiang ? 56361.01: 60100;
                } else {
                    return (lanxiOrPujiang ? 8421.89: 8000) + evaluate.getDirection() * (lanxiOrPujiang ? 11984.78: 13000);
                }
            case 2: //L1800
                if (evaluate.getDirection() == 4) {
                    return lanxiOrPujiang ? 87996.49: 90500;
                } else {
                    return (lanxiOrPujiang ? 8421.89: 8400) + evaluate.getDirection() * (lanxiOrPujiang ? 19893.65: 20300);
                }
            case 3: //UL2100
                return 0;
            case 4:
                if (evaluate.getDirection() == 4) {
                    return 231000;
                } else {
                    return (lanxiOrPujiang ? 8421.89: 104800) + evaluate.getDirection() * (lanxiOrPujiang ? 47908.61: 14100);
                }
            default:
                return 0;

        }
    }
}
