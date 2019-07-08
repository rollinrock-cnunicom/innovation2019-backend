package io.renren.modules.station.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Arpu对象", description="")
public class Arpu implements Serializable {

private static final long serialVersionUID=1L;

    private String netMngName;

    private Double arpu;

    private Double arpuRank;

    private Double relatedLocalUserCount;

    private Double relatedUserIntake;

    private Double relatedUserTotal;

    private Double relatedUserCountRank;

    private Double communityLatitude;

    private Double communityLongitude;

    private Double gdLatitude;

    private Double gdLongitude;


}
