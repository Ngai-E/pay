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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "t_country_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCountryPayment.findAll", query = "SELECT t FROM TCountryPayment t"),
    @NamedQuery(name = "TCountryPayment.findByLgCountryPaymentId", query = "SELECT t FROM TCountryPayment t WHERE t.lgCountryPaymentId = :lgCountryPaymentId"),
    @NamedQuery(name = "TCountryPayment.findByLgCountryId", query = "SELECT t FROM TCountryPayment t WHERE t.lgCountryId = :lgCountryId"),
    @NamedQuery(name = "TCountryPayment.findByLgPaymentMethodId", query = "SELECT t FROM TCountryPayment t WHERE t.lgPaymentMethodId = :lgPaymentMethodId"),
    @NamedQuery(name = "TCountryPayment.findByDtDateCreated", query = "SELECT t FROM TCountryPayment t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TCountryPayment.findByDtDateUpdated", query = "SELECT t FROM TCountryPayment t WHERE t.dtDateUpdated = :dtDateUpdated")})
public class TCountryPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lg_country_payment_id")
    private Long lgCountryPaymentId;
    @Column(name = "lg_country_id")
    private Integer lgCountryId;
    @Column(name = "lg_payment_method_id")
    private Integer lgPaymentMethodId;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Column(name = "dt_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateUpdated;

    public TCountryPayment() {
    }

    public TCountryPayment(Long lgCountryPaymentId) {
        this.lgCountryPaymentId = lgCountryPaymentId;
    }

    public Long getLgCountryPaymentId() {
        return lgCountryPaymentId;
    }

    public void setLgCountryPaymentId(Long lgCountryPaymentId) {
        this.lgCountryPaymentId = lgCountryPaymentId;
    }

    public Integer getLgCountryId() {
        return lgCountryId;
    }

    public void setLgCountryId(Integer lgCountryId) {
        this.lgCountryId = lgCountryId;
    }

    public Integer getLgPaymentMethodId() {
        return lgPaymentMethodId;
    }

    public void setLgPaymentMethodId(Integer lgPaymentMethodId) {
        this.lgPaymentMethodId = lgPaymentMethodId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgCountryPaymentId != null ? lgCountryPaymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCountryPayment)) {
            return false;
        }
        TCountryPayment other = (TCountryPayment) object;
        if ((this.lgCountryPaymentId == null && other.lgCountryPaymentId != null) || (this.lgCountryPaymentId != null && !this.lgCountryPaymentId.equals(other.lgCountryPaymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TCountryPayment[ lgCountryPaymentId=" + lgCountryPaymentId + " ]";
    }
    
}
