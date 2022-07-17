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
@Table(name = "t_country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCountry.findAll", query = "SELECT t FROM TCountry t"),
    @NamedQuery(name = "TCountry.findByStrCountryCode", query = "SELECT t FROM TCountry t WHERE t.strCountryCode = :strCountryCode"),
    @NamedQuery(name = "TCountry.findByStrCountryName", query = "SELECT t FROM TCountry t WHERE t.strCountryName = :strCountryName"),
    @NamedQuery(name = "TCountry.findByStrDialingCode", query = "SELECT t FROM TCountry t WHERE t.strDialingCode = :strDialingCode"),
    @NamedQuery(name = "TCountry.findByDtDateCreated", query = "SELECT t FROM TCountry t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TCountry.findByDtDateUpdated", query = "SELECT t FROM TCountry t WHERE t.dtDateUpdated = :dtDateUpdated")})
public class TCountry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "str_country_code")
    private String strCountryCode;
    @Column(name = "str_country_name")
    private String strCountryName;
    @Column(name = "str_dialing_code")
    private String strDialingCode;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Column(name = "dt_date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateUpdated;

    public TCountry() {
    }

    public TCountry(String strCountryCode) {
        this.strCountryCode = strCountryCode;
    }

    public String getStrCountryCode() {
        return strCountryCode;
    }

    public void setStrCountryCode(String strCountryCode) {
        this.strCountryCode = strCountryCode;
    }

    public String getStrCountryName() {
        return strCountryName;
    }

    public void setStrCountryName(String strCountryName) {
        this.strCountryName = strCountryName;
    }

    public String getStrDialingCode() {
        return strDialingCode;
    }

    public void setStrDialingCode(String strDialingCode) {
        this.strDialingCode = strDialingCode;
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
        hash += (strCountryCode != null ? strCountryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCountry)) {
            return false;
        }
        TCountry other = (TCountry) object;
        if ((this.strCountryCode == null && other.strCountryCode != null) || (this.strCountryCode != null && !this.strCountryCode.equals(other.strCountryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.payment.model.entity.TCountry[ strCountryCode=" + strCountryCode + " ]";
    }
    
}
