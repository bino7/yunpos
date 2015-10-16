package com.yunpos.card.weixin;

import java.util.Collection;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jackylian
 */
public class WxCardBaseInfo
{
	public String title ;// "132元双人火锅套餐",
    public String sub_title;// "周末狂欢必备",
    public String custom_url_name;// "立即使用",
    public String custom_url;// "http://www.qq.com",
    public String custom_url_sub_title;// "6个汉字tips",
    public String promotion_url_name;// "更多优惠",
    public String promotion_url;// "http://www.qq.com",
    public String source;// "大众点评"   
	
    org.json.JSONObject m_data;

    public WxCardBaseInfo()
    {
        m_data = new org.json.JSONObject();
        m_data.put("date_info", new org.json.JSONObject());
        m_data.put("location_id_list", new org.json.JSONArray());
        m_data.put("sku", new org.json.JSONObject());
    }

    public String toJsonString()
    {
        return m_data.toString();
    }

    public String toString()
    {
        return toJsonString();
    }

    public void setLogoUrl(String logoUrl)
    {
        m_data.put("logo_url", logoUrl);
    }

    public String getLogoUrl()
    {
        return m_data.optString("logo_url");
    }

    public static int CODE_TYPE_TEXT = 0;
    public static int CODE_TYPE_BARCODE = 1;
    public static int CODE_TYPE_QRCODE = 2;

    public void setCodeType(int code)
    {
        m_data.put("code_type", code);
    }

    public int getCodeType()
    {
        return m_data.optInt("code_type");
    }

    public void setBrandName(String name)
    {
        m_data.put("brand_name", name);
    }

    public String GetBrandName()
    {
        return m_data.optString("brand_name");
    }

    public void setTitle(String title)
    {
        m_data.put("title", title);
    }

    public String getTitle()
    {
        return m_data.optString("title");
    }

    public void setSubTitle(String subTitle)
    {
        m_data.put("sub_title", subTitle);
    }

    public String getSubTitle()
    {
        return m_data.optString("sub_title");
    }

    public void setDateInfoTimeRange(Date beginTime, Date endTime)
    {
        setDateInfoTimeRange(beginTime.getTime() / 1000, endTime.getTime() / 1000);
    }

    public void setDateInfoTimeRange(long beginTimestamp, long endTimestamp)
    {
        getDateInfo().put("type", 1);
        getDateInfo().put("begin_timestamp", beginTimestamp);
        getDateInfo().put("end_timestamp", endTimestamp);
    }

    public void setDateInfoFixTerm(int fixedTerm)
    {
        setDateInfoFixTerm(fixedTerm, 0);
    }

    public void setDateInfoFixTerm(int fixedTerm, int fixedBeginTerm) //fixedBeginTerm是领取后多少天开始生效
    {
        getDateInfo().put("type", 2);
        getDateInfo().put("fixed_term", fixedTerm);
        getDateInfo().put("fixed_begin_term", fixedBeginTerm);
    }

    public org.json.JSONObject getDateInfo()
    {
        return m_data.optJSONObject("date_info");
    }

    public void setColor(String color)
    {
        m_data.put("color", color);
    }

    public String getColor()
    {
        return m_data.optString("color");
    }

    public void setNotice(String notice)
    {
        m_data.put("notice", notice);
    }

    public String getNotice()
    {
        return m_data.optString("notice");
    }

    public void setServicePhone(String phone)
    {
        m_data.put("service_phone", phone);
    }

    public String getServicePhone()
    {
        return m_data.optString("service_phone");
    }

    public void setDescription(String desc)
    {
        m_data.put("description", desc);
    }

    public String getDescription()
    {
        return m_data.optString("description");
    }

    public void setLocationIdList(Collection<Integer> value)
    {
        org.json.JSONArray array = new org.json.JSONArray();
        value.stream().forEach((integer) ->
        {
            array.put(integer);
        });
        m_data.put("location_id_list", array);
    }
    
    public void addLocationIdList(int locationId)
    {
        getLocationIdList().put(locationId);
    }
    
    public org.json.JSONArray getLocationIdList()
    {
        return m_data.getJSONArray("location_id_list");
    }

    public void setUseLimit(int limit)
    {
        m_data.put("use_limit", limit);
    }

    public int getUseLimit()
    {
        return m_data.optInt("useLimit");
    }

    public void setGetLimit(int limit)
    {
        m_data.put("get_limit", limit);
    }

    public int getGetLimit()
    {
        return m_data.optInt("get_limit");
    }

    public void setCanShare(boolean canShare)
    {
        m_data.put("can_share", canShare);
    }

    public boolean getCanShare()
    {
        return m_data.optBoolean("can_share");
    }

    public void setCanGiveFriend(boolean canGive)
    {
        m_data.put("can_give_friend", canGive);
    }

    public boolean getCanGiveFriend()
    {
        return m_data.optBoolean("can_give_friend");
    }

    public void setUseCustomCode(boolean isUse)
    {
        m_data.put("use_custom_code", isUse);
    }

    public boolean getUseCustomCode()
    {
        return m_data.optBoolean("use_custom_code");
    }

    public void setBindOpenid(boolean isBind)
    {
        m_data.put("bind_openid", isBind);
    }

    public boolean getBindOpenid()
    {
        return m_data.optBoolean("bind_openid");
    }
    
    public void setQuantity(int quantity)
    {
        m_data.optJSONObject("sku").put("quantity", quantity);
    }
   
    public int getQuantity()
    {
        return m_data.optJSONObject("sku").optInt("quantity");
    }

	public String getSub_title() {
		return m_data.optString("sub_title");
	}

	public void setSub_title(String sub_title) {
		 m_data.put("sub_title", sub_title);
	}

	public String getCustom_url_name() {
		return m_data.optString("custom_url_name");
	}

	public void setCustom_url_name(String custom_url_name) {
		m_data.put("custom_url_name", custom_url_name);
	}

	public String getCustom_url() {
		return m_data.optString("custom_url");
	}

	public void setCustom_url(String custom_url) {
		m_data.put("custom_url", custom_url);
	}

	public String getCustom_url_sub_title() {
		return m_data.optString("custom_url_sub_title");
	}

	public void setCustom_url_sub_title(String custom_url_sub_title) {
		m_data.put("custom_url_sub_title", custom_url_sub_title);
	}

	public String getPromotion_url_name() {
		return m_data.optString("promotion_url_name");
	}

	public void setPromotion_url_name(String promotion_url_name) {
		m_data.put("promotion_url_name", promotion_url_name);
	}

	public String getPromotion_url() {
		return m_data.optString("promotion_url");
	}

	public void setPromotion_url(String promotion_url) {
		m_data.put("promotion_url", promotion_url);
	}

	public String getSource() {
		return m_data.optString("source");
	}

	public void setSource(String source) {
		m_data.put("source", source);
	}
    
    
}
