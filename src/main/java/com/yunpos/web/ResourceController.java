/*
 * *
 *  * 功能描述：
 *  * <p>
 *  * 版权所有：小牛信息科技有限公司
 *  * <p>
 *  * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *  *
 *  * @author Bino Zhong 新增日期：${date}
 *  * @author Bino Zhong 修改日期：${date}
 *  *
 *
 */

package com.yunpos.web;

import com.yunpos.model.FilterDifinition;
import com.yunpos.model.FilterDifinitionData;
import com.yunpos.model.FilterDifinitionValue;
import com.yunpos.model.Resource;
import com.yunpos.model.viewModel.FilterDifinitionViewModel;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.value.Value;
import com.yunpos.service.FilterDifinitionService;
import com.yunpos.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 功能描述：云铺后台登陆控制器
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年7月21日
 * @author Devin_Yang 修改日期：2015年7月21日
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    private static final int MAX_RESULT=50;

    @Autowired
    ResourceService resourceService;

    @Autowired
    private FilterDifinitionService filterDifinitionService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllResources() {
        List<Resource> resourceList=resourceService.getAllResources();
        return ResponseEntity.ok().body(resourceList);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{id}/filterDifinition")
    public ResponseEntity getAllFilterDifinitions(@PathVariable Integer id){
        List<FilterDifinition> difinitionList=resourceService.getAllFilterDifinitions(id);
        return ResponseEntity.ok().body(difinitionList);
    }

    @RequestMapping(method = RequestMethod.POST,value="/{id}/filter")
    public ResponseEntity addFilter(com.yunpos.model.Filter filter){
        int filterId=resourceService.addFilter(filter);
        if(filterId!=-1){
            return getAddedResponseEntity(filterId);
        }
        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity getAddedResponseEntity(int newObjectId) {
        try {
            URI uri=new URI(getRequest().getRequestURI());
            URI createdFilterDifinitionURI=new URI(uri.getPath()+"/"+newObjectId);
            return ResponseEntity.created(uri).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,value="/{id}/filter")
    public ResponseEntity updateFilter(com.yunpos.model.Filter filter){
        resourceService.updateFilter(filter);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}/filter/{filterId}")
    public ResponseEntity removeFilter(@PathVariable Integer filterId,HttpServletRequest request,HttpServletResponse response){
        resourceService.removeFilter(filterId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST,value="/{id}/filterGroups")
    public ResponseEntity addFilterGroup(com.yunpos.model.FilterGroup filterGroup){
        int gid=resourceService.addFilterGroup(filterGroup);
        if(gid==-1){
            return ResponseEntity.badRequest().build();
        }
        return getAddedResponseEntity(gid);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}/filterGroup/{filterGroupId}")
    public ResponseEntity removeFilterGroup(@PathVariable Integer filterGroupId){
        resourceService.removeFilterGroup(filterGroupId);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value="/{id}/difinition")
    public ResponseEntity addFilterDifinition(@PathVariable Integer id,@PathVariable Integer difinitionId,
                                              FilterDifinitionViewModel filterDifinition){
        FilterDifinition difinition=convert(filterDifinition);
        List<FilterDifinitionValue> values=null;
        try {
            values=getFilterDifinitionValues(difinition,difinition.getDataType(), filterDifinition.getValues());
            int difId=filterDifinitionService.addOrUpdateFilterDifinition(difinition,values);
            if(difId==-1){
                return ResponseEntity.notFound().build();
            }else{
                return getAddedResponseEntity(difId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,value="/{id}/difinition/{difinitionId}")
    public ResponseEntity updateFilterDifinition(@PathVariable Integer id,@PathVariable Integer difinitionId,
                                                 FilterDifinitionViewModel filterDifinition){
        FilterDifinition difinition=convert(filterDifinition);
        List<FilterDifinitionValue> values=null;
        try {
            values=getFilterDifinitionValues(difinition,difinition.getDataType(), filterDifinition.getValues());
            if(filterDifinitionService.addOrUpdateFilterDifinition(difinition,values)==-1){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.accepted().build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }
    private List<FilterDifinitionValue> getFilterDifinitionValues(FilterDifinition difinition,Value.DataType dataType,
                                                                  List<FilterDifinitionViewModel.ValueViewModel> list) throws ParseException, IOException {
        List<FilterDifinitionValue> valueList=new ArrayList<>();
        FilterDifinitionViewModel.ValueViewModel value=null;
        for(FilterDifinitionViewModel.ValueViewModel v:list){
            Value vv=new Value(dataType,v.getData());
            valueList.add(new FilterDifinitionValue(v.getId(),difinition,vv.toJson()));
        }
        return valueList;
    }
    private Value convert(Value.DataType dataType,Object value) throws ParseException {
        if(dataType== Value.DataType.INT || dataType== Value.DataType.STRING){
            return new Value(dataType,value);
        }else{
            return new Value(dataType,Value.dateFormat.parse((String)value));
        }
    }
    private FilterDifinition convert(FilterDifinitionViewModel filterDifinition){
        FilterDifinition difinition=new FilterDifinition();
        difinition.setId(filterDifinition.getId());
        difinition.setResource_id(filterDifinition.getResource_id());
        difinition.setName(filterDifinition.getName());
        difinition.setDataType(Value.DataType.fromCode(filterDifinition.getDataType()));
        difinition.setValueType(FilterDifinitionData.ValueType.fromCode(filterDifinition.getValueType()));
        difinition.setTable_name(filterDifinition.getTable_name());
        difinition.setCol_name(filterDifinition.getCol_name());
        difinition.setKey(filterDifinition.getKey());
        int supportOpCode=0;
        for(Integer code:filterDifinition.getSupportOpCodes()){
            supportOpCode=Filter.Op.setOp(supportOpCode, code);
        }
        difinition.setSupport_op_code(supportOpCode);
        return difinition;
    }

    private int getSupportOpCode(List<Integer> supportOpCodes){
        int supportOpCode=0;
        for(Integer code:supportOpCodes){
            supportOpCode=Filter.Op.setOp(supportOpCode,code);
        }
        return supportOpCode;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value="/{id}/difinition/{difinitionId}")
    public ResponseEntity removeFilterDifinition(@PathVariable Integer difinitionId){
        Integer id=filterDifinitionService.removeFilterDifinition(difinitionId);
        if(difinitionId==id){
            return ResponseEntity.ok().body("message:filter difinition "+difinitionId+" remove successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{id}/difinition/{difinitionId}")
    public ResponseEntity getFilterValue(@PathVariable Integer difinitionId){

        try {
            return new ResponseEntity(getFilterValue(difinitionId,0,MAX_RESULT), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{id}/difinition/{difinitionId}/slice")
    public ResponseEntity getFilterValue(@PathVariable Integer difinitionId,HttpServletRequest request) {
        String offsetParam=request.getParameter("offset"),limitParam=request.getParameter("limit");
        int offset=offsetParam==null?0:Integer.valueOf(offsetParam);
        int limit=limitParam== null ? MAX_RESULT : Math.max(Integer.valueOf(limitParam),MAX_RESULT);
        try {
            return new ResponseEntity(getFilterValue(difinitionId,offset,limit),HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    public List<Value> getFilterValue(Integer difinitionId,Integer offset,Integer limit) throws IOException, ParseException {
        return filterDifinitionService.getValues(difinitionId,offset,limit);
    }

}