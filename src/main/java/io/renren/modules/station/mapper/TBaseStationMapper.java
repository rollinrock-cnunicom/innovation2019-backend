package io.renren.modules.station.mapper;

import io.renren.modules.station.entity.TBaseStation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@Mapper
public interface TBaseStationMapper extends BaseMapper<TBaseStation> {

    @Select("select * from t_base_station " +
            "where st_distance_sphere(point( #{longitude}, #{latitude} ), point(gd_longitude, gd_latitude)) < #{radius} ")
    List<TBaseStation> searchAround(@Param("longitude") Double longitude,
                                    @Param("latitude") Double latitude,
                                    @Param("radius") Double radius);
}
