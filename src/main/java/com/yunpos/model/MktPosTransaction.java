package com.yunpos.model;

import java.util.Date;

import com.yunpos.utils.jqgrid.GridRequest;

/**
 * 
 * 功能描述：交易流水
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月9日
 * @author tiger_lin 修改日期：2015年9月9日
 *
 */
public class MktPosTransaction extends GridRequest {
	
	private Integer id;
	 
    private String tid;

    private String mid;

    private String txn_id;

    private String txn_name;

    private String pan;

    private Double txn_amt;

    private String systrace;

    private Date txn_time;

    private String rrn;

    private String batch_no;

    private String auth_code;

    private String order_no;

    private String acc_type;

    private String txn_type;

    private Date create_time;

    private String create_by;
    

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id == null ? null : txn_id.trim();
    }

    public String getTxn_name() {
        return txn_name;
    }

    public void setTxn_name(String txn_name) {
        this.txn_name = txn_name == null ? null : txn_name.trim();
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public Double getTxn_amt() {
        return txn_amt;
    }

    public void setTxn_amt(Double txn_amt) {
        this.txn_amt = txn_amt;
    }

    public Date getTxn_time() {
        return txn_time;
    }

    public void setTxn_time(Date txn_time) {
        this.txn_time = txn_time;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn == null ? null : rrn.trim();
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code == null ? null : auth_code.trim();
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no == null ? null : order_no.trim();
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type == null ? null : acc_type.trim();
    }

    public String getTxn_type() {
        return txn_type;
    }

    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type == null ? null : txn_type.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by == null ? null : create_by.trim();
    }
    
    public String getSystrace() {
        return systrace;
    }

    public void setSystrace(String systrace) {
        this.systrace = systrace == null ? null : systrace.trim();
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no == null ? null : batch_no.trim();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}