/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.payment.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOFT
 */
@Entity
@Table(name = "t_trace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTrace.findAll", query = "SELECT t FROM TTrace t"),
    @NamedQuery(name = "TTrace.findByLgTraceId", query = "SELECT t FROM TTrace t WHERE t.lgTraceId = :lgTraceId"),
    @NamedQuery(name = "TTrace.findByStrPhoneNumber", query = "SELECT t FROM TTrace t WHERE t.strPhoneNumber = :strPhoneNumber"),
    @NamedQuery(name = "TTrace.findByBCallbackReceived", query = "SELECT t FROM TTrace t WHERE t.bCallbackReceived = :bCallbackReceived"),
    @NamedQuery(name = "TTrace.findByStrCallbackUrl", query = "SELECT t FROM TTrace t WHERE t.strCallbackUrl = :strCallbackUrl"),
    @NamedQuery(name = "TTrace.findByDtDateCreated", query = "SELECT t FROM TTrace t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TTrace.findByStrOriginatingTransaction", query = "SELECT t FROM TTrace t WHERE t.strOriginatingTransaction = :strOriginatingTransaction"),
    @NamedQuery(name = "TTrace.findByDbAmount", query = "SELECT t FROM TTrace t WHERE t.dbAmount = :dbAmount")})
public class TTrace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_trace_id")
    private String lgTraceId;
    @Column(name = "str_phone_number")
    private String strPhoneNumber;
    @Column(name = "b_callback_received")
    private Boolean bCallbackReceived;
    @Column(name = "str_callback_url")
    private String strCallbackUrl;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Column(name = "str_originating_transaction")
    private String strOriginatingTransaction;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_amount")
    private Double dbAmount;
    @JoinColumn(name = "lg_payment_providers", referencedColumnName = "lg_payment_providers")
    @ManyToOne
    private TPaymentProviders lgPaymentProviders;

    public TTrace() {
    }

    public TTrace(String lgTraceId) {
        this.lgTraceId = lgTraceId;
    }

    public String getLgTraceId() {
        return lgTraceId;
    }

    public void setLgTraceId(String lgTraceId) {
        this.lgTraceId = lgTraceId;
    }

    public String getStrPhoneNumber() {
        return strPhoneNumber;
    }

    public void setStrPhoneNumber(String strPhoneNumber) {
        this.strPhoneNumber = strPhoneNumber;
    }

    public Boolean getBCallbackReceived() {
        return bCallbackReceived;
    }

    public void setBCallbackReceived(Boolean bCallbackReceived) {
        this.bCallbackReceived = bCallbackReceived;
    }

    public String getStrCallbackUrl() {
        return strCallbackUrl;
    }

    public void setStrCallbackUrl(String strCallbackUrl) {
        this.strCallbackUrl = strCallbackUrl;
    }

    public Date getDtDateCreated() {
        return dtDateCreated;
    }

    public void setDtDateCreated(Date dtDateCreated) {
        this.dtDateCreated = dtDateCreated;
    }

    public String getStrOriginatingTransaction() {
        return strOriginatingTransaction;
    }

    public void setStrOriginatingTransaction(String strOriginatingTransaction) {
        this.strOriginatingTransaction = strOriginatingTransaction;
    }

    public Double getDbAmount() {
        return dbAmount;
    }

    public void setDbAmount(Double dbAmount) {
        this.dbAmount = dbAmount;
    }

    public TPaymentProviders getLgPaymentProviders() {
        return lgPaymentProviders;
    }

    public void setLgPaymentProviders(TPaymentProviders lgPaymentProviders) {
        this.lgPaymentProviders = lgPaymentProviders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgTraceId != null ? lgTraceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTrace)) {
            return false;
        }
        TTrace other = (TTrace) object;
        if ((this.lgTraceId == null && other.lgTraceId != null) || (this.lgTraceId != null && !this.lgTraceId.equals(other.lgTraceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TTrace[ lgTraceId=" + lgTraceId + " ]";
    }
    
}
