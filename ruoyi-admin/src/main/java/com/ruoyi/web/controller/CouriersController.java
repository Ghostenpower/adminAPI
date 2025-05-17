package com.ruoyi.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Couriers;
import com.ruoyi.system.service.ICouriersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 跑腿员信息Controller
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@RestController
@RequestMapping("/system/couriers")
public class CouriersController extends BaseController
{
    @Autowired
    private ICouriersService couriersService;

    /**
     * 查询跑腿员信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:list')")
    @GetMapping("/list")
    public TableDataInfo list(Couriers couriers)
    {
        startPage();
        List<Couriers> list = couriersService.selectCouriersList(couriers);
        return getDataTable(list);
    }

    /**
     * 导出跑腿员信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:export')")
    @Log(title = "跑腿员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Couriers couriers)
    {
        List<Couriers> list = couriersService.selectCouriersList(couriers);
        ExcelUtil<Couriers> util = new ExcelUtil<Couriers>(Couriers.class);
        util.exportExcel(response, list, "跑腿员信息数据");
    }

    /**
     * 获取跑腿员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:query')")
    @GetMapping(value = "/{courierId}")
    public AjaxResult getInfo(@PathVariable("courierId") Long courierId)
    {
        return success(couriersService.selectCouriersByCourierId(courierId));
    }

    /**
     * 新增跑腿员信息
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:add')")
    @Log(title = "跑腿员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Couriers couriers)
    {
        return toAjax(couriersService.insertCouriers(couriers));
    }

    /**
     * 修改跑腿员信息
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:edit')")
    @Log(title = "跑腿员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Couriers couriers)
    {
        return toAjax(couriersService.updateCouriers(couriers));
    }

    /**
     * 删除跑腿员信息
     */
    @PreAuthorize("@ss.hasPermi('system:couriers:remove')")
    @Log(title = "跑腿员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{courierIds}")
    public AjaxResult remove(@PathVariable Long[] courierIds)
    {
        return toAjax(couriersService.deleteCouriersByCourierIds(courierIds));
    }

    @PreAuthorize("@ss.hasPermi('system:riderReview:list')")
    @PostMapping("/riderReviewList")
    public TableDataInfo riderReviewList(@RequestBody Couriers couriers){
        startPage();
        List<Couriers> list = couriersService.riderReviewList(couriers);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:riderReview:list')")
    @PostMapping("/riderReviewList/accept")
    public AjaxResult riderReviewListAccept(@RequestBody Couriers couriers){
    	return toAjax(couriersService.riderReviewListAccept(couriers));
    }

    @PreAuthorize("@ss.hasPermi('system:riderReview:list')")
    @PostMapping("/riderReviewList/reject")
    public AjaxResult riderReviewListReject(@RequestBody Couriers couriers){
        return toAjax(couriersService.riderReviewListReject(couriers));
    }
}
