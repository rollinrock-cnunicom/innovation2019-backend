package io.renren.modules.station.service.impl;

import io.renren.modules.station.dto.StationDTO;
import io.renren.modules.station.entity.TBaseStation;
import io.renren.modules.station.mapper.TBaseStationMapper;
import io.renren.modules.station.service.ITBaseStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public List<StationDTO> search(Double longitude, Double latitude, Double radius) {

        return stationMapper.searchAround(Objects.requireNonNull(longitude),
                Objects.requireNonNull(latitude), Objects.requireNonNull(radius)).stream().map(station -> {
                    return StationDTO.fromEntity(station);
        }).collect(Collectors.toList());
    }
}
