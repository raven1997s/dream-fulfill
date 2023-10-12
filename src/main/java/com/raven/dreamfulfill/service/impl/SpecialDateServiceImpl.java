package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.converter.SpecialDateConverter;
import com.raven.dreamfulfill.domain.entity.SpecialDate;
import com.raven.dreamfulfill.domain.entity.User;
import com.raven.dreamfulfill.domain.enums.IsYesEnum;
import com.raven.dreamfulfill.domain.req.specialdate.AddSpecialDateReq;
import com.raven.dreamfulfill.domain.req.specialdate.PageQuerySpecialDateListReq;
import com.raven.dreamfulfill.domain.req.specialdate.UpdateSpecialDateReq;
import com.raven.dreamfulfill.domain.resp.specialdate.SpecialDateResp;
import com.raven.dreamfulfill.common.exception.CommonException;
import com.raven.dreamfulfill.mapper.SpecialDateMapper;
import com.raven.dreamfulfill.mapper.UserMapper;
import com.raven.dreamfulfill.service.ISpecialDateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
@Service
@Slf4j
public class SpecialDateServiceImpl implements ISpecialDateService {

    @Autowired
    private SpecialDateConverter specialDateConverter;
    @Autowired
    private SpecialDateMapper specialDateMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<SpecialDateResp> pageQuerySpecialDateList(PageQuerySpecialDateListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<SpecialDate> specialDateList = specialDateMapper.selectByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andNotEqualTo(SpecialDate::getIsDelete, IsYesEnum.NO.getVal()))
                .orderByDesc("id")
                .build());
        PageInfo<SpecialDate> pageInfo = new PageInfo<>(specialDateList);

        return CollectionUtils.isEmpty(specialDateList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertSpecialDateListToSpecialDateRespList(specialDateList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());

    }

    @Override
    public void addSpecialDate(AddSpecialDateReq req) {
        int count = specialDateMapper.selectCountByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andEqualTo(SpecialDate::getHolidayName, req.getHolidayName()))
                .build());
        if (count > 0) {
            throw new CommonException("节日名称已存在~");
        }

        SpecialDate specialDate = specialDateConverter.specialDateAddReqToSpecialDateEntity(req);

        specialDateMapper.insertSelective(specialDate);
    }

    @Override
    public void updateSpecialDate(UpdateSpecialDateReq req) {
        int count = specialDateMapper.selectCountByExample(Example.builder(SpecialDate.class)
                .where(WeekendSqls.<SpecialDate>custom()
                        .andEqualTo(SpecialDate::getHolidayName, req.getHolidayName())
                        .andNotEqualTo(SpecialDate::getId, req.getId()))
                .build());
        if (count > 0) {
            throw new CommonException("节日名称已存在~");
        }

        SpecialDate specialDate = specialDateConverter.specialDateUpdateReqToSpecialDateEntity(req);
        specialDateMapper.updateByPrimaryKey(specialDate);
    }

    @Override
    public void deleteSpecialDate(Long id) {
        SpecialDate specialDate = specialDateMapper.selectByPrimaryKey(id);
        if (specialDate == null) {
            throw new CommonException("节日已删除~");
        }

        specialDate.setIsDelete(IsYesEnum.YES.getVal());
        specialDateMapper.updateByPrimaryKey(specialDate);
    }

    private List<SpecialDateResp> convertSpecialDateListToSpecialDateRespList(List<SpecialDate> specialDateList) {
        List<User> userList = userMapper.selectAll();
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        return specialDateList.stream().map(specialDate -> {
            SpecialDateResp specialDateResp = specialDateConverter.specialDateEntityToSpecialDateResp(specialDate);
            specialDateResp.setCreateUserName(userMap.get(specialDate.getCreateId()).getName());
            return specialDateResp;
        }).collect(Collectors.toList());
    }
}