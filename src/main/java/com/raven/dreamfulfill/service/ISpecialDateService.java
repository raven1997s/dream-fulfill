package com.raven.dreamfulfill.service;

import com.raven.dreamfulfill.common.base.PageResult;
import com.raven.dreamfulfill.domain.req.specialdate.AddSpecialDateReq;
import com.raven.dreamfulfill.domain.req.specialdate.PageQuerySpecialDateListReq;
import com.raven.dreamfulfill.domain.req.specialdate.UpdateSpecialDateReq;
import com.raven.dreamfulfill.domain.resp.specialdate.SpecialDateResp;

/**
 * Description:
 * date: 2023/10/11 09:45
 *
 * @author raven
 */
public interface ISpecialDateService {

    /**
     * 分页查询节日列表
     * @param req
     * @return
     */
    PageResult<SpecialDateResp> pageQuerySpecialDateList(PageQuerySpecialDateListReq req);

    /**
     * 新增节日
     * @param req
     */
    void addSpecialDate(AddSpecialDateReq req);

    /**
     * 修改节日
     * @param req
     */
    void updateSpecialDate(UpdateSpecialDateReq req);


    /**
     * 删除节日
     * @param id
     */
    void deleteSpecialDate(Long id);


    /**
     * 生成指定年份的法定节日
     * @param year
     */
    void insertSpecialDateByYear(int year);
}