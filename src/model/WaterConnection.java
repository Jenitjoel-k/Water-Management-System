package model;


import java.sql.Date;

public class WaterConnection {

    private int connectionId;
    private int customerId;
    private String connectionType;
    private String meterNumber;
    private Date installationDate;
    private String status;

    public WaterConnection() {
    }

    public WaterConnection(int connectionId, int customerId,
                           String connectionType,
                           String meterNumber,
                           Date installationDate,
                           String status) {

        this.connectionId = connectionId;
        this.customerId = customerId;
        this.connectionType = connectionType;
        this.meterNumber = meterNumber;
        this.installationDate = installationDate;
        this.status = status;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}