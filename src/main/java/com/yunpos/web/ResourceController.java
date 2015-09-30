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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpos.model.FilterDifinition;
import com.yunpos.model.FilterDifinitionValue;
import com.yunpos.model.Resource;
import com.yunpos.model.viewModel.FilterDifinitionViewModel;
import com.yunpos.model.viewModel.FilterViewModel;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.value.Value;
import com.yunpos.service.FilterDifinitionService;
import com.yunpos.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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
@RestController
@RequestMapping("/resource")
public class ResourceController{
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    private static final int MAX_RESULT=50;

    @Autowired
    ResourceService resourceService;

    @Autowired
    private FilterDifinitionService filterDifinitionService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addResource(@RequestBody  Resource resource) {
        int id=resourceService.addResource(resource);
        resource.setId(id);
        return ResponseEntity.created(URI.create("/resource/" + id)).body(resource);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllResources() {
        List<Resource> resourceList=resourceService.getAllResources();
        return ResponseEntity.ok().body(resourceList);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    public ResponseEntity getResource(@PathVariable Integer id) {
        Resource resource=resourceService.getResource(id);
        return ResponseEntity.ok().body(resource);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public ResponseEntity removeResource(@PathVariable Integer id) {
        int ret=resourceService.removeResource(id);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateResource(@RequestBody Resource res) {
        int ret=resourceService.updateResource(res);
        return ResponseEntity.ok().body(res);
    }


    @RequestMapping(method = RequestMethod.GET,value="/{id}/filterDifinition")
    public ResponseEntity getAllFilterDifinitions(@PathVariable Integer id){
        List<FilterDifinition> difinitionList=resourceService.getAllFilterDifinitions(id);
        List viewmodels=difinitionList.stream().map(d -> {
            FilterDifinitionViewModel v =new FilterDifinitionViewModel();
            v.setColName(d.getColName());
            v.setDataType(d.getDataType().getCode());
            v.setId(d.getId());
            v.setType(d.getType().getCode());
            v.setKeyColumn(d.getKeyColumn());
            v.setResource_id(d.getResourceId());
            Map<String,Boolean> supportOp=new HashMap();
            Filter.Op[] allOp=Filter.Op.values();
            for(int i=0;i<allOp.length;i++){
                supportOp.put(allOp[i].name(),false);
            }
            d.getSupportOp().forEach(op->
                supportOp.replace(op.name(),true)
            );
            v.setSupportOp(supportOp);
            v.setValueType(d.getValueType().getCode());
            v.setKeyParam(d.getKeyParam());
            ObjectMapper mapper=new ObjectMapper();
            try {
                logger.info("values json "+d.getValues());
                logger.info("values list "+mapper.readValue(d.getValues(),List.class).size());
                v.setValues(mapper.readValue(d.getValues(),List.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return v;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(viewmodels);
    }

    @RequestMapping(method = RequestMethod.POST,value="/{id}/filter")
    public ResponseEntity addFilter(@PathVariable Integer id,@RequestBody FilterViewModel vFilter){

        int filterId=resourceService.addFilter(id, viewToFilter(vFilter));
        if(filterId!=-1){
            return ResponseEntity.created(URI.create("/resource/"+id+"/filter/" + filterId)).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.PUT,value="/{id}/filter")
    public ResponseEntity updateFilter(@RequestBody FilterViewModel vFilter){
        resourceService.updateFilter(viewToFilter(vFilter));
        return ResponseEntity.accepted().build();
    }

    private com.yunpos.model.Filter viewToFilter(FilterViewModel vFilter){
        com.yunpos.model.Filter filter=new com.yunpos.model.Filter();
        filter.setId(vFilter.getId());
        filter.setDifinitionId(vFilter.getDifinitionId());
                filter.setFilterValue(vFilter.getFilterValue());
                        filter.setGroupId(vFilter.getGroupId());
        filter.setOp(vFilter.getOp());
                filter.setResourceId(vFilter.getResourceId());
        return filter;

    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}/filter/{filterId}")
    public ResponseEntity removeFilter(@PathVariable Integer id,@PathVariable Integer filterId,HttpServletRequest request,HttpServletResponse response){
        resourceService.removeFilter(filterId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.POST,value="/{id}/filterGroup")
    public ResponseEntity addFilterGroup(@PathVariable Integer id,@RequestBody com.yunpos.model.FilterGroup filterGroup){
        int gid=resourceService.addFilterGroup(filterGroup);
        if(gid==-1){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("/resource/" + id + "/filterGroup/" + gid)).build();
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/{id}/filterGroup/{filterGroupId}")
    public ResponseEntity removeFilterGroup(@PathVariable Integer filterGroupId){
        if(resourceService.removeFilterGroup(filterGroupId)==-1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value="/{id}/filterDifinition")
    public ResponseEntity addFilterDifinition(@PathVariable Integer id,
                                              @RequestBody FilterDifinitionViewModel filterDifinition){
        FilterDifinition difinition=convert(filterDifinition);
        try {
            int difId=filterDifinitionService.addOrUpdateFilterDifinition(difinition);
            if(difId==-1){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.created(URI.create("/resource/" + id + "/difinition/" + difId)).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,value="/{id}/filterDifinition/{difinitionId}")
    public ResponseEntity updateFilterDifinition(@PathVariable Integer id,@PathVariable Integer difinitionId,
                                                 @RequestBody FilterDifinitionViewModel filterDifinition){
        FilterDifinition difinition=convert(filterDifinition);
        try {
            int difId=filterDifinitionService.addOrUpdateFilterDifinition(difinition);
            if(difId==-1){
                return ResponseEntity.notFound().build();
            }else{
                return ResponseEntity.accepted().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    private List<FilterDifinitionValue> getFilterDifinitionValues(FilterDifinition difinition,Value.DataType dataType,
                                                                  List<String> list) throws ParseException, IOException {
        List<FilterDifinitionValue> valueList=new ArrayList<>();
        FilterDifinitionViewModel.ValueViewModel value=null;
        for(String v:list){
            Value vv=new Value(dataType,v);
            valueList.add(new FilterDifinitionValue(null,difinition,vv.toJson()));
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
        difinition.setResourceId(filterDifinition.getResource_id());
        difinition.setType(FilterDifinition.Type.fromCode(filterDifinition.getType()));
        difinition.setDataType(Value.DataType.fromCode(filterDifinition.getDataType()));
        difinition.setValueType(FilterDifinition.ValueType.fromCode(filterDifinition.getValueType()));
        difinition.setColName(filterDifinition.getColName());
        difinition.setKeyParam(filterDifinition.getKeyParam());
        difinition.setKeyColumn(filterDifinition.getKeyColumn());
        int supportOpCode=0;
        Iterator<Map.Entry<String,Boolean>> iterator=filterDifinition.getSupportOp().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Boolean> en=iterator.next();
            if(en.getValue()){
                Filter.Op op= Filter.Op.valueOf(en.getKey());
                supportOpCode=Filter.Op.setOp(supportOpCode, op.getCode());
            }
        }
        difinition.setSupportOpCode(supportOpCode);
        ObjectMapper mapper=new ObjectMapper();
        try {
            difinition.setValues(mapper.writeValueAsString(filterDifinition.getValues()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
    @RequestMapping(method = RequestMethod.DELETE,value="/{rid}/filterDifinition/{difinitionId}")
    public ResponseEntity removeFilterDifinition(@PathVariable Integer rid, @PathVariable Integer difinitionId){
        Integer id=filterDifinitionService.removeFilterDifinition(difinitionId);
        if(difinitionId==id){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body("message:filter difinition "+difinitionId+" remove successfully");
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value="/{id}/filterDifinition/{difinitionId}/value/slice")
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

    public Values getFilterValue(Integer difinitionId,Integer offset,Integer limit) throws IOException, ParseException {
        int total=filterDifinitionService.getValueCount(difinitionId);
        List<Value> valueList=filterDifinitionService.getValues(difinitionId,offset,limit);
        return new Values(total,valueList);
    }

    private class Values{
        private int total;
        private List<Value> valueList;

        public Values(int total, List<Value> valueList) {
            this.total = total;
            this.valueList = valueList;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Value> getValueList() {
            return valueList;
        }

        public void setValueList(List<Value> valueList) {
            this.valueList = valueList;
        }
    }
}