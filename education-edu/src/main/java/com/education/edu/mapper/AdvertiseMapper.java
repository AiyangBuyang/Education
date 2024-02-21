package com.education.edu.mapper;

import com.education.edu.domain.Advertise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdvertiseMapper {

    /**
     * 根据条件分页查询广告列表
     *
     * @param advertise 广告信息
     * @return 广告信息集合信息
     */
    List<Advertise> selectAdvertiseList(Advertise advertise);

    /**
     * 通过广告ID查询广告信息
     *
     * @param advertiseId 广告ID
     * @return 广告对象信息
     */
    Advertise selectAdvertiseById(Long advertiseId);

    /**
     * 修改广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    int updateAdvertise(Advertise advertise);

    /**
     * 新增广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    int insertAdvertise(Advertise advertise);

    /**
     * 批量删除广告信息
     *
     * @param advertiseIds 需要删除的广告ID
     * @return 结果
     */
    int deleteAdvertiseByIds(Long[] advertiseIds);
}
