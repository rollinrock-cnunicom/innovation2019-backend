package io.renren.modules.station.mapper;

import io.renren.modules.station.entity.AllyStation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cj
 * @since 2019-07-13
 */
@Mapper
public interface AllyStationMapper extends BaseMapper<AllyStation> {
    @Select("select avg(a.rpc_rank) from ally_station a " +
            " order by st_distance_sphere(point(a.gd_longitude, a.gd_latitude), point(#{longitude},#{latitude})) asc limit #{limit}")
    double predictRpc(@Param("longitude") double longitude,
                       @Param("latitude") double latitude,
                       @Param("limit") int limit);

    @Select("select max(a.rank) from ally_station a where a.rpc_rank < #{rpc} ")
    double rank(@Param("rpc") double rpc);
}
