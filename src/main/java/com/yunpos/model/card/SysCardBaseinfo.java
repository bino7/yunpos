package com.yunpos.model.card;

public class SysCardBaseinfo {
    private Integer id;

    private String logo_url;

    private String code_type;

    private String brand_name;

    private String title;

    private String sub_title;

    private String color;

    private String notice;

    private String description;

    private String sku_quantity;

    private String date_info_type;

    private Integer begin_timestamp;

    private Integer end_timestamp;

    private Integer fixed_term;

    private Integer fixed_begin_term;

    private Byte use_custom_code;

    private Byte bind_openid;

    private String service_phone;

    private String location_id_list;

    private String source;

    private String custom_url_name;

    private String custom_url;

    private String custom_url_sub_title;

    private String promotion_url_name;

    private String promotion_url;

    private String promotion_url_sub_title;

    private Integer get_limit;

    private Byte can_share;

    private Byte can_give_friend;

    private Byte need_push_on_view;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url == null ? null : logo_url.trim();
    }

    public String getCode_type() {
        return code_type;
    }

    public void setCode_type(String code_type) {
        this.code_type = code_type == null ? null : code_type.trim();
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name == null ? null : brand_name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title == null ? null : sub_title.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSku_quantity() {
        return sku_quantity;
    }

    public void setSku_quantity(String sku_quantity) {
        this.sku_quantity = sku_quantity == null ? null : sku_quantity.trim();
    }

    public String getDate_info_type() {
        return date_info_type;
    }

    public void setDate_info_type(String date_info_type) {
        this.date_info_type = date_info_type == null ? null : date_info_type.trim();
    }

    public Integer getBegin_timestamp() {
        return begin_timestamp;
    }

    public void setBegin_timestamp(Integer begin_timestamp) {
        this.begin_timestamp = begin_timestamp;
    }

    public Integer getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(Integer end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public Integer getFixed_term() {
        return fixed_term;
    }

    public void setFixed_term(Integer fixed_term) {
        this.fixed_term = fixed_term;
    }

    public Integer getFixed_begin_term() {
        return fixed_begin_term;
    }

    public void setFixed_begin_term(Integer fixed_begin_term) {
        this.fixed_begin_term = fixed_begin_term;
    }

    public Byte getUse_custom_code() {
        return use_custom_code;
    }

    public void setUse_custom_code(Byte use_custom_code) {
        this.use_custom_code = use_custom_code;
    }

    public Byte getBind_openid() {
        return bind_openid;
    }

    public void setBind_openid(Byte bind_openid) {
        this.bind_openid = bind_openid;
    }

    public String getService_phone() {
        return service_phone;
    }

    public void setService_phone(String service_phone) {
        this.service_phone = service_phone == null ? null : service_phone.trim();
    }

    public String getLocation_id_list() {
        return location_id_list;
    }

    public void setLocation_id_list(String location_id_list) {
        this.location_id_list = location_id_list == null ? null : location_id_list.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getCustom_url_name() {
        return custom_url_name;
    }

    public void setCustom_url_name(String custom_url_name) {
        this.custom_url_name = custom_url_name == null ? null : custom_url_name.trim();
    }

    public String getCustom_url() {
        return custom_url;
    }

    public void setCustom_url(String custom_url) {
        this.custom_url = custom_url == null ? null : custom_url.trim();
    }

    public String getCustom_url_sub_title() {
        return custom_url_sub_title;
    }

    public void setCustom_url_sub_title(String custom_url_sub_title) {
        this.custom_url_sub_title = custom_url_sub_title == null ? null : custom_url_sub_title.trim();
    }

    public String getPromotion_url_name() {
        return promotion_url_name;
    }

    public void setPromotion_url_name(String promotion_url_name) {
        this.promotion_url_name = promotion_url_name == null ? null : promotion_url_name.trim();
    }

    public String getPromotion_url() {
        return promotion_url;
    }

    public void setPromotion_url(String promotion_url) {
        this.promotion_url = promotion_url == null ? null : promotion_url.trim();
    }

    public String getPromotion_url_sub_title() {
        return promotion_url_sub_title;
    }

    public void setPromotion_url_sub_title(String promotion_url_sub_title) {
        this.promotion_url_sub_title = promotion_url_sub_title == null ? null : promotion_url_sub_title.trim();
    }

    public Integer getGet_limit() {
        return get_limit;
    }

    public void setGet_limit(Integer get_limit) {
        this.get_limit = get_limit;
    }

    public Byte getCan_share() {
        return can_share;
    }

    public void setCan_share(Byte can_share) {
        this.can_share = can_share;
    }

    public Byte getCan_give_friend() {
        return can_give_friend;
    }

    public void setCan_give_friend(Byte can_give_friend) {
        this.can_give_friend = can_give_friend;
    }

    public Byte getNeed_push_on_view() {
        return need_push_on_view;
    }

    public void setNeed_push_on_view(Byte need_push_on_view) {
        this.need_push_on_view = need_push_on_view;
    }
}