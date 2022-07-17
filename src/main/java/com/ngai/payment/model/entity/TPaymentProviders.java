/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.payment.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SOFT
 */
@Entity
@Table(name = "t_payment_providers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPaymentProviders.findAll", query = "SELECT t FROM TPaymentProviders t"),
    @NamedQuery(name = "TPaymentProviders.findByLgPaymentProviders", query = "SELECT t FROM TPaymentProviders t WHERE t.lgPaymentProviders = :lgPaymentProviders"),
    @NamedQuery(name = "TPaymentProviders.findByStrPaymentCode", query = "SELECT t FROM TPaymentProviders t WHERE t.strPaymentCode = :strPaymentCode"),
    @NamedQuery(name = "TPaymentProviders.findByDbMinWithdrawalAmount", query = "SELECT t FROM TPaymentProviders t WHERE t.dbMinWithdrawalAmount = :dbMinWithdrawalAmount"),
    @NamedQuery(name = "TPaymentProviders.findByDbMaxWithdrawalAmount", query = "SELECT t FROM TPaymentProviders t WHERE t.dbMaxWithdrawalAmount = :dbMaxWithdrawalAmount"),
    @NamedQuery(name = "TPaymentProviders.findByDbMinDepositAmount", query = "SELECT t FROM TPaymentProviders t WHERE t.dbMinDepositAmount = :dbMinDepositAmount"),
    @NamedQuery(name = "TPaymentProviders.findByDbMaxDepositAmount", query = "SELECT t FROM TPaymentProviders t WHERE t.dbMaxDepositAmount = :dbMaxDepositAmount"),
    @NamedQuery(name = "TPaymentProviders.findByStrImageUrl", query = "SELECT t FROM TPaymentProviders t WHERE t.strImageUrl = :strImageUrl"),
    @NamedQuery(name = "TPaymentProviders.findByStrPaymentType", query = "SELECT t FROM TPaymentProviders t WHERE t.strPaymentType = :strPaymentType"),
    @NamedQuery(name = "TPaymentProviders.findByDtDateCreated", query = "SELECT t FROM TPaymentProviders t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TPaymentProviders.findByDtDateUpdated", query = "SELECT t FROM TPaymentProviders t WHERE t.dtDateUpdated = :dtDateUpdated"),
    @NamedQuery(name = "TPaymentProviders.findByStrDriverClassName", query = "SELECT t FROM TPaymentProviders t WHERE t.strDriverClassName = :strDriverClassName"),
    @NamedQuery(name = "TPaymentProviders.findByBCashin", query = "SELECT t FROM TPaymentProviders t WHERE t.bCashin = :bCashin"),
    @NamedQuery(name = "TPaymentProviders.findByBCashout", query = "SELECT t FROM TPaymentProviders t WHERE t.bCashout = :bCashout"),
    @NamedQuery(name = "TPaymentProviders.findByBActive", query = "SELECT t FROM TPaymentProviders t WHERE t.bActive = :bActive")})
