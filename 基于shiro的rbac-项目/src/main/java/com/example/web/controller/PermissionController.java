package com.example.web.controller;

import com.example.entity.Permission;
import com.example.qo.SearchQuery;
import com.example.qo.TablePageInfo;
import com.example.service.IPermissionService;
import com.example.until.JsonResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //进入权限页面
    @RequestMapping("/list")
    public String getList() {
        return "permission/list";
    }

    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=permissionData.xlsx");
        Workbook is = permissionService.export();
        is.write(response.getOutputStream());
        is.close();
    }

    @RequestMapping("/noPermission")
    public String noPermission() {
        return "common/nopermission";
    }

    @RequestMapping("/importExcel")
    @ResponseBody
    public JsonResult importExcel(MultipartFile file){
        System.out.println(file);
        try{
            int count = permissionService.importExcel(file.getInputStream());
            return new JsonResult(true,"成功导入:"+count+"条记录");
        }catch(Exception ex){
            ex.printStackTrace();
            return new JsonResult(false,"导入数据失败");
        }
    }

    @RequestMapping("/listData")
    @ResponseBody
    public TablePageInfo listData(SearchQuery qo)
    {
        return permissionService.query(qo);
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Permission permission){
        if(permission.getId() != null){
            // 编辑
            permissionService.edit(permission);
        } else {
            // 新增
            permissionService.add(permission);
        }
        return new JsonResult(true);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        System.out.println(id);
        if (id != null) {
            try{
                int i = permissionService.delById(id);
                if (i != 0) {
                    return new JsonResult(true,"删除成功");
                }
                else
                    return new JsonResult(false,"删除失败");
            }catch (Exception e){
                e.printStackTrace();
                return new JsonResult(false,"删除失败");
            }
        }
       return new JsonResult(false,"删除失败");
    }
}
