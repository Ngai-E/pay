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
@Table(name = "t_received_callback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TReceivedCallback.findAll", query = "SELECT t FROM TReceivedCallback t"),
    @NamedQuery(name = "TReceivedCallback.findByLgtraceId", query = "SELECT t FROM TReceivedCallback t WHERE t.lgtraceId = :lgtraceId"),
    @NamedQuery(name = "TReceivedCallback.findByStrStatus", query = "SELECT t FROM TReceivedCallback t WHERE t.strStatus = :strStatus"),
    @NamedQuery(name = "TReceivedCallback.findByDtDateCreated", query = "SELECT t FROM TReceivedCallback t WHERE t.dtDateCreated = :dtDateCreated")})
public class TReceivedCallback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_trace_id")
    private String lgtraceId;
    @Column(name = "str_status")
    private String strStatus;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;

    public TReceivedCallback() {
    }

    public TReceivedCallback(String lgtraceId) {
        this.lgtraceId = lgtraceId;
    }

    public String getLgtraceId() {
        return lgtraceId;
    }

    public void setLgtraceId(String lgtraceId) {
        this.lgtraceId = lgtraceId;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public Date getDtDateCreated() {
        return dtDateCreated;
    }

    public void setDtDateCreated(Date dtDateCreated) {
        this.dtDateCreated = dtDateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgtraceId != null ? lgtraceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TReceivedCallback)) {
            return false;
        }
        TReceivedCallback other = (TReceivedCallback) object;
        if ((this.lgtraceId == null && other.lgtraceId != null) || (this.lgtraceId != null && !this.lgtraceId.equals(other.lgtraceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TReceivedCallback[ lgtraceId=" + lgtraceId + " ]";
    }
    
}
