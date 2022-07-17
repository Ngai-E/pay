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
@Table(name = "t_trace_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTraceStatus.findAll", query = "SELECT t FROM TTraceStatus t"),
    @NamedQuery(name = "TTraceStatus.findByLgTraceStatus", query = "SELECT t FROM TTraceStatus t WHERE t.lgTraceStatus = :lgTraceStatus"),
    @NamedQuery(name = "TTraceStatus.findByLgTraceId", query = "SELECT t FROM TTraceStatus t WHERE t.lgTraceId = :lgTraceId"),
    @NamedQuery(name = "TTraceStatus.findByDtDateCreated", query = "SELECT t FROM TTraceStatus t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TTraceStatus.findByStrStatus", query = "SELECT t FROM TTraceStatus t WHERE t.strStatus = :strStatus"),
    @NamedQuery(name = "TTraceStatus.findByStrExternalTransaction", query = "SELECT t FROM TTraceStatus t WHERE t.strExternalTransaction = :strExternalTransaction"),
    @NamedQuery(name = "TTraceStatus.findByStrMsg", query = "SELECT t FROM TTraceStatus t WHERE t.strMsg = :strMsg"),
    @NamedQuery(name = "TTraceStatus.findByStrExtCode", query = "SELECT t FROM TTraceStatus t WHERE t.strExtCode = :strExtCode")})
public class TTraceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_trace_status")
    private String lgTraceStatus;
    @Column(name = "lg_trace_id")
    private String lgTraceId;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Column(name = "str_status")
    private String strStatus;
    @Column(name = "str_external_transaction")
    private String strExternalTransaction;
    @Column(name = "str_msg")
    private String strMsg;
    @Column(name = "str_ext_code")
    private String strExtCode;

    public TTraceStatus() {
    }

    public TTraceStatus(String lgTraceStatus) {
        this.lgTraceStatus = lgTraceStatus;
    }

    public String getLgTraceStatus() {
        return lgTraceStatus;
    }

    public void setLgTraceStatus(String lgTraceStatus) {
        this.lgTraceStatus = lgTraceStatus;
    }

    public String getLgTraceId() {
        return lgTraceId;
    }

    public void setLgTraceId(String lgTraceId) {
        this.lgTraceId = lgTraceId;
    }

    public Date getDtDateCreated() {
        return dtDateCreated;
    }

    public void setDtDateCreated(Date dtDateCreated) {
        this.dtDateCreated = dtDateCreated;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrExternalTransaction() {
        return strExternalTransaction;
    }

    public void setStrExternalTransaction(String strExternalTransaction) {
        this.strExternalTransaction = strExternalTransaction;
    }

    public String getStrMsg() {
        return strMsg;
    }

    public void setStrMsg(String strMsg) {
        this.strMsg = strMsg;
    }

    public String getStrExtCode() {
        return strExtCode;
    }

    public void setStrExtCode(String strExtCode) {
        this.strExtCode = strExtCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgTraceStatus != null ? lgTraceStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTraceStatus)) {
            return false;
        }
        TTraceStatus other = (TTraceStatus) object;
        if ((this.lgTraceStatus == null && other.lgTraceStatus != null) || (this.lgTraceStatus != null && !this.lgTraceStatus.equals(other.lgTraceStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TTraceStatus[ lgTraceStatus=" + lgTraceStatus + " ]";
    }
    
}