public class TPaymentProviders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lg_payment_providers")
    private Short lgPaymentProviders;
    @Basic(optional = false)
    @Column(name = "str_payment_code")
    private String strPaymentCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_min_withdrawal_amount")
    private Double dbMinWithdrawalAmount;
    @Column(name = "db_max_withdrawal_amount")
    private Double dbMaxWithdrawalAmount;
    @Column(name = "db_min_deposit_amount")
    private Double dbMinDepositAmount;
    @Column(name = "db_max_deposit_amount")
    private Double dbMaxDepositAmount;
    @Column(name = "str_image_url")
    private Double strImageUrl;
    @Column(name = "str_payment_type")
    private String strPaymentType;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Column(name = "dt_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateUpdated;
    @Column(name = "str_driver_class_name")
    private String strDriverClassName;
    @Column(name = "b_cashin")
    private Boolean bCashin;
    @Column(name = "b_cashout")
    private Boolean bCashout;
    @Column(name = "b_active")
    private Boolean bActive;
    @OneToMany(mappedBy = "lgPaymentProviders")
    private List<TTrace> tTraceList;

    public TPaymentProviders() {
    }

    public TPaymentProviders(Short lgPaymentProviders) {
        this.lgPaymentProviders = lgPaymentProviders;
    }

    public TPaymentProviders(Short lgPaymentProviders, String strPaymentCode) {
        this.lgPaymentProviders = lgPaymentProviders;
        this.strPaymentCode = strPaymentCode;
    }

    public Short getLgPaymentProviders() {
        return lgPaymentProviders;
    }

    public void setLgPaymentProviders(Short lgPaymentProviders) {
        this.lgPaymentProviders = lgPaymentProviders;
    }

    public String getStrPaymentCode() {
        return strPaymentCode;
    }

    public void setStrPaymentCode(String strPaymentCode) {
        this.strPaymentCode = strPaymentCode;
    }

    public Double getDbMinWithdrawalAmount() {
        return dbMinWithdrawalAmount;
    }

    public void setDbMinWithdrawalAmount(Double dbMinWithdrawalAmount) {
        this.dbMinWithdrawalAmount = dbMinWithdrawalAmount;
    }

    public Double getDbMaxWithdrawalAmount() {
        return dbMaxWithdrawalAmount;
    }

    public void setDbMaxWithdrawalAmount(Double dbMaxWithdrawalAmount) {
        this.dbMaxWithdrawalAmount = dbMaxWithdrawalAmount;
    }

    public Double getDbMinDepositAmount() {
        return dbMinDepositAmount;
    }

    public void setDbMinDepositAmount(Double dbMinDepositAmount) {
        this.dbMinDepositAmount = dbMinDepositAmount;
    }

    public Double getDbMaxDepositAmount() {
        return dbMaxDepositAmount;
    }

    public void setDbMaxDepositAmount(Double dbMaxDepositAmount) {
        this.dbMaxDepositAmount = dbMaxDepositAmount;
    }

    public Double getStrImageUrl() {
        return strImageUrl;
    }

    public void setStrImageUrl(Double strImageUrl) {
        this.strImageUrl = strImageUrl;
    }

    public String getStrPaymentType() {
        return strPaymentType;
    }

    public void setStrPaymentType(String strPaymentType) {
        this.strPaymentType = strPaymentType;
    }

    public Date getDtDateCreated() {
        return dtDateCreated;
    }

    public void setDtDateCreated(Date dtDateCreated) {
        this.dtDateCreated = dtDateCreated;
    }

    public Date getDtDateUpdated() {
        return dtDateUpdated;
    }

    public void setDtDateUpdated(Date dtDateUpdated) {
        this.dtDateUpdated = dtDateUpdated;
    }

    public String getStrDriverClassName() {
        return strDriverClassName;
    }

    public void setStrDriverClassName(String strDriverClassName) {
        this.strDriverClassName = strDriverClassName;
    }

    public Boolean getBCashin() {
        return bCashin;
    }

    public void setBCashin(Boolean bCashin) {
        this.bCashin = bCashin;
    }

    public Boolean getBCashout() {
        return bCashout;
    }

    public void setBCashout(Boolean bCashout) {
        this.bCashout = bCashout;
    }

    public Boolean getBActive() {
        return bActive;
    }

    public void setBActive(Boolean bActive) {
        this.bActive = bActive;
    }

    @XmlTransient
    public List<TTrace> getTTraceList() {
        return tTraceList;
    }

    public void setTTraceList(List<TTrace> tTraceList) {
        this.tTraceList = tTraceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgPaymentProviders != null ? lgPaymentProviders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPaymentProviders)) {
            return false;
        }
        TPaymentProviders other = (TPaymentProviders) object;
        if ((this.lgPaymentProviders == null && other.lgPaymentProviders != null) || (this.lgPaymentProviders != null && !this.lgPaymentProviders.equals(other.lgPaymentProviders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TPaymentProviders[ lgPaymentProviders=" + lgPaymentProviders + " ]";
    }
    
}
