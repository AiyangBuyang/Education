package com.education.edu.service;

import com.education.edu.domain.Advertise;

import java.util.List;

public interface IAdvertiseService {

    /**
     * 查询广告列表
     *
     * @param advertise 广告信息
     * @return 广告列表
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
     * 新增广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    int insertAdvertise(Advertise advertise);

    /**
     * 修改广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    int updateAdvertise(Advertise advertise);

    /**
     * 批量删除广告信息
     *
     * @param advertiseIds 需要删除的广告ID
     * @return 结果
     */
    int deleteAdvertiseByIds(Long[] advertiseIds);
}
