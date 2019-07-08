package io.renren.modules.station.service;

import io.renren.modules.station.dto.StationDTO;
import io.renren.modules.station.entity.TBaseStation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
public interface ITBaseStationService extends IService<TBaseStation> {

    List<StationDTO> search(Double longitude, Double latitude, Double radius);

}
