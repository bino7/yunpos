package com.yunpos.web;

import com.yunpos.model.PathPrivileges;
import com.yunpos.model.Privilege;
import com.yunpos.service.PathPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/9/2
 * @author bino 修改日期：2015/9/2
 */
@Controller
@RequestMapping("/pathPrivilege")
public class PathPrivilegeController {
    @Autowired
    private PathPrivilegeService pathPrivilegeService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllPathPrivileges(){
        List<PathPrivileges> pathPrivilegesList=pathPrivilegeService.getAllPathPrivileges();
        return ResponseEntity.ok(pathPrivilegesList);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{path}")
    public ResponseEntity getPathPrivilege(@PathVariable String path){
        PathPrivileges pathPrivileges=pathPrivilegeService.getPathPrivilege(path);
        return ResponseEntity.ok(pathPrivileges);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addPathPrivilege(PathPrivileges pathPrivilege){
        List<Integer> ids=pathPrivilegeService.addPathPrivilege(pathPrivilege);
        return ResponseEntity.created(URI.create("/pathPrivilege/"+pathPrivilege.getPath())).build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value="/{path}")
    public ResponseEntity addPrivilege(Privilege privilege){
        int id=pathPrivilegeService.addPrivilege(privilege);
        return ResponseEntity.created(URI.create("/pathPrivilege/"+privilege.getPath()+"/"+id)).build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updatPrivilege(Privilege privilege){
        pathPrivilegeService.updatPrivilege(privilege);
        return ResponseEntity.accepted().build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value="/{path}")
    public ResponseEntity removePathPrivilege(@PathVariable String path){
        pathPrivilegeService.removePathPrivilege(path);
        return ResponseEntity.ok("remove success");
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value="/{path}/{id}")
    public void removePrivilege(@PathVariable Integer id){
        pathPrivilegeService.removePrivilege(id);
    }
}
