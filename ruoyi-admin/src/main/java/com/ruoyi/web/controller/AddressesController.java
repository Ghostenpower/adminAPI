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
import com.ruoyi.system.domain.Addresses;
import com.ruoyi.system.service.IAddressesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地址信息Controller
 * 
 * @author ruoyi
 * @date 2025-05-07
 */
@RestController
@RequestMapping("/system/addresses")
public class AddressesController extends BaseController
{
    @Autowired
    private IAddressesService addressesService;

    /**
     * 查询地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:list')")
    @GetMapping("/list")
    public TableDataInfo list(Addresses addresses)
    {
        startPage();
        List<Addresses> list = addressesService.selectAddressesList(addresses);
        return getDataTable(list);
    }

    /**
     * 导出地址信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:export')")
    @Log(title = "地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Addresses addresses)
    {
        List<Addresses> list = addressesService.selectAddressesList(addresses);
        ExcelUtil<Addresses> util = new ExcelUtil<Addresses>(Addresses.class);
        util.exportExcel(response, list, "地址信息数据");
    }

    /**
     * 获取地址信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return success(addressesService.selectAddressesByAddressId(addressId));
    }

    /**
     * 新增地址信息
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:add')")
    @Log(title = "地址信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Addresses addresses)
    {
        return toAjax(addressesService.insertAddresses(addresses));
    }

    /**
     * 修改地址信息
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:edit')")
    @Log(title = "地址信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Addresses addresses)
    {
        return toAjax(addressesService.updateAddresses(addresses));
    }

    /**
     * 删除地址信息
     */
    @PreAuthorize("@ss.hasPermi('system:addresses:remove')")
    @Log(title = "地址信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(addressesService.deleteAddressesByAddressIds(addressIds));
    }
}
