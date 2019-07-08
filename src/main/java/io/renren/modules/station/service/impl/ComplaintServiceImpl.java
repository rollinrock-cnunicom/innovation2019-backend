package io.renren.modules.station.service.impl;

import io.renren.modules.station.entity.Complaint;
import io.renren.modules.station.mapper.ComplaintMapper;
import io.renren.modules.station.service.IComplaintService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {

}
