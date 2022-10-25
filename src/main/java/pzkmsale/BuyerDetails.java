package pzkmsale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "BuyerDetails")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BuyerDetails", propOrder = {
        "taxpayerId",
        "subjectBriefName",
        "unifiedCountryCode"
})
public class BuyerDetails {
    @XmlElement(name = "TaxpayerId", required = true)
    protected String taxpayerId;
    @XmlElement(name = "SubjectBriefName", required = true)
    protected String subjectBriefName;
    @XmlElement(name = "UnifiedCountryCode", required = true)
    protected String unifiedCountryCode;

    public BuyerDetails(String taxpayerId, String subjectBriefName, String unifiedCountryCode) {
        this.taxpayerId = taxpayerId;
        this.subjectBriefName = subjectBriefName;
        this.unifiedCountryCode = unifiedCountryCode;
    }

    public BuyerDetails() {
    }

    public String getTaxpayerId() {
        return this.taxpayerId;
    }

    public String getSubjectBriefName() {
        return this.subjectBriefName;
    }

    public String getUnifiedCountryCode() {
        return this.unifiedCountryCode;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public void setSubjectBriefName(String subjectBriefName) {
        this.subjectBriefName = subjectBriefName;
    }

    public void setUnifiedCountryCode(String unifiedCountryCode) {
        this.unifiedCountryCode = unifiedCountryCode;
    }

}
