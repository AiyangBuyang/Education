package com.education.web.controller.edu;

import com.education.common.annotation.Log;
import com.education.common.config.RuoYiConfig;
import com.education.common.core.controller.BaseController;
import com.education.common.core.domain.AjaxResult;
import com.education.common.core.page.TableDataInfo;
import com.education.common.enums.BusinessType;
import com.education.common.utils.file.FileUploadUtils;
import com.education.common.utils.file.MimeTypeUtils;
import com.education.common.utils.poi.ExcelUtil;
import com.education.edu.domain.Advertise;
import com.education.edu.service.IAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/edu/advertise")
public class AdvertiseController extends BaseController {

    @Autowired
    private IAdvertiseService advertiseService;

    /**
     * 获取广告列表
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Advertise advertise) {
        startPage();
        List<Advertise> advertises = advertiseService.selectAdvertiseList(advertise);
        return getDataTable(advertises);
    }

    /**
     * 根据广告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:query')")
    @GetMapping(value = "/{advertiseId}")
    public AjaxResult getInfo(@PathVariable Long advertiseId) {
        return success(advertiseService.selectAdvertiseById(advertiseId));
    }

    /**
     * 新增广告信息
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:add')")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Advertise advertise) {
        advertise.setCreateBy(getUsername());
        return toAjax(advertiseService.insertAdvertise(advertise));
    }

    /**
     * 修改广告信息
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:edit')")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Advertise advertise) {
        advertise.setUpdateBy(getUsername());
        return toAjax(advertiseService.updateAdvertise(advertise));
    }

    /**
     * 删除广告信息
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:remove')")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{advertiseIds}")
    public AjaxResult remove(@PathVariable Long[] advertiseIds) {
        return toAjax(advertiseService.deleteAdvertiseByIds(advertiseIds));
    }

    /**
     * 导出广告信息
     */
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('edu:advertise:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Advertise advertise) {
        List<Advertise> list = advertiseService.selectAdvertiseList(advertise);
        ExcelUtil<Advertise> util = new ExcelUtil<>(Advertise.class);
        util.exportExcel(response, list, "广告数据");
    }

    /**
     * 处理带有文件的表单
     */
    @PreAuthorize("@ss.hasPermi('edu:advertise:edit')")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file, Advertise advertise) throws Exception {
        if (!file.isEmpty()) {
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
            advertise.setAdImg(advertise.getApi() + avatar);
        }
        if (advertise.getAdId() == null) {
            advertise.setCreateBy(getUsername());
            return toAjax(advertiseService.insertAdvertise(advertise));
        }
        if (advertise.getAdId() != null) {
            advertise.setUpdateBy(getUsername());
            return toAjax(advertiseService.updateAdvertise(advertise));
        }
        return AjaxResult.error();
    }
}
