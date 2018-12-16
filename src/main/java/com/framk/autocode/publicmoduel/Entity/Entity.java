package com.framk.autocode.publicmoduel.Entity;

public class Entity {
    private String javaType;
    private String column_name;
    private String mybatisColumnType;
    private String typePackage;
    private String javaName;
    private boolean ifKey;
    private String dbType;
    private String dbName;
    private String getName;
    private String setName;

    private String serviceName;
    private String serviceImplName;
    private String controllerName;

    public String getMybatisColumnType() {
        return mybatisColumnType;
    }

    public void setMybatisColumnType(String mybatisColumnType) {
        this.mybatisColumnType = mybatisColumnType;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getTypePackage() {
        return typePackage;
    }

    public void setTypePackage(String typePackage) {
        this.typePackage = typePackage;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public boolean isIfKey() {
        return ifKey;
    }

    public void setIfKey(boolean ifKey) {
        this.ifKey = ifKey;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
}
