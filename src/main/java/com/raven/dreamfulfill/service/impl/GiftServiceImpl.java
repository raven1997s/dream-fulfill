package com.raven.dreamfulfill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.converter.GiftConverter;
import com.raven.dreamfulfill.domain.entity.Gift;
import com.raven.dreamfulfill.domain.entity.User;
import com.raven.dreamfulfill.domain.enums.IsYesEnum;
import com.raven.dreamfulfill.domain.req.gift.AddGiftReq;
import com.raven.dreamfulfill.domain.req.gift.PageQueryGiftListReq;
import com.raven.dreamfulfill.domain.req.gift.UpdateGiftReq;
import com.raven.dreamfulfill.domain.resp.gift.GiftResp;
import com.raven.dreamfulfill.common.exception.CommonException;
import com.raven.dreamfulfill.mapper.GiftMapper;
import com.raven.dreamfulfill.mapper.UserMapper;
import com.raven.dreamfulfill.service.IGiftService;
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
public class GiftServiceImpl implements IGiftService {

    @Autowired
    private GiftConverter giftConverter;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<GiftResp> pageQueryGiftList(PageQueryGiftListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<Gift> giftList = giftMapper.selectByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andNotEqualTo(Gift::getIsDelete, IsYesEnum.NO.getVal()))
                .orderByDesc("id")
                .build());
        PageInfo<Gift> pageInfo = new PageInfo<>(giftList);

        return CollectionUtils.isEmpty(giftList) ? new PageResult<>(Collections.emptyList())
                : new PageResult<>(this.convertGiftListToGiftRespList(giftList),
                pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    @Override
    public void addGift(AddGiftReq req) {

        int count = giftMapper.selectCountByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andEqualTo(Gift::getGiftName, req.getGiftName()))
                .build());
        if (count > 0) {
            throw new CommonException("礼物名称已存在~");
        }

        Gift gift = giftConverter.giftAddReqToGiftEntity(req);

        giftMapper.insertSelective(gift);
    }

    @Override
    public void updateGift(UpdateGiftReq req) {
        int count = giftMapper.selectCountByExample(Example.builder(Gift.class)
                .where(WeekendSqls.<Gift>custom()
                        .andEqualTo(Gift::getGiftName, req.getGiftName())
                        .andNotEqualTo(Gift::getId, req.getId()))
                .build());
        if (count > 0) {
            throw new CommonException("礼物名称已存在~");
        }

        Gift gift = giftConverter.giftUpdateReqToGiftEntity(req);
        giftMapper.updateByPrimaryKey(gift);
    }

    @Override
    public void deleteGift(Long id) {
        Gift gift = giftMapper.selectByPrimaryKey(id);
        if (gift == null) {
            throw new CommonException("礼物已删除~");
        }

        gift.setIsDelete(IsYesEnum.YES.getVal());
        giftMapper.updateByPrimaryKey(gift);
    }

    private List<GiftResp> convertGiftListToGiftRespList(List<Gift> giftList) {
        List<User> userList = userMapper.selectAll();
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        return giftList.stream().map(gift -> {
            GiftResp giftResp = giftConverter.giftEntityToGiftResp(gift);
            giftResp.setGiftName(userMap.get(gift.getCreateId()).getName());
            return giftResp;
        }).collect(Collectors.toList());
    }
}