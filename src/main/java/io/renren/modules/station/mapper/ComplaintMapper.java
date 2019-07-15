package io.renren.modules.station.mapper;

import io.renren.modules.station.entity.Complaint;
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
public interface ComplaintMapper extends BaseMapper<Complaint> {

    @Select("select count(*) from complaint c where st_distance_sphere(point(c.gd_longitude, c.gd_latitude), point(#{longitude},#{latitude})) <= #{distance}")
    int countAround(@Param("longitude") double longitude,
                       @Param("latitude") double latitude,
                       @Param("distance") int distance /* 距离单位米 */);
}
