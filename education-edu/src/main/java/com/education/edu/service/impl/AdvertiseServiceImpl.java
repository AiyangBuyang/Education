package com.education.edu.service.impl;

import com.education.edu.domain.Advertise;
import com.education.edu.mapper.AdvertiseMapper;
import com.education.edu.service.IAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertiseServiceImpl implements IAdvertiseService {

    @Autowired
    private AdvertiseMapper advertiseMapper;

    /**
     * 查询广告列表
     *
     * @param advertise 广告信息
     * @return 广告列表
     */
    @Override
    public List<Advertise> selectAdvertiseList(Advertise advertise) {
        return advertiseMapper.selectAdvertiseList(advertise);
    }

    /**
     * 通过广告ID查询广告信息
     *
     * @param advertiseId 广告ID
     * @return 广告对象信息
     */
    @Override
    public Advertise selectAdvertiseById(Long advertiseId) {
        return advertiseMapper.selectAdvertiseById(advertiseId);
    }

    /**
     * 新增保存广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    @Override
    public int insertAdvertise(Advertise advertise) {
        return advertiseMapper.insertAdvertise(advertise);
    }

    /**
     * 修改保存广告信息
     *
     * @param advertise 广告信息
     * @return 结果
     */
    @Override
    public int updateAdvertise(Advertise advertise) {
        return advertiseMapper.updateAdvertise(advertise);
    }

    /**
     * 批量删除广告信息
     *
     * @param advertiseIds 需要删除的广告ID
     * @return 结果
     */
    @Override
    public int deleteAdvertiseByIds(Long[] advertiseIds) {
        return advertiseMapper.deleteAdvertiseByIds(advertiseIds);
    }
}
