package com.example.synthesis.pojo;

import java.util.Date;

/**
 * 亚马逊广告报告原始数据实体
 */
public class AmazonAdReport {
    private Long id;
    private Date reportDate;
    private String portfolioName;
    private String currency;
    private String campaignName;
    private String adGroupName;
    private String retailer;
    private String country;
    private String placement;
    private String matchType;
    private String searchTerm;
    private Integer impressions;
    private Integer clicks;
    private String ctr;
    private Double cpc;
    private Double spend;
    private Double sales7d;
    private String acos;
    private String roas;
    private Integer orders7d;
    private Integer units7d;
    private String conversionRate7d;
    private Integer adSkuUnits7d;
    private Integer otherSkuUnits7d;
    private Double adSkuSales7d;
    private Double otherSkuSales7d;
    private Date gmtCreate;
    private Date gmtModify;

    // ==================== getter/setter ====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getReportDate() { return reportDate; }
    public void setReportDate(Date reportDate) { this.reportDate = reportDate; }

    public String getPortfolioName() { return portfolioName; }
    public void setPortfolioName(String portfolioName) { this.portfolioName = portfolioName; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String campaignName) { this.campaignName = campaignName; }

    public String getAdGroupName() { return adGroupName; }
    public void setAdGroupName(String adGroupName) { this.adGroupName = adGroupName; }

    public String getRetailer() { return retailer; }
    public void setRetailer(String retailer) { this.retailer = retailer; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPlacement() { return placement; }
    public void setPlacement(String placement) { this.placement = placement; }

    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }

    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }

    public Integer getImpressions() { return impressions; }
    public void setImpressions(Integer impressions) { this.impressions = impressions; }

    public Integer getClicks() { return clicks; }
    public void setClicks(Integer clicks) { this.clicks = clicks; }

    public String getCtr() { return ctr; }
    public void setCtr(String ctr) { this.ctr = ctr; }

    public Double getCpc() { return cpc; }
    public void setCpc(Double cpc) { this.cpc = cpc; }

    public Double getSpend() { return spend; }
    public void setSpend(Double spend) { this.spend = spend; }

    public Double getSales7d() { return sales7d; }
    public void setSales7d(Double sales7d) { this.sales7d = sales7d; }

    public String getAcos() { return acos; }
    public void setAcos(String acos) { this.acos = acos; }

    public String getRoas() { return roas; }
    public void setRoas(String roas) { this.roas = roas; }

    public Integer getOrders7d() { return orders7d; }
    public void setOrders7d(Integer orders7d) { this.orders7d = orders7d; }

    public Integer getUnits7d() { return units7d; }
    public void setUnits7d(Integer units7d) { this.units7d = units7d; }

    public String getConversionRate7d() { return conversionRate7d; }
    public void setConversionRate7d(String conversionRate7d) { this.conversionRate7d = conversionRate7d; }

    public Integer getAdSkuUnits7d() { return adSkuUnits7d; }
    public void setAdSkuUnits7d(Integer adSkuUnits7d) { this.adSkuUnits7d = adSkuUnits7d; }

    public Integer getOtherSkuUnits7d() { return otherSkuUnits7d; }
    public void setOtherSkuUnits7d(Integer otherSkuUnits7d) { this.otherSkuUnits7d = otherSkuUnits7d; }

    public Double getAdSkuSales7d() { return adSkuSales7d; }
    public void setAdSkuSales7d(Double adSkuSales7d) { this.adSkuSales7d = adSkuSales7d; }

    public Double getOtherSkuSales7d() { return otherSkuSales7d; }
    public void setOtherSkuSales7d(Double otherSkuSales7d) { this.otherSkuSales7d = otherSkuSales7d; }

    public Date getGmtCreate() { return gmtCreate; }
    public void setGmtCreate(Date gmtCreate) { this.gmtCreate = gmtCreate; }

    public Date getGmtModify() { return gmtModify; }
    public void setGmtModify(Date gmtModify) { this.gmtModify = gmtModify; }
}
