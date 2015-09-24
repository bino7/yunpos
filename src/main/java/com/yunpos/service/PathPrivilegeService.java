package com.yunpos.service;

import com.yunpos.model.PathPrivileges;
import com.yunpos.model.Privilege;
import com.yunpos.persistence.dao.PrivilegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
@Service
public class PathPrivilegeService {
    @Autowired
    private PrivilegeMapper privilegeMapper;

    public List<PathPrivileges> getAllPathPrivileges(){
        List<Privilege> privilegeList=privilegeMapper.selectAll();
        List<PathPrivileges> ret=new ArrayList();
        PathPrivileges last=null;
        privilegeList.stream().sorted((p1,p2)->
            p1.getPath().compareTo(p2.getPath())
        ).forEach(p -> {
                    PathPrivileges pp = last;
                    if(pp==null || pp.getPath().equals(p.getPath())==false){
                        pp=new PathPrivileges();
                    }
                    pp.getAuthorities().add(p.getAuthority());
                }
        );
        return ret;
    }

    public PathPrivileges getPathPrivilege(String path){
        List<Privilege> privilegeList=privilegeMapper.selectByPath(path);
        PathPrivileges pathPrivilege=new PathPrivileges();
        pathPrivilege.setPath(path);
        ArrayList authorities = new ArrayList<>();
        privilegeList.forEach(p -> authorities.add(p.getAuthority()));
        pathPrivilege.setAuthorities(authorities);
        return pathPrivilege;
    }

    @Transactional
    public List<Integer> addPathPrivilege(PathPrivileges pathPrivilege){
        String path=pathPrivilege.getPath();
        List<Integer> ids=new ArrayList<>();
        pathPrivilege.getAuthorities()
                .forEach(auth -> ids.add(privilegeMapper.insert(new Privilege(path, auth))));
        return ids;
    }

    public int addPrivilege(Privilege privilege){
        return privilegeMapper.insert(privilege);
    }

    public void updatPrivilege(Privilege privilege){
        privilegeMapper.updateByPrimaryKey(privilege);
    }

    public void removePathPrivilege(String path){
        privilegeMapper.deleteByPath(path);
    }

    public void removePrivilege(int id){
        privilegeMapper.deleteByPrimaryKey(id);
    }

}
