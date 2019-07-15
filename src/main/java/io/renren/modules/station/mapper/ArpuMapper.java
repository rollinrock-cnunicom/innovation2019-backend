package io.renren.modules.station.mapper;

import io.renren.modules.station.entity.Arpu;
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
 * @since 2019-07-05
 */
@Mapper
public interface ArpuMapper extends BaseMapper<Arpu> {

    @Select("select avg(a.arpu) from arpu a where a.arpu != 0 " +
            " order by st_distance_sphere(point(a.gd_longitude, a.gd_latitude), point(#{longitude},#{latitude})) asc limit #{limit}")
    double predictArpu(@Param("longitude") double longitude,
                       @Param("latitude") double latitude,
                       @Param("limit") int limit);

    @Select("select avg(a.related_user_total) from arpu a where a.arpu != 0 " +
            " order by st_distance_sphere(point(a.gd_longitude, a.gd_latitude), point(#{longitude},#{latitude})) asc limit #{limit}")
    int predictUser(@Param("longitude") double longitude,
                       @Param("latitude") double latitude,
                       @Param("limit") int limit);

    @Select("select max(a.arpu_rank) from arpu a where a.arpu < #{arpu}")
    double rank(@Param("arpu") double arpu);

    @Select("select related_user_count_rank from arpu a where a.related_user_total <= #{count} order by a.related_user_total desc limit 1")
    int rankRelatedUser(@Param("arpu") int count);


}
