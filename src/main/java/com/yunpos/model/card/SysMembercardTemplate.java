package com.yunpos.model.card;

public class SysMembercardTemplate {
    private Integer id;

    private String card_type;

    private Integer base_info_id;

    private String prerogative;

    private Byte auto_activate;

    private Byte wx_activate;

    private Byte supply_bonus;

    private String bonus_url;

    private Byte supply_balance;

    private String balance_url;

    private String custom_field1_name_type;

    private String custom_field1_url;

    private String custom_field2_name_type;

    private String custom_field2_url;

    private String custom_field3_name_type;

    private String custom_field3_url;

    private String bonus_cleared;

    private String bonus_rules;

    private String balance_rules;

    private String activate_url;

    private String custom_cell1_name;

    private String custom_cell1_tips;

    private String custom_cell1_url;

    private String bonus_rule;

    private Integer cost_money_unit;

    private Integer increase_bonus;

    private Integer max_increase_bonus;

    private Integer init_increase_bonus;

    private Integer discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type == null ? null : card_type.trim();
    }

    public Integer getBase_info_id() {
        return base_info_id;
    }

    public void setBase_info_id(Integer base_info_id) {
        this.base_info_id = base_info_id;
    }

    public String getPrerogative() {
        return prerogative;
    }

    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative == null ? null : prerogative.trim();
    }

    public Byte getAuto_activate() {
        return auto_activate;
    }

    public void setAuto_activate(Byte auto_activate) {
        this.auto_activate = auto_activate;
    }

    public Byte getWx_activate() {
        return wx_activate;
    }

    public void setWx_activate(Byte wx_activate) {
        this.wx_activate = wx_activate;
    }

    public Byte getSupply_bonus() {
        return supply_bonus;
    }

    public void setSupply_bonus(Byte supply_bonus) {
        this.supply_bonus = supply_bonus;
    }

    public String getBonus_url() {
        return bonus_url;
    }

    public void setBonus_url(String bonus_url) {
        this.bonus_url = bonus_url == null ? null : bonus_url.trim();
    }

    public Byte getSupply_balance() {
        return supply_balance;
    }

    public void setSupply_balance(Byte supply_balance) {
        this.supply_balance = supply_balance;
    }

    public String getBalance_url() {
        return balance_url;
    }

    public void setBalance_url(String balance_url) {
        this.balance_url = balance_url == null ? null : balance_url.trim();
    }

    public String getCustom_field1_name_type() {
        return custom_field1_name_type;
    }

    public void setCustom_field1_name_type(String custom_field1_name_type) {
        this.custom_field1_name_type = custom_field1_name_type == null ? null : custom_field1_name_type.trim();
    }

    public String getCustom_field1_url() {
        return custom_field1_url;
    }

    public void setCustom_field1_url(String custom_field1_url) {
        this.custom_field1_url = custom_field1_url == null ? null : custom_field1_url.trim();
    }

    public String getCustom_field2_name_type() {
        return custom_field2_name_type;
    }

    public void setCustom_field2_name_type(String custom_field2_name_type) {
        this.custom_field2_name_type = custom_field2_name_type == null ? null : custom_field2_name_type.trim();
    }

    public String getCustom_field2_url() {
        return custom_field2_url;
    }

    public void setCustom_field2_url(String custom_field2_url) {
        this.custom_field2_url = custom_field2_url == null ? null : custom_field2_url.trim();
    }

    public String getCustom_field3_name_type() {
        return custom_field3_name_type;
    }

    public void setCustom_field3_name_type(String custom_field3_name_type) {
        this.custom_field3_name_type = custom_field3_name_type == null ? null : custom_field3_name_type.trim();
    }

    public String getCustom_field3_url() {
        return custom_field3_url;
    }

    public void setCustom_field3_url(String custom_field3_url) {
        this.custom_field3_url = custom_field3_url == null ? null : custom_field3_url.trim();
    }

    public String getBonus_cleared() {
        return bonus_cleared;
    }

    public void setBonus_cleared(String bonus_cleared) {
        this.bonus_cleared = bonus_cleared == null ? null : bonus_cleared.trim();
    }

    public String getBonus_rules() {
        return bonus_rules;
    }

    public void setBonus_rules(String bonus_rules) {
        this.bonus_rules = bonus_rules == null ? null : bonus_rules.trim();
    }

    public String getBalance_rules() {
        return balance_rules;
    }

    public void setBalance_rules(String balance_rules) {
        this.balance_rules = balance_rules == null ? null : balance_rules.trim();
    }

    public String getActivate_url() {
        return activate_url;
    }

    public void setActivate_url(String activate_url) {
        this.activate_url = activate_url == null ? null : activate_url.trim();
    }

    public String getCustom_cell1_name() {
        return custom_cell1_name;
    }

    public void setCustom_cell1_name(String custom_cell1_name) {
        this.custom_cell1_name = custom_cell1_name == null ? null : custom_cell1_name.trim();
    }

    public String getCustom_cell1_tips() {
        return custom_cell1_tips;
    }

    public void setCustom_cell1_tips(String custom_cell1_tips) {
        this.custom_cell1_tips = custom_cell1_tips == null ? null : custom_cell1_tips.trim();
    }

    public String getCustom_cell1_url() {
        return custom_cell1_url;
    }

    public void setCustom_cell1_url(String custom_cell1_url) {
        this.custom_cell1_url = custom_cell1_url == null ? null : custom_cell1_url.trim();
    }

    public String getBonus_rule() {
        return bonus_rule;
    }

    public void setBonus_rule(String bonus_rule) {
        this.bonus_rule = bonus_rule == null ? null : bonus_rule.trim();
    }

    public Integer getCost_money_unit() {
        return cost_money_unit;
    }

    public void setCost_money_unit(Integer cost_money_unit) {
        this.cost_money_unit = cost_money_unit;
    }

    public Integer getIncrease_bonus() {
        return increase_bonus;
    }

    public void setIncrease_bonus(Integer increase_bonus) {
        this.increase_bonus = increase_bonus;
    }

    public Integer getMax_increase_bonus() {
        return max_increase_bonus;
    }

    public void setMax_increase_bonus(Integer max_increase_bonus) {
        this.max_increase_bonus = max_increase_bonus;
    }

    public Integer getInit_increase_bonus() {
        return init_increase_bonus;
    }

    public void setInit_increase_bonus(Integer init_increase_bonus) {
        this.init_increase_bonus = init_increase_bonus;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}